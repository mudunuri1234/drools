package com.sample;

import java.text.*;
import java.util.*;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatelessKnowledgeSession;

public class AccountTypeFinder {
	
	private static final long MILLSECS_PER_DAY = 24*60*60*1000;	
		
	public AccountTypeFinder() {
	}
	
//	public static void main(String args[]) throws Exception {
//		try {
//			AccountTypeFinder har = new AccountTypeFinder();
//			har.testDetermineType();			
//		} catch(Exception ex) {
//			ex.printStackTrace();
//		}		
//	}
//	
//	private void testDetermineType() throws Exception {
//		Date lastDate = getDateFromString("10-03-2011");
//		Date earlyDate = getDateFromString("30-03-2012");
//		long deltaDays = Math.abs(determineDaysDifference(lastDate, earlyDate));
//		System.out.println("---- deltaDays ---- **" + deltaDays);			
//		ReconciliationBean recBean = executeDetermineType("MOD", deltaDays);
//	}
		
	public ReconciliationBean executeDetermineType(String accountType, long deltaDays) throws Exception {
		System.out.println( "---- accountType ----" + accountType + "---- deltaDays ---- **" + deltaDays);
		ReconciliationBean recBean = new ReconciliationBean();
		recBean.setAccountType(accountType);
		recBean.setDeltaDays(deltaDays);		
    	
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        //kbuilder.add( ResourceFactory.newClassPathResource("Applicant.drl", DroolsTest.class), ResourceType.DRL );
        kbuilder.add(ResourceFactory.newFileResource("C:/projects/Learning/Drools/src/rules/DetermineType.drl"), ResourceType.DRL );            
        if ( kbuilder.hasErrors() ) {
        	System.out.println( kbuilder.getErrors().toString() );
        }
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
        StatelessKnowledgeSession ksession = kbase.newStatelessKnowledgeSession();
        ksession.execute(recBean);
        System.out.println( "---- Output Account Type is ----" + recBean.getDetermineTypeAction());   
        
        //List<String> actions = recBean.getActions();
        Map<String, String> actionMap = recBean.getActionMap();
        //System.out.println( "---- actions ----" + actions);   
        System.out.println( "---- actionMap ----" + actionMap);   
        
        return recBean;
    }
	
//	private long determineDaysDifference(Date lastDate, Date earlyDate) throws Exception {	
//		long deltaDays = (lastDate.getTime() - earlyDate.getTime())/MILLSECS_PER_DAY;
//		return deltaDays;
//	}
//	
//	private Date getDateFromString(String stringData) throws Exception {	
//		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
//		Date lastDate = (Date) formatter.parse(stringData); 
//		return lastDate;
//	}

}
