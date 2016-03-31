package org.hiscox.exception;



public class MessageNotFoundException extends NullPointerException  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7670848744777100722L;

	public MessageNotFoundException(String message){
		super(message);
	}

}
