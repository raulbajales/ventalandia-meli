package com.ventalandia.domain.helper;

import java.util.Date;

import com.ventalandia.domain.User;

/**
 * 
 * @author matias
 *
 */
public class UserHelper {

    public static final int MELI_USER_ID = 1234;

    public static User create() {
        User user = new User();
        
        user.setCountry(CountryHelper.create());
        user.setMeliId(MELI_USER_ID);
        user.setNickName("mssulik");
        user.setRegistrationDate(new Date());
        
        return user;
    }

}
