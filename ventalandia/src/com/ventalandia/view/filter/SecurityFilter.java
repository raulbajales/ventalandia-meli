package com.ventalandia.view.filter;

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
import com.google.inject.Singleton;
import com.ventalandia.meli.domain.AuthToken;
import com.ventalandia.meli.service.MeliService;
import com.ventalandia.meli.service.MeliAuthContext;

/**
 * 
 * @author matias
 * @author german
 *
 */
@Singleton
public class SecurityFilter implements Filter {
	
	@Inject
	private Gson gson;
	
	@Inject
	private MeliService meliService;

	@Override
	public void destroy() {
		// do nothing 
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// do nothing		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		this.doInnerFilter((HttpServletRequest) request, (HttpServletResponse) response, filterChain);
	}

	private void doInnerFilter(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		AuthToken authToken = this.getAuthToken(request);
		
		if (authToken != null) {
			if(this.meliService.validate(authToken)) {
				MeliAuthContext.setAuthToken(authToken);
				filterChain.doFilter(request, response);
				MeliAuthContext.remove();
			} else {
				response.sendRedirect("/meli/redirect");
			}
		} else {
			response.sendRedirect("/meli/redirect");
		}		
	}

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
