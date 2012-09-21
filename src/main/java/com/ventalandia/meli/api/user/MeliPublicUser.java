package com.ventalandia.meli.api.user;



/**
 * 
 * @author matias
 * 
 */
public class MeliPublicUser extends AbstractMeliUser {

	private PublicStatus status;

	public PublicStatus getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "MeliPublicUser [status=" + status + ", toString()="
				+ super.toString() + "]";
	}



}
