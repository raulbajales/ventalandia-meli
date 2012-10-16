package com.ventalandia.service;

import java.util.List;

import javax.jdo.Query;

import com.google.inject.Inject;
import com.ventalandia.framework.persistence.JdoRepository;
import com.ventalandia.framework.persistence.PersistenceManagerProvider;

/**
 * 
 * @author msulik
 * 
 */
public class NewsFeedRepository extends JdoRepository<NewsFeed> {

    private static final int OFFSET = 20;

    @Inject
    public NewsFeedRepository(PersistenceManagerProvider persistenceManagerProvider) {
        super(persistenceManagerProvider);
    }

    public List<NewsFeed> find(long userId) {
        return this.find(userId, 0, OFFSET);
    }

    public List<NewsFeed> find(long userId, int from, int offset) {
        if (from < 0) {
            throw new IllegalArgumentException("'From' must be > 0.");
        }

        Query query = this.createQuery();

        query.setOrdering("date desc");
        query.setRange(from, from + offset);

        query.setFilter("userId == aMeliId");
        query.declareParameters("Long aMeliId");

        return this.list(query, userId);
    }

    @Override
    protected void afterAdd(NewsFeed newsFeed) {

    }

}
