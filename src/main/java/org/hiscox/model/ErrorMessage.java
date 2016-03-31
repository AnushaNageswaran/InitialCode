package org.hiscox.model;

public class ErrorMessage {
	private String errorMessage;
	private int errorCode;
	private String documentation;
	
	public ErrorMessage(){
	
		
	}
	
	public ErrorMessage(int errorCode, String errorMessage, String documentaion){
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.documentation = documentaion;
		
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getDocumentation() {
		return documentation;
	}
	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}
}
