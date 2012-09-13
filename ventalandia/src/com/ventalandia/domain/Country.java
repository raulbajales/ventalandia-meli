package com.ventalandia.domain;

public class Country {
	
	private String meliId;
	private String name;
	private String locale;
	private Currency currency;
	
	public String getMeliId() {
		return meliId;
	}
	public void setMeliId(String meliId) {
		this.meliId = meliId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public Currency getCurrency() {
		return currency;
	}
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	
}