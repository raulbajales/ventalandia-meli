package com.ventalandia.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Singleton;

/**
 * 
 * @author matias
 *
 */
@Singleton
public class EchoServlet extends HttpServlet {

	private static final long serialVersionUID = 3750062946924357873L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println(this.getClass().getSimpleName() + " : " + req.getPathInfo());
	}

}
