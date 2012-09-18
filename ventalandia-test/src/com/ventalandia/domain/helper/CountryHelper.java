package com.ventalandia.domain.helper;

import com.ventalandia.domain.Country;

/**
 * 
 * @author matias
 * 
 */
public class CountryHelper {

    public static Country create() {
        Country country = new Country();
        
        country.setLocale("AR");
        country.setMeliId("MLA");
        country.setName("Argentina");
        
        // TODO add country
        
        return country;
    }

}
