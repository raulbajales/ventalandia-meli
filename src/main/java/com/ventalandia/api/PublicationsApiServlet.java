package com.ventalandia.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;
import com.ventalandia.domain.Item;
import com.ventalandia.framework.util.MapBuilder;
import com.ventalandia.meli.pesistence.ItemRepository;
import com.ventalandia.meli.service.AuthContext;

@Path("/api/publications")
public class PublicationsApiServlet {

    private static final Logger LOGGER = Logger.getLogger(PublicationsApiServlet.class.getName());
    private ItemRepository itemRepository;

    @Inject
    public PublicationsApiServlet(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Object getPublications() {

        long meliUserId = AuthContext.getToken().getMeliId();
        LOGGER.info("getting publications of: " + meliUserId);
        List<Item> itemsBySeller = itemRepository.getItemsBySeller(meliUserId);
        List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();

        for (Item item : itemsBySeller) {
            Map<String, Object> itemMap = MapBuilder.build().putValue("id", item.getMeliId()).putValue("title", item.getTitle()).putValue("pictureUrl", item.getPictureUrl());
            items.add(itemMap);
        }

        return items;
    }

}