package com.ventalandia.api;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.ventalandia.domain.Question;
import com.ventalandia.meli.api.notification.Notification;
import com.ventalandia.meli.service.AuthContext;
import com.ventalandia.service.NotificationService;
import com.ventalandia.service.QuestionService;
import com.ventalandia.view.domain.NewsView;

/**
 * 
 * @author matias
 * 
 */
@Path("/echo")
public class EchoServlet {

	@Inject
	private NotificationService notificationService;

	@Inject
	private QuestionService questionService;

	@Inject
	private Gson gson;
	
	private static final Logger logger = Logger.getLogger(EchoServlet.class.getName());

	@GET
	@Path("test")
	@Produces(MediaType.TEXT_PLAIN)
	public String test() {
		
		List<Notification> notifications = notificationService.getUnreadQuestions();

		String result = null;

		if (notifications.isEmpty()) {
			result = "No existen notificaciones pendientes";

		} else {

			List<com.ventalandia.meli.api.notification.Question> questions = notificationService.getQuestionsFromMeli(notifications);
			if(!questions.isEmpty()){
				result = gson.toJson(questions);
			}

		}
		return result;

	}

	@GET
	@Path("questions")
	@Produces(MediaType.APPLICATION_JSON)
	public String getQuestions() {

		long meliUserId = AuthContext.getToken().getMeliId();
		List<com.ventalandia.domain.Question> questions = questionService.getQuestionsFromMeliByMeliUser(meliUserId);
		return this.gson.toJson(questions);
	}

	@GET
	@Path("api/news/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getNews(@PathParam("userId") Long userId) {

		List<com.ventalandia.domain.Question> unreadQuestions = questionService.getUnreadQuestions(userId);
		List<NewsView> questionViews = new ArrayList<NewsView>(unreadQuestions.size());

		for (Question question : unreadQuestions) {
//			NewsView news = new NewsView(question);
//			questionViews.add(news);
		}
		return gson.toJson(questionViews);

	}

	@GET
    @Path("api/testbla")
	@Produces(MediaType.APPLICATION_JSON)
    public List<NewsView> test2(){
		
		logger.fine("This is a Fine Message");
		logger.info("This is a Info Message");
		logger.warning("This is Warning Message");
		logger.severe("This is a Severe Message");
	    
	    List<NewsView> list = new ArrayList<NewsView>();
	    
	    NewsView newsView = new NewsView();
	    newsView.setId(1);
	    list.add(newsView);
	    
	    return list; 
	    
	}
	
}
