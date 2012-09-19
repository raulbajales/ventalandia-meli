package com.ventalandia.service;

import com.google.inject.Inject;
import com.ventalandia.domain.User;
import com.ventalandia.domain.transformer.UserTransformer;
import com.ventalandia.meli.pesistence.UserRepository;
import com.ventalandia.meli.service.MeliService;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private MeliService meliService;
    private UserTransformer userTransformer;

    @Inject
    public UserServiceImpl(UserRepository userRepository, MeliService meliService, UserTransformer userTransformer) {
        this.userRepository = userRepository;
        this.meliService = meliService;
        this.userTransformer = userTransformer;
    }


    @Override
    public User getByMeliId(long seller_id) {

        User user = userRepository.getByMeliId(seller_id);
        
        if (user == null) {
            com.ventalandia.meli.api.notification.User userMeli = meliService.getEntityFromMELI("/users/"+seller_id, com.ventalandia.meli.api.notification.User.class);
            user = userTransformer.transform(userMeli);
        }

        return user;
    }

}
