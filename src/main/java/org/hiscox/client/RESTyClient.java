package org.hiscox.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.LoggingFilter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.*;
import org.hiscox.model.QuoteGeneratorResponse;


public class RESTyClient {    
	public static void main(String[] args) throws Exception 
	{        
		ClientConfig config = new DefaultClientConfig();        
		config.getClasses().add(SoapProvider.class);       
		Client c = Client.create(config);        
		c.addFilter(new LoggingFilter());         
		MessageFactory messageFactory = MessageFactory.newInstance();        
		SOAPMessage message = messageFactory.createMessage();        
		SOAPPart soapPart = message.getSOAPPart();       
		SOAPEnvelope envelope = soapPart.getEnvelope();        
		SOAPBody body = envelope.getBody();        
		SOAPElement bodyElement = body.addChildElement(envelope.createName("QuoteGenerator", "", "http://anusha-nageswa:8088/mockQuote_Estimator_WSProviders_hiscoxQuoteEstimator_Binder"));        
	    bodyElement.addChildElement("AboutYourBusiness").addChildElement("Questions").addChildElement("code").addTextNode("SimilarInsurance");        
		message.saveChanges();        
		WebResource service = c.resource("http://anusha-nageswa:8088/mockQuote_Estimator_WSProviders_hiscoxQuoteEstimator_Binder");        
		// POST the request        
		ClientResponse cr = service.header("SOAPAction", "\"http://anusha-nageswa:8088/mockQuote_Estimator_WSProviders_hiscoxQuoteEstimator_Binder?WSDL/quoteGenerator\"").post(ClientResponse.class, message);        
		message = cr.getEntity(SOAPMessage.class);         
		JAXBContext ctx = JAXBContext.newInstance(QuoteGeneratorResponse.class);        
		Unmarshaller um = ctx.createUnmarshaller();        
		QuoteGeneratorResponse response = 
				(um.unmarshal(message.getSOAPPart().getEnvelope().getBody().extractContentAsDocument(), QuoteGeneratorResponse.class)).getValue();        
		//System.out.println("City : " + response.getGetCityWeatherByZIPResult().getCity());        
		//System.out.println("Temperature : " + response.getGetCityWeatherByZIPResult().getTemperature());    
		}
	
	}
