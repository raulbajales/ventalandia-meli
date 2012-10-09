package com.ventalandia.domain.meli.user;

import org.junit.Assert;
import org.junit.Test;

import com.google.inject.Inject;
import com.ventalandia.domain.Token;
import com.ventalandia.domain.helper.TokenHelper;
import com.ventalandia.meli.api.user.MeliUser;
import com.ventalandia.meli.service.AuthContext;
import com.ventalandia.meli.service.UserMeliService;
import com.ventalandia.service.MeliDomainTest;

/**
 * 
 * @author matias
 * 
 */
public class MeliUserServiceTest extends MeliDomainTest {

    @Inject
    private UserMeliService userMeliService;

    @Test
    public void test() throws Exception {
//        Token token = TokenHelper.create();
//
//        AuthContext.setAuthToken(token);
//        MeliUser user = this.userMeliService.getCurrentUser();
//
//        Assert.assertEquals("MSSULIK", user.getNickname());
    }

}
