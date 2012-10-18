package com.ventalandia.api;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;
import com.ventalandia.domain.Item;
import com.ventalandia.domain.Question;
import com.ventalandia.domain.User;
import com.ventalandia.framework.util.MapBuilder;
import com.ventalandia.meli.pesistence.ItemRepository;
import com.ventalandia.meli.pesistence.QuestionRepository;
import com.ventalandia.meli.pesistence.UserRepository;
import com.ventalandia.meli.service.AuthContext;
import com.ventalandia.service.NewsFeed;
import com.ventalandia.service.NewsFeedRepository;
import com.ventalandia.service.NewsFeedService;
import com.ventalandia.view.domain.ItemView;
import com.ventalandia.view.domain.NewsView;
import com.ventalandia.view.domain.SummaryView;
import com.ventalandia.view.domain.UserView;

/**
 * 
 * @author msulik
 * @author german
 * 
 */
@Path("/api/news")
public class NewsApiServlet {

    private static final Logger LOGGER = Logger.getLogger(NewsApiServlet.class.getName());

    private NewsFeedRepository newsFeedRepository;

    private UserRepository userRepository;

    private ItemRepository itemRepository;

    private QuestionRepository questionRepository;

    private NewsFeedService newsFeedService;

    @Inject
    public NewsApiServlet(NewsFeedRepository newsFeedRepository, UserRepository userRepository, ItemRepository itemRepository, NewsFeedService newsFeedService, QuestionRepository questionRepository) {
        this.newsFeedRepository = newsFeedRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.newsFeedService = newsFeedService;
        this.questionRepository = questionRepository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<NewsView> getNews() {
        return getNews(0, 10);
    }

    @GET
    @Path("/{fromPage}/{offset}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<NewsView> getNews(@PathParam("fromPage")
    Integer fromPage, @PathParam("offset")
    Integer offset) {

        LOGGER.info("getting news...");
        long meliUserId = AuthContext.getToken().getMeliId();

        LOGGER.info("Meli User Id: " + meliUserId);

        List<NewsFeed> newsFeeds = newsFeedRepository.find(meliUserId, fromPage, offset);
        List<NewsView> feeds = new ArrayList<NewsView>(newsFeeds.size());

        for (NewsFeed newsFeed : newsFeeds) {

            NewsView news = new NewsView();
            news.setId(newsFeed.getKey().getId());
            news.setDate(newsFeed.getDate());
            news.setType(newsFeed.getType());
            news.setItem(new ItemView(newsFeed.getItemId(), itemRepository.getByMeliId(newsFeed.getItemId()).getTitle()));
            news.setBuyer(new UserView(newsFeed.getBuyerId(), userRepository.getByMeliId(newsFeed.getBuyerId()).getNickName()));

            feeds.add(news);
        }

        return feeds;

    }

    @GET
    @Path("summary")
    @Produces(MediaType.APPLICATION_JSON)
    public SummaryView summary() {
        LOGGER.info("getting summary...");
        Summary summary = this.newsFeedService.getSummary();

        return new SummaryView(summary);
    }

    @GET
    @Path("/{newsId}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Object getNews(@PathParam("newsId")
    Long newsId) {

        LOGGER.info("getting newsId: " + newsId);
        Long meliUserId = AuthContext.getToken().getMeliId();
        NewsFeed newsFeed = newsFeedRepository.getByIdAndMeliId(newsId, meliUserId);

        if (newsFeed == null) {
            return "error";
        }

        Map<String, Object> newsDetail = MapBuilder.build();

        switch (newsFeed.getType()) {

        case QUESTION:

            Item item = itemRepository.getByMeliId(newsFeed.getItemId());
            User buyer = userRepository.getByMeliId(newsFeed.getBuyerId());
            Question question = questionRepository.getByMeliId(newsFeed.getEntityId());
            Map<String, Object> itemMap = MapBuilder.build().putValue("title", item.getTitle()).putValue("pictureUrl", item.getPictureUrl());
            Map<String, Object> buyerMap = MapBuilder.build().putValue("nickname", buyer.getNickName());

            newsDetail.put("item", itemMap);
            newsDetail.put("buyer", buyerMap);
            newsDetail.put("question", question.getText());
            break;

        default:
            break;
        }

        return newsDetail;

    }
    
}