package com.sample;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatelessKnowledgeSession;

import java.math.*;

public class SIMOCloseProcess {
	
	public SIMOCloseProcess() {		
	}
	
	public ReconciliationBean executeSIMOProcess(ReconciliationBean reconBean) throws Exception {
		//Map<String, String> actionMap = reconBean.getActionMap();
		Map<String, List<RuleDVO>> eventMap = reconBean.getEventMap();
		List<RuleDVO> alertsList = reconBean.getAlertsList();
		List<RuleDVO> correctionsList = reconBean.getCorrectionsList();
		List<RuleDVO> shawUpdates = reconBean.getShawUpdatesList();
		List<RuleDVO> alltelUpdates = reconBean.getAlltelUpdatesList();		
		List<RuleDVO> harpUpdates = reconBean.getHarpUpdatesList();
		List<RuleDVO> mismatchList = reconBean.getMismatchList();
		
		String hamaIndicator = reconBean.getHamaIndicator();
		int clientNumber = reconBean.getClientNumner();
		String slidCode = reconBean.getSlidCode();
		String mssNumber = reconBean.getMssNumber();
		String lcaAccountNumber = reconBean.getLcaAccountNumber();
		String firstMortgageAccountNumber = reconBean.getFirstMortgageAccountNumber();
		String secondLienHolderAccountNumber = reconBean.getSecondLienHolderAccountNumber();	
		
		BigDecimal appraisedValue = reconBean.getAppraisedValue();
		BigDecimal purchasePrice = reconBean.getPurchasePrice();
		BigDecimal propertyValue = reconBean.getPropertyValue();
		BigDecimal minPrice = determineMinimumPrice(appraisedValue, purchasePrice);
		System.out.println( "--- minPrice ---" + minPrice); 
		
		reconBean.setMinimumPrice(minPrice);
		BigDecimal intialValue = new BigDecimal("0");
		
		System.out.println( "--- secondLienHolderAccountNumber ---" + secondLienHolderAccountNumber); 
		System.out.println( "--- lcaAccountNumber ---" + lcaAccountNumber); 
		System.out.println( "--- clientNumber --" + clientNumber);
		System.out.println( "--- hamaIndicator --" + hamaIndicator);
		System.out.println( "--- slidCode ---" + slidCode);	
		System.out.println( "--- firstMortgageAccountNumber ---" + firstMortgageAccountNumber);		
		System.out.println( "--- appraisedValue --" + appraisedValue);
		System.out.println( "--- purchasePrice ---" + purchasePrice);	
		System.out.println( "--- propertyValue ---" + propertyValue);
				
		// Verify HAMA Indicator on LCA is equal to "Y"
		if("N".equalsIgnoreCase(hamaIndicator)) {
			addDataToList(alertsList, "4", "SHAW", "hamaIndicator", hamaIndicator, "");						
			eventMap.put("ALERTS", alertsList);
			return reconBean;			
		}
		
		// Verify 1st Mortgage account number is valid a client number , i.e. 472, 591, 685, 708 or 936
		if(clientNumber != 472 && clientNumber != 591 && clientNumber != 685 &&
				clientNumber != 708 && clientNumber != 936) {
			addDataToList(alertsList, "5", "MSP", "clientNumber", clientNumber+"", "");						
			eventMap.put("ALERTS", alertsList);
			return reconBean;			
		}
		
		// Verify 1st Mortgage SLID code is "SINGLE" or not. If not SINGLE, stop.
		if(!"SINGLE".equalsIgnoreCase(slidCode)) {
			addDataToList(alertsList, "6", "MSP", "slidCode", slidCode, "");						
			eventMap.put("ALERTS", alertsList);
			return reconBean;	
		}
		
		BigDecimal firstMortgagePrincipalBalance = reconBean.getFirstMortgageCurrentPrincipalBalance();
		String lcaStatus = reconBean.getLcaStatus();
		String docCust = reconBean.getDocCust();
		System.out.println("--- firstMortgagePrincipalBalance ---" + firstMortgagePrincipalBalance + 
						   "--- lcStatus ---" + lcaStatus +
						   "--- docCust ---" + docCust);
		
		/* If 1st Mortgage current principal balance is equal to zero, verify LCA is open;
		   Follow 1st Mortgage-PIF and LCA open process */
		if(firstMortgagePrincipalBalance.compareTo(intialValue) == 0 && "OPEN".equalsIgnoreCase(lcaStatus)) {
			int combinedStatementIndicator = reconBean.getCombinedStatementIndicator();
			String phoneFlag = reconBean.getPhoneFlag();
			String invalidHamaIndicator = reconBean.getHamaIndicator();
		
			System.out.println("--- combinedStatementIndicator ---" + combinedStatementIndicator);
			System.out.println("--- phoneFlag ---" + phoneFlag);
			System.out.println("--- invalidHamaIndicator ---" + invalidHamaIndicator);
			
			// Invoke the Rule engine for LCA-OPEN and 1st Mortgage-PIF
			String drlFile = "C:/projects/Learning/Drools/src/rules/LCAOpenFirstMortgagePaidInFull.drl";
			invokeRuleEngine(reconBean, drlFile);
			//After executing the Mortgage-PIF, lca status-OPEN rules; close the lca status to CLOSED in HARP.
			correctionsList = reconBean.getCorrectionsList();
			harpUpdates = reconBean.getHarpUpdatesList();
			addDataToList(correctionsList, "53", "HARP", "lcaStatus", "OPEN", "CLOSED");
			addDataToList(harpUpdates, "53", "HARP", "lcaStatus", "OPEN", "CLOSED");			
			eventMap.put("CORRETCIONS", correctionsList);
			eventMap.put("HARPUPDATES", harpUpdates);
						
			//Ends the process.
			return reconBean;
		}

		/* If 1st Mortgage current principal balance is equal to zero, verify LCA is Closed;
		   Follow 1st Mortgage-PIF and LCA Closed process */
		if(firstMortgagePrincipalBalance.compareTo(intialValue) == 0 && "CLOSED".equalsIgnoreCase(lcaStatus)) {
			correctionsList = reconBean.getCorrectionsList();
			harpUpdates = reconBean.getHarpUpdatesList();
			addDataToList(correctionsList, "64", "HARP", "lcaStatus", "CLOSED", "CLOSED");
			addDataToList(harpUpdates, "64", "HARP", "lcaStatus", "CLOSED", "CLOSED");	
			eventMap.put("CORRETCIONS", correctionsList);
			eventMap.put("HARPUPDATES", harpUpdates);
			return reconBean;
		}
		
		/* If 1st Mortgage current principal balance is greater than zero, verify LCA is Closed;
		   Follow 1st Mortgage-OPEN and LCA Closed process */		
		if(firstMortgagePrincipalBalance.compareTo(intialValue) > 0 && "CLOSED".equalsIgnoreCase(lcaStatus)) {
			if( "Single".equalsIgnoreCase(slidCode) || 
					"NYCEMA".equalsIgnoreCase(slidCode) ||  
						"NYMECA".equalsIgnoreCase(slidCode) ) {
				
				// Invoke the Rule engine for for LCA-CLOSED and 1st Mortgage-OPEN
				String drlFile = "C:/projects/Learning/Drools/src/rules/LCAClosedFirstMortgageOpen.drl";
				invokeRuleEngine(reconBean, drlFile);
				
				alltelUpdates = reconBean.getAlltelUpdatesList();
				harpUpdates = reconBean.getHarpUpdatesList();
				correctionsList = reconBean.getCorrectionsList();
				
				// Add a note for MSP
				addDataToList(alltelUpdates, "60", "MSP", "note", reconBean.getNote(), 
											"1st Mortgage Account Home Asset indicators have been removed");			
				// Change the account status to closed. Add it to corrections
				addDataToList(harpUpdates, "61", "HARP", "lcaStatus", "CLOSED", "CLOSED");
				addDataToList(correctionsList, "64", "HARP", "lcaStatus", "CLOSED", "CLOSED");
				
				eventMap.put("ALLTELUPDATES", alltelUpdates);
				eventMap.put("CORRETCIONS", correctionsList);
				eventMap.put("HARPUPDATES", harpUpdates);
				
				return reconBean;
			} else if ("HAREFI".equalsIgnoreCase(slidCode) || docCust != null ) {
				// Follow process - Mod/HARefi Process
				HamaRefinaceProcess hamaRefinaceProcess = new HamaRefinaceProcess();
				hamaRefinaceProcess.executeModHamaRefinaceProcess(reconBean);				
				return reconBean;
			}
		}
		
		// Verify mss number is null or not equal to 13 digits
		if(mssNumber == null || firstMortgageAccountNumber == null) {
			addDataToList(mismatchList, "9", "SHAW", "mssNumber", mssNumber, "");						
			eventMap.put("MISMATCHES", mismatchList);
			return reconBean;
		}
		
		if(mssNumber.length() != 13 ||
				mssNumber.equals("0000000000000") ||
					!mssNumber.equalsIgnoreCase(firstMortgageAccountNumber)) {
			addDataToList(mismatchList, "9", "SHAW", "mssNumber", mssNumber, "");						
			eventMap.put("MISMATCHES", mismatchList);
			return reconBean;
		}
		
		/* Verify 2nd Lien Holder account number, in 1st Mortgage Lien Loan#
			line is equal to LCA account number or not blank or null */
		if(lcaAccountNumber == null || secondLienHolderAccountNumber == null) {			
			addDataToList(mismatchList, "10", "MSP", "lcaAccountNumber", lcaAccountNumber, "");						
			eventMap.put("MISMATCHES", mismatchList);
			return reconBean;
		}
		
		if(secondLienHolderAccountNumber == "" || 
				lcaAccountNumber == "" || 
					!lcaAccountNumber.equalsIgnoreCase(secondLienHolderAccountNumber)) {
			addDataToList(mismatchList, "10", "MSP", "lcaAccountNumber", lcaAccountNumber, "");						
			eventMap.put("MISMATCHES", mismatchList);
			return reconBean;
		}
		
		// Invoke Rule engine for continution of SIMO Process.
		System.out.println("------  Started SIMO Continution ------");
		String drlFile = "C:/projects/Learning/Drools/src/rules/SimultaneousClose.drl";
		invokeRuleEngine(reconBean, drlFile);
		System.out.println("------  Started SIMO Continution After ------");

		// Prop Val (LCA) = 0, Appraised Val or Purchase price = 0 - Report an ALERT and STOP
		if(intialValue.compareTo(propertyValue) == 0) {
			reconBean.setPropertyValue(new BigDecimal("0"));	
		}
		if(appraisedValue == null) {
			reconBean.setAppraisedValue(new BigDecimal("0"));
		}		
		if(intialValue.compareTo(purchasePrice) == 0) {
			reconBean.setPurchasePrice(new BigDecimal("0"));
		}		
		if( (propertyValue.compareTo(intialValue) == 0) && 
				(appraisedValue.compareTo(intialValue) == 0 || 
						purchasePrice.compareTo(intialValue) == 0) ) {	
			alertsList = reconBean.getAlertsList();
			shawUpdates = reconBean.getShawUpdatesList();
			addDataToList(alertsList, "14A", "SHAW", "propertyValue", "0", "0");		
			addDataToList(shawUpdates, "14A", "SHAW", "propertyValue", "0", "0");
			eventMap.put("ALERTS", alertsList);
			eventMap.put("SHAWUPDATES", shawUpdates);
			return reconBean;
		}
		
		//	Invoke Rule engine for SIMO Process Continuation.
		System.out.println("------ Started SIMO Ext Continution ------");
		String drlFile1 = "C:/projects/Learning/Drools/src/rules/SimultaneousCloseExt.drl";
		invokeRuleEngine(reconBean, drlFile1);
		
		String propertyTypeCode = reconBean.getPropertyTypeCode();
		String occupancyCode = reconBean.getOccupancyCode();
		int occupancyCodeNum = 0;
		if(occupancyCode != null && occupancyCode != "") {
			occupancyCodeNum = new Integer(occupancyCode);
		}
		String livingUnits = reconBean.getLivingUnits();
		int livingUnitsNum = 0;
		if(livingUnits != null && livingUnits != "") {
			livingUnitsNum = new Integer(livingUnits);
		}
		
		// Prop Type code = 5,A,B, or D - Report an Alert and execute Remove HAMA Ind process
		if("5".equalsIgnoreCase(propertyTypeCode) || 
				"A".equalsIgnoreCase(propertyTypeCode) || 
					"B".equalsIgnoreCase(propertyTypeCode) ||
						"D".equalsIgnoreCase(propertyTypeCode)) {
			return executeHamaRemovalProcessRules(reconBean);			
		}

		// Occupancy Code 4 or greater - Report an Alert and execute Remove HAMA Ind process
		if(occupancyCodeNum >= 4) {
			return executeHamaRemovalProcessRules(reconBean);
		}
		
		// Living Units 5 or greater - Report an Alert and execute Remove HAMA Ind process
		if(livingUnitsNum >= 5) {
			return executeHamaRemovalProcessRules(reconBean);
		}
		
		//Continuue with Misc process
		String drlFileMisc = "C:/projects/Learning/Drools/src/rules/HamaMisc.drl";
		invokeRuleEngine(reconBean, drlFileMisc);
		
		return reconBean;
    }
	
	private void invokeRuleEngine(ReconciliationBean reconBean, String drlFile) throws Exception {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        //kbuilder.add( ResourceFactory.newClassPathResource("Applicant.drl", DroolsTest.class), ResourceType.DRL );
        kbuilder.add(ResourceFactory.newFileResource(drlFile), ResourceType.DRL );            
        if ( kbuilder.hasErrors() ) {
        	System.out.println( kbuilder.getErrors().toString() );
        }
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
        StatelessKnowledgeSession ksession = kbase.newStatelessKnowledgeSession();
        ksession.execute(Arrays.asList(new Object[] {reconBean, new RuleDVO()}));
        //ksession.execute(reconBean);	
	}
	
	public ReconciliationBean executeHamaRemovalProcessRules(ReconciliationBean reconBean) throws Exception {
		String hamaRemovalDrlFile = "C:/projects/Learning/Drools/src/rules/HamaRemovalProcess.drl";
		invokeRuleEngine(reconBean, hamaRemovalDrlFile);
		
		//Removal Process - Slid Code = HAREFI OR Doc Cust is Not Null
		String slidCode = reconBean.getSlidCode();
		String docCust = reconBean.getDocCust();
		System.out.println("--------- docCust ---------" + docCust);
		System.out.println("--------- slidCode ---------" + slidCode);
		if("HAREFI".equalsIgnoreCase(slidCode) || docCust != null ) {
			// Follow process - Mod/Refi Process
			HamaRefinaceProcess hamaRefinaceProcess = new HamaRefinaceProcess();
			hamaRefinaceProcess.executeModHamaRefinaceProcess(reconBean);				
			return reconBean;
		}
		
		// Change the account status to closed. Add it to corrections
		List<RuleDVO> harpUpdates = reconBean.getHarpUpdatesList();		
		addDataToList(harpUpdates, "75", "HARP", "lcaStatus", "CLOSED", "CLOSED");
		reconBean.getEventMap().put("HARPUPDATES", harpUpdates);
		
		return reconBean;
	}
	
	private BigDecimal determineMinimumPrice(BigDecimal appraisedValue, BigDecimal purchasePrice) {
		if(appraisedValue != null && purchasePrice != null) {
			if ( appraisedValue.compareTo(purchasePrice) > 0 ) {
				return purchasePrice;
			} else {
				return appraisedValue;
			}
		}
		return new BigDecimal("0");
	}	

	private void addDataToList(List<RuleDVO> dataList, String exceptionCode, String sourceSystem, 
			String fieldName, String filedOldVale, String filedNewValue) {
		RuleDVO ruleDVO = new RuleDVO();
		ruleDVO.setExceptioncode(exceptionCode);
		ruleDVO.setSourceSystem(sourceSystem);
		ruleDVO.setFieldName(fieldName);
		ruleDVO.setFieldOldVale(filedOldVale);
		ruleDVO.setFieldNewValue(filedNewValue);
		dataList.add(ruleDVO);
	}
	
}
