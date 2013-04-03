package com.ventalandia.meli.api.user;

/**
 * 
 * @author matias
 * 
 */
public class Credit {

	private Double consumed;
	private String credit_level_id;

	public Double getConsumed() {
		return consumed;
	}

	public void setConsumed(Double consumed) {
		this.consumed = consumed;
	}

	public String getCredit_level_id() {
		return credit_level_id;
	}

	public void setCredit_level_id(String credit_level_id) {
		this.credit_level_id = credit_level_id;
	}

	@Override
	public String toString() {
		return "Credit [consumed=" + consumed + ", credit_level_id="
				+ credit_level_id + "]";
	}

}
