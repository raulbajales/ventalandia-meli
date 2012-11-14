package com.ventalandia.meli.pesistence;

import java.util.List;

import javax.jdo.Query;

import com.google.appengine.api.datastore.Key;
import com.google.inject.Inject;
import com.ventalandia.domain.Question;
import com.ventalandia.domain.User;
import com.ventalandia.framework.persistence.MeliJdoRepository;
import com.ventalandia.framework.persistence.PersistenceManagerProvider;

public class QuestionRepository extends MeliJdoRepository<Question> {

	@Inject
	public QuestionRepository(PersistenceManagerProvider persistenceManagerProvider) {
		super(persistenceManagerProvider);
	}

	@SuppressWarnings("unchecked")
	public List<Question> getUnreadQuestionsByUserMeliId(long userMeliId) {

		Query query = this.createQuery();
		query.setFilter(" seller.meliId == userMeliId && read == false");
		query.declareParameters(Long.class.getName() + " userMeliId");

		return (List<Question>) query.execute(userMeliId);
	}
	
    @SuppressWarnings("unchecked")
    public List<Question> getQuestionsByItemAndUserMeliId(Key itemkey, Key buyerKey) {

        Query query = this.createQuery();
        query.setOrdering("creationDate desc");
        query.setFilter("item == itemkey && client == buyerKey");
        query.declareParameters(Key.class.getName()+" itemkey, "+Key.class.getName()+" buyerKey");

        return (List<Question>) query.execute(itemkey,buyerKey);
    }

    /**
     * 
     * @param meliUserId
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<User> getBuyersByMeliSellerId(long meliUserId) {
        
        Query query = this.getPersistenceManager().newQuery("select client from Question");
        query.setFilter(" seller.meliId == meliUserId");
        query.declareParameters(Long.class.getName() + " meliUserId");

        return (List<User>) query.execute(meliUserId);

    }	

}
