package com.ventalandia.meli.pesistence;

import java.util.Collections;
import java.util.List;

import javax.jdo.Query;

import com.google.appengine.api.datastore.Key;
import com.google.inject.Inject;
import com.ventalandia.domain.Item;
import com.ventalandia.domain.User;
import com.ventalandia.framework.persistence.MeliJdoRepository;
import com.ventalandia.framework.persistence.PersistenceManagerProvider;

public class ItemRepositoryImpl extends MeliJdoRepository<Item> implements ItemRepository{

	@Inject
	public ItemRepositoryImpl(PersistenceManagerProvider persistenceManagerProvider) {
		super(persistenceManagerProvider);
	}

    @SuppressWarnings("unchecked")
    @Override
    public List<Item> getItemsBySeller(long meliUserId) {
        
        Query query = this.getPersistenceManager().newQuery("select from "+ User.class.getName());
        query.setFilter(" meliId == meliUserId");
        query.setUnique(true);
        query.declareParameters(Long.class.getName() + " meliUserId");
        User seller = (User) query.execute(meliUserId);
        
        List<Item> list = Collections.EMPTY_LIST;
        
        if(seller!=null){
            query = this.createQuery();
            query.setFilter(" seller == meliUser");
            query.declareParameters(Key.class.getName()+" meliUser");
            list = (List<Item>) query.execute(seller.getKey());
        }
        
        return list;
    }

}
