package com.ventalandia.domain.transformer;

import junit.framework.Assert;

import org.junit.Test;

import com.ventalandia.domain.Token;
import com.ventalandia.meli.api.auth.AuthToken;
import com.ventalandia.meli.api.helper.AuthTokenHelper;

/**
 * 
 * @author matias
 * 
 */
public class TokenTransformerTest {

    @Test
    public void test() {
        TokenTransformer transformer = new TokenTransformer();

        AuthToken authToken = AuthTokenHelper.create();
        Token token = transformer.transform(authToken);

        Assert.assertEquals(authToken.getAccess_token(), token.getAccess_token());
        Assert.assertEquals(authToken.getExpires_in(), token.getExpires_in());
        Assert.assertEquals(authToken.getRefresh_token(), token.getRefresh_token());
    }

}
