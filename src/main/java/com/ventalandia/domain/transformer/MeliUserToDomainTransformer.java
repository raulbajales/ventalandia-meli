package com.ventalandia.domain.transformer;

import com.google.inject.Inject;
import com.ventalandia.domain.Country;
import com.ventalandia.domain.User;
import com.ventalandia.meli.api.user.MeliUser;
import com.ventalandia.service.CountryService;

/**
 * 
 * @author msulik
 * 
 */
public class MeliUserToDomainTransformer implements Transformer<MeliUser, User> {

    @Inject
    private CountryService countryService;

    @Override
    public User transform(MeliUser meliUser) {
        Country country = countryService.getByMeliId(meliUser.getCountry_id());

        User domainUser = new User();

        domainUser.setMeliId(meliUser.getId());
        domainUser.setNickName(meliUser.getNickname());
        domainUser.setRegistrationDate(meliUser.getRegistration_date());
        domainUser.setCountry(country);
        domainUser.setPictureUrl(meliUser.getLogo());
        domainUser.setEmail(meliUser.getEmail());

        return domainUser;
    }

}
