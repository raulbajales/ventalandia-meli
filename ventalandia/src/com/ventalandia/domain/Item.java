package com.ventalandia.domain;

import java.util.Date;

public class Item {

	private User seller;
	private String title;
	private String subTitle;
	private float price;
	private float basePrice;
	private Currency currency;
	private int initialQuantity;
	private int availableQuantity;
	private int soldQuantity;
	private Date creationDate;
	private Date lastUpdated;
	
	
	public User getSeller() {
		return seller;
	}
	public void setSeller(User seller) {
		this.seller = seller;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(float basePrice) {
		this.basePrice = basePrice;
	}
	public Currency getCurrency() {
		return currency;
	}
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	public int getInitialQuantity() {
		return initialQuantity;
	}
	public void setInitialQuantity(int initialQuantity) {
		this.initialQuantity = initialQuantity;
	}
	public int getAvailableQuantity() {
		return availableQuantity;
	}
	public void setAvailableQuantity(int availableQuantity) {
		this.availableQuantity = availableQuantity;
	}
	public int getSoldQuantity() {
		return soldQuantity;
	}
	public void setSoldQuantity(int soldQuantity) {
		this.soldQuantity = soldQuantity;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
}