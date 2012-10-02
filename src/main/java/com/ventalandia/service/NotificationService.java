package com.ventalandia.service;

import java.util.List;

import com.google.gson.JsonSyntaxException;
import com.ventalandia.meli.api.notification.Notification;
import com.ventalandia.meli.api.notification.Question;

/**
 * 
 * @author german
 *
 */
public interface NotificationService {

	/**
	 * Process Request to persist Notifications
	 * @param jsonData
	 * @throws JsonSyntaxException
	 */
	void processRequest(String jsonData) throws JsonSyntaxException;

	/**
	 * Gets Unread Notifications of type Question by given user.
	 * @param userId
	 * @return
	 */
	@Deprecated
	List<Notification> getUnreadQuestionsByUserId(long userId);

	/**
	 * Gets Question From MELI.
	 * @param notifications
	 * @param authToken
	 * @return
	 */
	List<Question> getQuestionsFromMeli(List<Notification> notifications);

	/**
	 * Gets Questions from MELI.
	 * @param userId
	 * @return
	 */
	List<Question> getQuestionsFromMeli(long userId);

	
	/**
	 * Gets all unread Questions
	 * @return
	 */
	List<Notification> getUnreadQuestions();
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	List<Notification> getUnreadQuestionsBySellerId(long userId);
	
}
