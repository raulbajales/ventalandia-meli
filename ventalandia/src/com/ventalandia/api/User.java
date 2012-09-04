package com.ventalandia.api;

/**
 * Ventalandia's User.
 * 
 * @author msulik
 * 
 */
public class User {

	private String nickname;
	private long meliId;

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

}
