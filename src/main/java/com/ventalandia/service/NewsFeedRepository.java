package com.ventalandia.service;

import com.google.inject.Inject;
import com.ventalandia.framework.persistence.JdoRepository;
import com.ventalandia.framework.persistence.PersistenceManagerProvider;

/**
 * 
 * @author msulik
 * 
 */
public class NewsFeedRepository extends JdoRepository<NewsFeed> {

    @Inject
    public NewsFeedRepository(PersistenceManagerProvider persistenceManagerProvider) {
        super(persistenceManagerProvider);
    }

}
