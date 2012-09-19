package com.ventalandia.domain.transformer;

import com.ventalandia.meli.api.notification.Country;

public class CountryTransformer implements Transformer<Country, com.ventalandia.domain.Country>{

    @Override
    public com.ventalandia.domain.Country transform(Country in) {

        com.ventalandia.domain.Country country = new com.ventalandia.domain.Country();
        country.setName(in.getName());
        country.setMeliId(in.getId());
        return country;
    }

}
