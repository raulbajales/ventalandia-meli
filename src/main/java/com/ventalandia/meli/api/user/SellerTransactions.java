package com.ventalandia.meli.api.user;

/**
 * 
 * @author matias
 * 
 */
public class SellerTransactions extends Transaction {

	private int canceled;
	private SellerRatings ratings;

	public int getCanceled() {
		return canceled;
	}

	public void setCanceled(int canceled) {
		this.canceled = canceled;
	}

	public SellerRatings getRatings() {
		return ratings;
	}

	public void setRatings(SellerRatings ratings) {
		this.ratings = ratings;
	}

	@Override
	public String toString() {
		return "SellerTransactions [canceled=" + canceled + ", ratings="
				+ ratings + ", getPeriod()=" + getPeriod() + ", getTotal()="
				+ getTotal() + ", getCompleted()=" + getCompleted() + "]";
	}

}
