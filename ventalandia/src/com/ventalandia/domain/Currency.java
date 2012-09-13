package com.ventalandia.domain;

public class Currency {

	private String meliId;
	private String description;
	private String symbol;
	private int decimalPlaces;
	
	public String getMeliId() {
		return meliId;
	}
	public void setMeliId(String meliId) {
		this.meliId = meliId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public int getDecimalPlaces() {
		return decimalPlaces;
	}
	public void setDecimalPlaces(int decimalPlaces) {
		this.decimalPlaces = decimalPlaces;
	}
	
}