package com.ventalandia.domain.helper;

import com.ventalandia.domain.Token;

/**
 * 
 * @author msulik
 * 
 */
public class TokenHelper {

    public static Token create() {
        Token token = new Token();

        token.setAccess_token("APP_USR-10601-091601-36ff0e0905ab3fd075a73a2de5cbda10-90661434");
        token.setExpires_in(10800L);
        token.setRefresh_token("TG-50556250e4b0923ae3046d62");
        token.setMeliId(UserHelper.MELI_USER_ID);

        return token;
    }

}
