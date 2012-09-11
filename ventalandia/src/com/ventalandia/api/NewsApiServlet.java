package com.ventalandia.api;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.ventalandia.meli.api.auth.AuthToken;
import com.ventalandia.meli.api.notification.Question;
import com.ventalandia.meli.service.MeliAuthContext;
import com.ventalandia.meli.service.UserMeliService;
import com.ventalandia.service.News;
import com.ventalandia.service.NewsType;
import com.ventalandia.service.NotificationService;

/**
 * 
 * @author msulik
 * 
 */
@Singleton
public class NewsApiServlet extends ApiServlet {

	private static final long serialVersionUID = -7592360020932936287L;
	@Inject
	private UserMeliService userMeliService;

	@Inject
	private NotificationService notificationService;

	@Override
	protected Object get(HttpServletRequest req, HttpServletResponse resp) {

		long userId = userMeliService.getCurrentUser().getId();
		AuthToken authToken = MeliAuthContext.getAuthToken();
		List<Question> questions = notificationService.getQuestionsFromMeli(userId, authToken);
		return getNews(questions);

	}

	private Object getNews(List<Question> questions) {

		List<News> result = new ArrayList<News>();

		for (Question question : questions) {

			News news = new News();
			news.setDate(question.getDate_created());
			news.setMessage(question.getText());
			news.setRead(false);
			news.setType(NewsType.QUESTION);
			news.setProduct(question.getItem_id());
			result.add(news);

		}
		return result;
	}

}
