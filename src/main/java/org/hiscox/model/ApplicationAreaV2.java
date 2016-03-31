package org.hiscox.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class ApplicationAreaV2 {

	private long messageNumber;
	private long transactionid;
	private long transmissionid;
	private Date creationDateTime;
	private String verb;
	//private String noun;
	private String senderid;
	private String  receiverid;
	
	public ApplicationAreaV2(){
		
	}
	
	public ApplicationAreaV2(long messageId, long transactionid, long transmissionid,String verb, String senderid, String receiverid){
		this.messageNumber = messageId;
		this.transactionid = transactionid;
		this.transmissionid = transmissionid;
		this.creationDateTime= new Date();
		this.verb= verb;
		//this.noun= noun;
		this.senderid= senderid;
		this.receiverid= receiverid;
		
	}
	
	public long getMessageNumber() {
		return messageNumber;
	}
	public void setMessageNumber(long messageNumber) {
		this.messageNumber = messageNumber;
	}
	public long getTransactionid() {
		return transactionid;
	}
	public void setTransactionid(long transactionid) {
		this.transactionid = transactionid;
	}
	public long getTransmissionid() {
		return transmissionid;
	}
	public void setTransmissionid(long transmissionid) {
		this.transmissionid = transmissionid;
	}
	public Date getDate() {
		return creationDateTime;
	}
	public void setDate(Date date) {
		this.creationDateTime = date;
	}
	public String getVerb() {
		return verb;
	}
	public void setVerb(String verb) {
		this.verb = verb;
	}
	/*public String getNoun() {
		return noun;
	}
	public void setNoun(String noun) {
		this.noun = noun;
	}*/
	public String getSenderid() {
		return senderid;
	}
	public void setSenderid(String senderid) {
		this.senderid = senderid;
	}
	public String getReceiverid() {
		return receiverid;
	}
	public void setReceiverid(String receiverid) {
		this.receiverid = receiverid;
	}
	
}
