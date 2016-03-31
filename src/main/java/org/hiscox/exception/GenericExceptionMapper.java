package org.hiscox.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.hiscox.model.ErrorMessage;
@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> 
{		
	@Override 	
	public Response toResponse(Throwable ex) 
	{ 		
		Response response;

		 if(ex instanceof WebApplicationException ) {			
			 WebApplicationException webEx = (WebApplicationException)ex;
		        int cd = webEx.getResponse().getStatus();
		        ErrorMessage error = new ErrorMessage(cd, ex.getMessage() ,"www.hiscox.com");

		        response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_JSON)
		                .entity(error).build();
			 } else {
				 
				 ErrorMessage error = new ErrorMessage(500, ex.getMessage() ,"www.hiscox.com");
				 response = Response.status(Response.Status.INTERNAL_SERVER_ERROR)
			                .entity(error).build();
		 }
		 
		 return response;
		 
	}
	
}