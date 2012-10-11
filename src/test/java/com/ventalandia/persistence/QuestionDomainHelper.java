package com.ventalandia.persistence;

import com.ventalandia.domain.Question;
import com.ventalandia.domain.helper.UserHelper;

/**
 * 
 * @author msulik
 * 
 */
public class QuestionDomainHelper {

    public static Question create() {
        Question question = new Question();

        question.setMeliId(1234L);
        question.setClient(UserHelper.create());
        question.setItem(ItemDomainHelper.createItem());
        question.setSeller(UserHelper.create());

        return question;
    }

}
