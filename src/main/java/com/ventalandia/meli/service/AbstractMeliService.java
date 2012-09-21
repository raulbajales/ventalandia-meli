package com.ventalandia.meli.service;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.ventalandia.framework.http.HttpConnector;
import com.ventalandia.meli.ioc.MeliCallbackUrlApi;
import com.ventalandia.meli.ioc.MeliClientIdApi;
import com.ventalandia.meli.ioc.MeliClientSecretApi;

/**
 * 
 * @author matias
 * 
 */
public class AbstractMeliService {

    @Inject
    @MeliClientSecretApi
    protected String clientSecret;

    @Inject
    @MeliClientIdApi
    protected Integer clientId;

    @Inject
    @MeliCallbackUrlApi
    protected String callback;

    @Inject
    protected Gson gson;

    @Inject
    protected HttpConnector http;

}
