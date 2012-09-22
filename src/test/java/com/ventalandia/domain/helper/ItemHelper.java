package com.ventalandia.domain.helper;

import com.ventalandia.meli.api.notification.Item;

public class ItemHelper {
    
    public static Item createItem() {

        Item item = new Item();
        item.setId("MLA430494065");
        item.setCurrency_id("ARS");
        item.setAvailable_quantity(100);
        item.setTitle("Llantas de aleacion - 15 pulgadas");
        return item;
    }
}
