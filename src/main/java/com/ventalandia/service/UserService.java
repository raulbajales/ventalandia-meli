package com.ventalandia.service;

import com.ventalandia.api.UserView;
import com.ventalandia.domain.User;

/**
 * 
 * @author matias
 * @author german
 *
 */
public interface UserService {

    User getByMeliId(long userId);

    UserView getCurrent();

    /**
     * Accept the Term conditions for the web site.
     */
    void acceptTOS();

}
