package com.ventalandia.service;

import com.ventalandia.domain.Question;
import com.ventalandia.domain.transformer.Transformer;

/**
 * 
 * @author msulik
 *
 */
public class QuestionToNewsFeedTransformer implements Transformer<Question, NewsFeed> {

    @Override
    public NewsFeed transform(Question question) {
        NewsFeed feed = new NewsFeed();

        feed.setType(NewsType.QUESTION);
        feed.setEntityId(question.getMeliId());
        feed.setUserId(question.getSeller().getMeliId());
        feed.setBuyerId(question.getClient().getMeliId());
        feed.setItemId(question.getItem().getMeliId());

        return feed;
    }

}
