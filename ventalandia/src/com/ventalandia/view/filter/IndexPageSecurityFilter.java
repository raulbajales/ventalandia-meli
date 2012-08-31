package com.ventalandia.view.filter;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author matias
 *
 */
public class IndexPageSecurityFilter extends AbstractSecurityFilter {

	@Override
	protected void onInvalidSession(HttpServletResponse response) {
		try {
			response.sendRedirect("/home.html");
		} catch (IOException e) {
			throw new RuntimeException("error when redirect.", e);
		}
	}

}
