package com.ventalandia.service;

import java.util.List;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.inject.Inject;
import com.ventalandia.framework.http.HttpConnector;
import com.ventalandia.framework.http.HttpResponse;
import com.ventalandia.meli.api.auth.AuthToken;
import com.ventalandia.meli.api.notification.Notification;
import com.ventalandia.meli.api.notification.Question;
import com.ventalandia.meli.pesistence.NotificationRepository;
import com.ventalandia.meli.pesistence.QuestionRepository;

/**
 * 
 * @author german
 * @author matias
 * 
 */
public class NotificationServiceImpl implements NotificationService {

	private static final Logger log = Logger.getLogger(NotificationServiceImpl.class.getName());

	@Inject
	private Gson gson;
	
	@Inject
	private NotificationRepository notificationRepository;
	
	@Inject
	private QuestionRepository questionRepository;

	@Inject
	private HttpConnector httpConnector;

	@Override
	public void processRequest(String jsonData) throws JsonSyntaxException {
		log.fine("processing json data");
		Notification notification = gson.fromJson(jsonData, Notification.class);
		this.notificationRepository.add(notification);
	}

	@Override
	public Question getQuestionFromMeli(Notification notification, AuthToken authToken) {

//		TODO REEMPLAZAR POR ESTA LA LINEA PARA QUE UTILICE EL TOKEN 
//		HttpResponse json = httpConnector.get(notification.getResource(), new FluentStringsMap().add(ACCESS_TOKEN, authToken.getAccess_token()));
		HttpResponse json = httpConnector.get(notification.getResource());
		
		if(json.getResponseCode() == 200){
			notification.markAsRead();
			notificationRepository.update(notification);
			Question question = gson.fromJson(json.getResponseMessage(), Question.class);
			questionRepository.add(question);
			return question;
		}
		
		throw new RuntimeException("Question Not Found");

	}

	
	@Override
	public List<Notification> getUnreadQuestionsByUserId(long userId) {
		return notificationRepository.getUnreadQuestionsByUserId(userId);

	}

}
