package com.ventalandia.view.domain;

import java.util.Date;

import com.ventalandia.domain.Question;
import com.ventalandia.service.NewsType;

/**
 * 
 * @author german
 * 
 */
public class NewsView {

	private long id;
	private UserView buyer;
	private Date date;
	private NewsType type;
	private ItemView item;
	
	public NewsView(Question question) {

		this.id = question.getKey().getId();
		this.buyer = new UserView(question.getClient().getKey().getId(), question.getClient().getNickName());
		this.date = question.getCreationDate();
		this.type = NewsType.QUESTION;
		this.item = new ItemView(question.getItem().getKey().getId(),question.getItem().getTitle());
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserView getBuyer() {
		return buyer;
	}

	public void setBuyer(UserView buyer) {
		this.buyer = buyer;
	}

	public ItemView getItem() {
		return item;
	}

	public void setItem(ItemView item) {
		this.item = item;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public NewsType getType() {
		return type;
	}

	public void setType(NewsType type) {
		this.type = type;
	}

}