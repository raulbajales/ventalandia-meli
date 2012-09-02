package com.ventalandia.meli.api.user;

/**
 * 
 * @author matias
 * 
 */
public class Identification {

	private String type;
	private String number;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Identification [type=" + type + ", number=" + number + "]";
	}

}
