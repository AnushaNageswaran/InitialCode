package org.hiscox.resources;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import org.hiscox.exception.MessageNotFoundException;
import org.hiscox.exception.SoapConnectionException;
import org.hiscox.model.ApplicationArea;
import org.hiscox.model.ApplicationAreaV2;
import org.hiscox.model.QuoteGenerator;
import org.hiscox.model.QuoteGeneratorResponse;
import org.hiscox.service.QuoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;


@Path("/QuoteServices")
@Api(value="QuoteService", description="Hiscox Quote Restful Services")
public class QuoteResource{
	QuoteService qs = new QuoteService();
	static final Logger LOGGER = LoggerFactory.getLogger(QuoteResource.class);
	
	
	@GET
	@ApiOperation(value="just to test the sample api")
	@Consumes(MediaType.TEXT_XML)
	@Produces(MediaType.TEXT_XML)
	public List<ApplicationArea> viewAllQuoteAsXml() throws WebApplicationException{
		LOGGER.info("Get Quote API called for a xml based request info");
		LOGGER.debug("Get Quote API called for a xml based request debug ");
		LOGGER.error("Get Quote API called for a xml based request error ");
		return qs.viewAllQuote();
	}
	
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/v1+json")
	public List<ApplicationArea> viewAllQuoteAsJSON() throws WebApplicationException {
		LOGGER.debug("VIjsdhjshdjshdjshdjs");
		return qs.viewAllQuote();
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/v2+json")
	public List<ApplicationAreaV2> viewAllQuoteAsJSONV2() throws WebApplicationException{
		return qs.viewAllQuoteV2();
	}
	
	
	@GET
	@ApiOperation(value="Another just to test the sample api version2")
	@Path("/{messageId}")
	@Consumes(MediaType.TEXT_XML)
	@Produces(MediaType.TEXT_XML)
	public ApplicationArea viewQuoteByIdAsXml( @PathParam("messageId") long messageId) throws MessageNotFoundException, SoapConnectionException{
		ApplicationArea response = null;
		response = qs.viewQuoteById(messageId);
		if(response == null)
		{
			throw new MessageNotFoundException(messageId +" Not Found");
		}
		else{
			return qs.viewQuoteById(messageId);
		}
	}
	
	
	@POST
	@Consumes(MediaType.TEXT_XML)
	@Produces(MediaType.TEXT_XML)
	public QuoteGeneratorResponse createQuoteAsXml(QuoteGenerator request) throws SoapConnectionException, NullPointerException, IOException{
		if(request == null) {
			throw new NullPointerException();
		}
		return qs.createQuote(request);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public QuoteGeneratorResponse createQuoteAsJSON(QuoteGenerator request) throws SoapConnectionException,NullPointerException, IOException{
		if(request == null){
			throw new NullPointerException();
		}
		
		LOGGER.info("Create Quote API called for a JSON based request ");
		return qs.createQuote(request);
	}

	@GET
	@Path("/{messageId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ApplicationArea viewQuoteByIdAsJSON(@PathParam("messageId") long messageId) throws MessageNotFoundException,SoapConnectionException {
		ApplicationArea response = null;
		response = qs.viewQuoteById(messageId);
		if(response == null)
		{
			throw new MessageNotFoundException(messageId +" Not Found");
		}
		else{
			return qs.viewQuoteById(messageId);
		}
	}
	
	
	@PUT
	@Consumes(MediaType.TEXT_XML)
	@Produces(MediaType.TEXT_XML)
	public ApplicationArea updateQuoteAsXml(ApplicationArea appArea){
		return qs.updateQuote(appArea);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ApplicationArea updateQuoteAsJSON(ApplicationArea appArea){
		return qs.updateQuote(appArea);
	}
	
}
