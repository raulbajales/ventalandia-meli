package com.ventalandia.meli.api.user;

import java.util.Arrays;

/**
 * 
 * @author matias
 * 
 */
public class StatusOption {

	private boolean allow;
	private String[] codes;
	private ImmediatePayment immediate_payment;

	public boolean isAllow() {
		return allow;
	}

	public void setAllow(boolean allow) {
		this.allow = allow;
	}

	public String[] getCodes() {
		return codes;
	}

	public void setCodes(String[] codes) {
		this.codes = codes;
	}

	public ImmediatePayment getImmediate_payment() {
		return immediate_payment;
	}

	public void setImmediate_payment(ImmediatePayment immediate_payment) {
		this.immediate_payment = immediate_payment;
	}

	@Override
	public String toString() {
		return "StatusOption [allow=" + allow + ", codes="
				+ Arrays.toString(codes) + ", immediate_payment="
				+ immediate_payment + "]";
	}

}
