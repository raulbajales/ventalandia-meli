package com.ventalandia.view.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.ventalandia.meli.domain.AuthToken;

/**
 * 
 * @author matias
 *
 */
@Singleton
public class ApiServlet extends HttpServlet {

	private static final long serialVersionUID = -2325761933018470019L;

	private static final String CONTENT_TYPE = "text/plain";
	
	@Inject
	private Gson gson;

	@Override
	protected final void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Object answer = this.get(req, resp);
		
		if (answer == null) {
			return;
		}
		
		resp.setContentType(CONTENT_TYPE);
		resp.getWriter().print(this.gson.toJson(answer));
	}
	
	@Override
	protected final void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Object answer = this.post(req, resp);
		
		if (answer == null) {
			return;
		}
		
		resp.setContentType(CONTENT_TYPE);
		resp.getWriter().print(this.gson.toJson(answer));
	}

	protected Object get(HttpServletRequest req, HttpServletResponse resp) {
		ApiError error = new ApiError();
		
		error.setMessage("GET message is not implemented.");
		resp.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
		
		return error;
	}

	protected Object post(HttpServletRequest req, HttpServletResponse resp) {
		ApiError error = new ApiError();
		
		error.setMessage("POST message is not implemented.");
		resp.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
		
		return error;
	}
	
}
