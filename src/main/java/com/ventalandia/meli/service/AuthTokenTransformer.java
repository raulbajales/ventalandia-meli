package com.ventalandia.meli.service;

import com.ventalandia.domain.Token;
import com.ventalandia.domain.transformer.Transformer;
import com.ventalandia.meli.api.auth.AuthToken;

/**
 * 
 * @author matias
 * 
 */
public class AuthTokenTransformer implements Transformer<Token, AuthToken> {

    public AuthToken transform(Token token) {
        AuthToken authToken = new AuthToken();

        authToken.setAccess_token(token.getAccess_token());
        authToken.setRefresh_token(token.getRefresh_token());
        authToken.setExpires_in(token.getExpires_in());

        return authToken;
    }

}
