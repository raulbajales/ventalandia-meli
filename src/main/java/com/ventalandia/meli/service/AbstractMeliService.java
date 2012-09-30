package com.ventalandia.meli.service;

import java.io.IOException;

import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.gson.Gson;
import com.google.inject.Inject;
import com.ventalandia.framework.http.HttpConnector;
import com.ventalandia.framework.http.HttpRequestBuilder;
import com.ventalandia.meli.ioc.MeliCallbackUrlApi;
import com.ventalandia.meli.ioc.MeliClientIdApi;
import com.ventalandia.meli.ioc.MeliClientSecretApi;
import com.ventalandia.service.AuthService;

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

    @Inject
    private AuthService authService;

    @Inject
    protected URLFetchService urlFetchService;

    /**
     * Execute an HTTP call. If the call is secured (it has an access token
     * associated) the call has the possibility of recovery by refreshing the
     * token.
     * 
     * @param httpRequestBuilder
     * @return a response from the remote end point.
     */
    protected HTTPResponse execute(HttpRequestBuilder httpRequestBuilder) {
        HTTPRequest httpRequest = httpRequestBuilder.build();

        try {
            HTTPResponse httpResponse = this.urlFetchService.fetch(httpRequest);

//            if (httpResponse.getResponseCode() == 404 && httpRequestBuilder.containsParam("access_token")) {
//                this.authService.refreshToken();
//                httpRequest = httpRequestBuilder.replaceParam("access_token", AuthContext.getToken().getRefresh_token()).build();
//                httpResponse = this.urlFetchService.fetch(httpRequest);
//            }

            return httpResponse;
        }
        catch (IOException e) {
            throw new RuntimeException("Error executing an HTTP call: " + httpRequest.getURL(), e);
        }
    }

    protected HttpRequestBuilder createJsonPost() {
        return createJson().post();
    }

    protected HttpRequestBuilder createJsonGet() {
        return createJson().get();
    }

    protected HttpRequestBuilder createJson() {
        return new HttpRequestBuilder(MeliService.apiUrl).acceptJson();
    }

    protected HttpRequestBuilder createJsonPut() {
        return createJson().put();
    }

    protected HttpRequestBuilder createJsonDelete() {
        return createJson().delete();
    }

    protected HttpRequestBuilder createJsonHead() {
        return createJson().head();
    }

    protected <T> T parseEntity(HTTPResponse httpResponse, Class<T> entity) {
        if (httpResponse.getResponseCode() == 200) {
            String json = new String(httpResponse.getContent());
            return gson.fromJson(json, entity);
        }
        else {
            throw new RuntimeException("Errors when getting the Entity " + entity.getName() + " from MELI.");
        }
    }

}
