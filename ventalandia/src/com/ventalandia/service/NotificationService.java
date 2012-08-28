package com.ventalandia.service;

import com.google.gson.JsonSyntaxException;

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
	
}
