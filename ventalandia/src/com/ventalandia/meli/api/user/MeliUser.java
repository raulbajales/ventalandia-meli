package com.ventalandia.meli.api.user;


/**
 * 
 * @author matias
 * 
 */
public class MeliUser extends AbstractMeliUser {

	private String first_name;
	private String last_name;
	private String email;
	private Identification identification;
	private Phone phone;
	private Phone alternative_phone;
	private String seller_experience;
	private BuyerReputation buyer_reputation;
	private Status status;
	private Credit credit;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
		return "MeliUser [first_name=" + first_name + ", last_name="
				+ last_name + ", email=" + email + ", identification="
				+ identification + ", phone=" + phone + ", alternative_phone="
				+ alternative_phone + ", seller_experience="
				+ seller_experience + ", buyer_reputation=" + buyer_reputation
				+ ", status=" + status + ", credit=" + credit + ", toString()="
				+ super.toString() + "]";
	}

	

}
