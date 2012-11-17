package com.ventalandia.service;

import java.util.Date;
import java.util.List;

import javax.jdo.Query;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.inject.Inject;
import com.ventalandia.framework.persistence.JdoRepository;
import com.ventalandia.framework.persistence.PersistenceManagerProvider;

/**
 * 
 * @author msulik
 * 
 */
public class NewsFeedRepository extends JdoRepository<NewsFeed> {

	private static final int OFFSET = 20;

	@Inject
	public NewsFeedRepository(PersistenceManagerProvider persistenceManagerProvider) {
		super(persistenceManagerProvider);
	}

	public List<NewsFeed> find(long userId, Date since, Integer fromPage, Integer offset2) {
		return this.find(userId, since, 0, OFFSET);
	}

	public List<NewsFeed> find(long userId, Date since, int from, int offset) {
		if (from < 0) {
			throw new IllegalArgumentException("'From' must be > 0.");
		}

		String dateFilter = "";
		String dateDefinition = "";
		
		if(since!=null){
		    dateFilter = " && date >= since";
	        dateDefinition = ", Date since";
		}
		
		Query query = this.createQuery();

		query.setOrdering("date desc");
		query.setRange(from, from + offset);
		if(since!=null){
		    query.declareImports("import java.util.Date");
		}
        query.setFilter("userId == aMeliId" + dateFilter);
        query.declareParameters("Long aMeliId" + dateDefinition);

		List<NewsFeed> list = (since != null) ? this.list(query, userId, since):this.list(query, userId);
		
        return list;
	}

	@Override
	protected void afterAdd(NewsFeed newsFeed) {

	}

	/**
	 * Get NewsFeed by id and meliUser. 
	 * @param newsId
	 * @param meliUserId
	 * @return
	 */
	public NewsFeed getByIdAndMeliId(Long newsId, Long meliUserId) {

		Key key = KeyFactory.createKey(NewsFeed.class.getSimpleName(),newsId);
		
		Query query = this.createQuery();
		query.setFilter("key == aKey && userId == aMeliId");
		query.declareParameters(Key.class.getName() +" aKey, Long aMeliId");
		
		List<NewsFeed> list = this.list(query, key, meliUserId);
		
		return list.isEmpty() ? null : list.get(0);
	}

	
	/**
	 * 
	 * @param meliBuyerId
	 * @param meliItemId
	 * @return
	 */
	public NewsFeed getByBuyerAndItem(Long meliBuyerId, String meliItemId){
	    
	    Query query = this.createQuery();
        query.setFilter("buyerId == meliBuyerId && itemId == meliItemId");
        query.declareParameters("Long meliBuyerId, String meliItemId");
        List<NewsFeed> list = this.list(query, meliBuyerId, meliItemId);
        
        return list.isEmpty() ? null : list.get(0);
	}

    public List<NewsFeed> findByContent(String[] keys) {
        return this.findByContent(keys, -1, -1);
    }

   /**
     * Perform a search based on the elements contains on the title. As there is
     * not such thing like SQL 'like' operator and search-based-text
     * functionality provided by GAE/J the search is based on a hook on the
     * elements on the content ('keys' attribute) provided by the resources from MELI (i.e.: questions).
     * If there is not match for keys it will return an empty {@link List}.
     * 
     * @param keys
     * @return a {@link List} of {@link NewsFeed}s which contains all keys on the
     *         content.
     */
    // TODO: add user id (owner) to the filter
    public List<NewsFeed> findByContent(String[] keys, int fromIndex, int toIndex) {
        Query query = this.createQuery();

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < keys.length; i++) {
            String key = keys[i];
            
            if (key == null || key.isEmpty()) {
                continue;
            }
            
            builder.append("keys.contains('" + key + "')");

            builder.append(" & ");
        }

        query.setFilter(builder.toString());
        query.setOrdering("date desc");

        if (fromIndex > -1) {
            query.setRange(fromIndex, toIndex);
        }

        return this.list(query);
    }

	
}
