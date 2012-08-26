package com.ventalandia.view;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Singleton;

/**
 * 
 * @author matias
 * @author german
 *
 */
@Singleton
public class SecurityFilter implements Filter {

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
		filterChain.doFilter(request, response);
	}

	private void doInnerFilter(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain) {
		// put here security stuff
		// Cookie[] cookies = request.getCookies();
		
		// check if there is a cookie with the token
		// if false redirect to MELI
		// if true 
		//     check if it is a valid token (need refresh)
		//           if false redirect to MELI
		//           if true refresh / set to thread local (MeliUserService)
		
	}

}
