package com.ventalandia.domain.transformer;

import com.google.inject.Inject;
import com.ventalandia.domain.Answer;
import com.ventalandia.domain.Item;
import com.ventalandia.domain.User;
import com.ventalandia.meli.api.notification.Question;
import com.ventalandia.service.ItemService;

public class QuestionTransformer implements Transformer<Question, com.ventalandia.domain.Question> {

    private ItemService itemService;

    @Inject
    public QuestionTransformer(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public com.ventalandia.domain.Question transform(Question in) {

        User seller = null;

        com.ventalandia.domain.Question result = new com.ventalandia.domain.Question();
        Item item = itemService.getByMeliId(in.getItem_id());

        if (in.isAnswered()) {

            Answer answer = new Answer();
            answer.setCreationDate(in.getAnswer().getDate_created());
            answer.setStatus(in.getAnswer().getStatus());
            answer.setText(in.getAnswer().getText());
            result.setAnswer(answer);
        }

        result.setSeller(seller);
        result.setItem(item);
        result.setText(in.getText());
        result.setStatus(in.getStatus());
        result.setCreationDate(in.getDate_created());
        result.setMeliId(in.getId());

        return result;
    }

}
