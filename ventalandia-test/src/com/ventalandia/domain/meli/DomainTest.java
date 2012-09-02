package com.ventalandia.domain.meli;

import org.junit.Before;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.ventalandia.ioc.VentalandiaDomainModule;

public class DomainTest {

	private static Injector injector;

	@Before
	public  void before() {

		if (injector == null) {
			injector = Guice.createInjector(getModules());
		}

		injector.injectMembers(this);
	}

	 protected Module[] getModules() {
         return new Module[] { new VentalandiaDomainModule() };
 }
}
