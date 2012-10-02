package com.ventalandia.view.filter;

import java.net.URLDecoder;

import javax.servlet.FilterChain;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.ventalandia.filter.AbstractSecurityFilter;
import com.ventalandia.view.WebappView;

/**
 * 
 * @author matias
 * @author raul
 */
@Singleton
public class WebappSecurityFilter extends AbstractSecurityFilter {

    public static final String VTD_TOKEN = "vtd_token";
	
	@Inject
	private WebappView webappView;

	@Override
	protected void onValidSession(FilterChain filterChain,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			webappView.renderHome(response,
					this.filterConfig.getServletContext());
		} catch (Exception e) {
			throw new RuntimeException("Unable to handle session request", e);
		}
	}

	@Override
	protected void onInvalidSession(HttpServletResponse response) {
		try {
			webappView.renderGuest(response,
					this.filterConfig.getServletContext());
		} catch (Exception e) {
			throw new RuntimeException("Unable to handle no-session request", e);
		}
	}

	protected String getVtdToken(HttpServletRequest request) {
		if (request.getCookies() == null) return null;
		for (Cookie cookie : request.getCookies()) {
			if (cookie.getName().equals(VTD_TOKEN)) {
				try {
					return URLDecoder.decode(cookie.getValue(), "UTF-8");
				} catch (Exception e) {
					throw new RuntimeException("Unable to get token from cookie", e);
				}
			} else {
				return null;
			}
		}
		return null;
	}
}
