package com.ventalandia.persistence;

import com.ventalandia.domain.Item;
import com.ventalandia.domain.helper.ItemHelper;

/**
 * 
 * @author msulik
 * 
 */
public class ItemDomainHelper {

    public static Item createItem() {
        Item item = new Item();

        com.ventalandia.meli.api.notification.Item meliItem = ItemHelper.createItem();

        item.setMeliId(meliItem.getId());

        return item;
    }

}
