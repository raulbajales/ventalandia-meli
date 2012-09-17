package com.ventalandia.domain.transformer;

import junit.framework.Assert;

import org.junit.Test;

import com.ventalandia.meli.api.notification.Question;

public class QuestionTransformerTest {

    
    @Test
    public void testname(){
        
        Question in = null;
        Transformer<Question, com.ventalandia.domain.Question> questionTransformer = new QuestionTransformer();
        
        Assert.assertNotNull(questionTransformer.transform(in));
        
        
    }
    
}
