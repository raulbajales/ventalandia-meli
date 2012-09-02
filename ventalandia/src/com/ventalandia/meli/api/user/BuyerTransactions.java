package com.ventalandia.meli.api.user;

/**
 * 
 * @author matias
 * 
 */
public class BuyerTransactions extends Transaction {

	private BuyerRatings canceled;
	private BuyerRatings unrated;
	private BuyerRatings not_yet_rated;

	public BuyerRatings getCanceled() {
		return canceled;
	}

	public void setCanceled(BuyerRatings canceled) {
		this.canceled = canceled;
	}

	public BuyerRatings getUnrated() {
		return unrated;
	}

	public void setUnrated(BuyerRatings unrated) {
		this.unrated = unrated;
	}

	public BuyerRatings getNot_yet_rated() {
		return not_yet_rated;
	}

	public void setNot_yet_rated(BuyerRatings not_yet_rated) {
		this.not_yet_rated = not_yet_rated;
	}

	@Override
	public String toString() {
		return "BuyerTransactions [canceled=" + canceled + ", unrated="
				+ unrated + ", not_yet_rated=" + not_yet_rated + "]";
	}

}
