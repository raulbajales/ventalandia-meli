package com.ventalandia.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.ventalandia.domain.Token;
import com.ventalandia.meli.api.auth.AuthToken;
import com.ventalandia.meli.api.notification.Notification;
import com.ventalandia.meli.api.notification.Question;
import com.ventalandia.meli.pesistence.QuestionRepository;
import com.ventalandia.meli.service.AuthContext;
import com.ventalandia.service.NotificationService;
import com.ventalandia.service.QuestionService;

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
	
	@Inject
	private QuestionService questionService;
	
	@Inject
	private QuestionRepository questionRepository;
	
	@Inject
	private Gson gson;

	@GET
	@Path("test")
	public String test() {
		Token authToken = AuthContext.getToken();

		long userId = 1234;
		List<Notification> notifications = notificationService.getUnreadQuestionsByUserId(userId);

		String result = null;
		
		if (notifications.isEmpty()) {
			result = "Usted no tiene notificaciones pendientes";
			
		} else {

			List<Question> questions = notificationService.getQuestionsFromMeli(notifications);

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
	
	@GET
    @Path("question/{userId}/{questionId}")
	public String getQuestion(@PathParam("userId") long userId, @PathParam("questionId") String questionId){
	    
	    
	    com.ventalandia.domain.Question question = questionService.getQuestionFromMeli(questionId, userId);    
	    
	    StringBuilder result = new StringBuilder();
	    
	    result.append("************ CLIENT *******************");
	    result.append("\n");
	    result.append("nickName: "+question.getClient().getNickName());
	    result.append("\n");
	    result.append("country: "+question.getClient().getCountry().getName());
	    result.append("\n");
	    result.append("\n");
	    
	    result.append("************ SELLER *******************");
	    result.append("\n");
	    result.append("nickName: "+question.getSeller().getNickName());
	    result.append("\n");
	    result.append("country: "+question.getSeller().getCountry().getName());
	    result.append("\n");
	    result.append("\n");
	    
	    result.append("************ ITEM *******************");
	    result.append("\n");
	    result.append("titulo: "+question.getItem().getTitle());
	    result.append("\n");
	    result.append("precio: "+question.getItem().getPrice());
	    result.append("\n");
	    result.append("vendidas: "+question.getItem().getSoldQuantity());
	    result.append("\n");
        result.append("cantidad inicial: "+question.getItem().getInitialQuantity());
        
        result.append("************ QUESTION *******************");
        result.append("\n");
        result.append("question: "+question.getText());
        result.append("\n");
        result.append("\n");
        
        result.append("************ ANSWER *******************");
        result.append("\n");
        result.append("answer: "+question.getAnswer().getText());
        result.append("\n");
        result.append("\n");
        
	    questionRepository.add(question);
        
	    return this.gson.toJson(question);
	}
	
	
	
}
