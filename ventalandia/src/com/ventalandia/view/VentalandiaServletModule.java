package com.ventalandia.view;

import com.google.inject.servlet.ServletModule;
import com.ventalandia.view.api.ApiServlet;
import com.ventalandia.view.api.NewsApiServlet;
import com.ventalandia.view.api.NotificationApiServlet;
import com.ventalandia.view.filter.PersistenceManagerFilter;
import com.ventalandia.view.filter.SecurityFilter;

/**
 * All IOC related with the View must be here.
 * 
 * @author matias
 *
 */
public class VentalandiaServletModule extends ServletModule {

	private String homePage = "/home.html";
	private String indexPage = "/index.html";

	@Override
	protected void configureServlets() {
		// MELI
		// authentication
		this.bind(String.class).annotatedWith(HomePage.class).toInstance(homePage);
		this.bind(String.class).annotatedWith(IndexPage.class).toInstance(indexPage);
		serve("/meli/redirect").with(MeliRedirectorServlet.class);
		serve("/meli/auth").with(AuthServlet.class);
		
		// Ventalandia
		// tests or healthcheckers
		serve("/echo", "/echo/*").with(EchoServlet.class);

		// support
		filter("/api/*").through(SecurityFilter.class);
		filter("/*").through(PersistenceManagerFilter.class);

		// api

		serve("/api/test").with(ApiServlet.class);
		serve("/api/news", "/api/news/*").with(NewsApiServlet.class);
		serve("/meli/notifications", "/meli/notifications/*").with(NotificationApiServlet.class);
	}
	
}
