package com.ventalandia.domain.meli;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;


public class MeliTest {

	private static final int clientId = 10601;
	private static final String clientSecret = "aYWPAudc48nQNEj9ZTVCxftGU36DJoh8";
	private String callback = "http://development.ventalandia-meli.appspot.com/meli/auth";
	
	@Test
	@Ignore
	public void getAuthUrl() {
		Meli meli = new Meli(clientId, clientSecret);
		String authUrl = meli.getAuthUrl(callback);
		
		Assert.assertEquals("https://auth.mercadolibre.com.ar/authorization?response_type=code&client_id=10601&redirect_uri=http%3A%2F%2Fdevelopment.ventalandia-meli.appspot.com%2Fmeli%2Fauth", authUrl);
	}
	
	@Test
	public void test() throws Exception {
		Meli meli = new Meli(clientId, clientSecret);
		
		// Still don't know how to test it. I generated the code using the site and pasted it to execute the test.
		String code = "TG-501e2509e4b0dfe9c32b28f7";
		
		meli.authorize(code, callback);
	}

}
