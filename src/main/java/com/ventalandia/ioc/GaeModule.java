package com.ventalandia.ioc;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.appengine.api.search.SearchService;
import com.google.appengine.api.search.SearchServiceFactory;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;
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
        this.bind(URLFetchService.class).toInstance(URLFetchServiceFactory.getURLFetchService());
        this.bind(MemcacheService.class).annotatedWith(TokenCache.class).toInstance(MemcacheServiceFactory.getMemcacheService("tokens"));
        this.bind(SearchService.class).toInstance(SearchServiceFactory.getSearchService());
//        this.bind(MemcacheService.class).annotatedWith(TokenCache.class).toInstance(MemcacheServiceFactory.getMemcacheService("users"));
    }

}
