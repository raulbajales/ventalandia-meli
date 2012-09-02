package com.ventalandia.filter;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.google.inject.Singleton;

/**
 * 
 * @author matias
 *
 */
@Singleton
public class HomePageSecurityFilter extends AbstractSecurityFilter {

	@Override
	protected void onInvalidSession(HttpServletResponse response) {
		try {
			response.sendRedirect("/index.html");
		} catch (IOException e) {
			throw new RuntimeException("error when redirect.", e);
		}
	}

}
