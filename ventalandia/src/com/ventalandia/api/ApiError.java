package com.ventalandia.api;

/**
 * 
 * @author matias
 *
 */
public class ApiError {

	private String message;
	
	public ApiError(String message) {
		this.message = message;
	}
	
	public ApiError() {
		// do nothing
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

}
