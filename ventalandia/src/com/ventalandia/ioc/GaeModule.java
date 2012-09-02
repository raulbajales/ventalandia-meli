package com.ventalandia.ioc;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.inject.AbstractModule;
import com.ventalandia.framework.persistence.PersistenceManagerProvider;

/**
 * 
 * @author matias
 * 
 */
public class GaeModule extends AbstractModule {

	@Override
	protected void configure() {
		this.bind(PersistenceManagerFactory.class).toInstance(JDOHelper.getPersistenceManagerFactory("transactions-optional"));
		this.bind(PersistenceManager.class).toProvider(PersistenceManagerProvider.class);
		this.bind(DatastoreService.class).toInstance(DatastoreServiceFactory.getDatastoreService());
	}
}
