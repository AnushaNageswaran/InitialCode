package org.hiscox.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.StatusType;
import javax.xml.soap.SOAPException;

public class SoapConnectionException extends SOAPException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7637660915263597273L;

	/**
	 * 
	 */
	
	public SoapConnectionException(String message){
		super(message);
	}
	

}
