package com.ventalandia.meli.service;

import com.ventalandia.meli.domain.AuthToken;

/**
 * 
 * @author matias
 *
 */
public class MeliAuthContext {

	private static final ThreadLocal<AuthToken> threadLocal = new ThreadLocal<AuthToken>();
	
	public static void setAuthToken(AuthToken authToken) {
		threadLocal.set(authToken);
	}

	public static void remove() {
		threadLocal.remove();
	}
	
}
