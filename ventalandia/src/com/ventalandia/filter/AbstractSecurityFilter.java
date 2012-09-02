package com.ventalandia.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.ventalandia.meli.api.auth.AuthToken;
import com.ventalandia.meli.service.MeliAuthContext;
import com.ventalandia.meli.service.MeliService;

/**
 * 
 * @author matias
 * @author german
 * 
 */
public abstract class AbstractSecurityFilter implements Filter {

	@Inject
	private Gson gson;

	@Inject
	private MeliService meliService;

	protected FilterConfig filterConfig;

	@Override
	public void destroy() {
		// do nothing
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	@Override
	public final void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		// casting
		this.doInnerFilter((HttpServletRequest) request,
				(HttpServletResponse) response, filterChain);
	}

	private void doInnerFilter(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		AuthToken authToken = this.getAuthToken(request);

		if (authToken != null) {
			if (this.meliService.validate(authToken)) {
				MeliAuthContext.setAuthToken(authToken);
				this.onValidSession(filterChain, request, response);
				MeliAuthContext.remove();
			} else {
				this.onInvalidSession(response);
			}
		} else {
			this.onInvalidSession(response);
		}
	}

	protected abstract void onInvalidSession(HttpServletResponse response);
	
	protected abstract void onValidSession(FilterChain filterChain, HttpServletRequest request, HttpServletResponse response);

	private AuthToken getAuthToken(HttpServletRequest request) {
		if (request.getCookies() == null) {
			return null;
		}

		for (Cookie cookie : request.getCookies()) {
			if (cookie.getName().equals("vtd_token")) {
				try {
					return this.parseToken(cookie.getValue());
				} catch (Exception e) {
					// do nothing
				}
			}
		}
		return null;
	}

	private AuthToken parseToken(String token) {
		try {
			String json = URLDecoder.decode(token, "UTF-8");
			return this.gson.fromJson(json, AuthToken.class);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
}
