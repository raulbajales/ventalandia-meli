package com.ventalandia.domain;

import java.util.Date;

public class Question {
	
	private User client;
	private User seller;
	private Answer answer;
	private Item item;
	private String text;
	private Date creationDate;
	private String status;
	private long meliId;
	
	
	public User getClient() {
		return client;
	}
	public void setClient(User client) {
		this.client = client;
	}
	public User getSeller() {
		return seller;
	}
	public void setSeller(User seller) {
		this.seller = seller;
	}
	public Answer getAnswer() {
		return answer;
	}
	public void setAnswer(Answer answer) {
		this.answer = answer;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getMeliId() {
		return meliId;
	}
	public void setMeliId(long meliId) {
		this.meliId = meliId;
	}
	
	
}
