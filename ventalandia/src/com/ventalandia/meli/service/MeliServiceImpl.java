package com.ventalandia.meli.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.ventalandia.framework.http.FluentStringsMap;
import com.ventalandia.framework.http.HttpResponse;
import com.ventalandia.meli.api.auth.AuthToken;
import com.ventalandia.meli.api.auth.AuthorizationFailure;

/**
 * 
 * @author matias
 * 
 */
public class MeliServiceImpl extends AbstractMeliService implements MeliService {

	@Override
	public AuthToken getAuthToken(String code) {
		FluentStringsMap params = new FluentStringsMap();

		params.add("grant_type", "authorization_code");
		params.add("client_id", String.valueOf(this.clientId));
		params.add("client_secret", this.clientSecret);
		params.add("code", code);
		params.add("redirect_uri", this.callback);

		HttpResponse response = http.post("/oauth/token", params, "");

		return this.parseToken(response);
	}

	private AuthToken parseToken(HttpResponse response) throws AuthorizationFailure {
		String responseBody = response.getResponseMessage();
		JsonParser p = new JsonParser();
		JsonObject object;

		try {
			object = p.parse(responseBody).getAsJsonObject();
		} catch (JsonSyntaxException e) {
			throw new AuthorizationFailure(responseBody);
		}

		if (response.getResponseCode() == 200) {
			return this.gson.fromJson(object, AuthToken.class);
		} else {
			throw new AuthorizationFailure(object.get("message").getAsString());
		}
	}

	@Override
	public boolean validate(AuthToken authToken) {
		return true;
	}
}
