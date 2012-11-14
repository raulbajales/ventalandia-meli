package com.ventalandia.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;
import com.ventalandia.framework.util.MapBuilder;
import com.ventalandia.meli.pesistence.QuestionRepository;
import com.ventalandia.meli.service.AuthContext;
import com.ventalandia.domain.User;
/**
 * 
 * @author german
 *
 */
@Path("/api/customers")
public class CustomersApiServlet {

    private static final Logger LOGGER = Logger.getLogger(NewsApiServlet.class.getName());
    
    private QuestionRepository questionRepository;
    
    @Inject
    public CustomersApiServlet(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Object getCustomers(){
    
        LOGGER.info("getting customers...");
        
        long meliUserId = AuthContext.getToken().getMeliId();
        List<User> customers = questionRepository.getBuyersByMeliSellerId(meliUserId);

        List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
        for (User user : customers) {
            result.add(MapBuilder.build().putValue("id",user.getMeliId()).putValue("nickName", user.getNickName()).putValue("pictureUrl", user.getPictureUrl()));
        }
        
        return result;
        
    }
    
    
}
