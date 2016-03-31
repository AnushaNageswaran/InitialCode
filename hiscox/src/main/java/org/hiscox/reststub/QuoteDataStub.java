package org.hiscox.reststub;

import java.util.HashMap;
import java.util.Map;

import org.hiscox.model.ApplicationArea;

public class QuoteDataStub {

	private static Map<Long, ApplicationArea> appArea = new HashMap<>() ; 
	
	public static Map<Long, ApplicationArea> getAllAppArea(){
		
		return appArea;
	}
}
