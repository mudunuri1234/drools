package com.sample;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.DecisionTableConfiguration;
import org.drools.builder.DecisionTableInputType;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatelessKnowledgeSession;

import com.sample.AIMBenefitStatus;
import com.sample.AIMBenefitStatusGroup;

public class TwoEventBenefitStatusHelper {
	
	/**
	 * Drools API to invoke the rule resources. This is an interface point between
	 * rule engine and application. Validate the data before invoking the rule engine.
	 * 
	 * @throws Exception - if there is any error.
	 */
    public StatusOutput executeRules(AIMBenefitStatusGroup aimBenefitStatusGroup) throws Exception {
    	
        //Typical decision tables are used statelessly
        StatelessKnowledgeSession ksession = getKnowledgeSessionForDrools();  
        
        //Getting the data from application.
        Map<String, Status> dataMap = getDataRequiredForRuleEngine(aimBenefitStatusGroup);
        Status1 status1 = (Status1) dataMap.get("status1");
        Status2 status2 = (Status2) dataMap.get("status2");
                     
        //Complete all Pre-Validations before calling rule engine.
        StatusOutput statusOutput = new StatusOutput();               
        if(status1 != null && status2 != null) {
	        if("true".equalsIgnoreCase(status1.getOpen()) || "true".equalsIgnoreCase(status2.getOpen())) {
	        	statusOutput.setAction("INVAID");
	        	return statusOutput;
	        }
    	}
        
        if(status1.getPlanStatus() == "C" || status1.getPlanStatus() == "T" || status1.getPlanStatus() == "V" || status1.getPlanStatus() == "N") {
        	statusOutput.setAction("POST");
        	return statusOutput;
        }
        
        //Completed all Pre-Validations, data is ready to invoke rule engine.
        //System.out.println( "------ Start Rule Engine call -------- ");
        String finalOutput = null;
        ksession.execute(Arrays.asList(new Object[] {status1, status2, statusOutput}));
        finalOutput = statusOutput.getAction();
        statusOutput.setAction(finalOutput);
        //System.out.println( "------ End of Rule Engine call -------- ");
               
        //Interchange the objects and see, if there is any data for it. List can have objects in any order. 
        if(finalOutput == null) {
        	//System.out.println( "------ Interchange the objects ---------- ");
        	Status1 status1New = getInterchangedEventDataForEvent1(status2, status1.getTotalEvents());
        	Status2 status2New = getInterchangedEventDataForEvent2(status1);
        	ksession.execute(Arrays.asList(new Object[] {status1New, status2New, statusOutput}));
        	finalOutput =  statusOutput.getAction();
            statusOutput.setAction(finalOutput);
        }
                     
        //Post Data Validations after rule engine call, if there is no output returned from rule engine.
        if(finalOutput == null || finalOutput == "") {
	        statusOutput.setAction(AIMBenefitStatusGroup.BenefitAction.REJECT.name());
	        if(status1.getTotalEvents() == 1) {
	        	//Set ACTION to REJECT, if no action is determined.
	        	statusOutput.setAction(AIMBenefitStatusGroup.RejectReason.SINGLE_EVENT_INVALID_COMBINATION.name());
	        }
	        if(status1.getTotalEvents() == 2) {
	        	//Set ACTION to REJECT, if no action is determined.
	        	statusOutput.setAction(AIMBenefitStatusGroup.RejectReason.MULTIPLE_EVENT_INVALID_COMBINATION.name());
	        }
        }
        
        return statusOutput;
    }
    
    /**
     * Drools specific code to invoke the rule engine 
     * @return StatelessKnowledgeSession - session used to execute rules.
     * @throws Exception - if there are any errors.
     */
    public StatelessKnowledgeSession getKnowledgeSessionForDrools() throws Exception {
    	DecisionTableConfiguration dtableconfiguration = KnowledgeBuilderFactory.newDecisionTableConfiguration();
        dtableconfiguration.setInputType( DecisionTableInputType.XLS );

        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add( ResourceFactory.newFileResource("C:/projects/Learning/Drools/src/rules/Book2.xls"),
                      ResourceType.DTABLE,
                      dtableconfiguration );

        if ( kbuilder.hasErrors() ) {
            System.err.println( kbuilder.getErrors() );
            return null;
        }

        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages( kbuilder.getKnowledgePackages() );

        //Typical decision tables are used statelessly
        StatelessKnowledgeSession ksession = kbase.newStatelessKnowledgeSession();  
        return ksession;
    }
    
    /**
     * This method prepares the data required for rule engine. All business rules are executed
     * againest this data to determine the final output.
     * 
     * @return Map<String, Status> - Final data to be passed to rule engine.
     */
    public Map<String, Status> getDataRequiredForRuleEngine(AIMBenefitStatusGroup group) {
    	List<AIMBenefitStatus> statusList = group.getBenefitStatuses();
    	int totalEvents = determineNumberOfEvents(group);
    	Map<String, Status> ruleDataMap = new HashMap<String, Status>();    	 
    	Status1 status1 = new Status1();  
    	Status2 status2 = new Status2();	 
    	status1.setTotalEvents(totalEvents);
    	status1.setPlanStatus(group.getPlanStatus());
    	     	
    	for(int i=0; i<statusList.size(); i++) {  		
    		AIMBenefitStatus aimBenefitStatus = statusList.get(i);
    		String benefitNumber = aimBenefitStatus.getBenefitNumber();
    		
    		if(status1.getBenefitNumber() == null) {
    			status1.setBenefitNumber(benefitNumber);
    		}
    		
    		if(status2.getBenefitNumber() == null && !benefitNumber.equalsIgnoreCase(status1.getBenefitNumber())) {
    			status2.setBenefitNumber(benefitNumber);
    		}
    		
    		if(benefitNumber.equalsIgnoreCase(status1.getBenefitNumber())) {    			
    			String value="";
    			if(aimBenefitStatus.getStatus() != null) {
    				value = aimBenefitStatus.getStatus().name();
    			}
    		 	if("D".equalsIgnoreCase(value)) {
    		 		status1.setDenied("true");
    		 	}	    		 	
    		 	if("P".equalsIgnoreCase(value)) {
    		 		status1.setPending("true");
    		 	}
    		 	if("A".equalsIgnoreCase(value)) {
    		 		status1.setApproved("true");	
    		 	}
    		 	if("T".equalsIgnoreCase(value)) {
    		 		status1.setTerminated("true");
    		 	}
    		 	if("O".equalsIgnoreCase(value)) {
    		 		status1.setOpen("true"); 	
    		 	}		       
    		}
    		
    		if(benefitNumber.equalsIgnoreCase(status2.getBenefitNumber())) {
    			String value = "";   			
    			if(aimBenefitStatus.getStatus() != null) {
    				value = aimBenefitStatus.getStatus().name();
    			}    			
    		 	if("D".equalsIgnoreCase(value)) {
    		 		status2.setDenied("true");
    		 	}	    		 	
    		 	if("P".equalsIgnoreCase(value)) {
    		 		status2.setPending("true");
    		 	}
    		 	if("A".equalsIgnoreCase(value)) {
    		 		status2.setApproved("true");	
    		 	}
    		 	if("T".equalsIgnoreCase(value)) {
    		 		status2.setTerminated("true");
    		 	}
    		 	if("O".equalsIgnoreCase(value)) {
    		 		status2.setOpen("true"); 	
    		 	}		       
    		}
    		
    		if(totalEvents == 1) {
    			ruleDataMap.put("status1", status1);
    		} else if (totalEvents == 2) {
    			ruleDataMap.put("status1", status1);
    			ruleDataMap.put("status2", status2);
    		}
    	}
    	
    	System.out.println("------ Total Number of events -----" + totalEvents);
    	System.out.println("------ Event 1 ----- Denied ---"  + status1.getDenied() + "--Pending--" + status1.getPending() + "--- Approved ---" + status1.getApproved() + "--- Terminated ---" + status1.getTerminated() + "--- Open ---" + status1.getOpen());
    	System.out.println("------ Event 2 ----- Denied ---"  + status2.getDenied() + "--Pending--" + status2.getPending() + "--- Approved ---" + status2.getApproved() + "--- Terminated ---" + status2.getTerminated() + "--- Open ---" + status2.getOpen());    	
    	return ruleDataMap;
    }
    
    /**
     * Determines the total number of events.
     * @param group - AIMBenefitStatusGroup
     * @return - total number of events
     */
    public int determineNumberOfEvents(AIMBenefitStatusGroup group) {    	
    	List<AIMBenefitStatus> statusList  = group.getBenefitStatuses();
   	 	Set<String> benefitNumberSet = new TreeSet<String>();		   	 	   	 	
	   	for(int i = 0; i<statusList.size(); i++) {
	   		AIMBenefitStatus aimBenefitStatus = statusList.get(i); 			   		
	   		benefitNumberSet.add(aimBenefitStatus.getBenefitNumber());	   		 
	   	}
	   	return benefitNumberSet.size();
    }
    
    private Status1 getInterchangedEventDataForEvent1(Status2 status2, int totalEvents) {  	
    	Status1 status1 = new Status1();
    	status1.setTotalEvents(totalEvents);
    	status1.setDenied(status2.getDenied());
 		status1.setPending(status2.getPending());
 		status1.setApproved(status2.getApproved());	
 		status1.setTerminated(status2.getTerminated());
 		status1.setOpen(status2.getOpen());	 		
 		return status1;
    }
    
    private Status2 getInterchangedEventDataForEvent2(Status1 status1) {
 		Status2 status2 = new Status2();
    	status2.setDenied(status1.getDenied());
 		status2.setPending(status1.getPending());
 		status2.setApproved(status1.getApproved());	
 		status2.setTerminated(status1.getTerminated());
 		status2.setOpen(status1.getOpen());	 		
 		return status2;
    }    
    
}
