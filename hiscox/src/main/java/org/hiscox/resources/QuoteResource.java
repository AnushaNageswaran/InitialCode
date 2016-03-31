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
import javax.ws.rs.core.MediaType;
import javax.xml.soap.SOAPException;

import org.hiscox.model.ApplicationArea;
import org.hiscox.model.QuoteGenerator;
import org.hiscox.model.QuoteGeneratorResponse;
import org.hiscox.service.QuoteService;

@Path("/QuoteServices")
public class QuoteResource{
	QuoteService qs = new QuoteService();
	
	@GET
	@Consumes(MediaType.TEXT_XML)
	@Produces(MediaType.TEXT_XML)
	public List<ApplicationArea> viewAllQuoteAsXml(){
		return qs.viewAllQuote();
	}
	
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<ApplicationArea> viewAllQuoteAsJSON(){
		return qs.viewAllQuote();
	}
	
	
	@GET
	@Path("/{messageId}")
	@Consumes(MediaType.TEXT_XML)
	@Produces(MediaType.TEXT_XML)
	public ApplicationArea viewQuoteByIdAsXml(@PathParam("messageId") long messageId){
		return qs.viewQuoteById(messageId);
	}
	
	
	@POST
	@Consumes(MediaType.TEXT_XML)
	@Produces(MediaType.TEXT_XML)
	public QuoteGeneratorResponse createQuoteAsXml(QuoteGenerator request) throws SOAPException, IOException{
		return qs.createQuote(request);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public QuoteGeneratorResponse createQuoteAsJSON(QuoteGenerator request) throws SOAPException,IOException{
		return qs.createQuote(request);
	}

	@GET
	@Path("/{messageId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ApplicationArea viewQuoteByIdAsJSON(@PathParam("messageId") long messageId){
		return qs.viewQuoteById(messageId);
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
