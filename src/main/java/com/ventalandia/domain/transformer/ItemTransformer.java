package com.ventalandia.domain.transformer;

import java.util.List;

import com.google.inject.Inject;
import com.ventalandia.domain.Currency;
import com.ventalandia.domain.Item;
import com.ventalandia.domain.User;
import com.ventalandia.meli.api.notification.Picture;
import com.ventalandia.service.CurrencyService;
import com.ventalandia.service.UserService;

/**
 * 
 * @author matias
 * 
 */
public class ItemTransformer implements Transformer<com.ventalandia.meli.api.notification.Item, Item> {

    private CurrencyService currencyService;
    private UserService userService;

    @Inject
    public ItemTransformer(CurrencyService currencyService, UserService userService) {
        this.currencyService = currencyService;
        this.userService = userService;
    }

    public Item transform(com.ventalandia.meli.api.notification.Item itemAPI) {
        
        Currency currency = currencyService.getByMeliId(itemAPI.getCurrency_id());
        User seller = userService.getByMeliId(itemAPI.getSeller_id());

        Item item = new Item();
        item.setAvailableQuantity(itemAPI.getAvailable_quantity());
        item.setBasePrice(itemAPI.getBase_price());
        item.setCreationDate(itemAPI.getDate_created());
        item.setCurrency(currency);
        item.setInitialQuantity(itemAPI.getInitial_quantity());
        item.setLastUpdated(itemAPI.getLast_updated());
        item.setPrice(itemAPI.getPrice());
        item.setSeller(seller);
        item.setSoldQuantity(itemAPI.getSold_quantity());
        item.setSubTitle(itemAPI.getSubtitle());
        item.setTitle(itemAPI.getTitle());
        item.setMeliId(itemAPI.getId());
        item.setStatus(itemAPI.getStatus());
        List<Picture> pictures = itemAPI.getPictures();
        if(pictures!=null && pictures.size()>0){
        	item.setPictureUrl(pictures.get(0).getUrl());
        }

        return item;
    }

}
