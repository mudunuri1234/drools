package com.sample;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class DroolsManager {
	
	
	public void invokeRuleEngine() throws Exception {
		
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

}
