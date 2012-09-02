package com.ventalandia.view;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.ventalandia.meli.api.auth.AuthToken;
import com.ventalandia.meli.service.MeliService;
import com.ventalandia.view.api.ApiError;
import com.ventalandia.view.api.ApiServlet;

/**
 * 
 * @author matias
 *
 */
@Singleton
public class AuthServlet extends ApiServlet {

	private static final long serialVersionUID = 6791535685445969788L;

	private static final String TOKEN = "vtd_token";
	private static final Object EMPTY_STRING = "";
	
	@Inject
	private MeliService meliService;
	
	@Inject
	private Gson gson;

	@Inject @HomePage
	private String homePage;

	@Override
	protected Object get(HttpServletRequest req, HttpServletResponse resp) {
		return post(req, resp);
	}
	
	@Override
	protected Object post(HttpServletRequest req, HttpServletResponse resp) {
		String error = req.getParameter("error");
		if (error != null) {
			return new ApiError("There was an issue when you try to login: " + req.getParameter("error_description"));
		} else if (req.getParameter("code") != null) {
			AuthToken authToken = this.meliService.getAuthToken(req.getParameter("code"));
			Cookie cookie = new Cookie(TOKEN, this.parseToken(authToken));
			cookie.setMaxAge(authToken.getExpires_in().intValue());
			cookie.setPath("/");
			resp.addCookie(cookie);
			try {
				resp.sendRedirect(homePage);
			} catch (IOException e) {
				resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				return new ApiError(e.getMessage());
			}
		}
		return EMPTY_STRING;
	}

	private String parseToken(AuthToken authToken) {
		String json = this.gson.toJson(authToken);
		try {
			return URLEncoder.encode(json, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
}
