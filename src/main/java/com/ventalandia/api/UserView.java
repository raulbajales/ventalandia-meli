package com.ventalandia.api;

/**
 * Ventalandia's User.
 * 
 * @author msulik
 * 
 */
public class UserView {

	private String nickname;
	private long meliId;
	private String name;
	private String surname;
	private int sellerReputationLevel;

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getNickname() {
		return nickname;
	}

	public void setMeliId(long id) {
		this.meliId = id;
	}

	public long getMeliId() {
		return meliId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSellerReputationLevel(int sellerReputationLevel) {
		this.sellerReputationLevel = sellerReputationLevel;
	}

	public int getSellerReputationLevel() {
		return sellerReputationLevel;
	}

}
