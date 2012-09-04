package com.ventalandia.api;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.ventalandia.meli.api.notification.Notification;
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
		List<Notification> notifications = notificationService.getUnreadQuestionsByUserId(userId);
		
		return getNews(notifications);

	}

	private Object getNews(List<Notification> notifications) {


		List<News> result = new ArrayList<News>();

		for (Notification notification : notifications) {

			News news = new News();
			news.setDate(notification.getSent());
			news.setMessage(notification.getTopic());
			news.setRead(notification.isRead());
			news.setType(NewsType.QUESTION);
			news.setProduct("this field is not available");
			result.add(news);

		}
		return result;
	}

}
