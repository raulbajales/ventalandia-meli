package com.ventalandia.persistence;

import javax.jdo.Query;

import com.google.inject.Inject;
import com.ventalandia.domain.Token;
import com.ventalandia.framework.persistence.JdoRepository;
import com.ventalandia.framework.persistence.PersistenceManagerProvider;

/**
 * 
 * @author matias
 *
 */
public class TokenRepository extends JdoRepository<Token> {

	@Inject
    public TokenRepository(PersistenceManagerProvider persistenceManagerProvider) {
		super(persistenceManagerProvider);
	}

	public Token getByMeliUserId(long aMeliId) {
        Query query = this.createQuery();

        query.setFilter("meliId == aMeliId");
        query.declareParameters("Long aMeliId");

        return this.list(query, aMeliId).get(0);
    }

}
