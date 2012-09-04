package com.ventalandia.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author matias
 * @author german
 * 
 */
// TODO remove this implementation when the news service implementation is ready
public class NewsServiceStub implements NewsService {

	public List<News> getNewsByUserId(long user) {
		List<News> newsList = new ArrayList<News>();

		newsList.add(this.create(new Date(), "message 1", false, NewsType.QUESTION, "Bicicleta usada rodado 26"));
		newsList.add(this.create(new Date(), "message 2", false, NewsType.QUESTION, "Anillo Del Amor Sexual Inalambrico"));
		newsList.add(this.create(new Date(), "message 3", false, NewsType.SELLING, "Chevrolet Cruze LTZ"));

		return newsList;
	}

	private News create(Date date, String message, boolean read, NewsType type, String product) {
		News news = new News();

		news.setDate(date);
		news.setMessage(message);
		news.setRead(read);
		news.setType(type);
		news.setProduct(product);

		return news;
	}

}
