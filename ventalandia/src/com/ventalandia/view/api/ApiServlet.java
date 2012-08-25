package com.ventalandia.view.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.ventalandia.domain.meli.AuthToken;

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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Object answer = get(req, resp);
		
		if (answer == null) {
			return;
		}
		
		resp.setContentType(CONTENT_TYPE);
		resp.getWriter().print(this.gson.toJson(answer));
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ApiError error = new ApiError();
		error.setMessage("POST message is not implemented.");
		
		resp.setContentType(CONTENT_TYPE);
		resp.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
		resp.getWriter().print(this.gson.toJson(error));
	}

	protected Object get(HttpServletRequest req, HttpServletResponse resp) {
//		AuthToken authToken = new AuthToken();
//		
//		authToken.setAccess_token("APP_USR-6092-3246532-cb45c82853f6e620bb0deda096b128d3-8035443");
//		authToken.setExpires_in(10800L);
//		authToken.setScope("write read");
//		authToken.setToken_type("bearer");
//		
//		return authToken;
		return null;
	}
	
}
