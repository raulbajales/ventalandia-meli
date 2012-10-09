package com.ventalandia.meli.service;

import com.ventalandia.meli.api.notification.Question;

/**
 * 
 * @author msulik
 * 
 */
public class MeliQuestionService extends AbstractMeliService {

    public Question getQuestionByResource(String resource) {
        Question question = this.getEntityFromMELI(resource, Question.class);

        return question;
    }

}
