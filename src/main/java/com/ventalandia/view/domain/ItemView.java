package com.ventalandia.view.domain;

public class ItemView {
	
	private long id;
	private String description;

	public ItemView(long id, String description) {
		this.id = id;
		this.description = description;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
