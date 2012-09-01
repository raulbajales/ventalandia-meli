package com.ventalandia.meli.domain.notification;

import java.util.Date;

import com.ventalandia.meli.domain.Answer;

public class Question {

	private long id;
	private Answer answer;
	private Date date_created;
	private String item_id;
	private long seller_id;
	private String status;
	private String text;
	private UserFrom from;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Answer getAnswer() {
		return answer;
	}
	public void setAnswer(Answer answer) {
		this.answer = answer;
	}
	public Date getDate_created() {
		return date_created;
	}
	public void setDate_created(Date date_created) {
		this.date_created = date_created;
	}
	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	public long getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(long seller_id) {
		this.seller_id = seller_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public UserFrom getFrom() {
		return from;
	}
	public void setFrom(UserFrom from) {
		this.from = from;
	}
	
	
	
	
}
