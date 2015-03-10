package com.sample.test;

import java.util.Arrays;

import org.drools.runtime.StatelessKnowledgeSession;

import com.sample.SingleEventBenefitStatusHelper;
import com.sample.Status;
import com.sample.StatusOutput;

public class SingleEventBenefitStatusTest {

	public static final void main(String[] args) throws Exception {
		try {
			SingleEventBenefitStatusTest singleEventBenefitStatusTest = new SingleEventBenefitStatusTest();
			singleEventBenefitStatusTest.executeAllSingleEventTests();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
    }

    public void executeAllSingleEventTests() throws Exception {
        //Typical decision tables are used statelessly
    	SingleEventBenefitStatusHelper benefitStatusHelper = new SingleEventBenefitStatusHelper();
        StatelessKnowledgeSession ksession = benefitStatusHelper.getKnowledgeSessionForDrools();      
        //Input and Output java objects
    	Status status = new Status();  
    	StatusOutput statusOutput = new StatusOutput();    	    	   	
		
    	//Event status = "Pending"
    	statusOutput.setAction(null);
    	String finalAction = testPendingStatusEvent("P", status, statusOutput, ksession);
        System.out.println( "Final Action Is: " + finalAction);  	
    	
    	//Event status = "Denied"
        statusOutput.setAction(null);
        finalAction = testPendingStatusEvent("D", status, statusOutput, ksession);
        System.out.println( "Final Action Is: " + finalAction);  
    	
    	//Event status = "Approved"
        statusOutput.setAction(null);
        finalAction = testPendingStatusEvent("A", status, statusOutput, ksession);
    	System.out.println( "Final Action Is: " + finalAction);  
    	
    	//Event status = "Terminated"
    	statusOutput.setAction(null);
    	finalAction = testPendingStatusEvent("T", status, statusOutput, ksession);
    	System.out.println( "Final Action Is: " + finalAction);  

    	//Event status = "Open"
    	statusOutput.setAction(null);
    	finalAction = testPendingStatusEvent("O", status, statusOutput, ksession);
    	System.out.println( "Final Action Is: " + finalAction);      	
    	
    	//Event status = "W" - To Test negative scenarios. 
    	statusOutput.setAction(null);
    	finalAction = testPendingStatusEvent("W", status, statusOutput, ksession);
    	System.out.println( "Final Action Is: " + finalAction);  
    	
    	//Event status = "Open"
    	statusOutput.setAction(null);
    	finalAction = testPendingStatusEvent("O", status, statusOutput, ksession);
    	System.out.println( "Final Action Is: " + finalAction);  
    }
    
    private String testPendingStatusEvent(String eventStatus, Status status,   
    		StatusOutput statusOutput, StatelessKnowledgeSession ksession) throws Exception {
        status.setStatus(eventStatus);
        ksession.execute( Arrays.asList(new Object[] {status, statusOutput}) );
        return statusOutput.getAction();
    }
    
}
