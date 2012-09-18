package com.ventalandia.domain.transformer;

import junit.framework.Assert;

import org.junit.Test;

import com.ventalandia.domain.helper.QuestionHelper;
import com.ventalandia.meli.api.notification.Question;
import com.ventalandia.service.ItemService;
import com.ventalandia.service.QuestionTransformer;

public class QuestionTransformerTest {

    
    @Test
    public void testTransform(){
        
        Question in = QuestionHelper.create();
        ItemService itemService = null;
        Transformer<Question, com.ventalandia.domain.Question> questionTransformer = new QuestionTransformer(itemService);
        
        Assert.assertNotNull(questionTransformer.transform(in));
        
        
    }
    

    
    
}
