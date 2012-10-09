package com.ventalandia.persistence;

import java.util.List;

import static junit.framework.Assert.*;

import org.junit.Test;

import com.google.inject.Inject;
import com.ventalandia.domain.DomainTest;
import com.ventalandia.domain.helper.UserHelper;
import com.ventalandia.service.NewsFeed;
import com.ventalandia.service.NewsFeedRepository;
import com.ventalandia.service.NewsType;

/**
 * 
 * @author msulik
 * 
 */
public class NewsFeedRepositoryTest extends DomainTest {

    private static final int OFFSET = 2;

    @Inject
    private NewsFeedRepository newsFeedRepository;

    @Test
    public void add() throws Exception {
        NewsFeed feed = NewsFeedHelper.create();

        assertNull(feed.getKey());

        this.newsFeedRepository.add(feed);

        assertNotNull(feed.getKey());
    }

    @Test
    public void find() throws Exception {
        this.addNewsFeeds();

        List<NewsFeed> feeds = this.newsFeedRepository.find(UserHelper.MELI_USER_ID, 0, OFFSET);
        assertEquals(2, feeds.size());

        feeds = this.newsFeedRepository.find(UserHelper.MELI_USER_ID, 3, OFFSET);
        assertEquals(2, feeds.size());

        feeds = this.newsFeedRepository.find(UserHelper.MELI_USER_ID, 0, OFFSET);
        assertEquals(2, feeds.size());
    }

    protected void addNewsFeeds() {
        NewsFeed feed1 = NewsFeedHelper.create();

        NewsFeed feed2 = NewsFeedHelper.create();
        feed2.setType(NewsType.SELLING);

        NewsFeed feed3 = NewsFeedHelper.create();
        feed3.setEntityId(4312);

        NewsFeed feed4 = NewsFeedHelper.create();
        feed4.setItemId("MLA12309847");

        NewsFeed feed5 = NewsFeedHelper.create();
        feed5.setType(NewsType.SELLING);

        this.newsFeedRepository.add(feed1);
        this.newsFeedRepository.add(feed2);
        this.newsFeedRepository.add(feed3);
        this.newsFeedRepository.add(feed4);
        this.newsFeedRepository.add(feed5);
    }

}
