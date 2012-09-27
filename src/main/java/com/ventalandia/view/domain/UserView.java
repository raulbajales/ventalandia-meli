package com.ventalandia.view.domain;

public class UserView {

	private long id;
	private String fullName;
	
	
	
	public UserView(long id, String fullName) {
		super();
		this.id = id;
		this.fullName = fullName;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
}
