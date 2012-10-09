package com.ventalandia.persistence;

import com.ventalandia.domain.Question;
import com.ventalandia.service.NewsFeed;
import com.ventalandia.service.NewsType;

public class NewsFeedHelper {

    public static NewsFeed create() {
        NewsFeed feed = new NewsFeed();

        Question question = QuestionDomainHelper.create();

        feed.setType(NewsType.QUESTION);
        feed.setEntityId(question.getMeliId());
        feed.setBuyerId(question.getClient().getMeliId());
        feed.setItemId(question.getItem().getMeliId());

        return feed;
    }

}
