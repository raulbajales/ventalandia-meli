package com.ventalandia.meli.domain;

/**
 * 
 * @author matias
 * 
 */
@Deprecated
public class MeliException extends RuntimeException{
	
	private static final long serialVersionUID = 7263275678852231779L;

	public MeliException(Throwable cause) {
		super(cause);
	}

	public MeliException(String message) {
		super(message);
	}

}
