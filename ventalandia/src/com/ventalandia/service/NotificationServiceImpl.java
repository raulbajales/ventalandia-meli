package com.ventalandia.service;

import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.inject.Inject;
import com.ventalandia.meli.domain.Notification;
import com.ventalandia.meli.domain.persistence.NotificationRepository;

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

	@Override
	public void processRequest(String jsonData) throws JsonSyntaxException {
		log.fine("processing json data");
		Notification notification = gson.fromJson(jsonData, Notification.class);
		this.notificationRepository.add(notification);
	}

}
