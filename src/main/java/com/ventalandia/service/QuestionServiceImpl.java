package com.ventalandia.service;

import java.util.List;

import com.google.inject.Inject;
import com.ventalandia.domain.Question;
import com.ventalandia.domain.User;
import com.ventalandia.domain.transformer.QuestionTransformer;
import com.ventalandia.meli.pesistence.QuestionRepository;
import com.ventalandia.meli.service.MeliService;

/**
 * 
 * @author gzanussi
 * 
 */
public class QuestionServiceImpl implements QuestionService {

    private QuestionTransformer questionTransformer;
    private UserService userService;
    private MeliService meliService;
    private QuestionRepository questionRepository;

    @Inject
    public QuestionServiceImpl(QuestionTransformer questionTransformer, UserService userService, MeliService meliService, QuestionRepository questionRepository) {
        this.questionTransformer = questionTransformer;
        this.userService = userService;
        this.meliService = meliService;
        this.questionRepository = questionRepository;
    }

	 /**
     * Gets question from MELI service.
     * 
     *@param questionId
     *@param userId
     */
    public Question getQuestionFromMeli(String questionId, long userId) {

        User client = userService.getByMeliId(userId);
        com.ventalandia.meli.api.notification.Question questionAPI = meliService.getEntityFromMELI("/questions/"+questionId, com.ventalandia.meli.api.notification.Question.class);
        Question question = questionTransformer.transform(questionAPI);
        question.setClient(client);
        questionRepository.add(question);

        return question;

    }

	/**
     * Gets unread questions by user id.
     * 
     * @param userId
     */
	@Override
	public List<Question> getUnreadQuestions(long userId) {
		
		return questionRepository.getUnreadQuestionsByUserId(userId);
		
	}
	
}
