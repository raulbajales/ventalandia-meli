package com.ventalandia.ioc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.ventalandia.meli.pesistence.CountryRepository;
import com.ventalandia.meli.pesistence.CountryRepositoryImpl;
import com.ventalandia.meli.pesistence.CurrencyRepository;
import com.ventalandia.meli.pesistence.CurrencyRepositoryImpl;
import com.ventalandia.meli.pesistence.ItemRepository;
import com.ventalandia.meli.pesistence.ItemRepositoryImpl;
import com.ventalandia.meli.pesistence.NotificationRepository;
import com.ventalandia.meli.pesistence.QuestionRepository;
import com.ventalandia.meli.pesistence.UserRepository;
import com.ventalandia.meli.pesistence.UserRepositoryImpl;
import com.ventalandia.persistence.TokenRepository;
import com.ventalandia.service.AuthService;
import com.ventalandia.service.CountryService;
import com.ventalandia.service.CountryServiceImpl;
import com.ventalandia.service.CurrencyService;
import com.ventalandia.service.CurrencyServiceImpl;
import com.ventalandia.service.ItemService;
import com.ventalandia.service.ItemServiceImpl;
import com.ventalandia.service.NewsService;
import com.ventalandia.service.NewsServiceStub;
import com.ventalandia.service.NotificationService;
import com.ventalandia.service.NotificationServiceImpl;
import com.ventalandia.service.QuestionService;
import com.ventalandia.service.QuestionServiceImpl;
import com.ventalandia.service.UserServiceImpl;

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
        this.bind(AuthService.class).toInstance(new AuthService());
        this.bind(com.ventalandia.service.UserService.class).to(UserServiceImpl.class);
        this.bind(CurrencyService.class).to(CurrencyServiceImpl.class);
        this.bind(CountryService.class).to(CountryServiceImpl.class);
        this.bind(ItemService.class).to(ItemServiceImpl.class);
        this.bind(QuestionService.class).to(QuestionServiceImpl.class);

        // repositories
        this.bind(NotificationRepository.class);
        this.bind(QuestionRepository.class);
        this.bind(TokenRepository.class);
        this.bind(UserRepository.class).to(UserRepositoryImpl.class);
        this.bind(CurrencyRepository.class).to(CurrencyRepositoryImpl.class);
        this.bind(CountryRepository.class).to(CountryRepositoryImpl.class);
        this.bind(ItemRepository.class).to(ItemRepositoryImpl.class);

    }

}
