package com.ventalandia.view;

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
		// add IOC Domain stuff here
	}

}
