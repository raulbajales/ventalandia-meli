
package com.ventalandia.meli.api.notification;


public class Seller_address{
   	private String address_line;
   	private City city;
   	private String comment;
   	private Country country;
   	private Number id;
   	private String latitude;
   	private String longitude;
   	private State state;
   	private String zip_code;

 	public String getAddress_line(){
		return this.address_line;
	}
	public void setAddress_line(String address_line){
		this.address_line = address_line;
	}
 	public City getCity(){
		return this.city;
	}
	public void setCity(City city){
		this.city = city;
	}
 	public String getComment(){
		return this.comment;
	}
	public void setComment(String comment){
		this.comment = comment;
	}
 	public Country getCountry(){
		return this.country;
	}
	public void setCountry(Country country){
		this.country = country;
	}
 	public Number getId(){
		return this.id;
	}
	public void setId(Number id){
		this.id = id;
	}
 	public String getLatitude(){
		return this.latitude;
	}
	public void setLatitude(String latitude){
		this.latitude = latitude;
	}
 	public String getLongitude(){
		return this.longitude;
	}
	public void setLongitude(String longitude){
		this.longitude = longitude;
	}
 	public State getState(){
		return this.state;
	}
	public void setState(State state){
		this.state = state;
	}
 	public String getZip_code(){
		return this.zip_code;
	}
	public void setZip_code(String zip_code){
		this.zip_code = zip_code;
	}
}
