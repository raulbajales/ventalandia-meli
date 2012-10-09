package com.ventalandia.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.google.inject.AbstractModule;
import com.ventalandia.domain.helper.QuestionHelper;
import com.ventalandia.framework.http.MeliException;
import com.ventalandia.meli.api.helper.AuthTokenHelper;
import com.ventalandia.meli.api.helper.MeliPublicUserHelper;
import com.ventalandia.meli.api.helper.MeliUserHelper;
import com.ventalandia.meli.api.user.MeliUser;
import com.ventalandia.meli.ioc.MeliCallbackUrlApi;
import com.ventalandia.meli.ioc.MeliClientIdApi;
import com.ventalandia.meli.ioc.MeliClientSecretApi;
import com.ventalandia.meli.ioc.MeliUrlApi;
import com.ventalandia.meli.service.MeliQuestionService;
import com.ventalandia.meli.service.MeliService;
import com.ventalandia.meli.service.UserMeliService;

/**
 * 
 * @author msulik
 * 
 */
public class LocalMeliModuleTest extends AbstractModule {

    private static String apiUrl = "https://api.mercadolibre.com";

    private static final int clientId = 10601;

    private static final String clientSecret = "aYWPAudc48nQNEj9ZTVCxftGU36DJoh8";

    private static final String callback = "http://development.ventalandia-meli.appspot.com/meli/auth";

    @Override
    protected void configure() {
        // setup
        this.bind(String.class).annotatedWith(MeliUrlApi.class).toInstance(apiUrl);
        this.bind(String.class).annotatedWith(MeliClientSecretApi.class).toInstance(clientSecret);
        this.bind(Integer.class).annotatedWith(MeliClientIdApi.class).toInstance(clientId);
        this.bind(String.class).annotatedWith(MeliCallbackUrlApi.class).toInstance(callback);

        this.bind(MeliService.class).toInstance(this.createMeliServiceMock());
        this.bind(UserMeliService.class).toInstance(this.createUserMeliServiceMock());
        this.bind(MeliQuestionService.class).toInstance(this.createMeliQuestionService());
    }

    private MeliQuestionService createMeliQuestionService() {
        MeliQuestionService meliQuestionService = mock(MeliQuestionService.class);

        when(meliQuestionService.getQuestionByResource("/questions/2455498075")).thenReturn(QuestionHelper.create());

        return meliQuestionService;
    }

    private UserMeliService createUserMeliServiceMock() {
        UserMeliService userMeliService = mock(UserMeliService.class);

        MeliUser currentUser = MeliUserHelper.create();
        when(userMeliService.getCurrentUser()).thenReturn(currentUser);
        when(userMeliService.getPulicUser(MeliPublicUserHelper.USER_ID)).thenReturn(MeliPublicUserHelper.create());

        return userMeliService;
    }

    private MeliService createMeliServiceMock() {
        MeliService meliService = mock(MeliService.class);

        when(meliService.getAuthToken(AuthTokenHelper.CODE)).thenReturn(AuthTokenHelper.create());
        when(meliService.refreshAuthToken(AuthTokenHelper.create().getRefresh_token())).thenReturn(AuthTokenHelper.createRefreshed());
        when(meliService.refreshAuthToken("this is fruit")).thenThrow(new MeliException("Wrong token - this is test"));

        return meliService;
    }

}
