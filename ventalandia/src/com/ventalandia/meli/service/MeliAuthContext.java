package com.ventalandia.meli.service;

import com.ventalandia.meli.api.auth.AuthToken;

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
	
	public static AuthToken getAuthToken() {
		return threadLocal.get();
	}

	public static void remove() {
		threadLocal.remove();
	}
	
}
