package com.ventalandia.domain.meli.user;

/**
 * 
 * @author matias
 * 
 */
public class Phone {
	
	// some types are string instead of any number implementation due to MELI API returns empty values
	private String area_code;
	private String number;
	private String extension;
	private boolean verified;

	public String getArea_code() {
		return area_code;
	}

	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	@Override
	public String toString() {
		return "Phone [area_code=" + area_code + ", number=" + number
				+ ", extension=" + extension + ", verified=" + verified + "]";
	}

}
