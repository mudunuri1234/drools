package com.sample;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class TyingLinesLoansTestSuite {

	public TyingLinesLoansTestSuite() {		
	}
	
	public static void main(String[] args) {
		try {
			TyingLinesLoansTestSuite testSuite = new TyingLinesLoansTestSuite();
			
			ReconciliationBean reconBean = new ReconciliationBean();
			//testSuite.testTLLHamaIndicatorRule(reconBean);
			//testSuite.testQPCARIndicatorRule(reconBean);
			
			//testSuite.testInvalidAccountNumberRule(reconBean);			
			//testSuite.testAccountNumberRule(reconBean);			
						
			//testSuite.testTLLPhoneFlagRule(reconBean);
			//testSuite.testTLLAppraisalTypeRule(reconBean);
			
			//testSuite.testTLLPropertyValueRule(reconBean);
			//testSuite.testMortgageAndPrincipalBalanceRule(reconBean);
			
			//testSuite.testTLLSlidCodeRule1(reconBean);
			
			//testSuite.testTLLComStatementIndicatorRule1(reconBean);
			//testSuite.testTLLComStatementIndicatorRule2(reconBean);
			//testSuite.testTLLComStatementIndicatorRule3(reconBean);
			
			//testSuite.testTLLBillModeRule1(reconBean);
			//testSuite.testTLLBillModeRule2(reconBean);
			//testSuite.testTLLBillModeRule3(reconBean);
			
			//testSuite.testTLLPersonCodeRule1(reconBean);
			//testSuite.testTLLPersonCodeRule2(reconBean);
			//testSuite.testTLLPersonCodeRule3(reconBean);
			
			//testSuite.testTLLPropertyTypeCodeRule1(reconBean);
			
			//testSuite.testTLLOccupancyCodeRule1(reconBean);
			//testSuite.testTLLOccupancyCodeRule2(reconBean);
			
			//testSuite.testTLLLivingUnitsRule1(reconBean);			
			testSuite.testTLLLivingUnitsRule2(reconBean);
			
			Map<String, List<RuleDVO>> eventMap = reconBean.getEventMap();
			List<RuleDVO> alertsList = eventMap.get("ALERTS");
			List<RuleDVO> correctionsList = eventMap.get("CORRETCIONS");
			List<RuleDVO> shawUpdatesList = eventMap.get("SHAWUPDATES");
			List<RuleDVO> alltelUpdatesList = eventMap.get("ALLTELUPDATES");
			List<RuleDVO> mismatchList = eventMap.get("MISMATCHES");
			List<RuleDVO> harpUpdatesList = eventMap.get("HARPUPDATES");		
			
			printListData(alertsList, "Alerts");
			printListData(correctionsList, "Corrections");
			printListData(shawUpdatesList, "SHAW Updates");
			printListData(alltelUpdatesList, "Alltel Updates");
			printListData(mismatchList, "MISMATCHES");
			printListData(harpUpdatesList, "HARP Updates");
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void testTLLHamaIndicatorRule(ReconciliationBean reconBean) throws Exception {
		TyingLinesLoansProcess tllProcess = new TyingLinesLoansProcess();
		reconBean.setHamaIndicator("N");
		tllProcess.executeTLLProcess(reconBean);		
	}
	
	private void testQPCARIndicatorRule(ReconciliationBean reconBean) throws Exception {
		TyingLinesLoansProcess tllProcess = new TyingLinesLoansProcess();
		reconBean.setHamaIndicator("Y");
		
		//	Rule Data - Invalid QPC AR Indicator Rule
		reconBean.setQpcarIndicator("Y");		
		tllProcess.executeTLLProcess(reconBean);		
	}
		
	private void testInvalidAccountNumberRule(ReconciliationBean reconBean) throws Exception {
		TyingLinesLoansProcess tllProcess = new TyingLinesLoansProcess();
		reconBean.setHamaIndicator("Y");
		reconBean.setQpcarIndicator("II");	
		
		// Rule Data - Invalid AccountNumber Rule
		reconBean.setMssNumber("0000123456789");
		reconBean.setFirstMortgageAccountNumber("000012345678");
		
		tllProcess.executeTLLProcess(reconBean);		
	}
	
	private void testAccountNumberRule(ReconciliationBean reconBean) throws Exception {
		TyingLinesLoansProcess tllProcess = new TyingLinesLoansProcess();
		reconBean.setHamaIndicator("Y");
		reconBean.setMssNumber("0000123456789");
		reconBean.setFirstMortgageAccountNumber("0000123456789");
		
		// Rule Data - Invalid AccountNumber Rule
		reconBean.setSecondLienHolderAccountNumber("0000123456789");
		reconBean.setLcaAccountNumber("0000123456789");
		
		tllProcess.executeTLLProcess(reconBean);			
	}	
	
	private void testTLLPhoneFlagRule(ReconciliationBean reconBean) throws Exception {
		TyingLinesLoansProcess tllProcess = new TyingLinesLoansProcess();
		reconBean.setHamaIndicator("Y");
		reconBean.setQpcarIndicator("II");
		reconBean.setMssNumber("0000123456789");
		reconBean.setFirstMortgageAccountNumber("0000123456789");
		reconBean.setSecondLienHolderAccountNumber("0000123456789");
		reconBean.setLcaAccountNumber("0000123456789");
		//Sub procces runs only if either one is zero
		reconBean.setPriorMortgageBalance(new BigDecimal("10"));
		reconBean.setFirstMortgageCurrentPrincipalBalance(new BigDecimal("100"));
		//Subprocess data - MOD/HREFI
		//reconBean.setQpcarIndicator("Y");
		//reconBean.setPhoneFlag("4");
		
		// Rule Data - PhoneFlagRule
		reconBean.setPhoneFlag("4");
		//reconBean.setPhoneFlag("2");
		
		tllProcess.executeTLLProcess(reconBean);			
	}	
	
	private void testTLLAppraisalTypeRule(ReconciliationBean reconBean) throws Exception {
		TyingLinesLoansProcess tllProcess = new TyingLinesLoansProcess();
		reconBean.setHamaIndicator("Y");
		reconBean.setMssNumber("0000123456789");
		reconBean.setFirstMortgageAccountNumber("0000123456789");
		reconBean.setSecondLienHolderAccountNumber("0000123456789");
		reconBean.setLcaAccountNumber("0000123456789");
		//Sub procces runs only if either one is zero
		reconBean.setPriorMortgageBalance(new BigDecimal("10"));
		reconBean.setFirstMortgageCurrentPrincipalBalance(new BigDecimal("100"));
		//Subprocess data - MOD/HREFI
		//reconBean.setQpcarIndicator("Y");
		//reconBean.setPhoneFlag("4");
		
		// Rule Data - Appraisal Type is "" or null
		reconBean.setAppraisalType("");
		//reconBean.setAppraisalType(null);
		
		tllProcess.executeTLLProcess(reconBean);			
	}
	
	private void testTLLPropertyValueRule(ReconciliationBean reconBean) throws Exception {
		TyingLinesLoansProcess tllProcess = new TyingLinesLoansProcess();
		reconBean.setHamaIndicator("Y");
		reconBean.setQpcarIndicator("II");
		reconBean.setMssNumber("0000123456789");
		reconBean.setFirstMortgageAccountNumber("0000123456789");
		reconBean.setSecondLienHolderAccountNumber("0000123456789");
		reconBean.setLcaAccountNumber("0000123456789");
		//Sub procces runs only if either one is zero
		reconBean.setPriorMortgageBalance(new BigDecimal("10"));
		reconBean.setFirstMortgageCurrentPrincipalBalance(new BigDecimal("100"));
		//Subprocess data - MOD/HREFI
		//reconBean.setQpcarIndicator("Y");
		//reconBean.setPhoneFlag("4");
		reconBean.setAppraisalType("A");
		
		// Rule Data - PhoneFlagRule
		reconBean.setPropertyValue(new BigDecimal("0"));
		reconBean.setAppraisedValue(new BigDecimal("0"));
		
		tllProcess.executeTLLProcess(reconBean);	
	}
	
	private void testMortgageAndPrincipalBalanceRule(ReconciliationBean reconBean) throws Exception {
		TyingLinesLoansProcess tllProcess = new TyingLinesLoansProcess();
		reconBean.setHamaIndicator("Y");
		reconBean.setQpcarIndicator("II");
		reconBean.setMssNumber("0000123456789");
		reconBean.setFirstMortgageAccountNumber("0000123456789");
		reconBean.setSecondLienHolderAccountNumber("0000123456789");
		reconBean.setLcaAccountNumber("0000123456789");

		// Rule Data - Prior Mortgage Balance, on LCA, should be greater than zero (0)
		// 1st Mortgage Principal Balance is greater than zero (0)
		reconBean.setPriorMortgageBalance(new BigDecimal("0"));
		reconBean.setFirstMortgageCurrentPrincipalBalance(new BigDecimal("100"));
		//Subprocess data - MOD/HREFI
		reconBean.setQpcarIndicator("Y");
		reconBean.setPhoneFlag("4");
		
		tllProcess.executeTLLProcess(reconBean);	
	}
	
	private void testTLLSlidCodeRule1(ReconciliationBean reconBean) throws Exception {
		TyingLinesLoansProcess tllProcess = new TyingLinesLoansProcess();
		reconBean.setHamaIndicator("Y");
		reconBean.setQpcarIndicator("II");
		reconBean.setMssNumber("0000123456789");
		reconBean.setFirstMortgageAccountNumber("0000123456789");
		reconBean.setSecondLienHolderAccountNumber("0000123456789");
		reconBean.setLcaAccountNumber("0000123456789");
		//Sub procces runs only if either one is zero
		reconBean.setPriorMortgageBalance(new BigDecimal("10"));
		reconBean.setFirstMortgageCurrentPrincipalBalance(new BigDecimal("100"));
		//Subprocess data - MOD/HREFI
		//reconBean.setQpcarIndicator("Y");
		//reconBean.setPhoneFlag("4");
		reconBean.setAppraisalType("A");
		reconBean.setPropertyValue(null);
		
		// Rule Data - Slid code is blank update with Single
		reconBean.setSlidCode("");
		
		tllProcess.executeTLLProcess(reconBean);	
	}
	
	private void testTLLComStatementIndicatorRule1(ReconciliationBean reconBean) throws Exception {
		TyingLinesLoansProcess tllProcess = new TyingLinesLoansProcess();
		reconBean.setHamaIndicator("Y");
		reconBean.setQpcarIndicator("II");
		reconBean.setMssNumber("0000123456789");
		reconBean.setFirstMortgageAccountNumber("0000123456789");
		reconBean.setSecondLienHolderAccountNumber("0000123456789");
		reconBean.setLcaAccountNumber("0000123456789");
		//Sub procces runs only if either one is zero
		reconBean.setPriorMortgageBalance(new BigDecimal("10"));
		reconBean.setFirstMortgageCurrentPrincipalBalance(new BigDecimal("100"));
		//Subprocess data - MOD/HREFI
		//reconBean.setQpcarIndicator("Y");
		//reconBean.setPhoneFlag("4");
		reconBean.setAppraisalType("A");
		reconBean.setPropertyValue(null);
		reconBean.setSlidCode("SINGLE");
		
		// Rule Data - ComStatementIndicatorRule = 6, lcadue =1; billcycle=H01
		reconBean.setCombinedStatementIndicator(6);
		reconBean.setLcaDue(1);
		reconBean.setBillCycle("H011");
		
		
		ReconciliationBean reconBeanFromSIMO = tllProcess.executeTLLProcess(reconBean);		
		printMapData(reconBeanFromSIMO);		
	}
	
	private void testTLLComStatementIndicatorRule2(ReconciliationBean reconBean) throws Exception {
		TyingLinesLoansProcess tllProcess = new TyingLinesLoansProcess();
		reconBean.setHamaIndicator("Y");
		reconBean.setQpcarIndicator("II");
		reconBean.setMssNumber("0000123456789");
		reconBean.setFirstMortgageAccountNumber("0000123456789");
		reconBean.setSecondLienHolderAccountNumber("0000123456789");
		reconBean.setLcaAccountNumber("0000123456789");
		//Sub procces runs only if either one is zero
		reconBean.setPriorMortgageBalance(new BigDecimal("10"));
		reconBean.setFirstMortgageCurrentPrincipalBalance(new BigDecimal("100"));
		//Subprocess data - MOD/HREFI
		//reconBean.setQpcarIndicator("Y");
		//reconBean.setPhoneFlag("4");
		reconBean.setAppraisalType("A");
		reconBean.setPropertyValue(null);
		reconBean.setSlidCode("SINGLE");
		
		// Rule Data - ComStatementIndicatorRule = 6, lcadue =1; billcycle = H20
		reconBean.setCombinedStatementIndicator(6);
		reconBean.setLcaDue(20);
		reconBean.setBillCycle("H200");
				
		tllProcess.executeTLLProcess(reconBean);		
	}	
	
	private void testTLLComStatementIndicatorRule3(ReconciliationBean reconBean) throws Exception {
		TyingLinesLoansProcess tllProcess = new TyingLinesLoansProcess();
		reconBean.setHamaIndicator("Y");
		reconBean.setMssNumber("0000123456789");
		reconBean.setFirstMortgageAccountNumber("0000123456789");
		reconBean.setSecondLienHolderAccountNumber("0000123456789");
		reconBean.setLcaAccountNumber("0000123456789");
		//Sub procces runs only if either one is zero
		reconBean.setPriorMortgageBalance(new BigDecimal("10"));
		reconBean.setFirstMortgageCurrentPrincipalBalance(new BigDecimal("100"));
		//Subprocess data - MOD/HREFI
		//reconBean.setQpcarIndicator("Y");
		//reconBean.setPhoneFlag("4");
		reconBean.setAppraisalType("A");
		reconBean.setPropertyValue(null);
		reconBean.setSlidCode("SINGLE");
		
		// Rule Data - ComStatementIndicatorRule = 5; Bill Cycle is NOT SSR
		reconBean.setCombinedStatementIndicator(5);
		reconBean.setBillCycle("NOT SSR");
				
		tllProcess.executeTLLProcess(reconBean);				
	}
	
	private void testTLLBillModeRule1(ReconciliationBean reconBean) throws Exception {
		TyingLinesLoansProcess tllProcess = new TyingLinesLoansProcess();
		reconBean.setHamaIndicator("Y");
		reconBean.setMssNumber("0000123456789");
		reconBean.setFirstMortgageAccountNumber("0000123456789");
		reconBean.setSecondLienHolderAccountNumber("0000123456789");
		reconBean.setLcaAccountNumber("0000123456789");
		//Sub procces runs only if either one is zero
		reconBean.setPriorMortgageBalance(new BigDecimal("10"));
		reconBean.setFirstMortgageCurrentPrincipalBalance(new BigDecimal("100"));
		//Subprocess data - MOD/HREFI
		//reconBean.setQpcarIndicator("Y");
		//reconBean.setPhoneFlag("4");
		reconBean.setAppraisalType("A");
		reconBean.setPropertyValue(null);
		reconBean.setSlidCode("SINGLE");
		reconBean.setCombinedStatementIndicator(5);
		reconBean.setBillCycle("SSR");
		
		// Rule Data -  Bill Mode = 6 and Bill Cycle = H01 or H20
		reconBean.setBillMode("6");
		reconBean.setBillCycle("H01");
				
		tllProcess.executeTLLProcess(reconBean);			
	}
	
	private void testTLLBillModeRule2(ReconciliationBean reconBean) throws Exception {
		TyingLinesLoansProcess tllProcess = new TyingLinesLoansProcess();
		reconBean.setHamaIndicator("Y");
		reconBean.setMssNumber("0000123456789");
		reconBean.setFirstMortgageAccountNumber("0000123456789");
		reconBean.setSecondLienHolderAccountNumber("0000123456789");
		reconBean.setLcaAccountNumber("0000123456789");
		//Sub procces runs only if either one is zero
		reconBean.setPriorMortgageBalance(new BigDecimal("10"));
		reconBean.setFirstMortgageCurrentPrincipalBalance(new BigDecimal("100"));
		//Subprocess data - MOD/HREFI
		//reconBean.setQpcarIndicator("Y");
		//reconBean.setPhoneFlag("4");
		reconBean.setAppraisalType("A");
		reconBean.setPropertyValue(null);
		reconBean.setSlidCode("SINGLE");
		reconBean.setCombinedStatementIndicator(5);
		reconBean.setBillCycle("SSR");
		
		// Rule Data -  Bill Mode = 6 and Bill Cycle = H01 or H20
		reconBean.setBillMode("6");
		reconBean.setBillCycle("SSR");
				
		tllProcess.executeTLLProcess(reconBean);				
	}
	
	private void testTLLBillModeRule3(ReconciliationBean reconBean) throws Exception {
		TyingLinesLoansProcess tllProcess = new TyingLinesLoansProcess();
		reconBean.setHamaIndicator("Y");
		reconBean.setMssNumber("0000123456789");
		reconBean.setFirstMortgageAccountNumber("0000123456789");
		reconBean.setSecondLienHolderAccountNumber("0000123456789");
		reconBean.setLcaAccountNumber("0000123456789");
		//Sub procces runs only if either one is zero
		reconBean.setPriorMortgageBalance(new BigDecimal("10"));
		reconBean.setFirstMortgageCurrentPrincipalBalance(new BigDecimal("100"));
		//Subprocess data - MOD/HREFI
		//reconBean.setQpcarIndicator("Y");
		//reconBean.setPhoneFlag("4");
		reconBean.setAppraisalType("A");
		reconBean.setPropertyValue(null);
		reconBean.setSlidCode("SINGLE");
		reconBean.setCombinedStatementIndicator(5);
		reconBean.setBillCycle("SSR");
		
		// Rule Data -  Bill Mode = 6 and Bill Cycle = H01 or H20
		reconBean.setBillMode("9");
		reconBean.setBillCycle("H01");
				
		tllProcess.executeTLLProcess(reconBean);				
	}
	
	private void testTLLPersonCodeRule1(ReconciliationBean reconBean) throws Exception {
		TyingLinesLoansProcess tllProcess = new TyingLinesLoansProcess();
		reconBean.setHamaIndicator("Y");
		reconBean.setMssNumber("0000123456789");
		reconBean.setFirstMortgageAccountNumber("0000123456789");
		reconBean.setSecondLienHolderAccountNumber("0000123456789");
		reconBean.setLcaAccountNumber("0000123456789");
		//Sub procces runs only if either one is zero
		reconBean.setPriorMortgageBalance(new BigDecimal("10"));
		reconBean.setFirstMortgageCurrentPrincipalBalance(new BigDecimal("100"));
		//Subprocess data - MOD/HREFI
		//reconBean.setQpcarIndicator("Y");
		//reconBean.setPhoneFlag("4");
		reconBean.setAppraisalType("A");
		reconBean.setPropertyValue(null);
		reconBean.setSlidCode("SINGLE");
		reconBean.setCombinedStatementIndicator(5);
		reconBean.setBillCycle("SSR");

		reconBean.setBillMode("9");
		reconBean.setBillCycle("H011");
		
		// Rule Data -  Bill Mode = 6 and Bill Cycle = H01 or H20
		reconBean.setPersonCode("0");
				
		tllProcess.executeTLLProcess(reconBean);	
	}
	
	private void testTLLPersonCodeRule2(ReconciliationBean reconBean) throws Exception {
		TyingLinesLoansProcess tllProcess = new TyingLinesLoansProcess();
		reconBean.setHamaIndicator("Y");
		reconBean.setMssNumber("0000123456789");
		reconBean.setFirstMortgageAccountNumber("0000123456789");
		reconBean.setSecondLienHolderAccountNumber("0000123456789");
		reconBean.setLcaAccountNumber("0000123456789");
		//Sub procces runs only if either one is zero
		reconBean.setPriorMortgageBalance(new BigDecimal("10"));
		reconBean.setFirstMortgageCurrentPrincipalBalance(new BigDecimal("100"));
		//Subprocess data - MOD/HREFI
		//reconBean.setQpcarIndicator("Y");
		//reconBean.setPhoneFlag("4");
		reconBean.setAppraisalType("A");
		reconBean.setPropertyValue(null);
		reconBean.setSlidCode("SINGLE");
		reconBean.setCombinedStatementIndicator(5);
		reconBean.setBillCycle("SSR");
		reconBean.setBillMode("9");
		reconBean.setBillCycle("H011");
		
		// Rule Data -  Bill Mode = 6 and Bill Cycle = H01 or H20
		reconBean.setPersonCode("B");
		reconBean.setBillCycle("H01");
				
		tllProcess.executeTLLProcess(reconBean);		
	}
	
	private void testTLLPersonCodeRule3(ReconciliationBean reconBean) throws Exception {
		TyingLinesLoansProcess tllProcess = new TyingLinesLoansProcess();
		reconBean.setHamaIndicator("Y");
		reconBean.setMssNumber("0000123456789");
		reconBean.setFirstMortgageAccountNumber("0000123456789");
		reconBean.setSecondLienHolderAccountNumber("0000123456789");
		reconBean.setLcaAccountNumber("0000123456789");
		//Sub procces runs only if either one is zero
		reconBean.setPriorMortgageBalance(new BigDecimal("10"));
		reconBean.setFirstMortgageCurrentPrincipalBalance(new BigDecimal("100"));
		//Subprocess data - MOD/HREFI
		//reconBean.setQpcarIndicator("Y");
		//reconBean.setPhoneFlag("4");
		reconBean.setAppraisalType("A");
		reconBean.setPropertyValue(null);
		reconBean.setSlidCode("SINGLE");
		reconBean.setCombinedStatementIndicator(5);
		reconBean.setBillCycle("SSR");
		reconBean.setBillMode("9");
		reconBean.setBillCycle("H011");
		
		// Rule Data -  Bill Mode = 6 and Bill Cycle = H01 or H20
		reconBean.setPersonCode("B");
		reconBean.setBillCycle("H01");
		reconBean.setBillMode("66");
				
		tllProcess.executeTLLProcess(reconBean);	
	}
	
	private void testTLLPropertyTypeCodeRule1(ReconciliationBean reconBean) throws Exception {
		TyingLinesLoansProcess tllProcess = new TyingLinesLoansProcess();
		reconBean.setHamaIndicator("Y");
		reconBean.setMssNumber("0000123456789");
		reconBean.setFirstMortgageAccountNumber("0000123456789");
		reconBean.setSecondLienHolderAccountNumber("0000123456789");
		reconBean.setLcaAccountNumber("0000123456789");
		//Sub procces runs only if either one is zero
		reconBean.setPriorMortgageBalance(new BigDecimal("10"));
		reconBean.setFirstMortgageCurrentPrincipalBalance(new BigDecimal("100"));
		//Subprocess data - MOD/HREFI
		//reconBean.setQpcarIndicator("Y");
		//reconBean.setPhoneFlag("4");
		reconBean.setAppraisalType("A");
		reconBean.setPropertyValue(null);
		reconBean.setSlidCode("SINGLE");
		reconBean.setCombinedStatementIndicator(5);
		reconBean.setBillCycle("SSR");
		reconBean.setBillMode("9");
		reconBean.setBillCycle("H011");
		
		// Rule Data -  propertyTypeCode == 4 || == 6 || == 7 and QPAAR is not II
		reconBean.setPropertyTypeCode("4");
		reconBean.setQpcarIndicator("YY");		
						
		tllProcess.executeTLLProcess(reconBean);	
	}
	
	private void testTLLOccupancyCodeRule1(ReconciliationBean reconBean) throws Exception {
		TyingLinesLoansProcess tllProcess = new TyingLinesLoansProcess();
		reconBean.setHamaIndicator("Y");
		reconBean.setQpcarIndicator("II");
		reconBean.setMssNumber("0000123456789");
		reconBean.setFirstMortgageAccountNumber("0000123456789");
		reconBean.setSecondLienHolderAccountNumber("0000123456789");
		reconBean.setLcaAccountNumber("0000123456789");
		//Sub procces runs only if either one is zero
		reconBean.setPriorMortgageBalance(new BigDecimal("10"));
		reconBean.setFirstMortgageCurrentPrincipalBalance(new BigDecimal("100"));
		//Subprocess data - MOD/HREFI
		//reconBean.setQpcarIndicator("Y");
		//reconBean.setPhoneFlag("4");
		reconBean.setAppraisalType("A");
		reconBean.setPropertyValue(null);
		reconBean.setSlidCode("SINGLE");
		reconBean.setCombinedStatementIndicator(5);
		reconBean.setBillCycle("SSR");
		reconBean.setBillMode("9");
		reconBean.setBillCycle("H011");
		
		reconBean.setPropertyTypeCode("444");
		reconBean.setQpcarIndicator("YY");
		
		// Rule Data -  Occupancy code Blank or Null
		reconBean.setOccupancyCode("");				
						
		tllProcess.executeTLLProcess(reconBean);			
	}
	
	private void testTLLOccupancyCodeRule2(ReconciliationBean reconBean) throws Exception {
		TyingLinesLoansProcess tllProcess = new TyingLinesLoansProcess();
		reconBean.setHamaIndicator("Y");
		reconBean.setMssNumber("0000123456789");
		reconBean.setFirstMortgageAccountNumber("0000123456789");
		reconBean.setSecondLienHolderAccountNumber("0000123456789");
		reconBean.setLcaAccountNumber("0000123456789");
		//Sub procces runs only if either one is zero
		reconBean.setPriorMortgageBalance(new BigDecimal("10"));
		reconBean.setFirstMortgageCurrentPrincipalBalance(new BigDecimal("100"));
		//Subprocess data - MOD/HREFI
		//reconBean.setQpcarIndicator("Y");
		//reconBean.setPhoneFlag("4");
		reconBean.setAppraisalType("A");
		reconBean.setPropertyValue(null);
		reconBean.setSlidCode("SINGLE");
		reconBean.setCombinedStatementIndicator(5);
		reconBean.setBillCycle("SSR");
		reconBean.setBillMode("9");
		reconBean.setBillCycle("H011");
		
		reconBean.setPropertyTypeCode("444");
		reconBean.setQpcarIndicator("YY");
		
		// Rule Data -  Occupancy code is 3 and qpc ar is NOT II
		reconBean.setOccupancyCode("3");	
		reconBean.setQpcarIndicator("III");
								
		tllProcess.executeTLLProcess(reconBean);	
	}	
	
	private void testTLLLivingUnitsRule1(ReconciliationBean reconBean) throws Exception {
		TyingLinesLoansProcess tllProcess = new TyingLinesLoansProcess();
		reconBean.setHamaIndicator("Y");
		reconBean.setMssNumber("0000123456789");
		reconBean.setFirstMortgageAccountNumber("0000123456789");
		reconBean.setSecondLienHolderAccountNumber("0000123456789");
		reconBean.setLcaAccountNumber("0000123456789");
		//Sub procces runs only if either one is zero
		reconBean.setPriorMortgageBalance(new BigDecimal("10"));
		reconBean.setFirstMortgageCurrentPrincipalBalance(new BigDecimal("100"));
		//Subprocess data - MOD/HREFI
		//reconBean.setQpcarIndicator("Y");
		//reconBean.setPhoneFlag("4");
		reconBean.setAppraisalType("A");
		reconBean.setPropertyValue(null);
		reconBean.setSlidCode("SINGLE");
		reconBean.setCombinedStatementIndicator(5);
		reconBean.setBillCycle("SSR");
		reconBean.setBillMode("9");
		reconBean.setBillCycle("H011");		
		reconBean.setPropertyTypeCode("444");
		reconBean.setQpcarIndicator("YY");
		
		// Rule Data -  living units  is null or blank
		reconBean.setLivingUnits("");
								
		tllProcess.executeTLLProcess(reconBean);	
	}
	
	private void testTLLLivingUnitsRule2(ReconciliationBean reconBean) throws Exception {
		TyingLinesLoansProcess tllProcess = new TyingLinesLoansProcess();
		reconBean.setHamaIndicator("Y");
		reconBean.setMssNumber("0000123456789");
		reconBean.setFirstMortgageAccountNumber("0000123456789");
		reconBean.setSecondLienHolderAccountNumber("0000123456789");
		reconBean.setLcaAccountNumber("0000123456789");
		//Sub procces runs only if either one is zero
		reconBean.setPriorMortgageBalance(new BigDecimal("10"));
		reconBean.setFirstMortgageCurrentPrincipalBalance(new BigDecimal("100"));
		//Subprocess data - MOD/HREFI
		//reconBean.setQpcarIndicator("Y");
		//reconBean.setPhoneFlag("4");
		reconBean.setAppraisalType("A");
		reconBean.setPropertyValue(null);
		reconBean.setSlidCode("SINGLE");
		reconBean.setCombinedStatementIndicator(5);
		reconBean.setBillCycle("SSR");
		reconBean.setBillMode("9");
		reconBean.setBillCycle("H011");		
		reconBean.setPropertyTypeCode("444");
		reconBean.setQpcarIndicator("YY");
		
		// Rule Data -  living units  is 2, 3, 4 and qpc ar is NOT II
		reconBean.setLivingUnits("2");	
		reconBean.setQpcarIndicator("III");
								
		tllProcess.executeTLLProcess(reconBean);	
	}
	
	private static void printListData(List<RuleDVO> dataList, String event) throws Exception {
		if(dataList != null) {
			System.out.println( "---------------- " + event + " Size --------------" + dataList.size());
			for(RuleDVO ruleDVO : dataList) {
				System.out.println( "---- Rule exceptioncode  ----" + ruleDVO.getExceptioncode() 
						+ "---- field Name  ----" + ruleDVO.getFieldName() 
						+ "---- old Value  ----" + ruleDVO.getFieldOldVale()
						+ "---- New Value  ----" + ruleDVO.getFieldNewValue());
			}
		}
	}	
	
	private void printMapData(ReconciliationBean reconBeanFromSIMO) throws Exception {
		Map<String, String> actionMap = reconBeanFromSIMO.getActionMap();
		for(String key : actionMap.keySet()) {
			System.out.println( "---- key ----" + key + "---- value ----" + actionMap.get(key));
		}
	}
	
}
