package com.ventalandia.api;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;
import com.ventalandia.meli.pesistence.ItemRepository;
import com.ventalandia.meli.pesistence.UserRepository;
import com.ventalandia.meli.service.AuthContext;
import com.ventalandia.service.NewsFeed;
import com.ventalandia.service.NewsFeedRepository;
import com.ventalandia.view.domain.ItemView;
import com.ventalandia.view.domain.NewsView;
import com.ventalandia.view.domain.UserView;

/**
 * 
 * @author msulik
 * @author german
 * 
 */
@Path("/api")
public class NewsApiServlet {
    
    private NewsFeedRepository newsFeedRepository;
    private UserRepository userRepository;
    private ItemRepository itemRepository;
    
    @Inject
    public NewsApiServlet(NewsFeedRepository newsFeedRepository, UserRepository userRepository, ItemRepository itemRepository) {
        this.newsFeedRepository = newsFeedRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }
    
    @GET
    @Path("news")
    @Produces(MediaType.APPLICATION_JSON)
    public List<NewsView> getNews() {

        long meliUserId = AuthContext.getToken().getMeliId();

        List<NewsFeed> newsFeeds = newsFeedRepository.find(meliUserId);
        List<NewsView> feeds = new ArrayList<NewsView>(newsFeeds.size());

        for (NewsFeed newsFeed : newsFeeds) {
            
            NewsView news = new NewsView();
            news.setId(newsFeed.getKey().getId());
            news.setDate(newsFeed.getDate());
            news.setType(newsFeed.getType());
            news.setItem(new ItemView(newsFeed.getItemId(),itemRepository.getByMeliId(newsFeed.getItemId()).getTitle()));
            news.setBuyer(new UserView(newsFeed.getUserId(), userRepository.getByMeliId(newsFeed.getUserId()).getNickName()));
            
            feeds.add(news);
        }

        return feeds;

    }

}
