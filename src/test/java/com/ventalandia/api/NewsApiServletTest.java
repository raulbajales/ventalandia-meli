package com.ventalandia.api;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.ventalandia.domain.Answer;
import com.ventalandia.domain.Item;
import com.ventalandia.domain.Question;
import com.ventalandia.domain.User;
import com.ventalandia.domain.helper.TokenHelper;
import com.ventalandia.domain.helper.UserHelper;
import com.ventalandia.meli.pesistence.ItemRepository;
import com.ventalandia.meli.pesistence.QuestionRepository;
import com.ventalandia.meli.pesistence.UserRepository;
import com.ventalandia.meli.service.AuthContext;
import com.ventalandia.service.NewsFeed;
import com.ventalandia.service.NewsFeedRepository;
import com.ventalandia.service.NewsFeedSearchService;
import com.ventalandia.service.NewsFeedService;
import com.ventalandia.service.NewsType;
import com.ventalandia.view.domain.NewsView;
import com.ventalandia.view.domain.SummaryView;

public class NewsApiServletTest {

    private NewsApiServlet newsApiServlet;
    private NewsFeedRepository newsFeedRepository;
    private UserRepository userRepository;
    private ItemRepository itemRepository;
    private QuestionRepository questionRepository;
    private NewsFeedService newsFeedService;
    private NewsFeedSearchService newsFeedSearchService;
    
    @Before
    public void setup() {

        newsFeedRepository = Mockito.mock(NewsFeedRepository.class);
        userRepository = Mockito.mock(UserRepository.class);
        itemRepository = Mockito.mock(ItemRepository.class);
        questionRepository = Mockito.mock(QuestionRepository.class);
        newsFeedService = Mockito.mock(NewsFeedService.class);
        newsFeedSearchService = Mockito.mock(NewsFeedSearchService.class);
        newsApiServlet = new NewsApiServlet(newsFeedRepository, userRepository, itemRepository, newsFeedService, questionRepository, newsFeedSearchService);

        AuthContext.setAuthToken(TokenHelper.create());

    }

    @Test
    public void testGetNews() {

        prepareDataForGetNews();
        List<NewsView> news = newsApiServlet.getNews(null);

        Assert.assertEquals(1, news.size());
        Assert.assertEquals(5678L, news.get(0).getBuyer().getId());
        Assert.assertEquals("TESTBUYER", news.get(0).getBuyer().getNickname());
        Assert.assertEquals("1234", news.get(0).getItem().getId());
        Assert.assertEquals("ItemTitle", news.get(0).getItem().getTitle());

    }

    @Test
    public void testPagedGetNews() {

        prepareDataForGetNews();
        List<NewsView> news = newsApiServlet.getPagedNews(0, 10, null);

        Assert.assertEquals(1, news.size());
        Assert.assertEquals(5678L, news.get(0).getBuyer().getId());
        Assert.assertEquals("TESTBUYER", news.get(0).getBuyer().getNickname());
        Assert.assertEquals("1234", news.get(0).getItem().getId());
        Assert.assertEquals("ItemTitle", news.get(0).getItem().getTitle());

    }

    @Test
    public void testSummary() {

        Summary summary = new Summary();
        summary.setNewQuestions(1234);
        summary.setUserId(1L);
        Mockito.when(newsFeedService.getSummary()).thenReturn(summary);
        SummaryView summaryView = newsApiServlet.summary(false);

        Assert.assertEquals(1234, summaryView.getNew_questions());
        Assert.assertEquals(1L, summaryView.getUser_id());

    }

    @Test
    public void testInexistentNewsDetail() {

        Map<String, Object> newsDetail = newsApiServlet.getNewsDetail(12345L);
        Assert.assertEquals("Inexistent news", newsDetail.get("error"));

    }

    @SuppressWarnings("unchecked")
    @Test
    public void testNewsDetail() {

        NewsFeed newsFeed = Mockito.mock(NewsFeed.class);
        Mockito.when(newsFeed.getItemId()).thenReturn("1234");
        Mockito.when(newsFeed.getBuyerId()).thenReturn(5678L);
        Mockito.when(newsFeed.getType()).thenReturn(NewsType.QUESTION);
        Mockito.when(newsFeed.getEntityId()).thenReturn(4444L);

        User buyer = new User();
        buyer.setMeliId(5678L);
        buyer.setNickName("TESTBUYER");
        Item item = new Item();
        item.setMeliId("itemMeliId");
        item.setTitle("ItemTitle");
        Question question = new Question();
        question.setText("what is this?");
        question.setMeliId(8888L);
        Answer answer = new Answer();
        answer.setText("It is Ventalandia MAN!!!");
        question.setAnswer(answer);

        Mockito.when(itemRepository.getByMeliId("1234")).thenReturn(item);
        Mockito.when(userRepository.getByMeliId(5678L)).thenReturn(buyer);
        Mockito.when(questionRepository.getQuestionsByItemAndUserMeliId(null,null)).thenReturn(Arrays.asList(question));

        Mockito.when(newsFeedRepository.getByIdAndMeliId(12345L, UserHelper.MELI_USER_ID)).thenReturn(newsFeed);

        Map<String, Object> newsDetail = newsApiServlet.getNewsDetail(12345L);
        Map<String,Object> questions = (Map<String, Object>) newsDetail.get("questions");
        Assert.assertNotNull(questions);
        Map<String,Object> questionMap = (Map<String, Object>)questions.get("8888");
        Assert.assertNotNull(questionMap);
        Assert.assertEquals("what is this?", questionMap.get("text"));
        Assert.assertNotNull(newsDetail.get("buyer"));
        Assert.assertNotNull(newsDetail.get("item"));

    }

    private void prepareDataForGetNews() {

        NewsFeed newsFeed = Mockito.mock(NewsFeed.class);
        Mockito.when(newsFeed.getItemId()).thenReturn("1234");
        Mockito.when(newsFeed.getBuyerId()).thenReturn(5678L);

        User buyer = new User();
        buyer.setMeliId(5678L);
        buyer.setNickName("TESTBUYER");

        Mockito.when(newsFeedRepository.find(1234,null, 0, 10)).thenReturn(Arrays.asList(newsFeed));
        Item item = new Item();
        item.setTitle("ItemTitle");

        Mockito.when(itemRepository.getByMeliId("1234")).thenReturn(item);
        Mockito.when(userRepository.getByMeliId(5678L)).thenReturn(buyer);
    }
}
