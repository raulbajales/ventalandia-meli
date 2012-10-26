package com.ventalandia.service;

import java.util.logging.Logger;

import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.inject.Inject;
import com.ventalandia.domain.User;
import com.ventalandia.domain.transformer.QuestionTransformer;
import com.ventalandia.framework.http.HttpRequestBuilder;
import com.ventalandia.meli.api.notification.MeliNotification;
import com.ventalandia.meli.api.notification.Question;
import com.ventalandia.meli.pesistence.QuestionRepository;
import com.ventalandia.meli.service.AbstractMeliService;
import com.ventalandia.meli.service.AuthContext;
import com.ventalandia.meli.service.MeliQuestionService;

/**
 * 
 * @author msulik
 * 
 */
public class NotificationReceiverService extends AbstractMeliService {

    private static final Logger LOGGER = Logger.getLogger(NotificationReceiverService.class.getName());

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
        LOGGER.info("Receiving order notification : " + meliNotification.getResource());

        HTTPResponse httpResponse = this.getResourceFromMeli(meliNotification);

        LOGGER.info("Order Response Code: " + httpResponse.getResponseCode());
        LOGGER.info("Order Response Content: " + new String(httpResponse.getContent()));
    }

    private HTTPResponse getResourceFromMeli(MeliNotification meliNotification) {
        String accessToken = AuthContext.getToken().getAccess_token();
        HttpRequestBuilder builder = this.createJsonGet().withPath(meliNotification.getResource()).addParam("access_token", accessToken);

        return this.execute(builder);
    }

    private void receiveItem(MeliNotification meliNotification) {
        LOGGER.info("Receiving item notification : " + meliNotification.getResource());

        HTTPResponse httpResponse = this.getResourceFromMeli(meliNotification);

        LOGGER.info("Item Response Code: " + httpResponse.getResponseCode());
        LOGGER.info("Item Response Content: " + new String(httpResponse.getContent()));
    }

}
