package com.ventalandia.view;

import com.google.inject.servlet.ServletModule;

/**
 * All IOC related with the View must be here.
 * 
 * @author matias
 *
 */
public class VentalandiaServletModule extends ServletModule {

	@Override
	protected void configureServlets() {
		serve("/echo", "/echo/*").with(EchoServlet.class);
	}
	
}
