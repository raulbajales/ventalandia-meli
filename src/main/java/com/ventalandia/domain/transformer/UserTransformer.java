package com.ventalandia.domain.transformer;

import com.google.inject.Inject;
import com.ventalandia.domain.Country;
import com.ventalandia.domain.User;
import com.ventalandia.meli.api.user.MeliPublicUser;
import com.ventalandia.service.CountryService;

public class UserTransformer implements Transformer<MeliPublicUser, User> {

    private CountryService countryService;

    @Inject
    public UserTransformer(CountryService countryService) {
        this.countryService = countryService;
    }

    public User transform(MeliPublicUser user) {

        Country country = countryService.getByMeliId(user.getCountry_id());

        User result = new User();

        result.setMeliId(user.getId());
        result.setNickName(user.getNickname());
        result.setRegistrationDate(user.getRegistration_date());
        result.setCountry(country);
        result.setPictureUrl(user.getLogo());

        return result;
    }

}
