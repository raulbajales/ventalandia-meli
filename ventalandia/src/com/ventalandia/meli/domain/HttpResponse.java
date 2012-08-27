package com.ventalandia.meli.domain;

/**
 * 
 * @author matias
 *
 */
public class HttpResponse {

	private int responseCode;
	private String responseMessage;

	public HttpResponse(int responseCode, String responseMessage) {
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
	}
	
	public int getResponseCode() {
		return responseCode;
	}
	
	public String getResponseMessage() {
		return responseMessage;
	}

}
