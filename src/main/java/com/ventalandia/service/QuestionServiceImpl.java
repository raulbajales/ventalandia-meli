package com.ventalandia.service;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.ventalandia.domain.Question;
import com.ventalandia.domain.User;
import com.ventalandia.domain.transformer.QuestionTransformer;
import com.ventalandia.meli.api.notification.Notification;
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
    private NotificationService notificationService;

    
    
    @Inject
    public QuestionServiceImpl(QuestionTransformer questionTransformer, UserService userService, MeliService meliService, QuestionRepository questionRepository, NotificationService notificationService) {
		super();
		this.questionTransformer = questionTransformer;
		this.userService = userService;
		this.meliService = meliService;
		this.questionRepository = questionRepository;
		this.notificationService = notificationService;
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
	 * Gets unread questions by user meli id.
	 * 
	 * @param userMeliId
	 */
	@Override
	public List<Question> getUnreadQuestions(long userMeliId) {
		
		return questionRepository.getUnreadQuestionsByUserMeliId(userMeliId);
	}

	@Override
	public List<Question> getQuestionsFromMeliByMeliUser(long meliUserId) {
		
        User client = userService.getByMeliId(meliUserId);
        
        List<Notification> notifications = notificationService.getUnreadQuestionsBySellerId(meliUserId);
        List<Question> questions = new ArrayList<Question>();
        
        for (Notification notification : notifications) {
			
        	com.ventalandia.meli.api.notification.Question questionAPI = meliService.getEntityFromMELI(notification.getResource(), com.ventalandia.meli.api.notification.Question.class);
        	Question question = questionTransformer.transform(questionAPI);
        	question.setClient(client);
        	questionRepository.add(question);
        	questions.add(question);
		}
        

        return questions;
	}
	
}
