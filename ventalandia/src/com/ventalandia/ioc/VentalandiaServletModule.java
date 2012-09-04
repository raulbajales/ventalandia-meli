package com.ventalandia.ioc;

import com.google.inject.servlet.ServletModule;
import com.ventalandia.api.ApiServlet;
import com.ventalandia.api.AuthServlet;
import com.ventalandia.api.EchoServlet;
import com.ventalandia.api.MeliRedirectorServlet;
import com.ventalandia.api.NewsApiServlet;
import com.ventalandia.api.UserApiServlet;
import com.ventalandia.filter.ApiSecurityFilter;
import com.ventalandia.filter.PersistenceManagerFilter;
import com.ventalandia.meli.callback.NotificationApiServlet;
import com.ventalandia.view.filter.WebappSecurityFilter;

/**
 * All IOC related with the View must be here.
 * 
 * @author matias
 *
 */
public class VentalandiaServletModule extends ServletModule {

	@Override
	protected void configureServlets() {
		// MELI
		// authentication
		serve("/meli/redirect").with(MeliRedirectorServlet.class);
		serve("/meli/auth").with(AuthServlet.class);
		serve("/meli/notifications", "/meli/notifications/*").with(NotificationApiServlet.class);
		
		// Ventalandia
		// tests or healthcheckers
		serve("/echo", "/echo/*").with(EchoServlet.class);

		// support
		filter("/").through(WebappSecurityFilter.class);
		filter("/api/*").through(ApiSecurityFilter.class);
		filter("/*").through(PersistenceManagerFilter.class);

		// api

		serve("/api/test").with(ApiServlet.class);
		serve("/api/news", "/api/news/*").with(NewsApiServlet.class);
		serve("/api/users/me").with(UserApiServlet.class);
	}
	
}
