package com.ventalandia.domain.meli.user;

import java.util.Arrays;

/**
 * 
 * @author matias
 * 
 */
public class ImmediatePayment {

	private boolean required;
	private String[] reasons;

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public String[] getReasons() {
		return reasons;
	}

	public void setReasons(String[] reasons) {
		this.reasons = reasons;
	}

	@Override
	public String toString() {
		return "ImmediatePayment [required=" + required + ", reasons="
				+ Arrays.toString(reasons) + "]";
	}

}
