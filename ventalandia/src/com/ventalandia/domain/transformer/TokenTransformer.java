package com.ventalandia.domain.transformer;

import com.ventalandia.domain.Token;
import com.ventalandia.meli.api.auth.AuthToken;

/**
 * 
 * @author msulik
 * 
 */
public class TokenTransformer implements Transformer<AuthToken, Token> {

    @Override
    public Token transform(AuthToken authToken) {
        Token token = new Token();

        token.setAccess_token(authToken.getAccess_token());
        token.setExpires_in(authToken.getExpires_in());
        token.setRefresh_token(authToken.getRefresh_token());

        return token;
    }

}
