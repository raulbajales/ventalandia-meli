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

    @Inject
    private QuestionRepository questionRepository;

    @Inject
    private MeliQuestionService meliQuestionService;

    public void answer(long questionId, String text) {
        this.meliQuestionService.answer(questionId, text);

        Question question = this.questionRepository.getByMeliId(questionId);

        Answer answer = new Answer();
        answer.setCreationDate(new Date());
        answer.setStatus("ACTIVE"); // check status
        answer.setText(text);

        question.setAnswer(answer);

        this.questionRepository.update(question);
    }

}
