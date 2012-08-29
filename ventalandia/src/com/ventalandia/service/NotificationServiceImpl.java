package com.ventalandia.service;

import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.inject.Inject;
import com.ventalandia.dao.NotificationDAO;
import com.ventalandia.meli.domain.Notification;

public class NotificationServiceImpl implements NotificationService {
	
	private Gson gson;
	private NotificationDAO notificationDAO;
	private static final Logger log = Logger.getLogger(NotificationServiceImpl.class.getName());
	
	@Inject
	public NotificationServiceImpl(Gson gson, NotificationDAO notificationDAO) {
		this.gson = gson;
		this.notificationDAO = notificationDAO;
	}

	@Override
	public void processRequest(String jsonData) throws JsonSyntaxException {

		log.fine("processing json data");
		
		
		Notification notification = gson.fromJson(jsonData, Notification.class);
		notificationDAO.persist(notification);
	}

}
