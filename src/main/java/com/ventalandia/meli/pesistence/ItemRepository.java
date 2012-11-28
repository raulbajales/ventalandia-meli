package com.ventalandia.meli.pesistence;

import java.util.List;

import com.ventalandia.domain.Item;

public interface ItemRepository extends MeliEntityRepository<Item> {

    public List<Item> getItemsBySeller(final long meliUserId);
    
}
