package com.ventalandia.service;

import com.google.inject.Inject;
import com.ventalandia.domain.Item;
import com.ventalandia.domain.transformer.ItemTransformer;
import com.ventalandia.meli.pesistence.ItemRepository;
import com.ventalandia.meli.service.MeliService;

public class ItemServiceImpl implements ItemService {

    private ItemTransformer itemTransformer;
    private ItemRepository itemRepository;
    private MeliService meliService;

    @Inject
    public ItemServiceImpl(ItemTransformer itemTransformer, ItemRepository itemRepository, MeliService meliService) {
        super();
        this.itemTransformer = itemTransformer;
        this.itemRepository = itemRepository;
        this.meliService = meliService;
    }

    @Override
    public Item getByMeliId(String itemId) {

        Item item = itemRepository.getByMeliId(itemId);

        if (item == null) {
            com.ventalandia.meli.api.notification.Item itemFromMELI = meliService.getEntityFromMELI("/items/" + itemId, com.ventalandia.meli.api.notification.Item.class);
            item = itemTransformer.transform(itemFromMELI);
        }

        return item;
    }

}
