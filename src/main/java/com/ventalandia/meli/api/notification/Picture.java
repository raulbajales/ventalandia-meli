
package com.ventalandia.meli.api.notification;


public class Picture{
   	private String id;
   	private String max_size;
   	private String quality;
   	private String secure_url;
   	private String size;
   	private String url;

 	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
 	public String getMax_size(){
		return this.max_size;
	}
	public void setMax_size(String max_size){
		this.max_size = max_size;
	}
 	public String getQuality(){
		return this.quality;
	}
	public void setQuality(String quality){
		this.quality = quality;
	}
 	public String getSecure_url(){
		return this.secure_url;
	}
	public void setSecure_url(String secure_url){
		this.secure_url = secure_url;
	}
 	public String getSize(){
		return this.size;
	}
	public void setSize(String size){
		this.size = size;
	}
 	public String getUrl(){
		return this.url;
	}
	public void setUrl(String url){
		this.url = url;
	}
}
