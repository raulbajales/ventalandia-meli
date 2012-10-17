package com.ventalandia.api;

import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.google.inject.Inject;
import com.ventalandia.service.AuthService;
import com.ventalandia.view.filter.WebappSecurityFilter;

/**
 * 
 * @author matias
 * @author german
 * 
 */
@Path("/meli/auth")
public class AuthServlet {

    private static final Logger LOGGER = Logger.getLogger(AuthServlet.class.getName());

    private AuthService authService;

    @Inject
    public AuthServlet(AuthService authService) {
        this.authService = authService;
    }

    @GET
    @Produces("text/html")
    public Response get(@QueryParam("error") String error, @QueryParam("code") String code, @QueryParam("error_description") String error_description) {
        if (error != null) {
            LOGGER.severe("There was an issue when you try to login: " + error_description);
            return Response.serverError().build();
        }
        else {
            LOGGER.info("Code from MELI: " + code);
            String hash = this.authService.generateToken(code);
            LOGGER.info("Generated hash: " + hash);
            // FIXME: Set expires properly (a week/month after today?)
            NewCookie newCookie = new NewCookie(WebappSecurityFilter.VTD_TOKEN, hash,"/","","",10000,false);
            return Response.seeOther(UriBuilder.fromUri("/").build()).cookie(newCookie).build();
        }
    }
}
