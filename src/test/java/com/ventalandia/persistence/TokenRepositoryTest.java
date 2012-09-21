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

        Token persistedToken = this.tokenRepository.getByMeliUserId(token.getMeliId());

        Assert.assertEquals(token.getKey(), persistedToken.getKey());
    }

    @Test
    public void update() {
        this.tokenRepository.add(TokenHelper.create());
        this.tokenRepository.update(TokenHelper.create());
        
        this.tokenRepository.getByMeliUserId(TokenHelper.create().getMeliId());
    }

    @Test(expected = RuntimeException.class)
    public void add_without_meliId() {
        Token token = TokenHelper.create();
        token.setMeliId(0);
        this.tokenRepository.add(token);
    }

}
