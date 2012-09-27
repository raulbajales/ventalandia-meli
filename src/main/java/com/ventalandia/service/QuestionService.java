package com.ventalandia.service;

import java.util.List;

import com.ventalandia.domain.Question;

public interface QuestionService {

	 /**
     * Gets question from MELI service.
     * 
     *@param questionId
     *@param userId
     */
	Question getQuestionFromMeli(String questionId, long userId);

	/**
     * Gets unread questions by user id.
     * 
     * @param userId
     */
	List<Question> getUnreadQuestions(long userId);

}
