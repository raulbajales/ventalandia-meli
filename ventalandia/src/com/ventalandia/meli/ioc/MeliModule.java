package com.ventalandia.meli.ioc;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.ventalandia.framework.http.HttpConnector;
import com.ventalandia.meli.service.MeliService;
import com.ventalandia.meli.service.MeliServiceImpl;
import com.ventalandia.meli.service.UserMeliService;

/**
 * 
 * @author matias
 *
 */
public class MeliModule extends AbstractModule {
	
	private static String apiUrl = "https://api.mercadolibre.com";
	private static final int clientId = 10601;
	private static final String clientSecret = "aYWPAudc48nQNEj9ZTVCxftGU36DJoh8";
	private static final String callback = "http://development.ventalandia-meli.appspot.com/meli/auth";
	
	@Override
	protected void configure() {
		// setup
		this.bind(String.class).annotatedWith(MeliUrlApi.class).toInstance(apiUrl);
		this.bind(String.class).annotatedWith(MeliClientSecretApi.class).toInstance(clientSecret);
		this.bind(Integer.class).annotatedWith(MeliClientIdApi.class).toInstance(clientId);
		this.bind(String.class).annotatedWith(MeliCallbackUrlApi.class).toInstance(callback);

		// services
		this.bind(MeliService.class).to(MeliServiceImpl.class).in(Scopes.SINGLETON);
		this.bind(UserMeliService.class).toInstance(new UserMeliService());
		this.bind(HttpConnector.class).toInstance(new HttpConnector());
		
	}

}
