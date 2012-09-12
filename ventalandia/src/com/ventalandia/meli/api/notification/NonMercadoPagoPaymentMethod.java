
package com.ventalandia.meli.api.notification;


public class NonMercadoPagoPaymentMethod{
   	private String description;
   	private String id;
   	private String type;

 	public String getDescription(){
		return this.description;
	}
	public void setDescription(String description){
		this.description = description;
	}
 	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
 	public String getType(){
		return this.type;
	}
	public void setType(String type){
		this.type = type;
	}
}
