package com.ventalandia.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.inject.Inject;
import com.ventalandia.meli.api.auth.AuthToken;
import com.ventalandia.meli.api.notification.Notification;
import com.ventalandia.meli.api.notification.Question;
import com.ventalandia.meli.ioc.MeliUrlApi;
import com.ventalandia.meli.pesistence.NotificationRepository;

/**
 * 
 * @author german
 * @author matias
 * 
 */
public class NotificationServiceImpl implements NotificationService {

	@Inject
	private Gson gson;

	private static final Logger log = Logger.getLogger(NotificationServiceImpl.class.getName());

	@Inject
	private NotificationRepository notificationRepository;

	@Inject @MeliUrlApi
	private String meliUrl;

	@Override
	public void processRequest(String jsonData) throws JsonSyntaxException {
		log.fine("processing json data");
		Notification notification = gson.fromJson(jsonData, Notification.class);
		this.notificationRepository.add(notification);
	}

	@Override
	public Question getQuestionFromMeli(Notification notification, AuthToken authToken) {
		
//		String json = readingFormURL(notification.getResource(),authToken.getAccess_token());
		String json = readingFormURL(notification.getResource(),null);
		return gson.fromJson(json, Question.class);
	}

	@Override
	public List<Notification> getAllQuestions() {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("topic", "questions");
		return this.notificationRepository.search(params);

	}

	private String readingFormURL(String resource, String access_token) {

		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader in = null;
		try {
//			URL url = new URL(meliUrl +  resource + "?access_token=" + access_token);
			URL url = new URL(meliUrl +  resource );
			in = new BufferedReader(new InputStreamReader(url.openStream()));

			for (String inputLine = in.readLine(); inputLine != null; inputLine = in.readLine()) {
				stringBuilder.append(inputLine);
			}

		} catch (Exception e) {

		} finally {
			if(in !=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return stringBuilder.toString();
	}

}
