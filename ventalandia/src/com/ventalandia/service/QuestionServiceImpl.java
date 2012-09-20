package com.ventalandia.service;

import com.google.inject.Inject;
import com.ventalandia.domain.Question;
import com.ventalandia.domain.User;
import com.ventalandia.domain.transformer.QuestionTransformer;
import com.ventalandia.meli.service.MeliService;

/**
 * 
 * @author gzanussi
 * 
 */
public class QuestionServiceImpl implements QuestionService {

    private QuestionTransformer questionTransformer;

    private UserService userService;

    private MeliService meliService;

    @Inject
    public QuestionServiceImpl(QuestionTransformer questionTransformer, UserService userService, MeliService meliService) {
        this.questionTransformer = questionTransformer;
        this.userService = userService;
        this.meliService = meliService;
    }

    /**
     * Gets question from MELI service.
     */
    @Override
    public Question getQuestionFromMeli(String questionId, long userId) {

        User client = userService.getByMeliId(userId);
        com.ventalandia.meli.api.notification.Question questionAPI = meliService.getEntityFromMELI("/questions/"+questionId, com.ventalandia.meli.api.notification.Question.class);
        Question question = questionTransformer.transform(questionAPI);
        question.setClient(client);

        return question;

    }
}
