package com.sample;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class HamaRemovalProcessTestSuite {

	public HamaRemovalProcessTestSuite() {
	}
	
	public static void main(String args[]) throws Exception {
		try {
			HamaRemovalProcessTestSuite testSuite = new HamaRemovalProcessTestSuite();
			ReconciliationBean reconBean = new ReconciliationBean();
			
			//testSuite.testHamaRemovalPersonCodeRule(reconBean);
			testSuite.testHamaRemovalCombStatementIndicatorRule(reconBean);			
			//testSuite.testHamaRemovalSlidCodeRule(reconBean);			
			//testSuite.testFirstMortgageAccountNumberRule(reconBean);
			
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
	
	private void testHamaRemovalPersonCodeRule(ReconciliationBean reconBean) throws Exception {		
		reconBean.setCombinedStatementIndicator(6);
		reconBean.setPersonCode("=");
		// Used in rule
		reconBean.setFirstMortgageAccountNumber("0000123456789");  // 0 or somevalue

		SIMOCloseProcess simoProcess = new SIMOCloseProcess();	
		simoProcess.executeHamaRemovalProcessRules(reconBean);	
	}
	
	private void testHamaRemovalCombStatementIndicatorRule(ReconciliationBean reconBean) throws Exception {
		// Data Related for STOP Rules
		reconBean.setHamaIndicator("Y");
		reconBean.setClientNumner(591);
		reconBean.setSlidCode("SINGLE");		
		reconBean.setMssNumber("0000123456789");
		reconBean.setFirstMortgageAccountNumber("0000123456789"); 			
		reconBean.setLcaAccountNumber("000123456789");
		reconBean.setSecondLienHolderAccountNumber("000123456789");			
		reconBean.setFirstMortgageCurrentPrincipalBalance(new BigDecimal("500"));		
					
		// Valid vlaues are - II, YI, NI, YE, YY, YN, NE, NY, NN are valid values in the list.
		//reconBean.setQpcarIndicator("NN");
		// If phone flg = 4 and Hama Indicator = Y
		reconBean.setPhoneFlag("6");
		
		// Appraisal Type is Null or blank
		reconBean.setAppraisalType("6");

		// Prop Val (LCA) = 0, Appraised Val or Purchase price = 0
		reconBean.setPropertyValue(new BigDecimal("500"));
		reconBean.setAppraisedValue(new BigDecimal("3000")); 
		reconBean.setPurchasePrice(new BigDecimal("500"));  

		// Prior Mtg Bal = 0
		reconBean.setPriorMortgageBalance(new BigDecimal("500"));
		reconBean.setOriginalMortgageBalance(new BigDecimal("10000"));			
		
		// LCA Recovery Code is B, F or G and Bill Cycle is H01 or H20
		reconBean.setRecoveryCode("BB");
		reconBean.setCombinedStatementIndicator(55);
		
		// This Rule Data - Verify Person Code, on 1st Mortgage billMode != 6 and  personCode is B or F and billCycle is H01 or H20		
		reconBean.setBillMode("7");
		reconBean.setBillCycle("H01");

		// Prop Type = Code 4,6, or 7 and QPC AR Ind not = II, change QpcarIndicator = II	
		reconBean.setPropertyTypeCode("44");
		
		// Occupancy Code = 3
		reconBean.setOccupancyCode("");	

		// This Rule Data - Verify Living Units on 1st Mortgage is blank or null
		reconBean.setLivingUnits("2");
		reconBean.setQpcarIndicator("III");
		
		//	Rule data - Combined Stmt Ind = 6, If Ph Flg Not 4, If HAMA Ind is Not N
		reconBean.setCombinedStatementIndicator(6);		
		reconBean.setPhoneFlag("2");
		reconBean.setHamaIndicator("Y");
		
		// This Rule Data to start the process
		//reconBean.setPropertyTypeCode("5");
		//reconBean.setOccupancyCode("4");
		//reconBean.setLivingUnits("5");
		//reconBean.setPersonCode("=");
		
		//Use above one or this one. It returns the data from above.
		reconBean.setForbearanceIndicator("Y");
		
		SIMOCloseProcess simoProcess = new SIMOCloseProcess();	
		simoProcess.executeSIMOProcess(reconBean);			
	}
	
	private void testHamaRemovalSlidCodeRule(ReconciliationBean reconBean) throws Exception {
		//	Data Related for STOP Rules
		reconBean.setHamaIndicator("Y");
		reconBean.setClientNumner(591);
		reconBean.setSlidCode("SINGLE");		
		reconBean.setMssNumber("0000123456789");
		reconBean.setFirstMortgageAccountNumber("0000123456789"); 			
		reconBean.setLcaAccountNumber("000123456789");
		reconBean.setSecondLienHolderAccountNumber("000123456789");			
		reconBean.setFirstMortgageCurrentPrincipalBalance(new BigDecimal("500"));		
					
		// Valid vlaues are - II, YI, NI, YE, YY, YN, NE, NY, NN are valid values in the list.
		//reconBean.setQpcarIndicator("NN");
		// If phone flg = 4 and Hama Indicator = Y
		reconBean.setPhoneFlag("6");
		
		// Appraisal Type is Null or blank
		reconBean.setAppraisalType("6");

		// Prop Val (LCA) = 0, Appraised Val or Purchase price = 0
		reconBean.setPropertyValue(new BigDecimal("500"));
		reconBean.setAppraisedValue(new BigDecimal("3000")); 
		reconBean.setPurchasePrice(new BigDecimal("500"));  

		// Prior Mtg Bal = 0
		reconBean.setPriorMortgageBalance(new BigDecimal("500"));
		reconBean.setOriginalMortgageBalance(new BigDecimal("10000"));			
		
		// LCA Recovery Code is B, F or G and Bill Cycle is H01 or H20
		reconBean.setRecoveryCode("BB");
		reconBean.setCombinedStatementIndicator(55);
		
		// This Rule Data - Verify Person Code, on 1st Mortgage billMode != 6 and  personCode is B or F and billCycle is H01 or H20		
		reconBean.setBillMode("7");
		reconBean.setBillCycle("H01");

		// Prop Type = Code 4,6, or 7 and QPC AR Ind not = II, change QpcarIndicator = II	
		reconBean.setPropertyTypeCode("44");
		
		// Occupancy Code = 3
		reconBean.setOccupancyCode("");	

		// This Rule Data - Verify Living Units on 1st Mortgage is blank or null
		reconBean.setLivingUnits("2");
		reconBean.setQpcarIndicator("III");
		
		//	Rule data - Combined Stmt Ind = 6, If Ph Flg Not 4, If HAMA Ind is Not N
		reconBean.setCombinedStatementIndicator(6);		
		reconBean.setPhoneFlag("2");
		reconBean.setHamaIndicator("Y");
		
		// This Rule Data to start the process
		reconBean.setPropertyTypeCode("5");
		reconBean.setOccupancyCode("4");
		reconBean.setLivingUnits("5");
		
		// Rule data - Slid code is single - change to XXXXXX
		reconBean.setSlidCode("Single".toUpperCase());
		
		SIMOCloseProcess simoProcess = new SIMOCloseProcess();	
		simoProcess.executeSIMOProcess(reconBean);			
	}
	
	private void testFirstMortgageAccountNumberRule(ReconciliationBean reconBean) throws Exception {
		// Data Related for STOP Rules
		reconBean.setHamaIndicator("Y");
		reconBean.setClientNumner(591);
		reconBean.setSlidCode("SINGLE");
		reconBean.setMssNumber("0000123456789");
		reconBean.setSecondLienHolderAccountNumber("0000123456789");
		reconBean.setLcaAccountNumber("0000123456789");
		reconBean.setFirstMortgageAccountNumber("0000123456789");	
		
		// Data Related to STOP Rules
		reconBean.setAppraisedValue(new BigDecimal("10000"));
		reconBean.setPurchasePrice(new BigDecimal("300000"));
		reconBean.setPropertyValue(new BigDecimal("310000"));
				
		// Data related to Rules - 1st MTG - is Greter than Zero and LCA-CLOSED
		reconBean.setFirstMortgageCurrentPrincipalBalance(new BigDecimal("500"));
		reconBean.setLcaStatus("CLOSED-1");
		reconBean.setSlidCode("Single".toUpperCase());

		// Valid vlaues are - II, YI, NI, YE, YY, YN, NE, NY, NN are valid values in the list.
		reconBean.setQpcarIndicator("NY");
		reconBean.setAppraisalType(null);
		reconBean.setPropertyValue(new BigDecimal("0"));
		reconBean.setAppraisedValue(new BigDecimal("310000"));
		reconBean.setPurchasePrice(new BigDecimal("1000"));
		reconBean.setPriorMortgageBalance(new BigDecimal(0));
		reconBean.setOriginalMortgageBalance(new BigDecimal("10000"));		
		
		// This Rule Data to start the process
		reconBean.setPropertyTypeCode("5");
		reconBean.setOccupancyCode("4");
		reconBean.setLivingUnits("5");
		reconBean.setSlidCode("Single".toUpperCase());
		
		// Rule data - Slid is single - change to XXXXXX
		reconBean.setPersonCode("=");
		
		SIMOCloseProcess simoProcess = new SIMOCloseProcess();
		ReconciliationBean reconBeanWithData = simoProcess.executeSIMOProcess(reconBean);	
		System.out.println( "---- The new Mortgage Account Number is ----" + reconBeanWithData.getFirstMortgageAccountNumber());
				
		printMapData(reconBean);
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
