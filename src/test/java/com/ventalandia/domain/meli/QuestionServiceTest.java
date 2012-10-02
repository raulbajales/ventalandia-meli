package com.ventalandia.domain.meli;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.ventalandia.domain.Item;
import com.ventalandia.domain.Question;
import com.ventalandia.domain.User;
import com.ventalandia.domain.helper.QuestionHelper;
import com.ventalandia.domain.transformer.QuestionTransformer;
import com.ventalandia.meli.pesistence.QuestionRepository;
import com.ventalandia.meli.service.MeliService;
import com.ventalandia.service.ItemService;
import com.ventalandia.service.NotificationService;
import com.ventalandia.service.QuestionServiceImpl;
import com.ventalandia.service.UserService;

public class QuestionServiceTest {

    private QuestionServiceImpl questionService;

    private QuestionTransformer questionTransformer;

    private ItemService itemService;

    private UserService userService;

    private MeliService meliService;
    
    private QuestionRepository questionRepository;

	private NotificationService notificationService;

    @Before
    public void setup() {

        meliService = mock(MeliService.class);
        itemService = mock(ItemService.class);
        userService = mock(UserService.class);
        notificationService = mock(NotificationService.class);
        questionRepository = mock(QuestionRepository.class);
        questionTransformer = new QuestionTransformer(itemService, userService);
        questionService = new QuestionServiceImpl(questionTransformer, userService, meliService,questionRepository, notificationService);

    }

    @Test
    public void testGetQuestion() throws Exception {

        String questionId = "/questions/1234";
        long userId = 79450083l;
        long sellerId = 86898669l;

        when(meliService.getEntityFromMELI(questionId, com.ventalandia.meli.api.notification.Question.class)).thenReturn(QuestionHelper.create());
        when(itemService.getByMeliId("MLA430494065")).thenReturn(createItem());
        when(userService.getByMeliId(sellerId)).thenReturn(new User());

        Question question = questionService.getQuestionFromMeli(questionId, userId);

        Assert.assertNotNull(question);
        Assert.assertEquals("Hola, trabajan los sabados?", question.getText());
        Assert.assertEquals("Balanza Electronica Digital Para Cocina Daewoo International", question.getItem().getTitle());

    }

    private Item createItem() {
        Item item = new Item();
        item.setTitle("Balanza Electronica Digital Para Cocina Daewoo International");
        return item;
    }

}
