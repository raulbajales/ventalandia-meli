package com.ventalandia.framework.persistence;

import java.util.List;

import javax.jdo.Query;

import com.ventalandia.meli.pesistence.MeliEntityRepository;

public abstract class MeliJdoRepository<T> extends JdoRepository<T> implements MeliEntityRepository<T> {

    @SuppressWarnings("unchecked")
    public T getByMeliId(Object meliId) {

        Query query = this.createQuery();
        query.setFilter("meliId == meliIdParam");
        query.declareParameters("Object meliIdParam");
        List<T> list = (List<T>) query.execute(meliId);
        return list.isEmpty() ? null : list.get(0);

    }

}
