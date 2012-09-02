package com.ventalandia.meli.api.user;

/**
 * 
 * @author matias
 * 
 */
public class BuyerRatings {

	private Long total;
	private Long paid;
	private Long units;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Long getPaid() {
		return paid;
	}

	public void setPaid(Long paid) {
		this.paid = paid;
	}

	public Long getUnits() {
		return units;
	}

	public void setUnits(Long units) {
		this.units = units;
	}

	@Override
	public String toString() {
		return "BuyerRatings [total=" + total + ", paid=" + paid + ", units="
				+ units + "]";
	}

}
