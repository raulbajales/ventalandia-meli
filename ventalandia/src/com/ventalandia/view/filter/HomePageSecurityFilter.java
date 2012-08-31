package com.ventalandia.view.filter;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author msulik
 *
 */
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
