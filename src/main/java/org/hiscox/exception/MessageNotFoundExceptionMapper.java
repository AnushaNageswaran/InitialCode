package org.hiscox.exception;


import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.hiscox.model.ErrorMessage;

@SuppressWarnings("rawtypes")
@Provider
public class MessageNotFoundExceptionMapper implements ExceptionMapper<MessageNotFoundException> {

    @Override
    public Response toResponse(MessageNotFoundException ex) {

        ErrorMessage error = new ErrorMessage(100, ex.getMessage() ,"www.hiscox.com");

        return Response.status(Status.SERVICE_UNAVAILABLE)
                .entity(error)
                .build();
    }

}