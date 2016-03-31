package org.hiscox.service;

import java.io.IOException;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.GenericEntity;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPException;

import org.hiscox.client.QuoteSoapClient;
import org.hiscox.exception.SoapConnectionException;
import org.hiscox.model.ApplicationArea;
import org.hiscox.model.ApplicationAreaV2;
import org.hiscox.model.QuoteGenerator;
import org.hiscox.model.QuoteGeneratorResponse;
import org.hiscox.model.RatePlan;
import org.hiscox.reststub.QuoteDataStub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class QuoteService {

	private Map<Long, ApplicationArea> appArea = QuoteDataStub.getAllAppArea() ; 
	private Map<Long, ApplicationAreaV2> appArea2 = QuoteDataStub.getAllAppAreaV2() ;
	static Logger LOGGER = LoggerFactory.getLogger(QuoteService.class);
	public QuoteService(){
		appArea.put(1L, new ApplicationArea(1, 12, 101, "ODEScreen", "Rq1","YourBusiness:ViewQuote1","hsx.USDCOrch.Synchronous:getPage3"));
		appArea.put(2L, new ApplicationArea(2, 23, 102, "ODEScreen1", "Rq2","YourBusiness:ViewQuote2","hsx.USDCOrch.Synchronous:getPage4"));
		
		appArea2.put(1L, new ApplicationAreaV2(1, 33, 201, "ODEScreen", "YourBusiness:ViewQuote1","hsx.USDCOrch.Synchronous:getPage3"));
		appArea2.put(2L, new ApplicationAreaV2(2, 55, 202, "ODEScreen1", "YourBusiness:ViewQuote2","hsx.USDCOrch.Synchronous:getPage4"));
	}
	
	public List<ApplicationArea> viewAllQuote(){
		return new ArrayList<ApplicationArea>(appArea.values());
	}
	
	public List<ApplicationAreaV2> viewAllQuoteV2(){
		return new ArrayList<ApplicationAreaV2>(appArea2.values());
	}
	
	public ApplicationArea viewQuoteById(long messageId){
		return appArea.get(messageId);
	}
	
	public QuoteGeneratorResponse createQuote(QuoteGenerator request) throws SoapConnectionException, IOException{
		//area.setMessageId(appArea.size() * 2);
		//appArea.put(area.getMessageId(), area);
		if(request == null){
			LOGGER.error("Create quote request is empty","Invalid Request");
		}
		LOGGER.debug("Processing create quote service...", "Test");
		QuoteGeneratorResponse response;
		QuoteSoapClient qc = new QuoteSoapClient();
		String url = "http://anusha-nageswa:8088/mockQuote_Estimator_WSProviders_hiscoxQuoteEstimator_Binder?WSDL";
		LOGGER.info("Calling create quote soap service...", url);
			response =  qc.sendRequestReceiveResponse(url, request, QuoteGeneratorResponse.class );
			return response;
		
	}
	
	public ApplicationArea updateQuote(ApplicationArea area){
		if(area.getMessageId() == 0){
			return null;
		}
		appArea.put(area.getMessageId(), area);
		return area;
	}
}
