package com.ventalandia.view;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.ventalandia.gae.ioc.GaeModule;

/**
 * Set guice-servlet support. This is basic set up for web.xml config replacement and one of the
 * necessary steps to set up IOC.
 * 
 * @see GuiceServletContextListener
 * 
 * @author matias
 * 
 */
public class VentalandiaServletConfig extends GuiceServletContextListener {

	/**
	 * Returns a new {@link Injector}. It creates and compounds all modules into
	 * one {@link Injector}.
	 */
	@Override
	protected Injector getInjector() {
		return Guice.createInjector(new VentalandiaDomainModule(), new VentalandiaServletModule(), new GaeModule(), new MeliModule());
	}

}
