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
        
        Query query = this.getPersistenceManager().newQuery("select from "+ User.class.getName());
        query.setFilter(" meliId == meliUserId");
        query.setUnique(true);
        query.declareParameters(Long.class.getName() + " meliUserId");
        User seller = (User) query.execute(meliUserId);
        
        query = this.getPersistenceManager().newQuery("select client from "+ Question.class.getName());
        query.setFilter(" seller == meliUser");
        query.setGrouping("client");
        query.declareParameters(Key.class.getName()+" meliUser");
        
        return (List<User>) query.execute(seller.getKey());

    }

    /**
     * Loohing unanswered questions from a given user.
     * @param seller
     * @return
     */
    public boolean hasUnAnsweredQuestions(User seller) {
        
        Query query = this.getPersistenceManager().newQuery("select count(q) from "+ Question.class.getName()+" q");
        query.setFilter(" seller == meliUser && answer == null");
        query.declareParameters(Key.class.getName() + " meliUser" );
        Long count = (Long) query.execute(seller.getKey());
        
        return count > 0;
        
    }	

}
