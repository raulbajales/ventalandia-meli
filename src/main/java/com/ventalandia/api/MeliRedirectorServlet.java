package com.ventalandia.api;

import java.net.URI;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.google.inject.Inject;
import com.ventalandia.meli.ioc.MeliCallbackUrlApi;
import com.ventalandia.meli.ioc.MeliClientIdApi;

/**
 * 
 * @author matias, german
 * 
 */
@Path("/meli/redirect")
public class MeliRedirectorServlet {

    private Logger logger = Logger.getLogger(MeliRedirectorServlet.class.getName());
    private Integer clientId;
    private String callback;

    @Inject
    public MeliRedirectorServlet(@MeliClientIdApi Integer clientId, @MeliCallbackUrlApi String callback) {
        this.clientId = clientId;
        this.callback = callback;

    }

    @GET
    public Response doGet(@Context HttpServletResponse currentResponse){
        
        String url = "https://auth.mercadolibre.com.ar/authorization";
        URI seeOther = UriBuilder.fromUri(url)
                        .queryParam("response_type", "code")
                        .queryParam("client_id", clientId)
                        .queryParam("redirect_uri", callback).build();
        
        logger.info("Redirecting to: "+seeOther.toString());
                
        return Response.seeOther(seeOther).build(); 
        

    }
   
}