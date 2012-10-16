package com.ventalandia.meli.service;

import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.ventalandia.framework.http.HttpRequestBuilder;
import com.ventalandia.meli.api.auth.AuthToken;
import com.ventalandia.meli.api.auth.AuthorizationFailure;

/**
 * 
 * @author matias
 * 
 */
public class MeliServiceImpl extends AbstractMeliService implements MeliService {

    public AuthToken getAuthToken(String code) {
        HttpRequestBuilder httpRequestBuilder = createJsonPost().withPath("/oauth/token") //
                .addParam("grant_type", "authorization_code").addParam("client_id", String.valueOf(this.clientId)) //
                .addParam("client_secret", this.clientSecret).addParam("code", code).addParam("redirect_uri", this.callback);

        HTTPResponse httpResponse = this.execute(httpRequestBuilder);

        return this.parseToken(httpResponse, AuthToken.class);
    }

    private <T> T parseToken(HTTPResponse httpResponse, Class<T> clazz) {
        String responseBody = new String(httpResponse.getContent());
        JsonParser p = new JsonParser();
        JsonObject object;

        try {
            object = p.parse(responseBody).getAsJsonObject();
        }
        catch (JsonSyntaxException e) {
            throw new AuthorizationFailure(responseBody);
        }

        if (httpResponse.getResponseCode() == 200) {
            return this.gson.fromJson(object, clazz);
        }
        else {
            // Should I use a different exception?
            throw new AuthorizationFailure(object.get("message").getAsString());
        }
    }

    public AuthToken refreshAuthToken(String refreshToken) {
        HttpRequestBuilder httpRequestBuilder = this.createJsonPost().withPath("/oauth/token")//
                .addParam("grant_type", "refresh_token").addParam("client_id", String.valueOf(this.clientId))//
                .addParam("client_secret", this.clientSecret).addParam("refresh_token", refreshToken);

        HTTPResponse httpResponse = this.execute(httpRequestBuilder);

        return this.parseToken(httpResponse, AuthToken.class);
    }

}
