package com.ventalandia.meli.pesistence;

import java.util.List;

import javax.jdo.Query;

import com.ventalandia.domain.Question;
import com.ventalandia.framework.persistence.MeliJdoRepository;

public class QuestionRepository extends MeliJdoRepository<Question> {

	@SuppressWarnings("unchecked")
	public List<Question> getUnreadQuestionsByUserMeliId(long userMeliId) {

		Query query = this.createQuery();
		query.setFilter(" seller.meliId == userMeliId && read == false");
		query.declareParameters(Long.class.getName() + " userMeliId");

		return (List<Question>) query.execute(userMeliId);
	}

}
