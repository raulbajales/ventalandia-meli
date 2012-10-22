package com.ventalandia.api;


import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.ventalandia.domain.helper.TokenHelper;
import com.ventalandia.meli.pesistence.ItemRepository;
import com.ventalandia.meli.pesistence.QuestionRepository;
import com.ventalandia.meli.pesistence.UserRepository;
import com.ventalandia.meli.service.AuthContext;
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
        List<NewsView> news = newsApiServlet.getNews();
        
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
}
