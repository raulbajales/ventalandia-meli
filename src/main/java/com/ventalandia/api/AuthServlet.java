package com.ventalandia.api;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import com.google.inject.Inject;
import com.sun.jersey.api.view.Viewable;
import com.ventalandia.service.AuthService;
import com.ventalandia.view.filter.WebappSecurityFilter;

/**
 * 
 * @author matias, german
 * 
 */
@Path("/meli/auth")
public class AuthServlet {

    private static final Logger logger = Logger.getLogger(AuthServlet.class.getName());

    private AuthService authService;

    @Inject
    public AuthServlet(AuthService authService) {
        this.authService = authService;
    }

    @GET
    @Produces("text/html")
    public Response get(@QueryParam("error")
    String error, @QueryParam("code")
    String code, @QueryParam("error_description")
    String error_description) {

        if (error != null) {
            logger.severe("There was an issue when you try to login: " + error_description);
            return Response.serverError().build();
        }
        else {
            logger.info("Code from MELI: " + code);
            String hash = this.authService.generateToken(code);
            logger.info("Generated hash: " + hash);
            // FIXME: Set expires properly (a week/month after today?)
            return Response.ok(new Viewable("/"))
                    .cookie(new NewCookie(WebappSecurityFilter.VTD_TOKEN, hash + ";Path=/;expires=Sat, 02 May 2029 23:38:25 GMT;"))
                    .build();
        }
    }
}