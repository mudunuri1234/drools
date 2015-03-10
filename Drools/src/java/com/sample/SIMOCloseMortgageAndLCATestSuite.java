package com.sample;

import java.math.BigDecimal;
import java.util.Map;

public class SIMOCloseMortgageAndLCATestSuite {
	
	public SIMOCloseMortgageAndLCATestSuite() {
	}
	
	public static void main(String args[]) throws Exception {
		try {
			SIMOCloseMortgageAndLCATestSuite simoProcess = new SIMOCloseMortgageAndLCATestSuite();
			
			ReconciliationBean reconBean = new ReconciliationBean();			
			//simoProcess.testHamaIndicatorRule(reconBean);
			//simoProcess.testInvalidClientNumberRule(reconBean);
			//simoProcess.testInvalidSlidCodeRule(reconBean);
			//simoProcess.testInvalidMSSNumberRule(reconBean);
			//simoProcess.testAccountNumberMismatchRule(reconBean);			
			//simoProcess.testPropertyValuePurchasePriceRule(reconBean);			
			//simoProcess.testMortagagePIFAndLCAOpenProcess(reconBean);						
			//simoProcess.testMortagagePIFAndLCAClosedProcess(reconBean);			
			//simoProcess.testMortagageIsOpenAndLCAClosedProcess1(reconBean);
			simoProcess.testMortagageIsOpenAndLCAClosedProcess2(reconBean);
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void testHamaIndicatorRule(ReconciliationBean reconBean) throws Exception {
		SIMOCloseProcess simoProcess = new SIMOCloseProcess();
		reconBean.setHamaIndicator("N");
		ReconciliationBean reconBeanFromSIMO = simoProcess.executeSIMOProcess(reconBean);		
		printMapData(reconBeanFromSIMO);
	}
	
	private void testInvalidClientNumberRule(ReconciliationBean reconBean) throws Exception {
		SIMOCloseProcess simoProcess = new SIMOCloseProcess();
		reconBean.setHamaIndicator("Y");
		//This rule data
		reconBean.setClientNumner(12);
		simoProcess.executeSIMOProcess(reconBean);				
		printMapData(reconBean);
	}
	
	private void testInvalidSlidCodeRule(ReconciliationBean reconBean) throws Exception {
		SIMOCloseProcess simoProcess = new SIMOCloseProcess();
		reconBean.setHamaIndicator("Y");
		reconBean.setClientNumner(472);
		//This rule data
		reconBean.setSlidCode("Not Single");
		simoProcess.executeSIMOProcess(reconBean);
		
		printMapData(reconBean);
	}
	
	private void testInvalidMSSNumberRule(ReconciliationBean reconBean) throws Exception {		
		reconBean.setHamaIndicator("Y");
		reconBean.setClientNumner(472);
		reconBean.setSlidCode("Single");
		//This rule data
		reconBean.setMssNumber("00001234567899");
		
		SIMOCloseProcess simoProcess = new SIMOCloseProcess();
		simoProcess.executeSIMOProcess(reconBean);		
		printMapData(reconBean);
	}
	
	private void testAccountNumberMismatchRule(ReconciliationBean reconBean) throws Exception {
		reconBean.setHamaIndicator("Y");
		reconBean.setClientNumner(472);
		reconBean.setSlidCode("Single");
		reconBean.setMssNumber("0000123456789");		
		//This rule data
		reconBean.setSecondLienHolderAccountNumber("000123456789");
		reconBean.setLcaAccountNumber("0001234567899");		
		
		SIMOCloseProcess simoProcess = new SIMOCloseProcess();
		simoProcess.executeSIMOProcess(reconBean);		
		printMapData(reconBean);
	}
	
	private void testPropertyValuePurchasePriceRule(ReconciliationBean reconBean) throws Exception {
		reconBean.setHamaIndicator("Y");
		reconBean.setClientNumner(472);
		reconBean.setSlidCode("Single");
		reconBean.setMssNumber("0000123456789");		
		reconBean.setSecondLienHolderAccountNumber("000123456789");
		reconBean.setLcaAccountNumber("000123456789");		
		//This rule data
		reconBean.setPropertyValue(new BigDecimal("0.0"));
		reconBean.setAppraisedValue(new BigDecimal("0.0"));
		reconBean.setPurchasePrice(new BigDecimal("0.0"));	
		
		SIMOCloseProcess simoProcess = new SIMOCloseProcess();
		simoProcess.executeSIMOProcess(reconBean);
		printMapData(reconBean);
	}	
	
	private void testMortagagePIFAndLCAOpenProcess(ReconciliationBean reconBean) throws Exception {	
		//Data required for STOP Rules
		reconBean.setHamaIndicator("Y");	
		reconBean.setClientNumner(591);
		reconBean.setSlidCode("SINGLE");
		reconBean.setMssNumber("0000123456789");		
		reconBean.setSecondLienHolderAccountNumber("000123456789");
		//TODO - Question same or different
		reconBean.setLcaAccountNumber("000123456789");
		reconBean.setFirstMortgageAccountNumber("0000123456789");		
		
		//Data Related to STOP Rules
		reconBean.setAppraisedValue(new BigDecimal("10000"));
		reconBean.setPurchasePrice(new BigDecimal("300000"));
		reconBean.setPropertyValue(new BigDecimal("310000"));
				
		//Data related to Rules - 1st MTG-PIF and LCA-OPEN
		reconBean.setFirstMortgageCurrentPrincipalBalance(new BigDecimal("0"));
		reconBean.setLcaStatus("OPEN");			
		reconBean.setCombinedStatementIndicator(6);
		reconBean.setPhoneFlag("2");
		reconBean.setHamaIndicator("Y");
		
		SIMOCloseProcess simoProcess = new SIMOCloseProcess();
		simoProcess.executeSIMOProcess(reconBean);
		printMapData(reconBean);
	}
	
	private void testMortagagePIFAndLCAClosedProcess(ReconciliationBean reconBean) throws Exception {
		//Data required for STOP Rules
		reconBean.setHamaIndicator("Y");	
		reconBean.setClientNumner(591);
		reconBean.setSlidCode("SINGLE");
		reconBean.setMssNumber("0000123456789");		
		reconBean.setSecondLienHolderAccountNumber("000123456789");
		reconBean.setLcaAccountNumber("000123456789");
		reconBean.setFirstMortgageAccountNumber("0000123456789");		
		
		//Data Related to STOP Rules
		reconBean.setAppraisedValue(new BigDecimal("10000"));
		reconBean.setPurchasePrice(new BigDecimal("300000"));
		reconBean.setPropertyValue(new BigDecimal("310000"));
				
		//Data related to Rules - 1st MTG-PIF and LCA-CLOSED
		reconBean.setFirstMortgageCurrentPrincipalBalance(new BigDecimal("0"));
		reconBean.setLcaStatus("CLOSED");
				
		SIMOCloseProcess simoProcess = new SIMOCloseProcess();
		simoProcess.executeSIMOProcess(reconBean);
		printMapData(reconBean);
	}		
	
	private void testMortagageIsOpenAndLCAClosedProcess1(ReconciliationBean reconBean) throws Exception {
		//Data Related for STOP Rules
		reconBean.setHamaIndicator("Y");
		reconBean.setClientNumner(591);
		reconBean.setSlidCode("SINGLE");
		reconBean.setMssNumber("0000123456789");
		reconBean.setSecondLienHolderAccountNumber("000123456789");
		reconBean.setLcaAccountNumber("000123456789");
		reconBean.setFirstMortgageAccountNumber("0000123456789");	
		
		//Data Related to STOP Rules
		reconBean.setAppraisedValue(new BigDecimal("10000"));
		reconBean.setPurchasePrice(new BigDecimal("300000"));
		reconBean.setPropertyValue(new BigDecimal("310000"));
				
		//Data related to Rules - 1st MTG - is Greter than Zero and LCA-CLOSED
		reconBean.setFirstMortgageCurrentPrincipalBalance(new BigDecimal("500"));
		reconBean.setLcaStatus("CLOSED");
		reconBean.setSlidCode("Single".toUpperCase());
		reconBean.setPersonCode("=");
		//reconBean.setPersonCode("B");
		reconBean.setBillMode("7");
		reconBean.setBillCycle("H20");
		//reconBean.setBillCycle("PPP");
		
		SIMOCloseProcess simoProcess = new SIMOCloseProcess();
		simoProcess.executeSIMOProcess(reconBean);
		printMapData(reconBean);
	}
	
	private void testMortagageIsOpenAndLCAClosedProcess2(ReconciliationBean reconBean) throws Exception {
		//Data Related for STOP Rules
		reconBean.setHamaIndicator("Y");
		reconBean.setClientNumner(591);
		reconBean.setSlidCode("SINGLE");
		reconBean.setMssNumber("0000123456789");
		reconBean.setSecondLienHolderAccountNumber("000123456789");
		reconBean.setLcaAccountNumber("000123456789");
		reconBean.setFirstMortgageAccountNumber("0000123456789");	
		
		//Data Related to STOP Rules
		reconBean.setAppraisedValue(new BigDecimal("10000"));
		reconBean.setPurchasePrice(new BigDecimal("300000"));
		reconBean.setPropertyValue(new BigDecimal("310000"));
				
		//Data related to Rules - 1st MTG - is Greter than Zero and LCA-CLOSED
		reconBean.setFirstMortgageCurrentPrincipalBalance(new BigDecimal("500"));
		reconBean.setLcaStatus("CLOSED");
		reconBean.setSlidCode("Single".toUpperCase());
		reconBean.setPersonCode("B");
		reconBean.setBillMode("7");
		reconBean.setBillCycle("PPP");
		
		SIMOCloseProcess simoProcess = new SIMOCloseProcess();
		simoProcess.executeSIMOProcess(reconBean);
		printMapData(reconBean);
	}

	private void printMapData(ReconciliationBean reconBeanFromSIMO) throws Exception {
		Map<String, String> actionMap = reconBeanFromSIMO.getActionMap();
		for(String key : actionMap.keySet()) {
			System.out.println( "---- key ----" + key + "---- value ----" + actionMap.get(key));
		}
	}

}
