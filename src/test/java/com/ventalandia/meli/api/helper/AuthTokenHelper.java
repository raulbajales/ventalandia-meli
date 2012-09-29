package com.ventalandia.meli.api.helper;

import com.ventalandia.meli.api.auth.AuthToken;

/**
 * 
 * @author matias
 * 
 */
public class AuthTokenHelper {

    public static AuthToken create() {
        AuthToken authToken = new AuthToken();

        authToken.setAccess_token("APP_USR-10601-091601-36ff0e0905ab3fd075a73a2de5cbda10-90661434");
        authToken.setExpires_in(10800L);
        authToken.setRefresh_token("TG-50556250e4b0923ae3046d62");
        authToken.setScope("offline_access+read+write");
        authToken.setToken_type("bearer");

        return authToken;
    }
    
    public static AuthToken createRefreshed() {
        AuthToken authToken = new AuthToken();

        authToken.setAccess_token("APP_USR-10601-091601-36ff0e0905ab3fd075a73a2de5cbda10-90661434");
        authToken.setExpires_in(10800L);
        authToken.setRefresh_token("TG-50556250e4b0923ae3046d62");
        authToken.setScope("offline_access+read+write");
        authToken.setToken_type("bearer");

        return authToken;
    }

}
