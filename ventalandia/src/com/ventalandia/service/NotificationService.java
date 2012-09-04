package com.ventalandia.service;

import java.util.List;

import com.google.gson.JsonSyntaxException;
import com.ventalandia.meli.api.auth.AuthToken;
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

	List<Notification> getUnreadQuestionsByUserId(int userId);

	Question getQuestionFromMeli(Notification notification, AuthToken authToken);
	
}
