package com.ventalandia.meli.pesistence;

import java.util.List;

import javax.jdo.Query;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.ventalandia.domain.Question;
import com.ventalandia.domain.User;
import com.ventalandia.framework.persistence.MeliJdoRepository;

public class QuestionRepository extends MeliJdoRepository<Question> {

	@SuppressWarnings("unchecked")
	public List<Question> getUnreadQuestionsByUserId(long userId) {

		Query query = this.createQuery();

		query.setFilter(" client == userKey && read == false");
		query.declareParameters(Key.class.getName() + " userKey");
		Key userKey = KeyFactory.createKey(User.class.getSimpleName(), userId);

		return (List<Question>) query.execute(userKey);
	}

}
