package com.ventalandia.meli.api.user;

import java.util.Arrays;
import java.util.Date;

/**
 * 
 * @author matias
 * 
 */
public class AbstractMeliUser {

	private long id;
	private String nickname;
	private Date registration_date;
	private String country_id;
	private String user_type;
	private String logo;
	private String points;
	private String site_id;
	private String permalink;
	private String[] shipping_modes;
	private SellerReputation seller_reputation;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Date getRegistration_date() {
		return registration_date;
	}

	public void setRegistration_date(Date registration_date) {
		this.registration_date = registration_date;
	}

	public String getCountry_id() {
		return country_id;
	}

	public void setCountry_id(String country_id) {
		this.country_id = country_id;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public String getSite_id() {
		return site_id;
	}

	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}

	public String getPermalink() {
		return permalink;
	}

	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}

	public void setSeller_reputation(SellerReputation seller_reputation) {
		this.seller_reputation = seller_reputation;
	}

	public SellerReputation getSeller_reputation() {
		return seller_reputation;
	}

	public void setShipping_modes(String[] shipping_modes) {
		this.shipping_modes = shipping_modes;
	}

	public String[] getShipping_modes() {
		return shipping_modes;
	}

	@Override
	public String toString() {
		return "AbstractMeliUser [id=" + id + ", nickname=" + nickname
				+ ", registration_date=" + registration_date + ", country_id="
				+ country_id + ", user_type=" + user_type + ", site_id="
				+ site_id + ", permalink=" + permalink + ", shipping_modes="
				+ Arrays.toString(shipping_modes) + ", seller_reputation="
				+ seller_reputation + "]";
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

}
