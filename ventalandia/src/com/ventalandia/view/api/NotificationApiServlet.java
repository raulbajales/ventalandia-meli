package com.ventalandia.view.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Singleton;

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
	
	protected Object process(HttpServletRequest req, HttpServletResponse resp) {
		// process notifications FROM MELI
		
		// TODO if the processing was OK set HTTP response to HTTP = 200
		resp.setStatus(HttpServletResponse.SC_OK);
		
		return EMPTY_STRING;		
	}
	
	// TODO validate which HTTP method should accept
	
	@Override
	protected Object post(HttpServletRequest req, HttpServletResponse resp) {
		return this.process(req, resp);
	}
	
	@Override
	protected Object get(HttpServletRequest req, HttpServletResponse resp) {
		return this.process(req, resp);
	}	

}
