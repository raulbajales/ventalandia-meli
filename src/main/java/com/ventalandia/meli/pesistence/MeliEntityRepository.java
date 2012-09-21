package com.ventalandia.meli.pesistence;

public interface MeliEntityRepository<T> extends Repository<T>{
    
    T getByMeliId(Object meliId);
    
}
