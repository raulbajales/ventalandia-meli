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
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = -4820215676967640863L;
	private static final String clientId = "10601";
	private String welcomePage = "http://development.ventalandia-meli.appspot.com/meli/auth";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.sendRedirect("https://auth.mercadolibre.com.ar/authorization?response_type=code&client_id=" + clientId + "&redirect_uri=" + welcomePage);
	}
	
}
