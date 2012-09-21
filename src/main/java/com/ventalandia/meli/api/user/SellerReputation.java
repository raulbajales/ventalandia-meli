package com.ventalandia.meli.api.user;

/**
 * 
 * @author matias
 * 
 */
public class SellerReputation {

	private String level_id;
	private String power_seller_status;
	private SellerTransactions transactions;

	public String getLevel_id() {
		return level_id;
	}

	public void setLevel_id(String level_id) {
		this.level_id = level_id;
	}

	public String getPower_seller_status() {
		return power_seller_status;
	}

	public void setPower_seller_status(String power_seller_status) {
		this.power_seller_status = power_seller_status;
	}

	public SellerTransactions getTransactions() {
		return transactions;
	}

	public void setTransactions(SellerTransactions transactions) {
		this.transactions = transactions;
	}

	@Override
	public String toString() {
		return "SellerReputation [level_id=" + level_id
				+ ", power_seller_status=" + power_seller_status
				+ ", transactions=" + transactions + "]";
	}

}
