package com.ventalandia.api;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.gson.Gson;
import com.google.inject.Inject;
import com.ventalandia.domain.Token;
import com.ventalandia.framework.http.HttpRequestBuilder;
import com.ventalandia.meli.service.MeliService;
import com.ventalandia.service.AuthService;
import com.ventalandia.view.filter.WebappSecurityFilter;

/**
 * 
 * @author msulik
 * 
 */
// TODO REMOVE before go to prod
@Path("/5upp0rt")
public class DevSupportServlet {

    private static final String DEVELOPMENT = "http://development.ventalandia-meli.appspot.com/";

    @Inject
    private AuthService authService;

    @Inject
    private Gson gson;

    @Inject
    private URLFetchService urlFetchService;

    @GET
    @Path("remote/tokens/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getNews(@PathParam("userId")
    Long userId) {
        return this.gson.toJson(this.authService.getToken(userId));
    }

    @GET
    @Path("local/fakelogin/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String fakelogin(@PathParam("userId")
    Long userId, HttpServletResponse httpServletResponse) throws IOException {
        HttpRequestBuilder builder = new HttpRequestBuilder(DEVELOPMENT).acceptJson();
        builder.withPath("remote/tokens/" + userId);

        HTTPResponse response = this.urlFetchService.fetch(builder.build());

        Token token = this.gson.fromJson(new String(response.getContent()), Token.class);
        String hash = this.authService.addToken(token);

        Cookie cookie = new Cookie(WebappSecurityFilter.VTD_TOKEN, hash);
        cookie.setPath("/");
        cookie.setMaxAge(Integer.MAX_VALUE);
        httpServletResponse.addCookie(cookie);

        return this.gson.toJson(this.authService.getToken(userId));
    }

}
