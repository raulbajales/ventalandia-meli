package com.ventalandia.domain;

import org.junit.After;
import org.junit.Before;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalMailServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalMemcacheServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalSearchServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.LocalURLFetchServiceTestConfig;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.ventalandia.ioc.GaeModule;
import com.ventalandia.ioc.VentalandiaDomainModule;
import com.ventalandia.meli.ioc.MeliModule;

/**
 * 
 * @author matias
 *
 */
public abstract class DomainTest {

	/**
	 * Helper to setup the local environment to execute test cases.
	 */
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalMailServiceTestConfig(),
			new LocalDatastoreServiceTestConfig(),
			new LocalURLFetchServiceTestConfig(),
			new LocalMemcacheServiceTestConfig(),
			new LocalSearchServiceTestConfig());

	private static Injector injector;

	@Before
	public void before() {
		helper.setUp();

		if (injector == null) {
			injector = Guice.createInjector(getModules());
		}

		injector.injectMembers(this);
	}

	protected Module[] getModules() {
		return new Module[] { new VentalandiaDomainModule(), new MeliModule(), new GaeModule()};
	}
	
	@After
	public void after() {
		helper.tearDown();
	}
}
