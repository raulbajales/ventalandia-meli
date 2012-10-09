package com.ventalandia.persistence;

import junit.framework.Assert;

import org.junit.Test;

import com.google.inject.Inject;
import com.ventalandia.domain.DomainTest;
import com.ventalandia.service.NewsFeed;
import com.ventalandia.service.NewsFeedRepository;

/**
 * 
 * @author msulik
 * 
 */
public class NewsFeedRepositoryTest extends DomainTest {

    @Inject
    private NewsFeedRepository newsFeedRepository;

    @Test
    public void test() throws Exception {
        NewsFeed feed = NewsFeedHelper.create();

        Assert.assertNull(feed.getKey());

        this.newsFeedRepository.add(feed);

        Assert.assertNotNull(feed.getKey());
    }

}
