package com.ventalandia.service;

import java.util.List;

import javax.jdo.Query;

import com.google.inject.Inject;
import com.ventalandia.api.Summary;
import com.ventalandia.framework.persistence.JdoRepository;
import com.ventalandia.framework.persistence.PersistenceManagerProvider;

/**
 * 
 * @author msulik
 * 
 */
public class SummaryRepository extends JdoRepository<Summary> {

    @Inject
    public SummaryRepository(PersistenceManagerProvider persistenceManagerProvider) {
        super(persistenceManagerProvider);
    }

    public Summary getByMeliUserId(long aMeliId) {
        Query query = this.createQuery();

        query.setFilter("meliId == aMeliId");
        query.declareParameters("Long aMeliId");

        List<Summary> result = this.list(query, aMeliId);

        if (result != null && result.size() == 1) {
            return result.get(0);
        }
        else {
            return null;
        }
    }

}
