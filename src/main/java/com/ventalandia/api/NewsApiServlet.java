package com.ventalandia.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;
import com.ventalandia.domain.Item;
import com.ventalandia.domain.Question;
import com.ventalandia.domain.User;
import com.ventalandia.framework.util.DateUtil;
import com.ventalandia.framework.util.MapBuilder;
import com.ventalandia.meli.pesistence.ItemRepository;
import com.ventalandia.meli.pesistence.QuestionRepository;
import com.ventalandia.meli.pesistence.UserRepository;
import com.ventalandia.meli.service.AuthContext;
import com.ventalandia.service.NewsFeed;
import com.ventalandia.service.NewsFeedRepository;
import com.ventalandia.service.NewsFeedSearchService;
import com.ventalandia.service.NewsFeedService;
import com.ventalandia.view.domain.ItemView;
import com.ventalandia.view.domain.NewsView;
import com.ventalandia.view.domain.SummaryView;
import com.ventalandia.view.domain.SimpleUserView;

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
    private NewsFeedSearchService newsFeedSearchService;

    @Inject
    public NewsApiServlet(NewsFeedRepository newsFeedRepository, UserRepository userRepository, ItemRepository itemRepository, NewsFeedService newsFeedService, QuestionRepository questionRepository, NewsFeedSearchService newsFeedSearchService) {
        this.newsFeedRepository = newsFeedRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.newsFeedService = newsFeedService;
        this.questionRepository = questionRepository;
        this.newsFeedSearchService = newsFeedSearchService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<NewsView> getNews(@QueryParam("since") String since){
        return getPagedNews(0, 10, since);
    }

    @GET
    @Path("/{fromPage}/{offset}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<NewsView> getPagedNews(@PathParam("fromPage") Integer fromPage, @PathParam("offset") Integer offset, @QueryParam("since") String since){

        LOGGER.info("getting news...");
        long meliUserId = AuthContext.getToken().getMeliId();

        LOGGER.info("Meli User Id: " + meliUserId);

        Date sinceDate= null;
        if(since!=null && since.length()>0){
            sinceDate= DateUtil.parse(since);
        }
        
        List<NewsFeed> newsFeeds = newsFeedRepository.find(meliUserId, sinceDate, fromPage, offset);
        
        return this.createNewsFeedForView(newsFeeds);
    }

    private List<NewsView> createNewsFeedForView(List<NewsFeed> newsFeeds) {
        List<NewsView> feeds = new ArrayList<NewsView>(newsFeeds.size());

        for (NewsFeed newsFeed : newsFeeds) {
            NewsView news = new NewsView();
            news.setId(newsFeed.getKeyId());
            news.setDate(newsFeed.getDate());
            news.setType(newsFeed.getType());
            news.setItem(new ItemView(newsFeed.getItemId(), itemRepository.getByMeliId(newsFeed.getItemId()).getTitle()));
            news.setBuyer(new SimpleUserView(newsFeed.getBuyerId(), userRepository.getByMeliId(newsFeed.getBuyerId()).getNickName()));
            news.setAnswered(newsFeed.isAnswered());

            feeds.add(news);
        }

        return feeds;
    }

    @GET
    @Path("summary")
    @Produces(MediaType.APPLICATION_JSON)
    public SummaryView summary(@DefaultValue("false") @QueryParam("reset") boolean reset) {
        LOGGER.info("Getting summary. Reset was: " + reset);
        Summary summary = this.newsFeedService.getSummary();

        SummaryView summaryView = new SummaryView(summary);

        if (reset) {
            this.newsFeedService.reset(summary);
        }

        return summaryView;
    }

    @GET
    @Path("/{newsId}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Map<String, Object> getNewsDetail(@PathParam("newsId") Long newsId) {

        Long meliUserId = AuthContext.getToken().getMeliId();
        LOGGER.info("getting newsId: " + newsId+ " meliUserId: "+meliUserId );
        NewsFeed newsFeed = newsFeedRepository.getByIdAndMeliId(newsId, meliUserId);
        LOGGER.info("newsFeed: "+newsFeed );
        
        if (newsFeed == null) {
            HashMap<String, Object> errorMap = new HashMap<String, Object>();
            errorMap.put("error", "Inexistent news");
            return errorMap;
        }

        Map<String, Object> newsDetail = MapBuilder.build();

        switch (newsFeed.getType()) {

        case QUESTION:

            Item item = itemRepository.getByMeliId(newsFeed.getItemId());
            User buyer = userRepository.getByMeliId(newsFeed.getBuyerId());
            
            LOGGER.info("item: "+ item.getTitle());
            LOGGER.info("buyer: "+buyer.getNickName());
            
            
            List<Question> questions = questionRepository.getQuestionsByItemAndUserMeliId(item.getKey(), buyer.getKey());
            
            Map<String, Object> itemMap = MapBuilder.build().putValue("title", item.getTitle()).putValue("pictureUrl", item.getPictureUrl()).putValue("active", !item.isClosed());
            Map<String, Object> buyerMap = MapBuilder.build().putValue("nickname", buyer.getNickName()).putValue("pictureUrl", buyer.getPictureUrl());
            List<Object> questionsList = new ArrayList<Object>();

            for (Question question : questions) {
                
                Map<String,Object> questionMap = MapBuilder.build()
                        .putValue("text", question.getText())
                        .putValue("date", DateUtil.format(question.getCreationDate()));
                
                Map<String,Object> answerMap =null;
                
                if(question.getAnswer()!=null){
                    answerMap = MapBuilder.build()
                            .putValue("text", question.getAnswer().getText())
                            .putValue("date", DateUtil.format(question.getAnswer().getCreationDate()));
                }
                
                Map<String, Object> questionAsMap = MapBuilder.build()
                        .putValue("id", question.getMeliId())
                        .putValue("question", questionMap)
                        .putValue("answer", answerMap);
                
                questionsList.add(questionAsMap);
            }

            newsDetail.put("item", itemMap);
            newsDetail.put("buyer", buyerMap);
            newsDetail.put("questions", questionsList);
            break;

        default:
            break;
        }

        return newsDetail;

    }

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public List<NewsView> search(@QueryParam("q") String keywords) {
        List<NewsFeed> result = this.newsFeedSearchService.search(keywords);
        
        return this.createNewsFeedForView(result);
    }
    
}
