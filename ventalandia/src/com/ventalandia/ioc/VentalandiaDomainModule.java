package com.ventalandia.ioc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.ventalandia.api.UserService;
import com.ventalandia.meli.pesistence.NotificationRepository;
import com.ventalandia.meli.pesistence.QuestionRepository;
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
		this.bind(Gson.class).toInstance(new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").create());

		// services
		this.bind(NewsService.class).toInstance(new NewsServiceStub());
		this.bind(NotificationService.class).to(NotificationServiceImpl.class);
		this.bind(UserService.class).toInstance(new UserService());

		// repositories
		this.bind(NotificationRepository.class).toInstance(new NotificationRepository());
<<<<<<< HEAD
		this.bind(QuestionRepository.class).toInstance(new QuestionRepository());

=======
>>>>>>> 155e232777ba307de945e8a393b02145b9e6cf2a
	}

}
