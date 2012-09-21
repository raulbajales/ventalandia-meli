
package com.ventalandia.meli.api.notification;


public class Rating{
   	private Number negative;
   	private Number neutral;
   	private Number positive;

 	public Number getNegative(){
		return this.negative;
	}
	public void setNegative(Number negative){
		this.negative = negative;
	}
 	public Number getNeutral(){
		return this.neutral;
	}
	public void setNeutral(Number neutral){
		this.neutral = neutral;
	}
 	public Number getPositive(){
		return this.positive;
	}
	public void setPositive(Number positive){
		this.positive = positive;
	}
}
