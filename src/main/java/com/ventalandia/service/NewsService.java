package com.ventalandia.service;

import java.util.List;

import com.ventalandia.view.domain.NewsView;


/**
 * 
 * @author matias
 * @author german
 * 
 */
public interface NewsService {
	
	/**
	 * Provide the list of the latest news sorted by descending date.
	 * 
	 * @param userId
	 * @return
	 */
	public List<NewsView> getNewsByUserId(long userId);

}
