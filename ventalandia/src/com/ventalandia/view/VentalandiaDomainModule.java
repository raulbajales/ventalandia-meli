package com.ventalandia.view;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.ventalandia.dao.NotificationDAO;
import com.ventalandia.dao.impl.NotificationDAOImpl;
import com.ventalandia.service.NewsService;
import com.ventalandia.service.NewsServiceStub;
import com.ventalandia.service.NotificationService;
import com.ventalandia.service.NotificationServiceImpl;

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
		this.bind(Gson.class).toInstance(new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create());

		// services
		this.bind(NewsService.class).toInstance(new NewsServiceStub());
		this.bind(NotificationService.class).to(NotificationServiceImpl.class);

		// daos
		PersistenceManagerFactory pmFactory = JDOHelper.getPersistenceManagerFactory("transactions-optional");
		this.bind(PersistenceManagerFactory.class).toInstance(pmFactory);
		this.bind(NotificationDAO.class).to(NotificationDAOImpl.class);

	}

}
