package com.ventalandia.meli.pesistence;

import java.util.List;

import javax.jdo.Query;

import com.google.inject.Inject;
import com.ventalandia.domain.Question;
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
    public List<Question> getQuestionsByItemAndUserMeliId(String itemMeliId, long userMeliId) {

        Query query = this.createQuery();
        query.setFilter("item.meliId == itemMeliId  seller.meliId == userMeliId && read == false");
        query.declareParameters("String itemMeliId, "+Long.class.getName() + " userMeliId");

        return (List<Question>) query.execute(itemMeliId,userMeliId);
    }	

}
