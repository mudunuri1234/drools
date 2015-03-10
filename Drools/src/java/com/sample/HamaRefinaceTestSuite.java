package com.sample;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class HamaRefinaceTestSuite {
	
	public HamaRefinaceTestSuite() {
	}
	
	public static void main(String args[]) throws Exception {
		try {
			HamaRefinaceTestSuite testSuite = new HamaRefinaceTestSuite();
			ReconciliationBean reconBean = new ReconciliationBean();
			
			testSuite.testModRefinanceTestRule(reconBean);
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void testModRefinanceTestRule(ReconciliationBean reconBean) throws Exception {
		// Data Related to STOP Rules
		reconBean.setHamaIndicator("Y");		
		reconBean.setMssNumber("0000123456789");
		reconBean.setFirstMortgageAccountNumber("0000123456789");	
		reconBean.setFirstMortgageCurrentPrincipalBalance(new BigDecimal("0"));
		
		// Data related to Rules
		reconBean.setQpcarIndicator("YYN");
		reconBean.setSlidCode("HAREFI");
		//reconBean.setDocCust();
		reconBean.setPhoneFlag("4");
		//reconBean.setPhoneFlag("2");
		
		// This Rule Data - Y is an In-valid value
		HamaRefinaceProcess hamaRefinace = new HamaRefinaceProcess();
		hamaRefinace.executeModHamaRefinaceProcess(reconBean);
				
//		List<RuleDVO> alertsList = reconBean.getAlertsList();
//		printListData(alertsList, "Alerts");	
//		List<RuleDVO> correctionsList = reconBean.getCorrectionsList();
//		printListData(correctionsList, "Corrections");
		
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

}
