package com.ventalandia.api;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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
import com.ventalandia.service.NewsFeedService;
import com.ventalandia.view.domain.ItemView;
import com.ventalandia.view.domain.NewsView;
import com.ventalandia.view.domain.UserView;

/**
 * 
 * @author msulik
 * @author german
 * 
 */
@Path("/news")
public class NewsApiServlet {

    private static final Logger LOGGER = Logger.getLogger(NewsApiServlet.class.getName());

    private NewsFeedRepository newsFeedRepository;

    private UserRepository userRepository;

    private ItemRepository itemRepository;

    private NewsFeedService newsFeedService;

    @Inject
    public NewsApiServlet(NewsFeedRepository newsFeedRepository, UserRepository userRepository, ItemRepository itemRepository, NewsFeedService newsFeedService) {
        this.newsFeedRepository = newsFeedRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.newsFeedService = newsFeedService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<NewsView> getNews() {

        LOGGER.info("getting news...");
        long meliUserId = AuthContext.getToken().getMeliId();
        LOGGER.info("Meli User Id: " + meliUserId);

        List<NewsFeed> newsFeeds = newsFeedRepository.find(meliUserId);
        List<NewsView> feeds = new ArrayList<NewsView>(newsFeeds.size());

        for (NewsFeed newsFeed : newsFeeds) {

            NewsView news = new NewsView();
            news.setId(newsFeed.getKey().getId());
            news.setDate(newsFeed.getDate());
            news.setType(newsFeed.getType());
            news.setItem(new ItemView(newsFeed.getItemId(), itemRepository.getByMeliId(newsFeed.getItemId()).getTitle()));
            news.setBuyer(new UserView(newsFeed.getUserId(), userRepository.getByMeliId(newsFeed.getUserId()).getNickName()));

            feeds.add(news);
        }

        return feeds;

    }

    @GET
    @Path("summary")
    @Produces(MediaType.APPLICATION_JSON)
    public Summary summary() {
        return this.newsFeedService.getSummary();
    }

}
