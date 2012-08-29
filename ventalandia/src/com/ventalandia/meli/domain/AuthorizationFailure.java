package com.ventalandia.meli.domain;

/**
 * 
 * @author matias
 * 
 */
public class AuthorizationFailure extends RuntimeException {

	private static final long serialVersionUID = 8688100047490895706L;

	public AuthorizationFailure(String message) {
		super(message);
	}

	public AuthorizationFailure(Throwable cause) {
		super(cause);
	}

}
