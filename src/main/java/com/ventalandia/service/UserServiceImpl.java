package com.ventalandia.service;

import com.google.inject.Inject;
import com.ventalandia.api.UserView;
import com.ventalandia.domain.User;
import com.ventalandia.domain.transformer.MeliUserToDomainTransformer;
import com.ventalandia.domain.transformer.UserTransformer;
import com.ventalandia.meli.api.user.MeliPublicUser;
import com.ventalandia.meli.api.user.MeliUser;
import com.ventalandia.meli.pesistence.UserRepository;
import com.ventalandia.meli.service.MeliUserContext;
import com.ventalandia.meli.service.UserMeliService;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private UserMeliService userMeliService;

    private UserTransformer userTransformer;

    private MeliUserToDomainTransformer meliUserToDomainTransformer;

    @Inject
    public UserServiceImpl(UserRepository userRepository, UserMeliService userMeliService, UserTransformer userTransformer, MeliUserToDomainTransformer meliUserToDomainTransformer) {
        this.userRepository = userRepository;
        this.userMeliService = userMeliService;
        this.userTransformer = userTransformer;
        this.meliUserToDomainTransformer = meliUserToDomainTransformer;
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

    public UserView getCurrent() {
        UserView user = new UserView();

        MeliUser meliUser = MeliUserContext.get();

        User persistedUser = this.userRepository.getByMeliId(meliUser.getId());

        if (persistedUser != null) {
            persistedUser.setEmail(meliUser.getEmail());
            persistedUser.setNickName(persistedUser.getNickName());

            this.userRepository.update(persistedUser);

            user.setTOS(persistedUser.isTOS());
        }
        else {
            user.setTOS(false);
        }

        user.setNickname(meliUser.getNickname());
        user.setMeliId(meliUser.getId());
        user.setName(meliUser.getFirst_name());
        user.setSurname(meliUser.getLast_name());
        user.setSellerReputationLevel(this.translateSellerReputationLevel(meliUser.getSeller_reputation().getLevel_id()));

        return user;
    }

    private int translateSellerReputationLevel(String level) {
        if (level == null) {
            return 0;
        }
        else if (level.startsWith("0")) {
            return 0;
        }
        else if (level.startsWith("1")) {
            return 1;
        }
        else if (level.startsWith("2")) {
            return 2;
        }
        else if (level.startsWith("3")) {
            return 3;
        }
        else if (level.startsWith("4")) {
            return 4;
        }
        else if (level.startsWith("5")) {
            return 5;
        }
        return 0;
    }

    @Override
    public void acceptTOS() {
        User user = this.userRepository.getByMeliId(MeliUserContext.get().getId());

        if (user == null) {
            user = this.meliUserToDomainTransformer.transform(MeliUserContext.get());
            user.setTOS(true);
            this.userRepository.add(user);
        }
        else {
            user.setTOS(true);
            this.userRepository.update(user);
        }
    }

}
