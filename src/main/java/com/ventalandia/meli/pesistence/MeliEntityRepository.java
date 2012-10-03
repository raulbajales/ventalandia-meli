package com.ventalandia.meli.pesistence;

import com.ventalandia.framework.persistence.Repository;

public interface MeliEntityRepository<T> extends Repository<T>{
    
    T getByMeliId(Object meliId);
    
}
