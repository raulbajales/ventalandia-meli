package com.ventalandia.domain.meli;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;
import com.ventalandia.meli.domain.AuthToken;

public class AuthTest {

	@Test
	public void test() {
		Gson gson = new Gson();
		
		String json = this.getJson();
		
		AuthToken authToken = gson.fromJson(json, AuthToken.class);
		
		Assert.assertEquals("APP_USR-6092-3246532-cb45c82853f6e620bb0deda096b128d3-8035443", authToken.getAccess_token());
		Assert.assertEquals("bearer", authToken.getToken_type());
		Assert.assertEquals(new Long("10800"), authToken.getExpires_in());
		Assert.assertEquals("write read", authToken.getScope());
	}

	private String getJson() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("{");
		builder.append("\"access_token\" : \"APP_USR-6092-3246532-cb45c82853f6e620bb0deda096b128d3-8035443\",");
		builder.append("\"token_type\" : \"bearer\",");
		builder.append("\"expires_in\" : 10800, \"scope\" : \"write read\"");
		builder.append("}");
		
		return builder.toString();
	}

}
