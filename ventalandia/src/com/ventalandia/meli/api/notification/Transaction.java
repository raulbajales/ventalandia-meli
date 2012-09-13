
package com.ventalandia.meli.api.notification;


public class Transaction{
   	private Number canceled;
   	private Number completed;
   	private String period;
   	private Rating ratings;
   	private Number total;

 	public Number getCanceled(){
		return this.canceled;
	}
	public void setCanceled(Number canceled){
		this.canceled = canceled;
	}
 	public Number getCompleted(){
		return this.completed;
	}
	public void setCompleted(Number completed){
		this.completed = completed;
	}
 	public String getPeriod(){
		return this.period;
	}
	public void setPeriod(String period){
		this.period = period;
	}
 	public Rating getRatings(){
		return this.ratings;
	}
	public void setRatings(Rating ratings){
		this.ratings = ratings;
	}
 	public Number getTotal(){
		return this.total;
	}
	public void setTotal(Number total){
		this.total = total;
	}
}
