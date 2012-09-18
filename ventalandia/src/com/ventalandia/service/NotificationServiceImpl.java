package com.ventalandia.service;

import java.util.ArrayList;
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

	private final Logger logger;

	@Inject
	private Gson gson;

	@Inject
	private NotificationRepository notificationRepository;

	@Inject
	private QuestionRepository questionRepository;

	@Inject
	private HttpConnector httpConnector;

	@Inject
	public NotificationServiceImpl(Logger logger) {
		this.logger = logger;
	}

	@Override
	public void processRequest(String jsonData) throws JsonSyntaxException {
		logger.fine("processing json data");
		Notification notification = gson.fromJson(jsonData, Notification.class);
		this.notificationRepository.add(notification);
	}

	@Override
	public List<Question> getQuestionsFromMeli(List<Notification> notifications) {

		List<Question> questions = new ArrayList<Question>();
		for (Notification notification : notifications) {

			// TODO REEMPLAZAR POR ESTA LA LINEA PARA QUE UTILICE EL TOKEN
			// HttpResponse json = httpConnector.get(notification.getResource(),new FluentStringsMap().add(ACCESS_TOKEN, authToken.getAccess_token()));
			HttpResponse json = httpConnector.get(notification.getResource());

			if (json.getResponseCode() == 200) {
				notification.markAsRead();
				notificationRepository.update(notification);
				Question question = gson.fromJson(json.getResponseMessage(), Question.class);
				question.setUser_id(notification.getUser_id());
				questionRepository.add(question);
				questions.add(question);
			}

			throw new RuntimeException("Question Not Found");

		}
		return questions;

	}

	@Override
	public List<Notification> getUnreadQuestionsByUserId(long userId) {
		logger.fine("getting unread questions");
		return notificationRepository.getUnreadQuestionsByUserId(userId);

	}

	@Override
	public List<Question> getQuestionsFromMeli(long userId) {

		List<Notification> notifications = notificationRepository.getUnreadQuestionsByUserId(userId);
		
		return getQuestionsFromMeli(notifications);
	}

}
