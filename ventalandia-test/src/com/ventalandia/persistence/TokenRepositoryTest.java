package com.ventalandia.persistence;

import junit.framework.Assert;

import org.junit.Test;

import com.google.inject.Inject;
import com.ventalandia.domain.Token;
import com.ventalandia.domain.helper.TokenHelper;
import com.ventalandia.domain.meli.DomainTest;

/**
 * 
 * @author matias
 * 
 */
public class TokenRepositoryTest extends DomainTest {

    @Inject
    private TokenRepository tokenRepository;

    @Test
    public void add() {
        Token token = TokenHelper.create();

        Assert.assertNull(token.getKey());
        this.tokenRepository.add(token);
        Assert.assertNotNull(token.getKey());
    }

    @Test(expected = RuntimeException.class)
    public void add_without_meliId() {
        Token token = TokenHelper.create();
        token.setMeliId(0);
        this.tokenRepository.add(token);
    }

}
