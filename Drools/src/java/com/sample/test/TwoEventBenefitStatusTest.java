package com.sample.test;

import java.util.ArrayList;
import java.util.List;

import com.sample.AIMBenefitStatus;
import com.sample.AIMBenefitStatusGroup;
import com.sample.StatusOutput;
import com.sample.TwoEventBenefitStatusHelper;

public class TwoEventBenefitStatusTest {
	
	public static final void main(String[] args) throws Exception {
		try {
			TwoEventBenefitStatusTest twoEventBenefitStatusTest = new TwoEventBenefitStatusTest();	
			
			twoEventBenefitStatusTest.executeAllPositiveTests();
			
			twoEventBenefitStatusTest.executeAllNegetiveTests();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
    }

    public void executeAllPositiveTests() throws Exception {
        //Typical decision tables are used statelessly
    	TwoEventBenefitStatusHelper benefitStatusHelper = new TwoEventBenefitStatusHelper();
    	StatusOutput statusOutput = new StatusOutput();; 	
    	
    	statusOutput.setAction(null);
    	statusOutput = benefitStatusHelper.executeRules(testDataForEvent1Test1());
    	System.out.println( "Final Action Is: " + statusOutput.getAction());
    	
    	statusOutput.setAction(null);
    	statusOutput = benefitStatusHelper.executeRules(testDataForEvent1Test2());
    	System.out.println( "Final Action Is: " + statusOutput.getAction());
    	
    	statusOutput.setAction(null);
    	statusOutput = benefitStatusHelper.executeRules(testDataForEvent1Test3());
    	System.out.println( "Final Action Is: " + statusOutput.getAction());
    	
    	statusOutput.setAction(null);
    	statusOutput = benefitStatusHelper.executeRules(testDataForEvent2Test1());
    	System.out.println( "Final Action Is: " + statusOutput.getAction());
    	
    	statusOutput.setAction(null);
    	statusOutput = benefitStatusHelper.executeRules(testDataForEvent2Test2());
    	System.out.println( "Final Action Is: " + statusOutput.getAction());
    	
    	statusOutput.setAction(null);
    	statusOutput = benefitStatusHelper.executeRules(testDataForEvent2Test3());
    	System.out.println( "Final Action Is: " + statusOutput.getAction());
    	
    	statusOutput.setAction(null);
    	statusOutput = benefitStatusHelper.executeRules(testDataForEvent2Test4());
    	System.out.println( "Final Action Is: " + statusOutput.getAction());    	
    	
    	statusOutput.setAction(null);
    	statusOutput = benefitStatusHelper.executeRules(testDataForEvent2Test5());
    	System.out.println( "Final Action Is: " + statusOutput.getAction());
    	
    	statusOutput.setAction(null);
    	statusOutput = benefitStatusHelper.executeRules(testDataForEvent2Test6());
    	System.out.println( "Final Action Is: " + statusOutput.getAction());
    	
    	statusOutput.setAction(null);
    	statusOutput = benefitStatusHelper.executeRules(testDataForEvent2Test7());
    	System.out.println( "Final Action Is: " + statusOutput.getAction());
    	
    	statusOutput.setAction(null);
    	statusOutput = benefitStatusHelper.executeRules(testDataForEvent2Test8());
    	System.out.println( "Final Action Is: " + statusOutput.getAction());

    	statusOutput.setAction(null);
    	statusOutput = benefitStatusHelper.executeRules(testDataForEvent2Test9());
    	System.out.println( "Final Action Is: " + statusOutput.getAction());
    	
    	statusOutput.setAction(null);
    	statusOutput = benefitStatusHelper.executeRules(testDataForEvent2Test10());
    	System.out.println( "Final Action Is: " + statusOutput.getAction());
    	
    	statusOutput.setAction(null);
    	statusOutput = benefitStatusHelper.executeRules(testDataForEvent2Test11());
    	System.out.println( "Final Action Is: " + statusOutput.getAction());
    }
    
    public void executeAllNegetiveTests() throws Exception {
    	//Typical decision tables are used statelessly
    	TwoEventBenefitStatusHelper benefitStatusHelper = new TwoEventBenefitStatusHelper();
    	StatusOutput statusOutput = new StatusOutput();
    	
    	statusOutput.setAction(null);
    	statusOutput = benefitStatusHelper.executeRules(testDataForEvent2Test12());
    	System.out.println( "Final Action Is: " + statusOutput.getAction());
    	    	
    	statusOutput.setAction(null);
    	statusOutput = benefitStatusHelper.executeRules(testDataForEvent2Test13());
    	System.out.println( "Final Action Is: " + statusOutput.getAction());
    	
    	statusOutput.setAction(null);
    	statusOutput = benefitStatusHelper.executeRules(testDataForEvent2Test14());
    	System.out.println( "Final Action Is: " + statusOutput.getAction());
    	
    	statusOutput.setAction(null);
    	statusOutput = benefitStatusHelper.executeRules(testDataForEvent2Test15());
    	System.out.println( "Final Action Is: " + statusOutput.getAction());
    	
    	statusOutput.setAction(null);
    	statusOutput = benefitStatusHelper.executeRules(testDataForEvent2Test16());
    	System.out.println( "Final Action Is: " + statusOutput.getAction());     	
    	
    	statusOutput.setAction(null);
    	statusOutput = benefitStatusHelper.executeRules(testDataForEvent2Test17());
    	System.out.println( "Final Action Is: " + statusOutput.getAction());
    			
    	statusOutput.setAction(null);
    	statusOutput = benefitStatusHelper.executeRules(testDataForEvent2Test18());
		System.out.println( "Final Action Is: " + statusOutput.getAction());
				
		statusOutput.setAction(null);
		statusOutput = benefitStatusHelper.executeRules(testDataForEvent2Test19());
		System.out.println( "Final Action Is: " + statusOutput.getAction());
		
		statusOutput.setAction(null);
		statusOutput = benefitStatusHelper.executeRules(testDataForEvent2Test20());
		System.out.println( "Final Action Is: " + statusOutput.getAction());		
		
		statusOutput.setAction(null);
		statusOutput = benefitStatusHelper.executeRules(testDataForEvent2Test21());
		System.out.println( "Final Action Is: " + statusOutput.getAction());
		
		statusOutput.setAction(null);
		statusOutput = benefitStatusHelper.executeRules(testDataForEvent2Test22());
		System.out.println( "Final Action Is: " + statusOutput.getAction());
		
//		statusOutput = benefitStatusHelper.executeRules(testDataForEvent2Test10());
//    	System.out.println( "Final Action Is: " + statusOutput.getAction());
    }
    

    /**
     * Test case data for 2 events - POST_AND_SUSPEND_PLAN
     */    
    private AIMBenefitStatusGroup testDataForEvent1Test1() {
    	List<AIMBenefitStatus> statusList1 = new ArrayList<AIMBenefitStatus>();		
		statusList1.add(new AIMBenefitStatus("8888", AIMBenefitStatus.BenefitStatus.P));	
		statusList1.add(new AIMBenefitStatus("8888", AIMBenefitStatus.BenefitStatus.A));		
		AIMBenefitStatusGroup group1 = new AIMBenefitStatusGroup("66366311944220001", "A", statusList1);		
		return group1;
    }

    /**
     * Test case data for 2 events - POST_AND_ACTIVATE_PLAN
     */
    private AIMBenefitStatusGroup testDataForEvent1Test2() {
    	List<AIMBenefitStatus> statusList1 = new ArrayList<AIMBenefitStatus>();		
		statusList1.add(new AIMBenefitStatus("8888", AIMBenefitStatus.BenefitStatus.A));	
		statusList1.add(new AIMBenefitStatus("8888", AIMBenefitStatus.BenefitStatus.T));		
		AIMBenefitStatusGroup group1 = new AIMBenefitStatusGroup("66366311944220001", "A", statusList1);		
		return group1;
    }
    
    /**
     * Test case data for 2 events - POST_AND_ACTIVATE_PLAN
     */
    private AIMBenefitStatusGroup testDataForEvent1Test3() {
    	List<AIMBenefitStatus> statusList1 = new ArrayList<AIMBenefitStatus>();		
    	statusList1.add(new AIMBenefitStatus("8888", AIMBenefitStatus.BenefitStatus.P));
    	statusList1.add(new AIMBenefitStatus("8888", AIMBenefitStatus.BenefitStatus.T));	
		statusList1.add(new AIMBenefitStatus("8888", AIMBenefitStatus.BenefitStatus.A));			
		AIMBenefitStatusGroup group1 = new AIMBenefitStatusGroup("66366311944220001", "A", statusList1);		
		return group1;
    }
    
    /**
     * Test case data for 2 events - POST_AND_ACTIVATE_PLAN
     */
    private AIMBenefitStatusGroup testDataForEvent2Test1() {
		List<AIMBenefitStatus> statusList2 = new ArrayList<AIMBenefitStatus>();
		statusList2.add(new AIMBenefitStatus("8888", AIMBenefitStatus.BenefitStatus.P));
		statusList2.add(new AIMBenefitStatus("9999", AIMBenefitStatus.BenefitStatus.A));
		statusList2.add(new AIMBenefitStatus("9999", AIMBenefitStatus.BenefitStatus.T));		
		AIMBenefitStatusGroup group2 = new AIMBenefitStatusGroup("66366311944220001", "A", statusList2);		
		
    	return group2;
    }
    
    /**
     * Test case data for 2 events - POST_AND_SUSPEND_PLAN
     */
    private AIMBenefitStatusGroup testDataForEvent2Test2() {
		List<AIMBenefitStatus> statusList2 = new ArrayList<AIMBenefitStatus>();
		statusList2.add(new AIMBenefitStatus("8888", AIMBenefitStatus.BenefitStatus.T));
		statusList2.add(new AIMBenefitStatus("9999", AIMBenefitStatus.BenefitStatus.P));
		statusList2.add(new AIMBenefitStatus("9999", AIMBenefitStatus.BenefitStatus.A));		
		AIMBenefitStatusGroup group2 = new AIMBenefitStatusGroup("66366311944220001", "A", statusList2);		
		
    	return group2;
    }
    
    /**
     * Test case data for 2 events - POST_AND_ACTIVATE_PLAN
     */
    private AIMBenefitStatusGroup testDataForEvent2Test3() {
		List<AIMBenefitStatus> statusList2 = new ArrayList<AIMBenefitStatus>();
		statusList2.add(new AIMBenefitStatus("8888", AIMBenefitStatus.BenefitStatus.T));
		statusList2.add(new AIMBenefitStatus("9999", AIMBenefitStatus.BenefitStatus.A));
		statusList2.add(new AIMBenefitStatus("9999", AIMBenefitStatus.BenefitStatus.T));		
		AIMBenefitStatusGroup group2 = new AIMBenefitStatusGroup("66366311944220001", "A", statusList2);		
		
    	return group2;
    }    
    
    /**
     * Test case data for 2 events - POST_AND_ACTIVATE_PLAN
     */
    private AIMBenefitStatusGroup testDataForEvent2Test4() {
		List<AIMBenefitStatus> statusList2 = new ArrayList<AIMBenefitStatus>();
		statusList2.add(new AIMBenefitStatus("8888", AIMBenefitStatus.BenefitStatus.T));
		statusList2.add(new AIMBenefitStatus("9999", AIMBenefitStatus.BenefitStatus.P));
		statusList2.add(new AIMBenefitStatus("9999", AIMBenefitStatus.BenefitStatus.A));
		statusList2.add(new AIMBenefitStatus("9999", AIMBenefitStatus.BenefitStatus.T));		
		AIMBenefitStatusGroup group2 = new AIMBenefitStatusGroup("66366311944220001", "A", statusList2);		
		
    	return group2;
    }       
    
    /**
     * Test case data for 2 events - POST_AND_ACTIVATE_PLAN
     */
    private AIMBenefitStatusGroup testDataForEvent2Test5() {
		List<AIMBenefitStatus> statusList2 = new ArrayList<AIMBenefitStatus>();
		statusList2.add(new AIMBenefitStatus("8888", AIMBenefitStatus.BenefitStatus.D));
		statusList2.add(new AIMBenefitStatus("9999", AIMBenefitStatus.BenefitStatus.P));
		statusList2.add(new AIMBenefitStatus("9999", AIMBenefitStatus.BenefitStatus.A));
		statusList2.add(new AIMBenefitStatus("9999", AIMBenefitStatus.BenefitStatus.T));		
		AIMBenefitStatusGroup group2 = new AIMBenefitStatusGroup("66366311944220001", "A", statusList2);		
		
    	return group2;
    }    
    
    /**
     * Test case data for 2 events - POST_AND_SUSPEND_PLAN
     */
    private AIMBenefitStatusGroup testDataForEvent2Test6() {
		List<AIMBenefitStatus> statusList2 = new ArrayList<AIMBenefitStatus>();
		statusList2.add(new AIMBenefitStatus("8888", AIMBenefitStatus.BenefitStatus.T));
		statusList2.add(new AIMBenefitStatus("9999", AIMBenefitStatus.BenefitStatus.A));
		AIMBenefitStatusGroup group2 = new AIMBenefitStatusGroup("66366311944220001", "A", statusList2);		
		
    	return group2;
    }     
    
    /**
     * Test case data for 2 events - POST
     */
    private AIMBenefitStatusGroup testDataForEvent2Test7() {
		List<AIMBenefitStatus> statusList2 = new ArrayList<AIMBenefitStatus>();
		statusList2.add(new AIMBenefitStatus("8888", AIMBenefitStatus.BenefitStatus.D));
		statusList2.add(new AIMBenefitStatus("9999", AIMBenefitStatus.BenefitStatus.P));
		AIMBenefitStatusGroup group2 = new AIMBenefitStatusGroup("66366311944220001", "A", statusList2);		
		
    	return group2;
    }       
    
    /**
     * Test case data for 2 events - POST_AND_SUSPEND_PLAN
     */
    private AIMBenefitStatusGroup testDataForEvent2Test8() {
		List<AIMBenefitStatus> statusList2 = new ArrayList<AIMBenefitStatus>();
		statusList2.add(new AIMBenefitStatus("8888", AIMBenefitStatus.BenefitStatus.D));
		statusList2.add(new AIMBenefitStatus("9999", AIMBenefitStatus.BenefitStatus.P));
		statusList2.add(new AIMBenefitStatus("9999", AIMBenefitStatus.BenefitStatus.A));
		AIMBenefitStatusGroup group2 = new AIMBenefitStatusGroup("66366311944220001", "A", statusList2);		
		
    	return group2;
    } 
    
    /**
     * Test case data for 2 events - POST_AND_ACTIVATE_PLAN
     */
    private AIMBenefitStatusGroup testDataForEvent2Test9() {
		List<AIMBenefitStatus> statusList2 = new ArrayList<AIMBenefitStatus>();
		statusList2.add(new AIMBenefitStatus("8888", null));
		statusList2.add(new AIMBenefitStatus("9999", AIMBenefitStatus.BenefitStatus.T));
		AIMBenefitStatusGroup group2 = new AIMBenefitStatusGroup("66366311944220001", "A", statusList2);		
		
    	return group2;
    }
    
    /**
     * Test case data for 2 events - POST_AND_SUSPEND_PLAN
     */
    private AIMBenefitStatusGroup testDataForEvent2Test10() {
		List<AIMBenefitStatus> statusList2 = new ArrayList<AIMBenefitStatus>();
		statusList2.add(new AIMBenefitStatus("8888", null));
		statusList2.add(new AIMBenefitStatus("9999", AIMBenefitStatus.BenefitStatus.A));
		AIMBenefitStatusGroup group2 = new AIMBenefitStatusGroup("66366311944220001", "A", statusList2);	
		
    	return group2;
    }
    
    /**
     * Test case data for 2 events - REJECT
     */
    private AIMBenefitStatusGroup testDataForEvent2Test11() {
		List<AIMBenefitStatus> statusList2 = new ArrayList<AIMBenefitStatus>();
		statusList2.add(new AIMBenefitStatus("8888", AIMBenefitStatus.BenefitStatus.D));
		statusList2.add(new AIMBenefitStatus("8888", AIMBenefitStatus.BenefitStatus.P));
		statusList2.add(new AIMBenefitStatus("8888", AIMBenefitStatus.BenefitStatus.A));
		statusList2.add(new AIMBenefitStatus("8888", AIMBenefitStatus.BenefitStatus.T));		
		statusList2.add(new AIMBenefitStatus("9999", AIMBenefitStatus.BenefitStatus.D));
		statusList2.add(new AIMBenefitStatus("9999", AIMBenefitStatus.BenefitStatus.P));
		statusList2.add(new AIMBenefitStatus("9999", AIMBenefitStatus.BenefitStatus.A));
		statusList2.add(new AIMBenefitStatus("9999", AIMBenefitStatus.BenefitStatus.T));		
		AIMBenefitStatusGroup group2 = new AIMBenefitStatusGroup("66366311944220001", "A", statusList2);		
    	
		return group2;
    }
      
    
    /**
     * Test case data for 2 events - POST_AND_ACTIVATE_PLAN
     */
    private AIMBenefitStatusGroup testDataForEvent2Test12() {
		List<AIMBenefitStatus> statusList2 = new ArrayList<AIMBenefitStatus>();
		statusList2.add(new AIMBenefitStatus("9999", AIMBenefitStatus.BenefitStatus.A));
		statusList2.add(new AIMBenefitStatus("9999", AIMBenefitStatus.BenefitStatus.T));		
		statusList2.add(new AIMBenefitStatus("8888", AIMBenefitStatus.BenefitStatus.P));
		AIMBenefitStatusGroup group2 = new AIMBenefitStatusGroup("66366311944220001", "A", statusList2);		
		
    	return group2;
    }
    
    /**
     * Test case data for 2 events - POST_AND_SUSPEND_PLAN
     */
    private AIMBenefitStatusGroup testDataForEvent2Test13() {
		List<AIMBenefitStatus> statusList2 = new ArrayList<AIMBenefitStatus>();
		statusList2.add(new AIMBenefitStatus("9999", AIMBenefitStatus.BenefitStatus.P));
		statusList2.add(new AIMBenefitStatus("9999", AIMBenefitStatus.BenefitStatus.A));
		statusList2.add(new AIMBenefitStatus("8888", AIMBenefitStatus.BenefitStatus.T));		
		AIMBenefitStatusGroup group2 = new AIMBenefitStatusGroup("66366311944220001", "A", statusList2);		
		
    	return group2;
    }
    
    /**
     * Test case data for 2 events - POST_AND_ACTIVATE_PLAN
     */
    private AIMBenefitStatusGroup testDataForEvent2Test14() {
		List<AIMBenefitStatus> statusList2 = new ArrayList<AIMBenefitStatus>();
		statusList2.add(new AIMBenefitStatus("9999", AIMBenefitStatus.BenefitStatus.A));
		statusList2.add(new AIMBenefitStatus("9999", AIMBenefitStatus.BenefitStatus.T));		
		statusList2.add(new AIMBenefitStatus("8888", AIMBenefitStatus.BenefitStatus.T));
		AIMBenefitStatusGroup group2 = new AIMBenefitStatusGroup("66366311944220001", "A", statusList2);		
		
    	return group2;
    }    
    
    /**
     * Test case data for 2 events - POST_AND_ACTIVATE_PLAN
     */
    private AIMBenefitStatusGroup testDataForEvent2Test15() {
		List<AIMBenefitStatus> statusList2 = new ArrayList<AIMBenefitStatus>();
		statusList2.add(new AIMBenefitStatus("9999", AIMBenefitStatus.BenefitStatus.P));
		statusList2.add(new AIMBenefitStatus("9999", AIMBenefitStatus.BenefitStatus.A));
		statusList2.add(new AIMBenefitStatus("9999", AIMBenefitStatus.BenefitStatus.T));		
		statusList2.add(new AIMBenefitStatus("8888", AIMBenefitStatus.BenefitStatus.T));
		AIMBenefitStatusGroup group2 = new AIMBenefitStatusGroup("66366311944220001", "A", statusList2);		
		
    	return group2;
    }       
    
    /**
     * Test case data for 2 events - POST_AND_ACTIVATE_PLAN
     */
    private AIMBenefitStatusGroup testDataForEvent2Test16() {
		List<AIMBenefitStatus> statusList2 = new ArrayList<AIMBenefitStatus>();
		statusList2.add(new AIMBenefitStatus("9999", AIMBenefitStatus.BenefitStatus.P));
		statusList2.add(new AIMBenefitStatus("9999", AIMBenefitStatus.BenefitStatus.A));
		statusList2.add(new AIMBenefitStatus("9999", AIMBenefitStatus.BenefitStatus.T));		
		statusList2.add(new AIMBenefitStatus("8888", AIMBenefitStatus.BenefitStatus.D));
		AIMBenefitStatusGroup group2 = new AIMBenefitStatusGroup("66366311944220001", "A", statusList2);		
		
    	return group2;
    }       
    
    /**
     * Test case data for 2 events - POST_AND_SUSPEND_PLAN
     */
    private AIMBenefitStatusGroup testDataForEvent2Test17() {
		List<AIMBenefitStatus> statusList2 = new ArrayList<AIMBenefitStatus>();
		statusList2.add(new AIMBenefitStatus("9999", AIMBenefitStatus.BenefitStatus.A));
		statusList2.add(new AIMBenefitStatus("8888", AIMBenefitStatus.BenefitStatus.T));		
		AIMBenefitStatusGroup group2 = new AIMBenefitStatusGroup("66366311944220001", "A", statusList2);		
		
    	return group2;
    }    
    
    /**
     * Test case data for 2 events - Reverse the events test - POST
     */
    private AIMBenefitStatusGroup testDataForEvent2Test18() {
		List<AIMBenefitStatus> statusList2 = new ArrayList<AIMBenefitStatus>();
		statusList2.add(new AIMBenefitStatus("9999", AIMBenefitStatus.BenefitStatus.P));
		statusList2.add(new AIMBenefitStatus("8888", AIMBenefitStatus.BenefitStatus.D));		
		AIMBenefitStatusGroup group2 = new AIMBenefitStatusGroup("66366311944220001", "A", statusList2);		
		
    	return group2;
    }          
    
    /**
     * Test case data for 2 events - Reverse the events test - POST_AND_SUSPEND_PLAN
     */
    private AIMBenefitStatusGroup testDataForEvent2Test19() {
		List<AIMBenefitStatus> statusList2 = new ArrayList<AIMBenefitStatus>();
		statusList2.add(new AIMBenefitStatus("9999", AIMBenefitStatus.BenefitStatus.P));
		statusList2.add(new AIMBenefitStatus("9999", AIMBenefitStatus.BenefitStatus.A));
		statusList2.add(new AIMBenefitStatus("8888", AIMBenefitStatus.BenefitStatus.D));
		AIMBenefitStatusGroup group2 = new AIMBenefitStatusGroup("66366311944220001", "A", statusList2);		
		
    	return group2;
    }    
    
    /**
     * Test case data for 2 events - Reverse the events test - POST_AND_ACTIVATE_PLAN
     */
    private AIMBenefitStatusGroup testDataForEvent2Test20() {
		List<AIMBenefitStatus> statusList2 = new ArrayList<AIMBenefitStatus>();
		statusList2.add(new AIMBenefitStatus("9999", AIMBenefitStatus.BenefitStatus.T));
		statusList2.add(new AIMBenefitStatus("8888", null));		
		AIMBenefitStatusGroup group2 = new AIMBenefitStatusGroup("66366311944220001", "A", statusList2);		
		
    	return group2;
    }
  
    /**
     * Test case data for 2 events - Reverse the events test - POST_AND_SUSPEND_PLAN
     */
    private AIMBenefitStatusGroup testDataForEvent2Test21() {
		List<AIMBenefitStatus> statusList2 = new ArrayList<AIMBenefitStatus>();
		statusList2.add(new AIMBenefitStatus("9999", AIMBenefitStatus.BenefitStatus.A));
		statusList2.add(new AIMBenefitStatus("8888", null));		
		AIMBenefitStatusGroup group2 = new AIMBenefitStatusGroup("66366311944220001", "A", statusList2);		
		
    	return group2;
    }    
    
    /**
     * Test case data for 2 events - Reverse the events test - REJECT
     */
    private AIMBenefitStatusGroup testDataForEvent2Test22() {
		List<AIMBenefitStatus> statusList2 = new ArrayList<AIMBenefitStatus>();
		statusList2.add(new AIMBenefitStatus("9999", AIMBenefitStatus.BenefitStatus.D));
		statusList2.add(new AIMBenefitStatus("9999", AIMBenefitStatus.BenefitStatus.P));
		statusList2.add(new AIMBenefitStatus("9999", AIMBenefitStatus.BenefitStatus.A));
		statusList2.add(new AIMBenefitStatus("9999", AIMBenefitStatus.BenefitStatus.T));
		statusList2.add(new AIMBenefitStatus("8888", AIMBenefitStatus.BenefitStatus.D));
		statusList2.add(new AIMBenefitStatus("8888", AIMBenefitStatus.BenefitStatus.P));
		statusList2.add(new AIMBenefitStatus("8888", AIMBenefitStatus.BenefitStatus.A));
		statusList2.add(new AIMBenefitStatus("8888", AIMBenefitStatus.BenefitStatus.T));				
		AIMBenefitStatusGroup group2 = new AIMBenefitStatusGroup("66366311944220001", "A", statusList2);		    	
		return group2;
    }   
    
}
