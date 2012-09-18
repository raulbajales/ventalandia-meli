package com.ventalandia.domain.helper;

import com.ventalandia.meli.api.notification.Question;

public class QuestionHelper {

    
    public static Question create(){
        
        Question question = new Question();

        return question;
    }
    
    
    
    private String getQuestionBody() {
        return "{\"id\":2455498075,\"answer\":{\"date_created\":\"2012-08-31T14:59:46.000-04:00\",\"status\":\"ACTIVE\",\"text\":\"GERMANTANO  Hola ," +
                " si estamos de 10 a 13 el sabado. saludos.sd\"},\"date_created\":\"2012-08-31T14:54:15.000-04:00\",\"item_id\":\"MLA430494065\",\"seller_id" +
                "\":86898669,\"status\":\"ANSWERED\",\"text\":\"Hola, trabajan los sabados?\"}";
    }
}
