package com.ventalandia.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.ventalandia.meli.ioc.MeliCallbackUrlApi;
import com.ventalandia.meli.ioc.MeliClientIdApi;

/**
 * 
 * @author matias
 *
 */
@Singleton
public class MeliRedirectorServlet extends HttpServlet {

	private static final long serialVersionUID = -4820215676967640863L;

	@Inject @MeliClientIdApi
	private Integer clientId;

	@Inject @MeliCallbackUrlApi
	private String callback;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.sendRedirect("https://auth.mercadolibre.com.ar/authorization?response_type=code&client_id=" + clientId + "&redirect_uri=" + callback);
	}

}
