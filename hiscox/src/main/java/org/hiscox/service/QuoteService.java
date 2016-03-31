package org.hiscox.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPException;

import org.hiscox.client.QuoteSoapClient;
import org.hiscox.model.ApplicationArea;
import org.hiscox.model.QuoteGenerator;
import org.hiscox.model.QuoteGeneratorResponse;
import org.hiscox.model.RatePlan;
import org.hiscox.reststub.QuoteDataStub;


public class QuoteService {

	private Map<Long, ApplicationArea> appArea = QuoteDataStub.getAllAppArea() ; 
	
	public QuoteService(){
		appArea.put(1L, new ApplicationArea(1, 12, 101, "ODEScreen", "Rq1","YourBusiness:ViewQuote1","hsx.USDCOrch.Synchronous:getPage3"));
		appArea.put(2L, new ApplicationArea(2, 23, 102, "ODEScreen1", "Rq2","YourBusiness:ViewQuote2","hsx.USDCOrch.Synchronous:getPage4"));
	}
	
	public List<ApplicationArea> viewAllQuote(){
		return new ArrayList<ApplicationArea>(appArea.values());
	}
	
	public ApplicationArea viewQuoteById(long messageId){
		return appArea.get(messageId);
	}
	
	public QuoteGeneratorResponse createQuote(QuoteGenerator area) throws SOAPException, IOException{
		//area.setMessageId(appArea.size() * 2);
		//appArea.put(area.getMessageId(), area);
		
		QuoteGeneratorResponse response;
		QuoteSoapClient qc = new QuoteSoapClient();
		String url = "http://anusha-nageswa:8088/mockQuote_Estimator_WSProviders_hiscoxQuoteEstimator_Binder?WSDL";
		response =  qc.sendRequestReceiveResponse(url, area, QuoteGeneratorResponse.class );
		
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
