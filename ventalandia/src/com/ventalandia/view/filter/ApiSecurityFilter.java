package com.ventalandia.view.filter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Singleton;

/**
 * 
 * @author matias
 *
 */
@Singleton
public class ApiSecurityFilter extends AbstractSecurityFilter {

	@Override
	protected void onInvalidSession(HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
	}

	@Override
	protected void onValidSession(FilterChain filterChain,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			filterChain.doFilter(request, response);
		} catch (Exception e) {
			throw new RuntimeException("Unable to handle a valid session", e);
		}		
	}

}
