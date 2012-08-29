package com.ventalandia.meli.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.inject.Inject;
import com.ventalandia.meli.domain.AuthToken;
import com.ventalandia.meli.domain.AuthorizationFailure;
import com.ventalandia.meli.domain.FluentStringsMap;
import com.ventalandia.meli.domain.HttpConnector;
import com.ventalandia.meli.domain.HttpResponse;
import com.ventalandia.view.MeliCallbackUrlApi;
import com.ventalandia.view.MeliClientIdApi;
import com.ventalandia.view.MeliClientSecretApi;

/**
 * 
 * @author matias
 * 
 */
public class MeliServiceImpl implements MeliService {

	@Inject @MeliClientSecretApi
	private String clientSecret;
	
	@Inject @MeliClientIdApi
	private Integer clientId;
	
	@Inject @MeliCallbackUrlApi
	private String callback;
	
	@Inject
	private Gson gson;
	
	private HttpConnector http = new HttpConnector();

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
}
