package com.ventalandia.domain.helper;

import java.util.Date;

import com.ventalandia.meli.api.notification.Answer;
import com.ventalandia.meli.api.notification.Question;
import com.ventalandia.meli.api.notification.UserFrom;

public class QuestionHelper {

    
    public static Question create(){
        
        Question question = new Question();
        question.setAnswer(getAnswer());
        question.setDate_created(new Date());
        question.setFrom(new UserFrom());
        question.setId(2455498075l);
        question.setItem_id("MLA430494065");
        question.setSeller_id(86898669);
        question.setStatus("ANSWERED");
        question.setText("Hola, trabajan los sabados?");
        question.setUser_id(1234l);
        return question;
    }

    public static Answer getAnswer() {
        Answer answer = new Answer();
        answer.setDate_created(new Date());
        answer.setStatus("ACTIVE");
        answer.setText("GERMANTANO  Hola , si estamos de 10 a 13 el sabado. saludos.sd");
        return answer;
    }

}