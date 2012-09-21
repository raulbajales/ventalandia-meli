package com.ventalandia.service;

import com.ventalandia.domain.Question;

public interface QuestionService {

	Question getQuestionFromMeli(String questionId, long userId);
	
}
