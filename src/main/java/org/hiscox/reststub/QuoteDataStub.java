package org.hiscox.reststub;

import java.util.HashMap;
import java.util.Map;

import org.hiscox.model.ApplicationArea;
import org.hiscox.model.ApplicationAreaV2;

public class QuoteDataStub {

	private static Map<Long, ApplicationArea> appArea = new HashMap<>() ; 
	private static Map<Long, ApplicationAreaV2> appAreaV2 = new HashMap<>() ;
	
	public static Map<Long, ApplicationArea> getAllAppArea(){
		
		return appArea;
	}
	
public static Map<Long, ApplicationAreaV2> getAllAppAreaV2(){
		
		return appAreaV2;
	}
}
