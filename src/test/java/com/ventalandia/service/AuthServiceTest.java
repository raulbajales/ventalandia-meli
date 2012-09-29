package com.ventalandia.service;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.google.inject.Inject;
import com.ventalandia.domain.Token;
import com.ventalandia.domain.User;
import com.ventalandia.domain.helper.TokenHelper;
import com.ventalandia.domain.helper.UserHelper;
import com.ventalandia.domain.meli.DomainTest;
import com.ventalandia.persistence.TokenRepository;
import com.ventalandia.service.AuthService;

/**
 * 
 * @author matias
 * 
 */
public class AuthServiceTest extends MeliDomainTest {

    @Inject
    private AuthService authService;

    @Inject
    private TokenRepository tokenRepository;

    @Test
    public void add() {
        this.tokenRepository.add(TokenHelper.create());

        User user = UserHelper.create();

        Token token = this.authService.getToken(user.getMeliId());

        assertEquals(user.getMeliId(), token.getMeliId());
    }

    @Test
    public void hash() throws Exception {
        Token token = TokenHelper.create();
        String hash = this.authService.addToken(token);

        Token tokenFromCache = this.authService.getToken(hash);

        assertEquals(token.getAccess_token(), tokenFromCache.getAccess_token());
        assertEquals(token.getRefresh_token(), tokenFromCache.getRefresh_token());
    }

    @Test(expected = RuntimeException.class)
    public void offline_failWhenUserIdIsNotCorrect() {
        int userId = UserHelper.MELI_USER_ID;
        this.authService.getToken(userId);
    }

    @Test
    public void offline() throws Exception {
        Token token = TokenHelper.create();
        this.authService.addToken(token);

        this.authService.generateOfflineToken(token.getMeliId());
    }

}
