package com.ventalandia.view.api;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.ventalandia.service.NotificationService;

/**
 * 
 * @author matias
 * @author german
 * 
 */
@Singleton
public class NotificationApiServlet extends ApiServlet {

	private static final long serialVersionUID = -7582121992545851199L;
	private static final String EMPTY_STRING = "";
	private static final Logger log = Logger.getLogger(NotificationApiServlet.class.getName());
	
	@Inject
	private Gson gson;

	@Inject
	private NotificationService notificationService;

	protected Object process(HttpServletRequest req, HttpServletResponse resp) {

		log.fine("running Notification Api Servlet...");
		
		try {

			String requestBody = getRequestBody(req);
			notificationService.processRequest(requestBody);

		} catch (Exception e) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			log.severe(e.getMessage());
			return null;
		}
		resp.setStatus(HttpServletResponse.SC_OK);

		return EMPTY_STRING;
	}

	@Override
	protected Object post(HttpServletRequest req, HttpServletResponse resp) {
		return this.process(req, resp);
	}

}
