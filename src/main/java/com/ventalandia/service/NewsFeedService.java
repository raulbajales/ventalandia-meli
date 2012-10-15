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

    @Inject
    private SummaryRepository summaryRepository;

    private QuestionToNewsFeedTransformer questionToNewsFeedTransformer = new QuestionToNewsFeedTransformer();

    public void create(Question question) {
        NewsFeed feed = this.questionToNewsFeedTransformer.transform(question);
        this.newsFeedRepository.add(feed);

        // TODO add some place where UI can ask for a summary of feeds
        this.updateSummary(feed);
    }

    private void updateSummary(NewsFeed feed) {
        Summary summary = this.summaryRepository.get(feed.getUserId());

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

}
