package com.ventalandia.service;

import com.google.inject.Inject;
import com.ventalandia.domain.Question;

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

}
