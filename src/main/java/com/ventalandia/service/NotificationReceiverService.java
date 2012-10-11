package com.ventalandia.service;

import com.google.inject.Inject;
import com.ventalandia.domain.User;
import com.ventalandia.domain.transformer.QuestionTransformer;
import com.ventalandia.meli.api.notification.MeliNotification;
import com.ventalandia.meli.api.notification.Question;
import com.ventalandia.meli.pesistence.QuestionRepository;
import com.ventalandia.meli.service.AbstractMeliService;
import com.ventalandia.meli.service.MeliQuestionService;

/**
 * 
 * @author msulik
 * 
 */
public class NotificationReceiverService extends AbstractMeliService {

    @Inject
    private QuestionTransformer questionTransformer;

    @Inject
    private UserService userService;

    @Inject
    private QuestionRepository questionRepository;

    @Inject
    private NewsFeedService newsFeedService;
    
    @Inject
    private MeliQuestionService meliQuestionService;

    public void receive(MeliNotification meliNotification) {
        if (Topic.QUESTION.is(meliNotification.getTopic())) {
            this.receiveQuestion(meliNotification);
        }
        else if (Topic.ORDER.is(meliNotification.getTopic())) {
            this.receiveOrder(meliNotification);
        }
        else if (Topic.ITEM.is(meliNotification.getTopic())) {
            this.receiveItem(meliNotification);
        }
        else {
            throw new IllegalArgumentException("Unknown topic - " + meliNotification);
        }
    }

    private void receiveQuestion(MeliNotification meliNotification) {
        Question meliQuestion = this.meliQuestionService.getQuestionByNotification(meliNotification);

        com.ventalandia.domain.Question question = this.questionTransformer.transform(meliQuestion);
        User buyer = this.userService.getByMeliId(meliQuestion.getFrom().getId());
        question.setClient(buyer);

        this.questionRepository.add(question);

        this.newsFeedService.create(question);
    }

    private void receiveOrder(MeliNotification meliNotification) {
        // TODO code it
    }

    private void receiveItem(MeliNotification meliNotification) {
        // TODO code it
    }

}
