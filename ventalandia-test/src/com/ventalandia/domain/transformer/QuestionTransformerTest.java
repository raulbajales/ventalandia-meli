package com.ventalandia.domain.transformer;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;

import com.ventalandia.domain.helper.QuestionHelper;
import com.ventalandia.meli.api.notification.Question;
import com.ventalandia.meli.pesistence.ItemRepository;
import com.ventalandia.meli.service.MeliService;
import com.ventalandia.service.ItemService;
import com.ventalandia.service.ItemServiceImpl;

public class QuestionTransformerTest {

    

    @Test
    public void testTransform(){
        
        ItemRepository itemRepository = Mockito.mock(ItemRepository.class);
        ItemTransformer itemTransformer = null;
        MeliService meliService = null;
        
        Question in = QuestionHelper.create();
        ItemService itemService = new ItemServiceImpl(itemTransformer, itemRepository, meliService);
        Transformer<Question, com.ventalandia.domain.Question> questionTransformer = new QuestionTransformer(itemService);
        
        Assert.assertNotNull(questionTransformer.transform(in));
        
        
    }
    

    
    
}
