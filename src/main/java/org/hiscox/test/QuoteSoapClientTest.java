package org.hiscox.test;

import java.net.MalformedURLException;

import javax.xml.ws.Endpoint;

import org.junit.Test;

public class QuoteSoapClientTest {

	@Test  
	public void shouldCheckCreditCardValidity() throws MalformedURLException {    
	// Publishes the SOAP Web Service    
	//Endpoint endpoint = Endpoint.publish("http://localhost:8080/cardValidator", new CardValidator());    
	/*assertTrue(endpoint.isPublished());    
	assertEquals("http://schemas.xmlsoap.org/wsdl/soap/http", endpoint.getBinding().getBindingID());     
	// Data to access the web service    
	URL wsdlDocumentLocation = new URL("http://localhost:8080/cardValidator?wsdl");    
	String namespaceURI = "http://chapter21.javaee7.book.agoncal.org/";    
	String servicePart = "CardValidatorService";    
	String portName = "CardValidatorPort";    
	QName serviceQN = new QName(namespaceURI, servicePart);   
	 QName portQN = new QName(namespaceURI, portName);     
	// Creates a service instance    
	Service service = Service.create(wsdlDocumentLocation, serviceQN);    
	Validator cardValidator = service.getPort(portQN, Validator.class);     
	// Invokes the web service    
	CreditCard creditCard = new CreditCard("12341234", "10/10", 1234, "VISA");     
	assertFalse("Credit card should be valid", cardValidator.validate(creditCard));    
	creditCard.setNumber("12341233");    
	assertTrue("Credit card should not be valid", cardValidator.validate(creditCard));     
	// Unpublishes the SOAP Web Service    
	endpoint.stop();    
	assertFalse(endpoint.isPublished());  */
	}
	
}
