package com.ventalandia.domain.meli.user;

/**
 * 
 * @author matias
 * 
 */
public class Status {

	private String site_status;
	private boolean mercadopago_tc_accepted;
	private String mercadopago_account_type;
	private String mercadoenvios;
	private boolean immediate_payment;
	private StatusOption list;
	private StatusOption buy;
	private StatusOption sell;
	private StatusOption billing;

	public String getSite_status() {
		return site_status;
	}

	public void setSite_status(String site_status) {
		this.site_status = site_status;
	}

	public boolean isMercadopago_tc_accepted() {
		return mercadopago_tc_accepted;
	}

	public void setMercadopago_tc_accepted(boolean mercadopago_tc_accepted) {
		this.mercadopago_tc_accepted = mercadopago_tc_accepted;
	}

	public String getMercadopago_account_type() {
		return mercadopago_account_type;
	}

	public void setMercadopago_account_type(String mercadopago_account_type) {
		this.mercadopago_account_type = mercadopago_account_type;
	}

	public String getMercadoenvios() {
		return mercadoenvios;
	}

	public void setMercadoenvios(String mercadoenvios) {
		this.mercadoenvios = mercadoenvios;
	}

	public boolean isImmediate_payment() {
		return immediate_payment;
	}

	public void setImmediate_payment(boolean immediate_payment) {
		this.immediate_payment = immediate_payment;
	}

	public StatusOption getList() {
		return list;
	}

	public void setList(StatusOption list) {
		this.list = list;
	}

	public StatusOption getBuy() {
		return buy;
	}

	public void setBuy(StatusOption buy) {
		this.buy = buy;
	}

	public StatusOption getSell() {
		return sell;
	}

	public void setSell(StatusOption sell) {
		this.sell = sell;
	}

	public StatusOption getBilling() {
		return billing;
	}

	public void setBilling(StatusOption billing) {
		this.billing = billing;
	}

	@Override
	public String toString() {
		return "Status [site_status=" + site_status
				+ ", mercadopago_tc_accepted=" + mercadopago_tc_accepted
				+ ", mercadopago_account_type=" + mercadopago_account_type
				+ ", mercadoenvios=" + mercadoenvios + ", immediate_payment="
				+ immediate_payment + ", list=" + list + ", buy=" + buy
				+ ", sell=" + sell + ", billing=" + billing + "]";
	}

}
