package com.ventalandia.service;

import com.google.inject.Inject;
import com.ventalandia.domain.User;
import com.ventalandia.domain.transformer.UserTransformer;
import com.ventalandia.meli.api.user.MeliPublicUser;
import com.ventalandia.meli.pesistence.UserRepository;
import com.ventalandia.meli.service.UserMeliService;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private UserMeliService userMeliService;

    private UserTransformer userTransformer;

    @Inject
    public UserServiceImpl(UserRepository userRepository, UserMeliService userMeliService, UserTransformer userTransformer) {
        this.userRepository = userRepository;
        this.userMeliService = userMeliService;
        this.userTransformer = userTransformer;
    }

    public User getByMeliId(long userId) {
        User user = userRepository.getByMeliId(userId);

        if (user == null) {
            MeliPublicUser publicUser = this.userMeliService.getPulicUser(userId);
            user = userTransformer.transform(publicUser);
            userRepository.add(user);
        }

        return user;
    }

}
