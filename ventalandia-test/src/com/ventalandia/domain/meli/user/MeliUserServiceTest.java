package com.ventalandia.domain.meli.user;

import org.junit.Assert;
import org.junit.Test;

import com.google.inject.Inject;
import com.ventalandia.domain.meli.AbstractMeliTest;
import com.ventalandia.meli.api.auth.AuthToken;
import com.ventalandia.meli.api.helper.AuthTokenHelper;
import com.ventalandia.meli.api.user.MeliUser;
import com.ventalandia.meli.service.MeliAuthContext;
import com.ventalandia.meli.service.UserMeliService;

/**
 * 
 * @author matias
 * 
 */
public class MeliUserServiceTest extends AbstractMeliTest {

    @Inject
    private UserMeliService userMeliService;

    @Test
    public void test() throws Exception {
        AuthToken authToken = AuthTokenHelper.create();

        MeliAuthContext.setAuthToken(authToken);
        MeliUser user = this.userMeliService.getCurrentUser();

        Assert.assertEquals("test", user.getNickname());
    }

}
