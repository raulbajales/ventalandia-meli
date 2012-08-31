package com.ventalandia.view;

import com.google.inject.servlet.ServletModule;
import com.ventalandia.view.api.ApiServlet;
import com.ventalandia.view.api.NewsApiServlet;
import com.ventalandia.view.api.NotificationApiServlet;
import com.ventalandia.view.filter.HomePageSecurityFilter;
import com.ventalandia.view.filter.IndexPageSecurityFilter;
import com.ventalandia.view.filter.PersistenceManagerFilter;
import com.ventalandia.view.filter.ApiSecurityFilter;

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
		filter("/index.html").through(IndexPageSecurityFilter.class);
		filter("/home.html").through(HomePageSecurityFilter.class);
		filter("/api/*").through(ApiSecurityFilter.class);
		filter("/api/*").through(PersistenceManagerFilter.class);

		// api

		serve("/api/test").with(ApiServlet.class);
		serve("/api/news", "/api/news/*").with(NewsApiServlet.class);
		serve("/meli/notifications", "/meli/notifications/*").with(NotificationApiServlet.class);
	}
	
}
