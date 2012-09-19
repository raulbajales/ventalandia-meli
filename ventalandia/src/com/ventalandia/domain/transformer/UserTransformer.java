package com.ventalandia.domain.transformer;

import com.ventalandia.domain.Country;
import com.ventalandia.meli.api.notification.User;
import com.ventalandia.service.CountryService;

public class UserTransformer implements Transformer<User, com.ventalandia.domain.User>{

    private CountryService countryService;
    
    public UserTransformer(CountryService countryService) {
        super();
        this.countryService = countryService;
    }

    @Override
    public com.ventalandia.domain.User transform(User user) {

        Country country = countryService.getByMeliId(user.getCountry_id());
        
        com.ventalandia.domain.User result = new com.ventalandia.domain.User();
        
        result.setMeliId(user.getId());
        result.setNickName(user.getNickname());
        result.setRegistrationDate(user.getRegistration_date());
        result.setCountry(country);
        
        return result;
    }

}
