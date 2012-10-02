package com.ventalandia.meli.callback;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.ventalandia.api.ApiServlet;
import com.ventalandia.service.NotificationService;

/**
 * 
 * @author matias
 * @author german
 * 
 */
@Singleton
public class NotificationApiServlet extends ApiServlet {

    private static final long serialVersionUID = -7582121992545851199L;
    private static final String EMPTY_STRING = "";
    private static final Logger LOGGER = Logger.getLogger(NotificationApiServlet.class.getName());

    private NotificationService notificationService;

    @Inject
    public NotificationApiServlet(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

    @GET
	@Path("/meli/notifications")
	@Produces(MediaType.TEXT_PLAIN)
	public String process(HttpServletRequest req, HttpServletResponse resp) {
    	
        LOGGER.fine("Running Notification Api Servlet.");
        try {
            String requestBody = getRequestBody(req);
            notificationService.processRequest(requestBody);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            LOGGER.severe(e.getMessage());
            return e.getMessage();
        }
        resp.setStatus(HttpServletResponse.SC_OK);

        return EMPTY_STRING;
    }

    @Override
    protected Object post(HttpServletRequest req, HttpServletResponse resp) {
        return this.process(req, resp);
    }

}
