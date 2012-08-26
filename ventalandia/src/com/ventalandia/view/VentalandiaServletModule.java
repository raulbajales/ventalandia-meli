package com.ventalandia.view;

import com.google.inject.servlet.ServletModule;
import com.ventalandia.view.api.ApiServlet;
import com.ventalandia.view.api.NewsApiServlet;
import com.ventalandia.view.api.NotificationApiServlet;

/**
 * All IOC related with the View must be here.
 * 
 * @author matias
 *
 */
public class VentalandiaServletModule extends ServletModule {

	@Override
	protected void configureServlets() {
		// tests or healthcheckers
		serve("/echo", "/echo/*").with(EchoServlet.class);
		
		// authentication
		serve("/login.html").with(LoginServlet.class);
		serve("/meli/auth").with(AuthServlet.class);
		
		// api
		filter("/api/*").through(SecurityFilter.class);

		serve("/api/test").with(ApiServlet.class);
		serve("/api/news", "/api/news/*").with(NewsApiServlet.class);
		serve("/meli/notifications", "/meli/notifications/*").with(NotificationApiServlet.class);
	}
	
}
