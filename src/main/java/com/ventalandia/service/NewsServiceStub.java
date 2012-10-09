package com.ventalandia.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ventalandia.view.domain.NewsView;

/**
 * 
 * @author matias
 * @author german
 * 
 */
// TODO remove this implementation when the news service implementation is ready
public class NewsServiceStub implements NewsService {

    public List<NewsView> getNewsByUserId(long user) {
        List<NewsView> newsList = new ArrayList<NewsView>();

        newsList.add(this.create(new Date(), "message 1", false, NewsType.QUESTION, "Bicicleta usada rodado 26"));
        newsList.add(this.create(new Date(), "message 2", false, NewsType.QUESTION,
                "Anillo Del Amor Sexual Inalambrico"));
        newsList.add(this.create(new Date(), "message 3", false, NewsType.SELLING, "Chevrolet Cruze LTZ"));

        return newsList;
    }

    private NewsView create(Date date, String message, boolean read, NewsType type, String product) {
        NewsView news = new NewsView();

        news.setDate(date);
        news.setType(type);
        return news;
    }

}
