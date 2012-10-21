package com.ventalandia.api;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.inject.Inject;
import com.ventalandia.service.AnswersService;

/**
 * 
 * @author msulik
 * 
 */
@Path("/api/answers")
public class AnswersApiServlet {

    private static final Logger LOGGER = Logger.getLogger(AnswersApiServlet.class.getName());

    @Inject
    private AnswersService answersService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response answer(AnswerApi answerApi) {
        try {
            LOGGER.info(answerApi.toString());
            this.answersService.answer(answerApi.getQuestion_id(), answerApi.getText());
        }
        catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error when answering a question...", e);
            return Response.serverError().build();
        }

        return Response.ok().build();
    }
}
