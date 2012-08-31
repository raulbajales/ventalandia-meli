package com.ventalandia.view.filter;

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

}
