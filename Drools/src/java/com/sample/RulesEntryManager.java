package com.sample;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class RulesEntryManager {
	
	private static final long MILLSECS_PER_DAY = 24*60*60*1000;	

	public static void main(String[] args) {
		try {
			RulesEntryManager rulesEntryManager = new RulesEntryManager();
			rulesEntryManager.testDetermineType();			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void testDetermineType() throws Exception {
		Date lastDate = getDateFromString("01-02-2011");
		Date earlyDate = getDateFromString("30-03-2012");					
		
		ReconciliationBean reconBean = new ReconciliationBean();
		reconBean.setAccountType("MOD");
		reconBean.setLcaNoteDate(lastDate);
		reconBean.setFirstMortgageDate(earlyDate);
		
		startProcessingRules(reconBean);
		Map<String, List<RuleDVO>> eventMap = reconBean.getEventMap();
		List<RuleDVO> alertsList = eventMap.get("ALERTS");
		List<RuleDVO> correctionsList = eventMap.get("CORRETCIONS");
		List<RuleDVO> shawUpdatesList = eventMap.get("SHAWUPDATES");
		List<RuleDVO> alltelUpdatesList = eventMap.get("ALLUPDATES");
		List<RuleDVO> mismatchList = eventMap.get("MISMATCHES");
		List<RuleDVO> harpUpdatesList = eventMap.get("HARPUPDATES");		
		
		printListData(alertsList, "Alerts");
		printListData(correctionsList, "Corrections");
		printListData(shawUpdatesList, "SHAW Updates");
		printListData(alltelUpdatesList, "Alltel Updates");
		printListData(mismatchList, "MISMATCHES");
		printListData(harpUpdatesList, "HARP Updates");
	}

	public ReconciliationBean startProcessingRules(ReconciliationBean reconBean) throws Exception {	
		String accountType = reconBean.getAccountType();
		Date lcaNoteDate = reconBean.getLcaNoteDate();
		Date firstMortgageDate = reconBean.getFirstMortgageDate();
		long deltaDaysForSimo = Math.abs(determineDaysDifference(lcaNoteDate, firstMortgageDate));
		long deltaDaysForTLL = determineDaysDifference(lcaNoteDate, firstMortgageDate);
		long deltaDaysForModHrefi = determineDaysDifference(firstMortgageDate, lcaNoteDate);

		System.out.println("---- Input Account Type ----" + accountType + 
				"---- deltaDays ---- **" + deltaDaysForSimo +
				"---- deltaDays ---- **" + deltaDaysForTLL +
				"---- deltaDays ---- **" + deltaDaysForModHrefi);
		
		// Starting the SIMO Process
		if(accountType != null && "SIMo".equalsIgnoreCase(accountType) && deltaDaysForSimo <= 60) {
			// Test data for SIMO - TODO to be removed
			setSimoTestData(reconBean);
			
			SIMOCloseProcess simoProcess = new SIMOCloseProcess();
			reconBean = simoProcess.executeSIMOProcess(reconBean);
			printMapData(reconBean);			
		}
		
		// Starting the TLL Process
		if(accountType != null && "TLL".equalsIgnoreCase(accountType) && deltaDaysForTLL > 60) {
			// Test data for TLL - TODO to be removed
			testTLLTestData(reconBean);
			
			TyingLinesLoansProcess tllProcess = new TyingLinesLoansProcess();
			reconBean = tllProcess.executeTLLProcess(reconBean);				
			printMapData(reconBean);	
		}
		
		// Starting the MOD/HREFI Process
		if(accountType != null && 
				("MOD".equalsIgnoreCase(accountType) || 
					 "HREFI".equalsIgnoreCase(accountType)) && 
					 	deltaDaysForModHrefi > 60) {
			// Test data for MOD/HREFI- TODO to be removed
			setModHrefiTestData(reconBean);
			
			//Invoking the rul
			HamaRefinaceProcess hamaRefinaceProcess = new HamaRefinaceProcess();
			reconBean = hamaRefinaceProcess.executeModHamaRefinaceProcess(reconBean);
		}		
		return reconBean;
	}
	
	private long determineDaysDifference(Date lastDate, Date earlyDate) throws Exception {	
		long deltaDays = (lastDate.getTime() - earlyDate.getTime())/MILLSECS_PER_DAY;
		return deltaDays;
	}
	
	private Date getDateFromString(String stringData) throws Exception {	
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date lastDate = (Date) formatter.parse(stringData); 
		return lastDate;
	}
	
	private void printMapData(ReconciliationBean reconBeanFromSIMO) throws Exception {
		Map<String, String> actionMap = reconBeanFromSIMO.getActionMap();
		for(String key : actionMap.keySet()) {
			System.out.println( "---- key ----" + key + "---- value ----" + actionMap.get(key));
		}
	}
	
	private void printListData(List<RuleDVO> dataList, String event) throws Exception {
		if(dataList != null) {
			System.out.println( "---------- " + event + " Size -----------" + dataList.size());
			for(RuleDVO ruleDVO : dataList) {
				System.out.println( "---- Rule exceptioncode  ----" + ruleDVO.getExceptioncode() );
				System.out.println( "---- field Name  ----" + ruleDVO.getFieldName());
				System.out.println( "---- old Value  ----" + ruleDVO.getFieldOldVale());
				System.out.println( "---- New Value  ----" + ruleDVO.getFieldNewValue());
			}
		}
	}
	
	private void setModHrefiTestData(ReconciliationBean reconBean) throws Exception {
		// Data Related to STOP Rules
		reconBean.setHamaIndicator("Y");		
		reconBean.setMssNumber("0000123456789");
		reconBean.setFirstMortgageAccountNumber("0000123456789");	
		reconBean.setFirstMortgageCurrentPrincipalBalance(new BigDecimal("0"));
		
		// Data related to Rules
		reconBean.setQpcarIndicator("Y");
		reconBean.setSlidCode("HAREFI");
		//reconBean.setDocCust();
		reconBean.setPhoneFlag("4");
		//reconBean.setPhoneFlag("2");
	}
	
	private void setSimoTestData(ReconciliationBean reconBean) throws Exception {
		// Data Related for STOP Rules
		reconBean.setHamaIndicator("Y");
		reconBean.setClientNumner(591);
		reconBean.setSlidCode("SINGLE");
		reconBean.setMssNumber("0000123456789");
		reconBean.setSecondLienHolderAccountNumber("000123456789");
		reconBean.setLcaAccountNumber("000123456789");
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
		reconBean.setPhoneFlag("2");
		reconBean.setAppraisalType(null);
		reconBean.setPropertyValue(new BigDecimal("0"));
		reconBean.setAppraisedValue(new BigDecimal("310000"));
		reconBean.setPurchasePrice(new BigDecimal("1000"));
		reconBean.setPriorMortgageBalance(new BigDecimal(0));
		reconBean.setOriginalMortgageBalance(new BigDecimal("10000"));		
		reconBean.setRecoveryCode("B");
		reconBean.setLcaDue(20);
		reconBean.setPersonCode("B");
		reconBean.setBillMode("9");
		reconBean.setBillCycle("H01");
		reconBean.setPropertyTypeCode("4");
		reconBean.setOccupancyCode("3");	
		//reconBean.setHamaIndicator("II");
		
		// Verify Living Units on 1st Mortgage is blank or null
		reconBean.setLivingUnits("2");
		reconBean.setHamaIndicator("III");						
	}
	
	private void testTLLTestData(ReconciliationBean reconBean) throws Exception {
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
	}
	
}
