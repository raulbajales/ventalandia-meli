package com.ventalandia.domain;

import java.util.Date;

/**
 * @author gzanussi
 *
 */
public class User {

	private long meliId;
	private String nickName;
	private Country country;
	private Date registrationDate;
	
	public long getMeliId() {
		return meliId;
	}
	public void setMeliId(long meliId) {
		this.meliId = meliId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	
	
}
