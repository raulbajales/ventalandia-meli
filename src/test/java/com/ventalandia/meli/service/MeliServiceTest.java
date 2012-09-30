package com.ventalandia.meli.service;

import org.junit.Assert;
import org.junit.Test;

import com.google.inject.Inject;
import com.ventalandia.framework.http.MeliException;
import com.ventalandia.meli.api.auth.AuthToken;
import com.ventalandia.meli.api.helper.AuthTokenHelper;
import com.ventalandia.service.MeliDomainTest;

/**
 * 
 * @author matias
 * 
 */
public class MeliServiceTest extends MeliDomainTest {

    @Inject
    private MeliService meliService;

    @Test
    public void test() {
        AuthToken authToken1 = AuthTokenHelper.create();
        AuthToken authToken2 = this.meliService.refreshAuthToken(authToken1.getRefresh_token());

        Assert.assertFalse(authToken1.getAccess_token().equals(authToken2.getAccess_token()));
    }

    @Test(expected = MeliException.class)
    public void test_failWithInvalidRefreshToken() {
        this.meliService.refreshAuthToken("this is fruit");
    }

}
