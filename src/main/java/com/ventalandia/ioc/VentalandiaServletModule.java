package com.ventalandia.ioc;

import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import com.ventalandia.api.ApiServlet;
import com.ventalandia.api.AuthServlet;
import com.ventalandia.api.DevSupportServlet;
import com.ventalandia.api.EchoServlet;
import com.ventalandia.api.MeliRedirectorServlet;
import com.ventalandia.api.NewsApiServlet;
import com.ventalandia.api.UsersApiServlet;
import com.ventalandia.filter.ApiSecurityFilter;
import com.ventalandia.filter.PersistenceManagerFilter;
import com.ventalandia.meli.callback.NotificationReceiverServlet;
import com.ventalandia.view.filter.WebappSecurityFilter;

/**
 * All IOC related with the View must be here.
 * 
 * @author matias
 * 
 */
public class VentalandiaServletModule extends JerseyServletModule {

    @Override
    protected void configureServlets() {
        
        // meli
        bind(MeliRedirectorServlet.class);//meli/redirect
        bind(AuthServlet.class);//meli/auth
        bind(NotificationReceiverServlet.class);//meli/notifications
        
        // Ventalandia
        bind(EchoServlet.class);
        bind(DevSupportServlet.class);

        // support
        filter("/").through(WebappSecurityFilter.class);
        filter("/api/*").through(ApiSecurityFilter.class);
        filter("/*").through(PersistenceManagerFilter.class);

        // api
        serve("/api/test").with(ApiServlet.class);
        bind(NewsApiServlet.class);
        
        // serve("/api/users/me").with(UserApiServlet.class);
        serve("/*").with(GuiceContainer.class);
        bind(UsersApiServlet.class);
        // serve("/api/users/*").with(UsersApiServlet.class);
    }

}
