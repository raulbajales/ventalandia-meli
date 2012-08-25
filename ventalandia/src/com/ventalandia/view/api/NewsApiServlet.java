package com.ventalandia.view.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Singleton;

/**
 * 
 * @author msulik
 *
 */
@Singleton
public class NewsApiServlet extends ApiServlet {

	private static final long serialVersionUID = -7592360020932936287L;

	@Override
	protected Object get(HttpServletRequest req, HttpServletResponse resp) {
		List<News> newsList = new ArrayList<News>();
		
		newsList.add(this.create(new Date(), "message 1", false, NewsType.QUESTION));
		newsList.add(this.create(new Date(), "message 2", false, NewsType.QUESTION));
		newsList.add(this.create(new Date(), "message 3", false, NewsType.SELLING));
		
		return newsList;
	}

	private News create(Date date, String message, boolean read, NewsType type) {
		News news = new News();
		
		news.setDate(date);
		news.setMessage(message);
		news.setRead(read);
		news.setType(type);
		
		return news;
	}
	
}
