package com.ventalandia.domain.transformer;

import com.google.inject.Inject;
import com.ventalandia.domain.Currency;
import com.ventalandia.domain.Item;
import com.ventalandia.meli.service.CurrencyService;

/**
 * 
 * @author matias
 * 
 */
public class ItemTransformer implements Transformer<com.ventalandia.meli.api.notification.Item, Item> {

    private CurrencyService currencyService;
    
    @Inject
    public ItemTransformer(CurrencyService currencyService) {
       this.currencyService = currencyService;
    }


    @Override
    public Item transform(com.ventalandia.meli.api.notification.Item itemAPI) {
        Currency currency = currencyService.getByMeliId(itemAPI.getCurrency_id());
        
        Item item = new Item();
        item.setAvailableQuantity(itemAPI.getAvailable_quantity());
        item.setBasePrice(itemAPI.getBase_price());
        item.setCreationDate(itemAPI.getDate_created());
        item.setCurrency(currency);
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
