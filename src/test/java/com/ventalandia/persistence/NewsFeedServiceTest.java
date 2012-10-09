package com.ventalandia.persistence;

import junit.framework.Assert;

import org.junit.Test;

import com.google.inject.Inject;
import com.ventalandia.domain.DomainTest;
import com.ventalandia.domain.Question;
import com.ventalandia.service.NewsFeed;
import com.ventalandia.service.NewsFeedRepository;
import com.ventalandia.service.NewsFeedService;
import com.ventalandia.service.NewsType;

/**
 * 
 * @author msulik
 * 
 */
public class NewsFeedServiceTest extends DomainTest {

    @Inject
    private NewsFeedService newsFeedService;

    @Inject
    private NewsFeedRepository newsFeedRepository;

    @Test
    public void test() throws Exception {
        Question question = QuestionDomainHelper.create();

        this.newsFeedService.create(question);

        NewsFeed newsFeed = this.newsFeedRepository.get(1L);

        Assert.assertEquals(question.getMeliId(), newsFeed.getEntityId());
        Assert.assertEquals(NewsType.QUESTION, newsFeed.getType());
    }

}
