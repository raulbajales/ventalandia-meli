package com.ventalandia.domain.transformer;

import com.ventalandia.domain.Item;
import com.ventalandia.domain.User;
import com.ventalandia.meli.api.notification.Question;

public class QuestionTransformer implements Transformer<Question, com.ventalandia.domain.Question>{

    
    
    
    @Override
    public com.ventalandia.domain.Question transform(Question in) {

        com.ventalandia.domain.Question result = new com.ventalandia.domain.Question();        
        
        
//        User seller = getUser(in.getSeller_id());
//        Item item = getItem(in.getItem_id());
//
//        if (in.isAnswered()) {
//            result.setAnswer(getAnswer(in.getAnswer()));
//        }

        
//        result.setSeller(seller);
//        result.setText(in.getText());
//        result.setItem(item);
        result.setStatus(in.getStatus());
        result.setCreationDate(in.getDate_created());
        result.setMeliId(in.getId());
        
        return result;
    }
    

}
