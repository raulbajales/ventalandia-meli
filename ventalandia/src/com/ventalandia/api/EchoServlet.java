package com.ventalandia.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.ventalandia.meli.api.auth.AuthToken;
import com.ventalandia.meli.api.notification.Notification;
import com.ventalandia.meli.api.notification.Question;
import com.ventalandia.meli.service.MeliAuthContext;
import com.ventalandia.service.NotificationService;

/**
 * 
 * @author matias
 * 
 */
@Singleton
public class EchoServlet extends HttpServlet {

	private static final long serialVersionUID = 3750062946924357873L;

	@Inject
	private NotificationService notificationService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/plain");
		AuthToken authToken = MeliAuthContext.getAuthToken();
		long userId = 1234;
		List<Notification> notifications = notificationService.getUnreadQuestionsByUserId(userId);

		PrintWriter writer = resp.getWriter();
		if (notifications.isEmpty()) {
			writer.print("Usted no tiene notificaciones pendientes");
		} else {

			Question question = notificationService.getQuestionFromMeli(notifications.get(0), authToken);

			writer.println("pregunta: " + question.getText());
			writer.println("respuesta: " + question.getAnswer().getText());

		}

	}

}
