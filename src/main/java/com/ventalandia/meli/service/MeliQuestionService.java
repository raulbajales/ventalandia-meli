package com.ventalandia.meli.service;

import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.inject.Inject;
import com.ventalandia.framework.http.HttpRequestBuilder;
import com.ventalandia.meli.api.notification.MeliNotification;
import com.ventalandia.meli.api.notification.Question;
import com.ventalandia.persistence.TokenRepository;

/**
 * 
 * @author msulik
 * 
 */
public class MeliQuestionService extends AbstractMeliService {

    @Inject
    private TokenRepository tokenRepository;

    public Question getQuestionByNotification(MeliNotification meliNotification) {
        String accessToken = this.tokenRepository.getByMeliUserId(meliNotification.getUser_id()).getAccess_token();
        HttpRequestBuilder builder = this.createJsonGet().withPath(meliNotification.getResource()).addParam("access_token", accessToken);

        HTTPResponse httpResponse = this.execute(builder);

        return this.parseEntity(httpResponse, Question.class);
    }

}
