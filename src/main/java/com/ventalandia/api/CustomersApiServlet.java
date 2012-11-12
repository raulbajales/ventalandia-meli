package com.ventalandia.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ventalandia.framework.util.MapBuilder;
/**
 * 
 * @author german
 *
 */
@Path("/api/customers")
public class CustomersApiServlet {

   
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Object getCustomers(){
        
        List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
        
        result.add(MapBuilder.build().putValue("id",12345).putValue("nickName", "ICLACREYO").putValue("pictureUrl", "http://domain.com/myLogo.png"));
        result.add(MapBuilder.build().putValue("id",12346).putValue("nickName", "NICEASS").putValue("pictureUrl", "http://domain.com/myLogo.png"));
        
        return result;
        
    }
    
    
}
