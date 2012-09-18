package com.ventalandia.domain.transformer;

import junit.framework.Assert;

import org.junit.Test;

import com.ventalandia.domain.helper.QuestionHelper;
import com.ventalandia.meli.api.notification.Question;

public class QuestionTransformerTest {

    
    @Test
    public void testTransform(){
        
        Question in = QuestionHelper.create();
        Transformer<Question, com.ventalandia.domain.Question> questionTransformer = new QuestionTransformer();
        
        Assert.assertNotNull(questionTransformer.transform(in));
        
        
    }
    

    
    
}
