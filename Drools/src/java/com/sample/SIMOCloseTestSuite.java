package com.sample;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class SIMOCloseTestSuite {
	
	public SIMOCloseTestSuite() {
	}
	
	public static void main(String args[]) throws Exception {
		try {
			
			SIMOCloseTestSuite testSuite = new SIMOCloseTestSuite();
			ReconciliationBean reconBean = new ReconciliationBean();	
			
			//testSuite.testHamaIndicatorRule(reconBean);
			//testSuite.testClientNumberRule(reconBean);
			//testSuite.testSlidCodeRule(reconBean);
			//testSuite.testMSSNumberRule(reconBean);
			//testSuite.testMSSNumberAndMortgageAcctNumberRule(reconBean);
			//testSuite.testLCAAccountNumberRule(reconBean);
			//testSuite.testMortgagePIFAndLCAOpen(reconBean);  //Harp Updates			
			//testSuite.testMortgagePIFAndLCAClosed(reconBean); //Harp Updates			
			//testSuite.testMortgageOpenAndLCAClosed(reconBean);
			
			//testSuite.testQpcarIndicatorAndPhoneFlagRule(reconBean);						
			
			//testSuite.testInvalidAppraisalTypeRule(reconBean);			
			//testSuite.testPropertyValueTestRule(reconBean);
			
			//testSuite.testPriorMortgageBalanceTestRule(reconBean);
			
			//testSuite.testLCARecoveryCodeRule1(reconBean);			
			//testSuite.testLCARecoveryCodeRule2(reconBean);
			//testSuite.testLCARecoveryCodeRule3(reconBean);
			
			//testSuite.testCombinesStatementIndicatorRule1(reconBean);
			//testSuite.testCombinesStatementIndicatorRule2(reconBean);
			//testSuite.testCombinesStatementIndicatorRule3(reconBean);
			
			//testSuite.testBillModeAndBillCycleRule1(reconBean);
			//testSuite.testBillModeAndBillCycleRule2(reconBean);
			//testSuite.testBillModeAndBillCycleRule3(reconBean);
			
			//testSuite.testPersonCodeRule1(reconBean);
			//testSuite.testPersonCodeRule2(reconBean);
			//testSuite.testPersonCodeRule3(reconBean);
			
			//testSuite.testPropertyTypeCodeRule1(reconBean);
			//2nd one is a process
			
			//testSuite.testOccupancyCodeRule1(reconBean);
			//testSuite.testOccupancyCodeRule2(reconBean);
			
			//testSuite.testLivingUnitsRule1(reconBean);
			testSuite.testLivingUnitsRule2(reconBean);
			
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
	
	private void testHamaIndicatorRule(ReconciliationBean reconBean) throws Exception {
		// Data Related for STOP Rules
		reconBean.setHamaIndicator("N");
		SIMOCloseProcess simoProcess = new SIMOCloseProcess();
		simoProcess.executeSIMOProcess(reconBean);
	}
	
	private void testClientNumberRule(ReconciliationBean reconBean) throws Exception {
		// Data Related for STOP Rules
		reconBean.setHamaIndicator("Y");
		reconBean.setClientNumner(5911);

		SIMOCloseProcess simoProcess = new SIMOCloseProcess();
		simoProcess.executeSIMOProcess(reconBean);
	}
	
	private void testSlidCodeRule(ReconciliationBean reconBean) throws Exception {
		// Data Related for STOP Rules
		reconBean.setHamaIndicator("Y");
		reconBean.setClientNumner(591);
		reconBean.setSlidCode("NOT SINGLE");

		SIMOCloseProcess simoProcess = new SIMOCloseProcess();
		simoProcess.executeSIMOProcess(reconBean);
	}
	
	private void testMSSNumberRule(ReconciliationBean reconBean) throws Exception {
		// Data Related for STOP Rules
		reconBean.setHamaIndicator("Y");
		reconBean.setClientNumner(591);
		reconBean.setSlidCode("SINGLE");		
		reconBean.setMssNumber("0000123456789");
		reconBean.setFirstMortgageCurrentPrincipalBalance(new BigDecimal("500"));

		SIMOCloseProcess simoProcess = new SIMOCloseProcess();
		simoProcess.executeSIMOProcess(reconBean);
	}
	
	private void testMSSNumberAndMortgageAcctNumberRule(ReconciliationBean reconBean) throws Exception {
		// Data Related for STOP Rules
		reconBean.setHamaIndicator("Y");
		reconBean.setClientNumner(591);
		reconBean.setSlidCode("SINGLE");		
		reconBean.setMssNumber("0000123456789");
		reconBean.setFirstMortgageAccountNumber("000012345678"); //Invalid Number
		reconBean.setFirstMortgageCurrentPrincipalBalance(new BigDecimal("500"));

		SIMOCloseProcess simoProcess = new SIMOCloseProcess();
		simoProcess.executeSIMOProcess(reconBean);
	}
	
	private void testLCAAccountNumberRule(ReconciliationBean reconBean) throws Exception {
		// Data Related for STOP Rules
		reconBean.setHamaIndicator("Y");
		reconBean.setClientNumner(591);
		reconBean.setSlidCode("SINGLE");		
		reconBean.setMssNumber("0000123456789");
		reconBean.setFirstMortgageAccountNumber("0000123456789"); 
		reconBean.setFirstMortgageCurrentPrincipalBalance(new BigDecimal("500"));
		
		reconBean.setLcaAccountNumber("000123456789");
		reconBean.setSecondLienHolderAccountNumber("000123456789");		

		SIMOCloseProcess simoProcess = new SIMOCloseProcess();
		simoProcess.executeSIMOProcess(reconBean);
	}
	
	private void testMortgagePIFAndLCAOpen(ReconciliationBean reconBean) throws Exception {
		// Data Related for STOP Rules
		reconBean.setHamaIndicator("Y");
		reconBean.setClientNumner(591);
		reconBean.setSlidCode("SINGLE");		
		reconBean.setMssNumber("0000123456789");
		reconBean.setFirstMortgageAccountNumber("0000123456789"); 			
		reconBean.setLcaAccountNumber("000123456789");
		reconBean.setSecondLienHolderAccountNumber("000123456789");		
		
		reconBean.setFirstMortgageCurrentPrincipalBalance(new BigDecimal("0"));
		reconBean.setLcaStatus("OPEN");
		reconBean.setCombinedStatementIndicator(6);
		reconBean.setPhoneFlag("2");		

		SIMOCloseProcess simoProcess = new SIMOCloseProcess();
		simoProcess.executeSIMOProcess(reconBean);
	}
	
	private void testMortgagePIFAndLCAClosed(ReconciliationBean reconBean) throws Exception {
		// Data Related for STOP Rules
		reconBean.setHamaIndicator("Y");
		reconBean.setClientNumner(591);
		reconBean.setSlidCode("SINGLE");		
		reconBean.setMssNumber("0000123456789");
		reconBean.setFirstMortgageAccountNumber("0000123456789"); 			
		reconBean.setLcaAccountNumber("000123456789");
		reconBean.setSecondLienHolderAccountNumber("000123456789");		
		
		reconBean.setFirstMortgageCurrentPrincipalBalance(new BigDecimal("0"));
		reconBean.setLcaStatus("CLOSED");
		reconBean.setCombinedStatementIndicator(6);
		reconBean.setPhoneFlag("2");		

		SIMOCloseProcess simoProcess = new SIMOCloseProcess();
		simoProcess.executeSIMOProcess(reconBean);
	}
	
	private void testMortgageOpenAndLCAClosed(ReconciliationBean reconBean) throws Exception {
		// Data Related for STOP Rules
		reconBean.setHamaIndicator("Y");
		reconBean.setClientNumner(591);
		reconBean.setSlidCode("SINGLE");		
		reconBean.setMssNumber("0000123456789");
		reconBean.setFirstMortgageAccountNumber("0000123456789"); 			
		reconBean.setLcaAccountNumber("000123456789");
		reconBean.setSecondLienHolderAccountNumber("000123456789");		
		
		reconBean.setFirstMortgageCurrentPrincipalBalance(new BigDecimal("500"));
		reconBean.setLcaStatus("CLOSED");
		reconBean.setPersonCode("=");
		reconBean.setBillMode("7");
		reconBean.setBillCycle("H20");
		
		SIMOCloseProcess simoProcess = new SIMOCloseProcess();
		simoProcess.executeSIMOProcess(reconBean);
	}
	
	private void testQpcarIndicatorAndPhoneFlagRule(ReconciliationBean reconBean) throws Exception {
		// Data Related for STOP Rules
		reconBean.setHamaIndicator("Y");
		reconBean.setClientNumner(591);
		reconBean.setSlidCode("SINGLE");		
		reconBean.setMssNumber("0000123456789");
		reconBean.setFirstMortgageAccountNumber("0000123456789"); 			
		reconBean.setLcaAccountNumber("000123456789");
		reconBean.setSecondLienHolderAccountNumber("000123456789");			
		reconBean.setFirstMortgageCurrentPrincipalBalance(new BigDecimal("500"));		
		
		//Data Related to STOP Rules
		reconBean.setAppraisedValue(new BigDecimal("10000"));
		reconBean.setPurchasePrice(new BigDecimal("300000"));
		reconBean.setPropertyValue(new BigDecimal("310000"));
				
		// Valid vlaues are - II, YI, NI, YE, YY, YN, NE, NY, NN are valid values in the list.
		reconBean.setQpcarIndicator("NPP");
		// This Rule Data - If phone flg = 4 and Hama Indicator = Y
		reconBean.setPhoneFlag("4");		// Tested for 4 and 2
		
		SIMOCloseProcess simoProcess = new SIMOCloseProcess();
		simoProcess.executeSIMOProcess(reconBean);
	}	
	
	private void testInvalidAppraisalTypeRule(ReconciliationBean reconBean) throws Exception {
		// Data Related for STOP Rules
		reconBean.setHamaIndicator("Y");
		reconBean.setClientNumner(591);
		reconBean.setSlidCode("SINGLE");		
		reconBean.setMssNumber("0000123456789");
		reconBean.setFirstMortgageAccountNumber("0000123456789"); 			
		reconBean.setLcaAccountNumber("000123456789");
		reconBean.setSecondLienHolderAccountNumber("000123456789");			
		reconBean.setFirstMortgageCurrentPrincipalBalance(new BigDecimal("500"));		
		
		//Data Related to STOP Rules
		reconBean.setAppraisedValue(new BigDecimal("10000"));
		reconBean.setPurchasePrice(new BigDecimal("300000"));
		reconBean.setPropertyValue(new BigDecimal("310000"));
				
		// Valid vlaues are - II, YI, NI, YE, YY, YN, NE, NY, NN are valid values in the list.
		reconBean.setQpcarIndicator("NN");
		// If phone flg = 4 and Hama Indicator = Y
		reconBean.setPhoneFlag("6");		// Tested for 4 and 2

		// This Rule Data - Appraisal Type is Null or blank
		reconBean.setAppraisalType("");
		
		SIMOCloseProcess simoProcess = new SIMOCloseProcess();
		simoProcess.executeSIMOProcess(reconBean);		
	}
	
	private void testPropertyValueTestRule(ReconciliationBean reconBean) throws Exception {
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
		reconBean.setQpcarIndicator("NN");
		// If phone flg = 4 and Hama Indicator = Y
		reconBean.setPhoneFlag("6");
		// Appraisal Type is Null or blank
		reconBean.setAppraisalType("6");

		// This Rule Data - Prop Val (LCA) = 0, Appraised Val or Purchase price = 0
		reconBean.setPropertyValue(new BigDecimal("0"));
		reconBean.setAppraisedValue(new BigDecimal("3000"));  //Tested for 0
		reconBean.setPurchasePrice(new BigDecimal("500"));  //Tested for 0
		
		SIMOCloseProcess simoProcess = new SIMOCloseProcess();
		simoProcess.executeSIMOProcess(reconBean);	
	}
	
	private void testPriorMortgageBalanceTestRule(ReconciliationBean reconBean) throws Exception {
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
		reconBean.setQpcarIndicator("NN");
		// If phone flg = 4 and Hama Indicator = Y
		reconBean.setPhoneFlag("6");
		// Appraisal Type is Null or blank
		reconBean.setAppraisalType("6");

		// This Rule Data - Prop Val (LCA) = 0, Appraised Val or Purchase price = 0
		reconBean.setPropertyValue(new BigDecimal("500"));
		reconBean.setAppraisedValue(new BigDecimal("3000")); 
		reconBean.setPurchasePrice(new BigDecimal("500"));  

		// This Rule Data - Prior Mtg Bal = 0
		reconBean.setPriorMortgageBalance(new BigDecimal(0));
		reconBean.setOriginalMortgageBalance(new BigDecimal("10000"));				

		SIMOCloseProcess simoProcess = new SIMOCloseProcess();
		simoProcess.executeSIMOProcess(reconBean);		
	}
	
	private void testLCARecoveryCodeRule1(ReconciliationBean reconBean) throws Exception {
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
		reconBean.setQpcarIndicator("NN");
		// If phone flg = 4 and Hama Indicator = Y
		reconBean.setPhoneFlag("6");
		// Appraisal Type is Null or blank
		reconBean.setAppraisalType("6");

		//Prop Val (LCA) = 0, Appraised Val or Purchase price = 0
		reconBean.setPropertyValue(new BigDecimal("500"));
		reconBean.setAppraisedValue(new BigDecimal("3000")); 
		reconBean.setPurchasePrice(new BigDecimal("500"));  

		//Prior Mtg Bal = 0
		reconBean.setPriorMortgageBalance(new BigDecimal("500"));
		reconBean.setOriginalMortgageBalance(new BigDecimal("10000"));			
		
		// This Rule Data - LCA Recovery Code is B, F or G and Bill Cycle is H01 or H20
		reconBean.setRecoveryCode("B");
		reconBean.setBillCycle("H01");		

		SIMOCloseProcess simoProcess = new SIMOCloseProcess();
		simoProcess.executeSIMOProcess(reconBean);	
	}
	
	private void testLCARecoveryCodeRule2(ReconciliationBean reconBean) throws Exception {
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
		reconBean.setQpcarIndicator("NN");
		// If phone flg = 4 and Hama Indicator = Y
		reconBean.setPhoneFlag("6");
		// Appraisal Type is Null or blank
		reconBean.setAppraisalType("6");

		//Prop Val (LCA) = 0, Appraised Val or Purchase price = 0
		reconBean.setPropertyValue(new BigDecimal("500"));
		reconBean.setAppraisedValue(new BigDecimal("3000")); 
		reconBean.setPurchasePrice(new BigDecimal("500"));  

		//Prior Mtg Bal = 0
		reconBean.setPriorMortgageBalance(new BigDecimal("500"));
		reconBean.setOriginalMortgageBalance(new BigDecimal("10000"));			
		
		// This Rule Data - LCA Recovery Code is B, F or G and Bill Cycle is H01 or H20
		reconBean.setRecoveryCode("B");
		reconBean.setBillCycle("H01");	
		reconBean.setCombinedStatementIndicator(6);

		SIMOCloseProcess simoProcess = new SIMOCloseProcess();
		simoProcess.executeSIMOProcess(reconBean);		
	}
	
	private void testLCARecoveryCodeRule3(ReconciliationBean reconBean) throws Exception {
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
		reconBean.setQpcarIndicator("NN");
		// If phone flg = 4 and Hama Indicator = Y
		reconBean.setPhoneFlag("6");
		// Appraisal Type is Null or blank
		reconBean.setAppraisalType("6");

		//Prop Val (LCA) = 0, Appraised Val or Purchase price = 0
		reconBean.setPropertyValue(new BigDecimal("500"));
		reconBean.setAppraisedValue(new BigDecimal("3000")); 
		reconBean.setPurchasePrice(new BigDecimal("500"));  

		//Prior Mtg Bal = 0
		reconBean.setPriorMortgageBalance(new BigDecimal("500"));
		reconBean.setOriginalMortgageBalance(new BigDecimal("10000"));			
		
		// This Rule Data - LCA Recovery Code is B, F or G and Bill Cycle is H01 or H20
		reconBean.setRecoveryCode("B");
		reconBean.setBillCycle("H01");	
		reconBean.setCombinedStatementIndicator(6);
		reconBean.setBillMode("5");

		SIMOCloseProcess simoProcess = new SIMOCloseProcess();
		simoProcess.executeSIMOProcess(reconBean);		
	}
	
	private void testCombinesStatementIndicatorRule1(ReconciliationBean reconBean) throws Exception {
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
		reconBean.setQpcarIndicator("NN");
		// If phone flg = 4 and Hama Indicator = Y
		reconBean.setPhoneFlag("6");
		// Appraisal Type is Null or blank
		reconBean.setAppraisalType("6");

		//Prop Val (LCA) = 0, Appraised Val or Purchase price = 0
		reconBean.setPropertyValue(new BigDecimal("500"));
		reconBean.setAppraisedValue(new BigDecimal("3000")); 
		reconBean.setPurchasePrice(new BigDecimal("500"));  

		//Prior Mtg Bal = 0
		reconBean.setPriorMortgageBalance(new BigDecimal("500"));
		reconBean.setOriginalMortgageBalance(new BigDecimal("10000"));			
		
		// LCA Recovery Code is B, F or G and Bill Cycle is H01 or H20
		reconBean.setRecoveryCode("BB");
		reconBean.setBillCycle("H011");	
		reconBean.setCombinedStatementIndicator(6);
		reconBean.setBillMode("5");
		
		// This Rule Data - Verify if LCA is on IBS, by validating COMB STMT IND is equal to 6, LCA Due on 1st & Bill cycle not = H01
		reconBean.setCombinedStatementIndicator(6);
		reconBean.setLcaDue(1);
		reconBean.setBillCycle("H011");		//not H01

		SIMOCloseProcess simoProcess = new SIMOCloseProcess();
		simoProcess.executeSIMOProcess(reconBean);		
	}
		
	private void testCombinesStatementIndicatorRule2(ReconciliationBean reconBean) throws Exception {
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
		reconBean.setQpcarIndicator("NN");
		// If phone flg = 4 and Hama Indicator = Y
		reconBean.setPhoneFlag("6");
		// Appraisal Type is Null or blank
		reconBean.setAppraisalType("6");

		//Prop Val (LCA) = 0, Appraised Val or Purchase price = 0
		reconBean.setPropertyValue(new BigDecimal("500"));
		reconBean.setAppraisedValue(new BigDecimal("3000")); 
		reconBean.setPurchasePrice(new BigDecimal("500"));  

		//Prior Mtg Bal = 0
		reconBean.setPriorMortgageBalance(new BigDecimal("500"));
		reconBean.setOriginalMortgageBalance(new BigDecimal("10000"));			
		
		// LCA Recovery Code is B, F or G and Bill Cycle is H01 or H20
		reconBean.setRecoveryCode("BB");
		reconBean.setBillCycle("H011");	
		reconBean.setCombinedStatementIndicator(6);
		reconBean.setBillMode("5");
		
		// This Rule Data - Verify if LCA is on IBS, by validating COMB STMT IND is equal to 6, LCA Due on 1st & Bill cycle not = H01
		reconBean.setCombinedStatementIndicator(6);
		reconBean.setLcaDue(20);
		reconBean.setBillCycle("H200");		//not H20

		SIMOCloseProcess simoProcess = new SIMOCloseProcess();
		simoProcess.executeSIMOProcess(reconBean);	
	}

	private void testCombinesStatementIndicatorRule3(ReconciliationBean reconBean) throws Exception {
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
		reconBean.setQpcarIndicator("NN");
		// If phone flg = 4 and Hama Indicator = Y
		reconBean.setPhoneFlag("6");
		// Appraisal Type is Null or blank
		reconBean.setAppraisalType("6");

		//Prop Val (LCA) = 0, Appraised Val or Purchase price = 0
		reconBean.setPropertyValue(new BigDecimal("500"));
		reconBean.setAppraisedValue(new BigDecimal("3000")); 
		reconBean.setPurchasePrice(new BigDecimal("500"));  

		//Prior Mtg Bal = 0
		reconBean.setPriorMortgageBalance(new BigDecimal("500"));
		reconBean.setOriginalMortgageBalance(new BigDecimal("10000"));			
		
		// LCA Recovery Code is B, F or G and Bill Cycle is H01 or H20
		reconBean.setRecoveryCode("BB");
		reconBean.setBillMode("5");
		
		// This Rule Data - Verify if LCA is on IBS, by validating COMB STMT IND is equal to 6, LCA Due on 1st & Bill cycle not = H01
		reconBean.setCombinedStatementIndicator(5);
		reconBean.setBillCycle("NOTSSR");	//not SSR

		SIMOCloseProcess simoProcess = new SIMOCloseProcess();	
		simoProcess.executeSIMOProcess(reconBean);		
	}
	
	private void testBillModeAndBillCycleRule1(ReconciliationBean reconBean) throws Exception {
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
		reconBean.setQpcarIndicator("NN");
		// If phone flg = 4 and Hama Indicator = Y
		reconBean.setPhoneFlag("6");
		// Appraisal Type is Null or blank
		reconBean.setAppraisalType("6");

		//Prop Val (LCA) = 0, Appraised Val or Purchase price = 0
		reconBean.setPropertyValue(new BigDecimal("500"));
		reconBean.setAppraisedValue(new BigDecimal("3000")); 
		reconBean.setPurchasePrice(new BigDecimal("500"));  

		//Prior Mtg Bal = 0
		reconBean.setPriorMortgageBalance(new BigDecimal("500"));
		reconBean.setOriginalMortgageBalance(new BigDecimal("10000"));			
		
		// LCA Recovery Code is B, F or G and Bill Cycle is H01 or H20
		reconBean.setRecoveryCode("BB");
		reconBean.setCombinedStatementIndicator(55);

		// This Rule Data - Bill mode = 6, Bill Cycle is H01 or H20, Change QHIST to &
		reconBean.setBillMode("6");
		reconBean.setBillCycle("H01");
		
		SIMOCloseProcess simoProcess = new SIMOCloseProcess();	
		simoProcess.executeSIMOProcess(reconBean);			
	}
	
	private void testBillModeAndBillCycleRule2(ReconciliationBean reconBean) throws Exception {
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
		reconBean.setQpcarIndicator("NN");
		// If phone flg = 4 and Hama Indicator = Y
		reconBean.setPhoneFlag("6");
		// Appraisal Type is Null or blank
		reconBean.setAppraisalType("6");

		//Prop Val (LCA) = 0, Appraised Val or Purchase price = 0
		reconBean.setPropertyValue(new BigDecimal("500"));
		reconBean.setAppraisedValue(new BigDecimal("3000")); 
		reconBean.setPurchasePrice(new BigDecimal("500"));  

		//Prior Mtg Bal = 0
		reconBean.setPriorMortgageBalance(new BigDecimal("500"));
		reconBean.setOriginalMortgageBalance(new BigDecimal("10000"));			
		
		// LCA Recovery Code is B, F or G and Bill Cycle is H01 or H20
		reconBean.setRecoveryCode("BB");
		reconBean.setCombinedStatementIndicator(55);

		// This Rule Data - Bill mode = 6, Bill Cycle is SSR, Change Bill Mode to 9
		reconBean.setBillMode("6");
		reconBean.setBillCycle("SSR");
				
		SIMOCloseProcess simoProcess = new SIMOCloseProcess();	
		simoProcess.executeSIMOProcess(reconBean);		
	}
	
	private void testBillModeAndBillCycleRule3(ReconciliationBean reconBean) throws Exception {
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
		reconBean.setQpcarIndicator("NN");
		// If phone flg = 4 and Hama Indicator = Y
		reconBean.setPhoneFlag("6");
		// Appraisal Type is Null or blank
		reconBean.setAppraisalType("6");

		//Prop Val (LCA) = 0, Appraised Val or Purchase price = 0
		reconBean.setPropertyValue(new BigDecimal("500"));
		reconBean.setAppraisedValue(new BigDecimal("3000")); 
		reconBean.setPurchasePrice(new BigDecimal("500"));  

		//Prior Mtg Bal = 0
		reconBean.setPriorMortgageBalance(new BigDecimal("500"));
		reconBean.setOriginalMortgageBalance(new BigDecimal("10000"));			
		
		// LCA Recovery Code is B, F or G and Bill Cycle is H01 or H20
		reconBean.setRecoveryCode("BB");
		reconBean.setCombinedStatementIndicator(55);

		// This Rule Data - Verify Bill Mode on 1st Mortgage = 9 and Bill Cycle is H01 or H20, Change Bill Mode to 7
		reconBean.setBillMode("9");
		reconBean.setBillCycle("H01");
		
		SIMOCloseProcess simoProcess = new SIMOCloseProcess();	
		simoProcess.executeSIMOProcess(reconBean);			
	}
	
	private void testPersonCodeRule1(ReconciliationBean reconBean) throws Exception {
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
		reconBean.setQpcarIndicator("NN");
		// If phone flg = 4 and Hama Indicator = Y
		reconBean.setPhoneFlag("6");
		// Appraisal Type is Null or blank
		reconBean.setAppraisalType("6");

		//Prop Val (LCA) = 0, Appraised Val or Purchase price = 0
		reconBean.setPropertyValue(new BigDecimal("500"));
		reconBean.setAppraisedValue(new BigDecimal("3000")); 
		reconBean.setPurchasePrice(new BigDecimal("500"));  

		//Prior Mtg Bal = 0
		reconBean.setPriorMortgageBalance(new BigDecimal("500"));
		reconBean.setOriginalMortgageBalance(new BigDecimal("10000"));			
		
		// LCA Recovery Code is B, F or G and Bill Cycle is H01 or H20
		reconBean.setRecoveryCode("BB");
		reconBean.setCombinedStatementIndicator(55);

		// Verify Bill Mode on 1st Mortgage = 9 and Bill Cycle is H01 or H20, Change Bill Mode to 7
		reconBean.setBillMode("99");
		reconBean.setBillCycle("H01");

		// This Rule Data - Verify Person Code is If 0 to 9 update to =   		
		reconBean.setPersonCode("0");
		
		SIMOCloseProcess simoProcess = new SIMOCloseProcess();
		simoProcess.executeSIMOProcess(reconBean);
	}
	
	private void testPersonCodeRule2(ReconciliationBean reconBean) throws Exception {
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
		reconBean.setQpcarIndicator("NN");
		// If phone flg = 4 and Hama Indicator = Y
		reconBean.setPhoneFlag("6");
		// Appraisal Type is Null or blank
		reconBean.setAppraisalType("6");

		//Prop Val (LCA) = 0, Appraised Val or Purchase price = 0
		reconBean.setPropertyValue(new BigDecimal("500"));
		reconBean.setAppraisedValue(new BigDecimal("3000")); 
		reconBean.setPurchasePrice(new BigDecimal("500"));  

		//Prior Mtg Bal = 0
		reconBean.setPriorMortgageBalance(new BigDecimal("500"));
		reconBean.setOriginalMortgageBalance(new BigDecimal("10000"));			
		
		// LCA Recovery Code is B, F or G and Bill Cycle is H01 or H20
		reconBean.setRecoveryCode("BB");
		reconBean.setCombinedStatementIndicator(55);
		//reconBean.setBillMode("PPP");

		// This Rule Data - Person Code is B or F & Bill cycle is H01 or H20 - Change Bill Cycle to SSR, Change Comb Stmt Ind to 5 		
		reconBean.setPersonCode("B");
		reconBean.setBillCycle("H01");
		
		SIMOCloseProcess simoProcess = new SIMOCloseProcess();	
		simoProcess.executeSIMOProcess(reconBean);		
	}
	
	private void testPersonCodeRule3(ReconciliationBean reconBean) throws Exception {
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
		reconBean.setQpcarIndicator("NN");
		// If phone flg = 4 and Hama Indicator = Y
		reconBean.setPhoneFlag("6");
		// Appraisal Type is Null or blank
		reconBean.setAppraisalType("6");

		//Prop Val (LCA) = 0, Appraised Val or Purchase price = 0
		reconBean.setPropertyValue(new BigDecimal("500"));
		reconBean.setAppraisedValue(new BigDecimal("3000")); 
		reconBean.setPurchasePrice(new BigDecimal("500"));  

		//Prior Mtg Bal = 0
		reconBean.setPriorMortgageBalance(new BigDecimal("500"));
		reconBean.setOriginalMortgageBalance(new BigDecimal("10000"));			
		
		// LCA Recovery Code is B, F or G and Bill Cycle is H01 or H20
		reconBean.setRecoveryCode("BB");
		reconBean.setCombinedStatementIndicator(55);
		
		//This Rule Data - Verify Person Code, on 1st Mortgage billMode != 6 and  personCode is B or F and billCycle is H01 or H20		
		reconBean.setPersonCode("B");
		reconBean.setBillMode("9");
		reconBean.setBillCycle("H01");
		
		SIMOCloseProcess simoProcess = new SIMOCloseProcess();	
		simoProcess.executeSIMOProcess(reconBean);
	}
	
	private void testPropertyTypeCodeRule1(ReconciliationBean reconBean) throws Exception {
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
		reconBean.setQpcarIndicator("NN");
		// If phone flg = 4 and Hama Indicator = Y
		reconBean.setPhoneFlag("6");
		// Appraisal Type is Null or blank
		reconBean.setAppraisalType("6");

		//Prop Val (LCA) = 0, Appraised Val or Purchase price = 0
		reconBean.setPropertyValue(new BigDecimal("500"));
		reconBean.setAppraisedValue(new BigDecimal("3000")); 
		reconBean.setPurchasePrice(new BigDecimal("500"));  

		//Prior Mtg Bal = 0
		reconBean.setPriorMortgageBalance(new BigDecimal("500"));
		reconBean.setOriginalMortgageBalance(new BigDecimal("10000"));			
		
		// LCA Recovery Code is B, F or G and Bill Cycle is H01 or H20
		reconBean.setRecoveryCode("BB");
		reconBean.setCombinedStatementIndicator(55);
		
		//This Rule Data - Verify Person Code, on 1st Mortgage billMode != 6 and  personCode is B or F and billCycle is H01 or H20		
		reconBean.setPersonCode("BBB");
		reconBean.setBillMode("9");
		reconBean.setBillCycle("H011");

		//This Rule Data - Prop Type = Code 4,6, or 7 and QPC AR Ind not = II, change QpcarIndicator = II	
		reconBean.setPropertyTypeCode("4");
		
		SIMOCloseProcess simoProcess = new SIMOCloseProcess();	
		simoProcess.executeSIMOProcess(reconBean);				
	}
		
	private void testOccupancyCodeRule1(ReconciliationBean reconBean) throws Exception {
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
		reconBean.setQpcarIndicator("NN");
		// If phone flg = 4 and Hama Indicator = Y
		reconBean.setPhoneFlag("6");
		// Appraisal Type is Null or blank
		reconBean.setAppraisalType("6");

		//Prop Val (LCA) = 0, Appraised Val or Purchase price = 0
		reconBean.setPropertyValue(new BigDecimal("500"));
		reconBean.setAppraisedValue(new BigDecimal("3000")); 
		reconBean.setPurchasePrice(new BigDecimal("500"));  

		//Prior Mtg Bal = 0
		reconBean.setPriorMortgageBalance(new BigDecimal("500"));
		reconBean.setOriginalMortgageBalance(new BigDecimal("10000"));			
		
		// LCA Recovery Code is B, F or G and Bill Cycle is H01 or H20
		reconBean.setRecoveryCode("BB");
		reconBean.setCombinedStatementIndicator(55);
		
		//This Rule Data - Verify Person Code, on 1st Mortgage billMode != 6 and  personCode is B or F and billCycle is H01 or H20		
		reconBean.setPersonCode("BBB");
		reconBean.setBillMode("9");
		reconBean.setBillCycle("H011");

		//Prop Type = Code 4,6, or 7 and QPC AR Ind not = II, change QpcarIndicator = II	
		reconBean.setPropertyTypeCode("44");
		
		// This Rule Data - Occupancy code Blank or Null
		reconBean.setOccupancyCode(null);	
		
		SIMOCloseProcess simoProcess = new SIMOCloseProcess();	
		simoProcess.executeSIMOProcess(reconBean);		
	}
	
	private void testOccupancyCodeRule2(ReconciliationBean reconBean) throws Exception {
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

		//Prop Val (LCA) = 0, Appraised Val or Purchase price = 0
		reconBean.setPropertyValue(new BigDecimal("500"));
		reconBean.setAppraisedValue(new BigDecimal("3000")); 
		reconBean.setPurchasePrice(new BigDecimal("500"));  

		//Prior Mtg Bal = 0
		reconBean.setPriorMortgageBalance(new BigDecimal("500"));
		reconBean.setOriginalMortgageBalance(new BigDecimal("10000"));			
		
		// LCA Recovery Code is B, F or G and Bill Cycle is H01 or H20
		reconBean.setRecoveryCode("BB");
		reconBean.setCombinedStatementIndicator(55);
		
		//This Rule Data - Verify Person Code, on 1st Mortgage billMode != 6 and  personCode is B or F and billCycle is H01 or H20		
		reconBean.setPersonCode("BBB");
		reconBean.setBillMode("9");
		reconBean.setBillCycle("H011");

		//Prop Type = Code 4,6, or 7 and QPC AR Ind not = II, change QpcarIndicator = II	
		reconBean.setPropertyTypeCode("44");
		
		// This Rule Data - Occupancy Code = 3
		reconBean.setOccupancyCode("3");	
		reconBean.setQpcarIndicator("III");
		
		SIMOCloseProcess simoProcess = new SIMOCloseProcess();	
		simoProcess.executeSIMOProcess(reconBean);
	}
	
	private void testLivingUnitsRule1(ReconciliationBean reconBean) throws Exception {
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
		reconBean.setPersonCode("BBB");
		reconBean.setBillMode("9");
		reconBean.setBillCycle("H011");

		// Prop Type = Code 4,6, or 7 and QPC AR Ind not = II, change QpcarIndicator = II	
		reconBean.setPropertyTypeCode("44");
		
		// This Rule Data - Occupancy Code = 3
		reconBean.setOccupancyCode("");	
		reconBean.setQpcarIndicator("II");

		// Verify Living Units on 1st Mortgage is blank or null
		reconBean.setLivingUnits("");			

		SIMOCloseProcess simoProcess = new SIMOCloseProcess();	
		simoProcess.executeSIMOProcess(reconBean);		
	}	
	
	private void testLivingUnitsRule2(ReconciliationBean reconBean) throws Exception {
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
		reconBean.setPersonCode("BBB");
		reconBean.setBillMode("9");
		reconBean.setBillCycle("H011");

		// Prop Type = Code 4,6, or 7 and QPC AR Ind not = II, change QpcarIndicator = II	
		reconBean.setPropertyTypeCode("44");
		
		// Occupancy Code = 3
		reconBean.setOccupancyCode("");	

		// This Rule Data - Verify Living Units on 1st Mortgage is blank or null
		reconBean.setLivingUnits("2");
		reconBean.setQpcarIndicator("III");
		
		SIMOCloseProcess simoProcess = new SIMOCloseProcess();	
		simoProcess.executeSIMOProcess(reconBean);	
	}	
	
//	private void printMapData(ReconciliationBean reconBeanFromSIMO) throws Exception {
//		Map<String, String> actionMap = reconBeanFromSIMO.getActionMap();
//		for(String key : actionMap.keySet()) {
//			System.out.println( "---- key ----" + key + "---- value ----" + actionMap.get(key));
//		}
//	}

}



