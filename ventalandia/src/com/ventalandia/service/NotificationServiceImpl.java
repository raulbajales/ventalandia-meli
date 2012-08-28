package com.ventalandia.service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.inject.Inject;
import com.ventalandia.dao.NotificationDAO;
import com.ventalandia.meli.domain.Notification;

public class NotificationServiceImpl implements NotificationService {
	
	private Gson gson;
	private NotificationDAO notificationDAO;
	
	@Inject
	public NotificationServiceImpl(Gson gson, NotificationDAO notificationDAO) {
		this.gson = gson;
		this.notificationDAO = notificationDAO;
	}

	@Override
	public void processRequest(String jsonData) throws JsonSyntaxException {

		Notification notification = gson.fromJson(jsonData, Notification.class);
		notificationDAO.persist(notification);
	}

}
