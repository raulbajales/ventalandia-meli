package com.ventalandia.api;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.ventalandia.domain.Item;
import com.ventalandia.domain.User;
import com.ventalandia.domain.helper.TokenHelper;
import com.ventalandia.meli.pesistence.ItemRepository;
import com.ventalandia.meli.pesistence.QuestionRepository;
import com.ventalandia.meli.pesistence.UserRepository;
import com.ventalandia.meli.service.AuthContext;
import com.ventalandia.service.NewsFeed;
import com.ventalandia.service.NewsFeedRepository;
import com.ventalandia.service.NewsFeedService;
import com.ventalandia.view.domain.NewsView;

public class NewsApiServletTest {

    private NewsApiServlet newsApiServlet;
    private NewsFeedRepository newsFeedRepository;
    private UserRepository userRepository;
    private ItemRepository itemRepository;
    private QuestionRepository questionRepository;
    private NewsFeedService newsFeedService;

    @Before
    public void setup() {

        newsFeedRepository = Mockito.mock(NewsFeedRepository.class);
        userRepository = Mockito.mock(UserRepository.class);
        itemRepository = Mockito.mock(ItemRepository.class);
        questionRepository = Mockito.mock(QuestionRepository.class);
        newsFeedService = Mockito.mock(NewsFeedService.class);
        newsApiServlet = new NewsApiServlet(newsFeedRepository, userRepository, itemRepository, newsFeedService, questionRepository);

        AuthContext.setAuthToken(TokenHelper.create());

    }

    @Test
    public void testGetNews() {

        prepareDataForGetNews();
        List<NewsView> news = newsApiServlet.getNews();

        Assert.assertEquals(1, news.size());
        Assert.assertEquals(5678L, news.get(0).getBuyer().getId());
        Assert.assertEquals("TESTBUYER", news.get(0).getBuyer().getNickname());
        Assert.assertEquals("1234", news.get(0).getItem().getId());
        Assert.assertEquals("ItemTitle", news.get(0).getItem().getTitle());
        
    }


    @Test
    public void testPagedGetNews() {

    }

    @Test
    public void testSummary() {

    }

    @Test
    public void testNewsDetail() {
        

    }
    
    private void prepareDataForGetNews() {
        
        NewsFeed newsFeed = Mockito.mock(NewsFeed.class);
        Mockito.when(newsFeed.getItemId()).thenReturn("1234");
        Mockito.when(newsFeed.getBuyerId()).thenReturn(5678L);
        
        User buyer = new User();
        buyer.setMeliId(5678L);
        buyer.setNickName("TESTBUYER");
        
        Mockito.when(newsFeedRepository.find(1234, 0, 10)).thenReturn(Arrays.asList(newsFeed));
        Item item = new Item();
        item.setTitle("ItemTitle");
        
        Mockito.when(itemRepository.getByMeliId("1234")).thenReturn(item);
        Mockito.when(userRepository.getByMeliId(5678L)).thenReturn(buyer);
    }
}
