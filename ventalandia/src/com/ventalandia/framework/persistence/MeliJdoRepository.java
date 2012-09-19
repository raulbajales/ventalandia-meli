package com.ventalandia.framework.persistence;

import javax.jdo.Query;

import com.ventalandia.meli.pesistence.MeliEntityRepository;

public abstract class MeliJdoRepository<T> extends JdoRepository<T> implements MeliEntityRepository<T> {

    @SuppressWarnings("unchecked")
    public T getByMeliId(Object meliId) {

        Query query = this.createQuery();
        query.setFilter("meliId == meliIdParam");
        query.declareParameters("Object meliIdParam");
        return (T) query.execute("meliIdParam", meliId);

    }

}
