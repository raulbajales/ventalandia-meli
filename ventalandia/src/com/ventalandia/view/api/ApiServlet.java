package com.ventalandia.view.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * 
 * @author matias
 * 
 */
@Singleton
public class ApiServlet extends HttpServlet {

	private static final long serialVersionUID = -2325761933018470019L;
	private static final String CONTENT_TYPE = "text/plain";
	private static final Logger log = Logger.getLogger(ApiServlet.class.getName());

	@Inject
	private Gson gson;

	@Override
	protected final void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		log.fine("running...");
		
		
		Object answer = this.get(req, resp);

		if (answer == null) {
			return;
		}

		resp.setContentType(CONTENT_TYPE);
		resp.getWriter().print(this.gson.toJson(answer));
	}

	@Override
	protected final void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

	/**
	 * Get request body in a post method.
	 * @author german
	 * @param req
	 * @return
	 * @throws IOException
	 */
	protected String getRequestBody(final HttpServletRequest req) throws IOException {

		BufferedReader buff = req.getReader();
		StringBuilder stringBuilder = new StringBuilder();
		
		for (String len = buff.readLine(); len != null; len = buff.readLine()) {
			stringBuilder.append(len);
		}
		return stringBuilder.toString();

	}

}
