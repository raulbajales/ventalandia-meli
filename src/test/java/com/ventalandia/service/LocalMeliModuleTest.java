package com.ventalandia.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.google.inject.AbstractModule;
import com.ventalandia.meli.api.helper.AuthTokenHelper;
import com.ventalandia.meli.api.helper.MeliPublicUserHelper;
import com.ventalandia.meli.api.helper.MeliUserHelper;
import com.ventalandia.meli.service.MeliService;
import com.ventalandia.meli.service.UserMeliService;

/**
 * 
 * @author msulik
 * 
 */
public class LocalMeliModuleTest extends AbstractModule {

    @Override
    protected void configure() {
        this.bind(MeliService.class).toInstance(this.createMeliServiceMock());
        this.bind(UserMeliService.class).toInstance(this.createUserMeliServiceMock());
    }

    private UserMeliService createUserMeliServiceMock() {
        UserMeliService userMeliService = mock(UserMeliService.class);
        
        when(userMeliService.getCurrentUser()).thenReturn(MeliUserHelper.create());
        when(userMeliService.getPulicUser(123)).thenReturn(MeliPublicUserHelper.create());
        
        return userMeliService;
    }

    private MeliService createMeliServiceMock() {
        MeliService meliService = mock(MeliService.class);

        when(meliService.getAuthToken("BLA")).thenReturn(AuthTokenHelper.create());
        when(meliService.refreshAuthToken(AuthTokenHelper.create().getRefresh_token())).thenReturn(AuthTokenHelper.create());

        return meliService;
    }
}
