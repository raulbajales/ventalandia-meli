
package com.ventalandia.meli.api.notification;


public class Seller_reputation{
   	private String level_id;
   	private String power_seller_status;
   	private Transaction transactions;

 	public String getLevel_id(){
		return this.level_id;
	}
	public void setLevel_id(String level_id){
		this.level_id = level_id;
	}
 	public String getPower_seller_status(){
		return this.power_seller_status;
	}
	public void setPower_seller_status(String power_seller_status){
		this.power_seller_status = power_seller_status;
	}
 	public Transaction getTransactions(){
		return this.transactions;
	}
	public void setTransactions(Transaction transactions){
		this.transactions = transactions;
	}
}
