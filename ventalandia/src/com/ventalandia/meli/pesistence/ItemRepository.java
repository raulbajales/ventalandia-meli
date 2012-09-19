package com.ventalandia.meli.pesistence;

import com.ventalandia.domain.Item;

public interface ItemRepository extends Repository<Item> {

    Item getByMeliId(String itemId);

}
