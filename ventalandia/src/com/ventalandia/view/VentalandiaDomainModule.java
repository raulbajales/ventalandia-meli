package com.ventalandia.view;


import com.google.gson.Gson;
import com.google.inject.AbstractModule;
import com.google.inject.Module;

/**
 * All IOC related with the Domain must be here. Repositories, Services and
 * their dependency relations must be declared in this {@linkplain Module}.
 * 
 * @author matias
 * 
 */
public class VentalandiaDomainModule extends AbstractModule {

	@Override
	protected void configure() {
		// support
		this.bind(Gson.class).toInstance(new Gson());
	}

}
