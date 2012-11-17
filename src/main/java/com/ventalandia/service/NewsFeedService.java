package com.ventalandia.service;

import java.util.Date;

import com.google.inject.Inject;
import com.ventalandia.api.Summary;
import com.ventalandia.domain.Item;
import com.ventalandia.domain.Question;
import com.ventalandia.domain.User;
import com.ventalandia.meli.service.AuthContext;

/**
 * 
 * @author msulik
 * 
 */
public class NewsFeedService {

    @Inject
    private NewsFeedRepository newsFeedRepository;

    @Inject
    private SummaryRepository summaryRepository;

    private QuestionToNewsFeedTransformer questionToNewsFeedTransformer = new QuestionToNewsFeedTransformer();

    public void create(Question question) {
        
        User client = question.getClient();
        Item item = question.getItem();
        NewsFeed feed = newsFeedRepository.getByBuyerAndItem(client.getMeliId(), item.getMeliId());
        
        if(feed == null){
            feed = this.questionToNewsFeedTransformer.transform(question);
            feed.index(question);
            this.newsFeedRepository.add(feed);
        }else{
            feed.setDate(new Date());
            feed.setAsNotAnswered();
            feed.index(question);
            this.newsFeedRepository.update(feed);
        }
        
        // TODO add some place where UI can ask for a summary of feeds
        this.updateSummary(feed);
    }

    private void updateSummary(NewsFeed feed) {
        Summary summary = this.summaryRepository.getByMeliUserId(feed.getUserId());

        if (summary == null) {
            summary = new Summary();
            summary.setUserId(feed.getUserId());
        }

        if (feed.getType().equals(NewsType.QUESTION)) {
            summary.incNewQuestions();
        }
        // else if (feed.getType().equals(NewsType.ITEM)) {
        //
        // }
        this.summaryRepository.update(summary);
    }

    public Summary getSummary() {
        Summary summary = this.summaryRepository.getByMeliUserId(AuthContext.getToken().getMeliId());
        if (summary == null) {
            summary = new Summary();
            summary.setUserId(AuthContext.getToken().getMeliId());
        }
        return summary;
    }

    public void reset(Summary summary) {
        if (summary != null && summary.getKey() != null) {
            summary.setNewQuestions(0);
            this.summaryRepository.update(summary);
        }
    }

}
