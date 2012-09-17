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

        token.setAccess_token("APP.asdfasdf");
        token.setExpires_in(123L);
        token.setMeliId(123);
        token.setRefresh_token("apaodsfj");

        return token;
    }

}
