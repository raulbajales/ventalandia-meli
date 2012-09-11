package com.ventalandia.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;
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
@Path("/echo")
@Produces(MediaType.TEXT_PLAIN)
public class EchoServlet {

	@Inject
	private NotificationService notificationService;

	@GET
	@Path("test")
	public String test() {

		AuthToken authToken = MeliAuthContext.getAuthToken();
		long userId = 1234;
		List<Notification> notifications = notificationService.getUnreadQuestionsByUserId(userId);

		String result = null;
		
		if (notifications.isEmpty()) {
			result = "Usted no tiene notificaciones pendientes";
			
		} else {

			List<Question> questions = notificationService.getQuestionsFromMeli(notifications, authToken);

			for (Question question : questions) {
				result =  "pregunta: " + question.getText() + "\n" + "respuesta: " + question.getAnswer().getText();
			}

		}
		return result;

	}
		
	@GET
	@Path("users/{userId}")
	public String getUserById(@PathParam("userId") Long userId) {
		
		return "El id de usuario es: "+userId;

	}
	
}
