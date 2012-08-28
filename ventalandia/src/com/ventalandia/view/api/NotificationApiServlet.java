package com.ventalandia.view.api;

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

	@Inject
	private Gson gson;

	@Inject
	private NotificationService notificationService;

	protected Object process(HttpServletRequest req, HttpServletResponse resp) {

		try {

			String requestBody = getRequestBody(req);
			notificationService.processRequest(requestBody);

		} catch (Exception e) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
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
