package com.ventalandia.api;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.ventalandia.meli.api.notification.Question;
import com.ventalandia.meli.service.UserMeliService;
import com.ventalandia.service.NewsType;
import com.ventalandia.service.NotificationService;
import com.ventalandia.view.domain.NewsView;

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

		List<Question> questions = notificationService.getQuestionsFromMeli(userId);
		return getNews(questions);

	}

	private Object getNews(List<Question> questions) {

		List<NewsView> result = new ArrayList<NewsView>();

		for (Question question : questions) {

			NewsView news = new NewsView(null);
			news.setDate(question.getDate_created());
			news.setType(NewsType.QUESTION);
			result.add(news);

		}
		return result;
	}

}
