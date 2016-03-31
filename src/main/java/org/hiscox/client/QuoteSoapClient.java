package org.hiscox.client;

import java.io.IOException;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException; 
import javax.xml.bind.Marshaller; 
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.Node; 
import javax.xml.soap.SOAPBody; 
import javax.xml.soap.SOAPConnection; 
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope; 
import javax.xml.soap.SOAPException; 
import javax.xml.soap.SOAPMessage; 
import javax.xml.soap.SOAPPart;

import org.hiscox.exception.SoapConnectionException;
import org.hiscox.model.ObjectFactory;
import org.hiscox.model.QuoteGenerator;
import org.hiscox.resources.QuoteResource;
import org.hiscox.service.QuoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

 

public class QuoteSoapClient { 
 
    
    static final Logger LOGGER = LoggerFactory.getLogger(QuoteSoapClient.class);

 
    /**
     * Creates request message to be send to the SOAP server 
     * 
     * @param clazz 
     *            object to be marshaled 
     * @param userName 
     *            the user name 
     * @param password 
     *            the password 
     * @return the created soap message 
     * @throws SOAPException 
     *             if unable to create message or envelope 
     * @throws JAXBException 
     *             if unable to marshal the clazz 
     * @throws IOException 
     */ 
    public static <T> SOAPMessage createRequest(T clazz) throws SOAPException, JAXBException, IOException { 
 
        SOAPMessage message = createMessage(); 
        LOGGER.info("Creating Soap Message");
        //addAuthorization(message, userName, password); 
 
        SOAPEnvelope soapEnvelope = createEnvelope(message.getSOAPPart()); 
 
        SOAPBody soapBody = soapEnvelope.getBody(); 
        marshal(clazz, soapBody); 
        soapEnvelope.createName("his:quoteGenerator", "", "");

        message.saveChanges(); 
        
        /* Print the request message */
        System.out.print("Request SOAP Message = ");
        
        LOGGER.debug("Request SOAP Message = ", message);
        message.writeTo(System.out);
        System.out.println();
        return message; 
    } 
 
    /**
     * Creates SOAP message to be send to the SOAP server 
     * 
     * @return the created SOAP message 
     * @throws SOAPException 
     *             if unable to create soap message 
     */ 
    public static SOAPMessage createMessage() throws SOAPException { 
        MessageFactory messageFactory = MessageFactory.newInstance(); 
        SOAPMessage message = messageFactory.createMessage(); 
        return message; 
    } 
 
    
 
    /**
     * Creates SOAP envelope and adds namespace declaration and sets encoding 
     * style 
     * 
     * @param soapPart 
     *            the message part 
     * @return the SOAP envelope 
     * @throws SOAPException 
     */ 
    public static SOAPEnvelope createEnvelope(SOAPPart soapPart) 
            throws SOAPException { 
        SOAPEnvelope soapEnvelope = soapPart.getEnvelope(); 
        addNamespaceDeclaration(soapEnvelope); 
        
        //setEncodingStyle(soapEnvelope); 
        return soapEnvelope; 
    } 
 
    /**
     * Adds namespace declaration to the specified parameter 
     * <code>soapEnvelop</code> 
     * 
     * @param soapEnvelope 
     *            the SOAP envelope 
     * @throws SOAPException 
     */ 
    private static void addNamespaceDeclaration(SOAPEnvelope soapEnvelope) 
            throws SOAPException { 
        soapEnvelope.addNamespaceDeclaration("his", 
                "hiscoxQuoteEstimator"); 
         
    } 
 
  
    /**
     * Marshals the specified parameter <code>clazz</code> to the specified 
     * <code>soapBody</code> 
     * 
     * @param clazz 
     *            the object to be marshaled 
     * @param soapBody 
     *            the SOAP body, result of marshal 
     * @throws JAXBException 
     *             if marshaling failed 
     */ 
    public static <T> void marshal(T clazz, SOAPBody soapBody) 
            throws JAXBException { 
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz.getClass());

        Marshaller marshaller = jaxbContext.createMarshaller(); 
     // output pretty printed
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
       
       // QName qName = new QName("his", "QuoteGenerator");
        
        JAXBElement root = new JAXBElement(new QName("his:quoteGenerator"), (Class<T>) clazz.getClass(), clazz);
      
        marshaller.marshal(root, soapBody); 
    } 
 
    /**
     * Unmarshals the specified paramter <code>soapBody</code> to the specified 
     * <code>clazz</code> 
     * 
     * @param clazz 
     *            object to hold unmarashal result 
     * @param soapBody 
     *            the soap body to be unmarshalled 
     * @return the unmarashalled object 
     * @throws JAXBException 
     *             if unmarshal failed 
     */ 
    @SuppressWarnings("unchecked") 
    public static <T> T unmarshal(Class<T> clazz, SOAPBody soapBody) 
            throws JAXBException { 
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz); 
 
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller(); 
        Node bindElement = (Node) soapBody.getFirstChild(); 
        while (bindElement.getNodeType() != Node.ELEMENT_NODE) { 
            bindElement = (Node) bindElement.getNextSibling(); 
        }
        

        return unmarshaller.unmarshal(bindElement, clazz).getValue(); 
    } 
 
    /**
     * Sends the SOAP message request to the SOAP server 
     * 
     * @param url 
     *            the endpoint of the web service 
     * @param message 
     *            the SOAP message to be send 
     * @return the response, SOAP message 
     * @throws SOAPException 
     *             if unable to send request 
     */ 
    public static SOAPMessage sendRequest(String url, SOAPMessage message) 
            throws SOAPException { 
        SOAPConnection connection = null; 
        SOAPMessage response = null; 
        try { 
            connection = createConnection(); 
            MimeHeaders headers = message.getMimeHeaders();
            headers.addHeader("SOAPAction", "hiscoxQuoteEstimator"  + "quoteGenerator");
            LOGGER.info("Making a Soap request");
            response = connection.call(message, url); 
        } finally { 
            if (connection != null) { 
            	LOGGER.info("Closing the connection");
                closeConnection(connection); 
            } 
        } 
        return response; 
    } 
 
 
    /**
     * Creates a SOAP connection to the SOAP server 
     * 
     * @return the SOAPconnection object 
     * @throws SOAPException 
     *             if unable to create connection 
     */ 
    public static SOAPConnection createConnection() throws SOAPException { 
    	LOGGER.info("Creating Soap Connection");
    	SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory 
                .newInstance(); 
        SOAPConnection connection = soapConnectionFactory.createConnection(); 
        return connection; 
    } 
 
    /**
     * Closes the SOAP connection 
     * 
     * @param connection 
     *            the SOAP connection 
     * @throws SOAPException 
     *             if unable to close connection 
     */ 
    public static void closeConnection(SOAPConnection connection) 
            throws SOAPException { 
        connection.close(); 
    } 
 
    /**
     * Sends soap request to the SOAP server. Receives and unmarshals the 
     * response 
     * 
     * @param url 
     *            the SOAP server url(endpoint) 
     * @param userName 
     *            the user 
     * @param password 
     *            the password 
     * @param request 
     *            the request object 
     * @param response 
     *            the response class 
     * @return the expected object or null 
     * @throws IOException 
     * @throws SoapConnectionException 
     */ 
    public static <T, U> T sendRequestReceiveResponse(String url, 
             U request, Class<T> response) throws IOException, SoapConnectionException { 
        try { 
            SOAPMessage requestMsg = createRequest(request); 
            SOAPMessage responseMsg = sendRequest(url, requestMsg); 
            return unmarshal(response, responseMsg.getSOAPBody()); 
        } catch (SOAPException soapExp) { 
        	System.err.println("Error occurred while sending SOAP Request to Server");
        	LOGGER.error("Create Quote API called for a JSON based request caused a soap exception ", soapExp);
        	throw new SoapConnectionException("Error occurred while sending SOAP Request to Server");
        } catch (JAXBException jaxbExp) { 
        	System.err.println("Error occurred while sending SOAP Request to Server");
        	LOGGER.error("Create Quote API called for a JSON based request caused a jaxbexception ", jaxbExp);
        	jaxbExp.printStackTrace();
        } 
 
        return null; 
    } 
 
   
   
}
