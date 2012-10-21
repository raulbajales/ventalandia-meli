package com.ventalandia.meli.service;

import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.gson.Gson;
import com.google.inject.Inject;
import com.ventalandia.framework.http.HttpRequestBuilder;
import com.ventalandia.meli.api.notification.MeliNotification;
import com.ventalandia.meli.api.notification.Question;

/**
 * 
 * @author msulik
 * 
 */
public class MeliQuestionService extends AbstractMeliService {

    @Inject
    private Gson gson;

    public Question getQuestionByNotification(MeliNotification meliNotification) {
        String accessToken = AuthContext.getToken().getAccess_token();
        HttpRequestBuilder builder = this.createJsonGet().withPath(meliNotification.getResource()).addParam("access_token", accessToken);

        HTTPResponse httpResponse = this.execute(builder);

        return this.parseEntity(httpResponse, Question.class);
    }

    public void answer(long question_id, String text) {
        String accessToken = AuthContext.getToken().getAccess_token();
        HttpRequestBuilder builder = this.createJsonPost().withPath("answers") //
                .addParam("access_token", accessToken) //
                .body("{question_id: " + question_id +", text:\""+ text + "\"}");
//                        this.gson.toJson(new AnswerBody(question_id, text)));

        HTTPResponse httpResponse = this.execute(builder);

        if (httpResponse.getResponseCode() != 200) {
            throw new RuntimeException(new String(httpResponse.getContent()));
        }
    }

    // setter and getter are unused on this class, they are here just for json
    // serialization.
    @SuppressWarnings("unused")
    private class AnswerBody {

        private long question_id;

        private String text;

        public AnswerBody(long question_id, String text) {
            this.question_id = question_id;
            this.text = text;
        }

        public long getQuestion_id() {
            return question_id;
        }

        public void setQuestion_id(long question_id) {
            this.question_id = question_id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

    }
}
