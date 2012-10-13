package com.ventalandia.meli.callback;

import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.inject.Inject;
import com.ventalandia.meli.api.notification.MeliNotification;
import com.ventalandia.service.NotificationReceiverService;

/**
 * 
 * @author german
 * @author msulik
 * 
 */
@Path("/")
public class NotificationReceiverServlet {

    private static final Logger LOGGER = Logger.getLogger(NotificationReceiverServlet.class.getName());

    @Inject
    private NotificationReceiverService notificationReceiverService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response process(MeliNotification meliNotification) {
        try {
            LOGGER.info("Receiving ... " + meliNotification);
            this.notificationReceiverService.receive(meliNotification);
        }
        catch (Exception e) {
            LOGGER.severe(e.getMessage());
            return Response.serverError().build();
        }

        return Response.ok().build();
    }

}
