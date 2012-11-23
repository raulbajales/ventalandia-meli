package com.ventalandia.service;

import java.util.Date;

import com.google.inject.Inject;
import com.ventalandia.domain.Answer;
import com.ventalandia.domain.Question;
import com.ventalandia.meli.pesistence.QuestionRepository;
import com.ventalandia.meli.service.MeliQuestionService;

/**
 * 
 * @author msulik
 * 
 */
public class AnswersService {

    private QuestionRepository questionRepository;
    private NewsFeedRepository newsFeedRepository;
    private MeliQuestionService meliQuestionService;
    

    @Inject
    public AnswersService(QuestionRepository questionRepository, NewsFeedRepository newsFeedRepository, MeliQuestionService meliQuestionService) {
        super();
        this.questionRepository = questionRepository;
        this.newsFeedRepository = newsFeedRepository;
        this.meliQuestionService = meliQuestionService;
    }



    public void answer(long questionId, String text) {
        
        
        this.meliQuestionService.answer(questionId, text);

        Question question = this.questionRepository.getByMeliId(questionId);

        if (question.getAnswer() != null) {
            throw new RuntimeException("The question was answered: " + question);
        }
        
        Answer answer = new Answer();
        answer.setCreationDate(new Date());
        answer.setStatus("ACTIVE"); // TODO check status
        answer.setText(text);

        question.setAnswer(answer);

        this.questionRepository.update(question);
        
        boolean answered = questionRepository.hasUnAnsweredQuestions(question.getSeller());
        
        NewsFeed feed = newsFeedRepository.getByBuyerAndItem(question.getClient().getMeliId(), question.getItem().getMeliId());
        feed.setAnswered(answered);
        newsFeedRepository.update(feed);
        
        
    }

}
