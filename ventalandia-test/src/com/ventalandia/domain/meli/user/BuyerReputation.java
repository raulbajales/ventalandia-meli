package com.ventalandia.domain.meli.user;

/**
 * 
 * @author matias
 *
 */
public class BuyerReputation {

	private int canceled_transactions;
	private BuyerTransactions transactions;

	public int getCanceled_transactions() {
		return canceled_transactions;
	}

	public void setCanceled_transactions(int canceled_transactions) {
		this.canceled_transactions = canceled_transactions;
	}

	public BuyerTransactions getTransactions() {
		return transactions;
	}

	public void setTransactions(BuyerTransactions transactions) {
		this.transactions = transactions;
	}

	@Override
	public String toString() {
		return "BuyerReputation [canceled_transactions="
				+ canceled_transactions + ", transactions=" + transactions
				+ "]";
	}

}
