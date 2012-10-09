package com.ventalandia.meli.callback;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.ventalandia.api.ApiError;
import com.ventalandia.api.ApiServlet;
import com.ventalandia.meli.api.notification.MeliNotification;
import com.ventalandia.service.NotificationReceiverService;

/**
 * 
 * @author msulik
 * 
 */
@Singleton
public class NotificationReceiverServlet extends ApiServlet {

    private static final long serialVersionUID = 5884900221232357136L;

    private static final Logger LOGGER = Logger.getLogger(NotificationReceiverServlet.class.getName());

    private static final String EMPTY_STRING = "";

    @Inject
    private NotificationReceiverService notificationReceiverService;

    @Override
    protected Object post(HttpServletRequest req, HttpServletResponse resp) {
        return this.process(req, resp);
    }

    public Object process(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.fine("Running Notification Receiver Servlet.");
        try {
            String json = this.getRequestBody(req);
            MeliNotification meliNotification = this.gson.fromJson(json, MeliNotification.class);
            this.notificationReceiverService.receive(meliNotification);
        }
        catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            LOGGER.severe(e.getMessage());
            return new ApiError(e.getMessage());
        }
        resp.setStatus(HttpServletResponse.SC_OK);

        return EMPTY_STRING;
    }

}
