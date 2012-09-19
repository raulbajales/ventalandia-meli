package com.ventalandia.domain.transformer;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.ventalandia.domain.User;
import com.ventalandia.domain.helper.QuestionHelper;
import com.ventalandia.meli.api.notification.Question;
import com.ventalandia.service.ItemService;
import com.ventalandia.service.UserService;

/**
 * 
 * @author gzanussi
 * 
 */
public class QuestionTransformerTest {

    private ItemService itemService;
    private UserService userService;

    @Before
    public void setup() {
        itemService = mock(ItemService.class);
        userService = mock(UserService.class);
    }

    @Test
    public void testTransform() {

        when(itemService.getByMeliId("MLA430494065")).thenReturn(new com.ventalandia.domain.Item());
        when(userService.getByMeliId(86898669)).thenReturn(new User());
        
        Question question = QuestionHelper.create();
        Transformer<Question, com.ventalandia.domain.Question> questionTransformer = new QuestionTransformer(itemService, userService);

        assertNotNull(questionTransformer.transform(question));
        assertEquals("Hola, trabajan los sabados?", question.getText());

    }

}
