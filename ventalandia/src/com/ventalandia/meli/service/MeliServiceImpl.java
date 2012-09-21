package com.ventalandia.meli.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.ventalandia.domain.Token;
import com.ventalandia.framework.http.FluentStringsMap;
import com.ventalandia.framework.http.HttpResponse;
import com.ventalandia.meli.api.auth.AuthToken;
import com.ventalandia.meli.api.auth.AuthorizationFailure;

/**
 * 
 * @author matias
 * 
 */
public class MeliServiceImpl extends AbstractMeliService implements MeliService {

    private AuthTokenTransformer authTokenTransformer = new AuthTokenTransformer();

    @Override
    public AuthToken getAuthToken(String code) {
        FluentStringsMap params = new FluentStringsMap();

        params.add("grant_type", "authorization_code");
        params.add("client_id", String.valueOf(this.clientId));
        params.add("client_secret", this.clientSecret);
        params.add("code", code);
        params.add("redirect_uri", this.callback);

        HttpResponse response = http.post("/oauth/token", params);

        return this.parseToken(response);
    }

    @Override
    public AuthToken refreshAuthToken(String refreshToken) {
        FluentStringsMap params = new FluentStringsMap();

        params.add("grant_type", "refresh_token");
        params.add("client_id", String.valueOf(this.clientId));
        params.add("client_secret", this.clientSecret);
        params.add("refresh_token", refreshToken);

        HttpResponse response = http.post("/oauth/token", params);

        return this.parseToken(response);
    }

    private AuthToken parseToken(HttpResponse response) throws AuthorizationFailure {
        String responseBody = response.getResponseMessage();
        JsonParser p = new JsonParser();
        JsonObject object;

        try {
            object = p.parse(responseBody).getAsJsonObject();
        }
        catch (JsonSyntaxException e) {
            throw new AuthorizationFailure(responseBody);
        }

        if (response.getResponseCode() == 200) {
            return this.gson.fromJson(object, AuthToken.class);
        }
        else {
            throw new AuthorizationFailure(object.get("message").getAsString());
        }
    }

    @Override
    public boolean validate(AuthToken authToken) {
        // TODO validate token
        return true;
    }

    @Override
    public boolean validate(Token token) {
        return this.validate(this.authTokenTransformer.transform(token));
    }

    /**
     * 
     * @param resource
     * @param clazz
     * @return
     */
    @Override
    public <T> T getEntityFromMELI(String resource, Class<T> clazz) {
        HttpResponse httpResponse = http.get(resource);

        if (httpResponse.getResponseCode() == 200) {
            String json = httpResponse.getResponseMessage();
            return gson.fromJson(json, clazz);
        } else {
            throw new RuntimeException("problems to get Entity " + clazz.getName() + " from MELI");
        }
    }

}
