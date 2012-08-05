package com.ventalandia.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.inject.Singleton;

/**
 * 
 * @author matias
 *
 */
@Singleton
public class AuthServlet extends HttpServlet {

	private static final long serialVersionUID = 6791535685445969788L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/plain");
		String error = req.getParameter("error");
		if (error != null) {			
			resp.getWriter().println("There was an issue when you try to login: ");
			resp.getWriter().println(req.getParameter("error_description"));
		} else if (req.getParameter("code") != null) {
			resp.getWriter().println("you are logged in and your code is: " + req.getParameter("code"));
			// TODO replace this, hit MELI Api to complete the flow (it needs to validate the code token)
		}
	}
	
}
