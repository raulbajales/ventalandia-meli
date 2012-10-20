package com.ventalandia.api;

import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * 
 * @author msulik
 * 
 */
@Path("/api/answers")
public class AnswersApiServlet {

    private static final Logger LOGGER = Logger.getLogger(AnswersApiServlet.class.getName());

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response answer(AnswerApi answerApi) {
        LOGGER.info(answerApi.toString());
        return Response.ok().build();
    }
}
