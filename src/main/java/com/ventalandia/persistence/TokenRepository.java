package com.ventalandia.persistence;

import javax.jdo.Query;

import com.ventalandia.domain.Token;
import com.ventalandia.framework.persistence.JdoRepository;

/**
 * 
 * @author matias
 *
 */
public class TokenRepository extends JdoRepository<Token> {

    public Token getByMeliUserId(long aMeliId) {
        Query query = this.createQuery();

        query.setFilter("meliId == aMeliId");
        query.declareParameters("Long aMeliId");

        return this.list(query, aMeliId).get(0);
    }

}
