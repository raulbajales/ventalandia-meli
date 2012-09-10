package com.ventalandia.meli.service;

import com.ventalandia.framework.http.FluentStringsMap;
import com.ventalandia.framework.http.HttpResponse;
import com.ventalandia.framework.http.MeliException;
import com.ventalandia.meli.api.user.MeliPublicUser;
import com.ventalandia.meli.api.user.MeliUser;

/**
 * 
 * @author matias
 *
 */
public class UserMeliService extends AbstractMeliService {
	
	public MeliUser getCurrentUser() {
		FluentStringsMap params = new FluentStringsMap();

		params.add("access_token", MeliAuthContext.getAuthToken().getAccess_token());

		HttpResponse response = http.get("/users/me", params, "");

		return this.parsePrivateUser(response);
	}

	private MeliUser parsePrivateUser(HttpResponse response) {
		if (response.getResponseCode() == 200) {
			String json = response.getResponseMessage();
			return gson.fromJson(json, MeliUser.class);			
		} else {
			throw new MeliException(response.getResponseMessage());
		}
	}
	
	public MeliPublicUser getPulicUser(long id) {
		FluentStringsMap params = new FluentStringsMap();

		HttpResponse response = http.get("/users/"+id, params, "");

		return this.parsePublicUser(response);
	}

	private MeliPublicUser parsePublicUser(HttpResponse response) {
		if (response.getResponseCode() == 200) {
			String json = response.getResponseMessage();
			return gson.fromJson(json, MeliPublicUser.class);			
		} else {
			throw new MeliException(response.getResponseMessage());
		}
	}

}
