
package com.ventalandia.meli.api.notification;


public class Shipping{
   	private boolean free_shipping;
   	private boolean local_pick_up;
   	private String profile_id;

 	public boolean getFree_shipping(){
		return this.free_shipping;
	}
	public void setFree_shipping(boolean free_shipping){
		this.free_shipping = free_shipping;
	}
 	public boolean getLocal_pick_up(){
		return this.local_pick_up;
	}
	public void setLocal_pick_up(boolean local_pick_up){
		this.local_pick_up = local_pick_up;
	}
 	public String getProfile_id(){
		return this.profile_id;
	}
	public void setProfile_id(String profile_id){
		this.profile_id = profile_id;
	}
}
