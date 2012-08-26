package com.ventalandia.service;

import java.util.Date;

/**
 * 
 * @author matias
 * 
 */
public class News {

	private boolean read;
	private String message;
	private Date date;
	private NewsType type;

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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
