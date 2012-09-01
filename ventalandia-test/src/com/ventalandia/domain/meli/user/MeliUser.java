package com.ventalandia.domain.meli.user;

import java.util.Date;

/**
 * 
 * @author matias
 * 
 */
public class MeliUser {

	private long id;
	private String nickname;
	// private Date registration_date;
	private String first_name;
	private String last_name;
	private String country_id;
	private String email;
	private Identification identification;
	private Phone phone;
	private Phone alternative_phone;
	private String user_type;
	private String logo;
	private String points;
	private String site_id;
	private String permalink;
	private String seller_experience;

	private SellerReputation seller_reputation;

	private BuyerReputation buyer_reputation;

	private Status status;
	private Credit credit;

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

	// public Date getRegistration_date() {
	// return registration_date;
	// }
	//
	// public void setRegistration_date(Date registration_date) {
	// this.registration_date = registration_date;
	// }

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getCountry_id() {
		return country_id;
	}

	public void setCountry_id(String country_id) {
		this.country_id = country_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
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

	public String getSeller_experience() {
		return seller_experience;
	}

	public void setSeller_experience(String seller_experience) {
		this.seller_experience = seller_experience;
	}

	public void setIdentification(Identification identification) {
		this.identification = identification;
	}

	public Identification getIdentification() {
		return identification;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	public Phone getPhone() {
		return phone;
	}

	public void setAlternative_phone(Phone alternative_phone) {
		this.alternative_phone = alternative_phone;
	}

	public Phone getAlternative_phone() {
		return alternative_phone;
	}

	public void setSeller_reputation(SellerReputation seller_reputation) {
		this.seller_reputation = seller_reputation;
	}

	public SellerReputation getSeller_reputation() {
		return seller_reputation;
	}

	public void setBuyer_reputation(BuyerReputation buyer_reputation) {
		this.buyer_reputation = buyer_reputation;
	}

	public BuyerReputation getBuyer_reputation() {
		return buyer_reputation;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Status getStatus() {
		return status;
	}

	public void setCredit(Credit credit) {
		this.credit = credit;
	}

	public Credit getCredit() {
		return credit;
	}

	@Override
	public String toString() {
		return "MeliUser [id=" + id + ", nickname=" + nickname
				+ ", first_name=" + first_name + ", last_name=" + last_name
				+ ", country_id=" + country_id + ", email=" + email
				+ ", identification=" + identification + ", phone=" + phone
				+ ", alternative_phone=" + alternative_phone + ", user_type="
				+ user_type + ", logo=" + logo + ", points=" + points
				+ ", site_id=" + site_id + ", permalink=" + permalink
				+ ", seller_experience=" + seller_experience
				+ ", seller_reputation=" + seller_reputation
				+ ", buyer_reputation=" + buyer_reputation + ", status="
				+ status + ", credit=" + credit + "]";
	}
}
