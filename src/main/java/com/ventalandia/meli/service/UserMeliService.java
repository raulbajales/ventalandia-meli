package com.ventalandia.meli.service;

import com.google.appengine.api.urlfetch.HTTPResponse;
import com.ventalandia.framework.http.HttpRequestBuilder;
import com.ventalandia.meli.api.user.MeliPublicUser;
import com.ventalandia.meli.api.user.MeliUser;

/**
 * 
 * @author matias
 * 
 */
public class UserMeliService extends AbstractMeliService {

	public MeliUser getCurrentUser() {
		HttpRequestBuilder httpRequestBuilder = this.createJsonGet().withPath("/users/me")//
				.addParam("access_token", AuthContext.getToken().getAccess_token());

		HTTPResponse httpResponse = this.execute(httpRequestBuilder);

		return this.parseEntity(httpResponse, MeliUser.class);
	}

	public MeliPublicUser getPulicUser(long id) {
		HttpRequestBuilder httpRequestBuilder = this.createJsonGet().withPath("/users/" + id);

		HTTPResponse httpResponse = this.execute(httpRequestBuilder);

		return this.parseEntity(httpResponse, MeliPublicUser.class);
	}

}
