package com.ventalandia.api;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.ventalandia.domain.Question;
import com.ventalandia.meli.service.UserMeliService;
import com.ventalandia.service.QuestionService;
import com.ventalandia.view.domain.NewsView;

/**
 * 
 * @author msulik
 * 
 */
public class NewsApiServlet {

	private QuestionService questionService;
	private Gson gson;
	private UserMeliService userMeliService;

	@Inject
	public NewsApiServlet(QuestionService questionService, Gson gson, UserMeliService userMeliService) {
		this.questionService = questionService;
		this.gson = gson;
		this.userMeliService = userMeliService;
	}

	@GET
	@Path("api/news")
	@Produces(MediaType.APPLICATION_JSON)
	public String getNews() {

		long meliUserId = userMeliService.getCurrentUser().getId();

		List<com.ventalandia.domain.Question> unreadQuestions = questionService.getUnreadQuestions(meliUserId);
		List<NewsView> questionViews = new ArrayList<NewsView>(unreadQuestions.size());

		for (Question question : unreadQuestions) {
			NewsView news = new NewsView(question);
			questionViews.add(news);
		}

		return gson.toJson(questionViews);

	}
	
	

}
