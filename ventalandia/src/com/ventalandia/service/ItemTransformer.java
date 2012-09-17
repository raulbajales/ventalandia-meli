package com.ventalandia.service;

import com.ventalandia.domain.Currency;
import com.ventalandia.domain.Item;
import com.ventalandia.domain.User;
import com.ventalandia.domain.transformer.Transformer;

/**
 * 
 * @author matias
 * 
 */
public class ItemTransformer implements Transformer<com.ventalandia.meli.api.notification.Item, Item> {

    // private CurrencyTrasformer currencyTrasformer = new CurrencyTrasformer();

    @Override
    public Item transform(com.ventalandia.meli.api.notification.Item itemAPI) {
        Item item = new Item();

        item.setAvailableQuantity(itemAPI.getAvailable_quantity());
        item.setBasePrice(itemAPI.getBase_price());
        item.setCreationDate(itemAPI.getDate_created());
        // item.setCurrency(getCurrency(itemAPI.getCurrency_id());
        item.setInitialQuantity(itemAPI.getInitial_quantity());
        item.setLastUpdated(itemAPI.getLast_updated());
        item.setPrice(itemAPI.getPrice());
        // item.setSeller(getUser(itemAPI.getSeller_id()));
        item.setSoldQuantity(itemAPI.getSold_quantity());
        item.setSubTitle(itemAPI.getSubtitle());
        item.setTitle(itemAPI.getTitle());

        return item;
    }

}
