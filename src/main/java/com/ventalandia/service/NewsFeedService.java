package com.ventalandia.service;

import com.google.inject.Inject;
import com.ventalandia.api.Summary;
import com.ventalandia.domain.Question;
import com.ventalandia.meli.service.AuthContext;

/**
 * 
 * @author msulik
 * 
 */
public class NewsFeedService {

    @Inject
    private NewsFeedRepository newsFeedRepository;

    private QuestionToNewsFeedTransformer questionToNewsFeedTransformer = new QuestionToNewsFeedTransformer();

    public void create(Question question) {
        NewsFeed feed = this.questionToNewsFeedTransformer.transform(question);
        this.newsFeedRepository.add(feed);

        // TODO add some place where UI can ask for a summary of feeds
    }

    public Summary getSummary() {
        return this.newsFeedRepository.getSummary(AuthContext.getToken().getMeliId());
    }

}
