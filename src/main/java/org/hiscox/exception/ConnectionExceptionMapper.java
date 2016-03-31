package org.hiscox.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.hiscox.model.ErrorMessage;

@SuppressWarnings("rawtypes")
@Provider
public class ConnectionExceptionMapper implements ExceptionMapper<SoapConnectionException> {

    @Override
    public Response toResponse(SoapConnectionException ex) {
       // Response.StatusType type = getStatusType(ex);

        ErrorMessage error = new ErrorMessage(100, ex.getMessage() ,"www.hiscox.com");

        return Response.status(Status.SERVICE_UNAVAILABLE)
                .entity(error)
                .build();
    }

}