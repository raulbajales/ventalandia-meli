package com.ventalandia.domain.meli.user;

/**
 * 
 * @author matias
 *
 */
public class SellerRatings {

	private int positive;
	private int negative;
	private int neutral;

	public int getPositive() {
		return positive;
	}

	public void setPositive(int positive) {
		this.positive = positive;
	}

	public int getNegative() {
		return negative;
	}

	public void setNegative(int negative) {
		this.negative = negative;
	}

	public int getNeutral() {
		return neutral;
	}

	public void setNeutral(int neutral) {
		this.neutral = neutral;
	}

	@Override
	public String toString() {
		return "Ratings [positive=" + positive + ", negative=" + negative
				+ ", neutral=" + neutral + "]";
	}

}
