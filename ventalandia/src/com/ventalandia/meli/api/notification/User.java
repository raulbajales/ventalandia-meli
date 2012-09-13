
package com.ventalandia.meli.api.notification;

import java.util.Date;


public class User{
   	private String country_id;
   	private long id;
   	private String logo;
   	private String nickname;
   	private String permalink;
   	private Number points;
   	private Date registration_date;
   	private Seller_reputation seller_reputation;
   	private String site_id;
   	private Status status;
   	private String user_type;

 	public String getCountry_id(){
		return this.country_id;
	}
	public void setCountry_id(String country_id){
		this.country_id = country_id;
	}
 	public long getId(){
		return this.id;
	}
	public void setId(long id){
		this.id = id;
	}
 	public String getLogo(){
		return this.logo;
	}
	public void setLogo(String logo){
		this.logo = logo;
	}
 	public String getNickname(){
		return this.nickname;
	}
	public void setNickname(String nickname){
		this.nickname = nickname;
	}
 	public String getPermalink(){
		return this.permalink;
	}
	public void setPermalink(String permalink){
		this.permalink = permalink;
	}
 	public Number getPoints(){
		return this.points;
	}
	public void setPoints(Number points){
		this.points = points;
	}
 	public Date getRegistration_date(){
		return this.registration_date;
	}
	public void setRegistration_date(Date registration_date){
		this.registration_date = registration_date;
	}
 	public Seller_reputation getSeller_reputation(){
		return this.seller_reputation;
	}
	public void setSeller_reputation(Seller_reputation seller_reputation){
		this.seller_reputation = seller_reputation;
	}
 	public String getSite_id(){
		return this.site_id;
	}
	public void setSite_id(String site_id){
		this.site_id = site_id;
	}
 	public Status getStatus(){
		return this.status;
	}
	public void setStatus(Status status){
		this.status = status;
	}
 	public String getUser_type(){
		return this.user_type;
	}
	public void setUser_type(String user_type){
		this.user_type = user_type;
	}
}
