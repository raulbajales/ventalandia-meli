package com.ventalandia.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.ventalandia.service.NewsService;

/**
 * 
 * @author msulik
 *
 */
@Singleton
public class NewsApiServlet extends ApiServlet {

	private static final long serialVersionUID = -7592360020932936287L;
	
	@Inject
	private NewsService newsService;

	@Override
	protected Object get(HttpServletRequest req, HttpServletResponse resp) {
		String user = req.getPathInfo();
		
		return this.newsService.getNewsFor(user);
	}
	
}
