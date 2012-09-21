package com.ventalandia.meli.service;

import org.junit.Assert;
import org.junit.Test;

import com.google.inject.Inject;
import com.ventalandia.domain.meli.DomainTest;
import com.ventalandia.framework.http.MeliException;
import com.ventalandia.meli.api.auth.AuthToken;
import com.ventalandia.meli.api.helper.AuthTokenHelper;

/**
 * 
 * @author matias
 * 
 */
public class MeliServiceTest extends DomainTest {

    @Inject
    private MeliService meliService;

    // it will fail, we should have some cool thing to get tokens from MELI
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
