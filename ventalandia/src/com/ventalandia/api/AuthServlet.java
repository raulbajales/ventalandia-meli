package com.ventalandia.api;

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
import com.ventalandia.view.WebappView;

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
	private WebappView webappView;

	@Inject
	private Gson gson;

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
				webappView.renderHome(resp, getServletContext());
			} catch (Exception e) {
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
