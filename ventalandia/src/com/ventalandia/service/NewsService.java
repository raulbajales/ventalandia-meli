package com.ventalandia.service;

import java.util.List;


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
	 * @param user
	 * @return
	 */
	public List<News> getNewsFor(String user);

}
