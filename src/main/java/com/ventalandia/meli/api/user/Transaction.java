package com.ventalandia.meli.api.user;

/**
 * 
 * @author matias
 * 
 */
public class Transaction {

	private String period;
	private int total;
	private int completed;

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCompleted() {
		return completed;
	}

	public void setCompleted(int completed) {
		this.completed = completed;
	}

}
