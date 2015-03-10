package com.sample;

import java.math.BigDecimal;
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

public class TyingLinesLoansProcess {
	
	public TyingLinesLoansProcess() {
	}
	
	public ReconciliationBean executeTLLProcess(ReconciliationBean reconBean) throws Exception {
		Map<String, String> actionMap = reconBean.getActionMap();
		String hamaIndicator = reconBean.getHamaIndicator();
		
		Map<String, List<RuleDVO>> eventMap = reconBean.getEventMap();
		List<RuleDVO> alertsList = reconBean.getAlertsList();
		List<RuleDVO> correctionsList = reconBean.getCorrectionsList();
		List<RuleDVO> shawUpdates = reconBean.getShawUpdatesList();
		List<RuleDVO> alltelUpdates = reconBean.getAlltelUpdatesList();		
		List<RuleDVO> harpUpdates = reconBean.getHarpUpdatesList();
		List<RuleDVO> mismatchList = reconBean.getMismatchList();
						
		// Verify HAMA Indicator on LCA is equal to "N"
		if("N".equalsIgnoreCase(hamaIndicator)) {
			addDataToList(alertsList, "24", "SHAW", "hamaIndicator", hamaIndicator, "");						
			eventMap.put("ALERTS", alertsList);
			return reconBean;		
		}
		
		String qpcarIndicator = reconBean.getQpcarIndicator();
		boolean isValidQPCARIndicator = isAValidQPCARIndicator(qpcarIndicator);
		if(isValidQPCARIndicator) {
			addDataToList(alertsList, "25", "SHAW", "qpcarIndicator", qpcarIndicator, "");						
			eventMap.put("ALERTS", alertsList);				
		}
		
		// Verify mss number is null or not equal to 13 digits, MSS# = MTG acct #
		String mssNumber = reconBean.getMssNumber();
		String firstMortgageAccountNumber = reconBean.getFirstMortgageAccountNumber();
		System.out.println("--- mssNumber ---" + mssNumber + "-- firstMortgageAccountNumber --" + firstMortgageAccountNumber);
		if(mssNumber == null || firstMortgageAccountNumber == null) {
			addDataToList(mismatchList, "26", "SHAW", "mssNumber", mssNumber, "");						
			eventMap.put("MISMATCHES", mismatchList);
			return reconBean;
		}
		
		if(mssNumber.length() != 13 ||
				mssNumber.equals("0000000000000") ||
					!mssNumber.equalsIgnoreCase(firstMortgageAccountNumber)) {
			addDataToList(mismatchList, "26", "SHAW", "mssNumber", mssNumber, "");						
			eventMap.put("MISMATCHES", mismatchList);
			return reconBean;
		}
		
		/* Verify 2nd Lien Holder account number, in 1st Mortgage Lien Loan#
		line is equal to LCA account number or not blank or null */
		String secondLienHolderAccountNumber = reconBean.getSecondLienHolderAccountNumber();
		String lcaAccountNumber = reconBean.getLcaAccountNumber();
		System.out.println("-- secondLienHolderAccountNumber --" + secondLienHolderAccountNumber + 
						 	"-- lcaAccountNumber --" + lcaAccountNumber);
		if(lcaAccountNumber == null || secondLienHolderAccountNumber == null) {
			addDataToList(mismatchList, "27", "MSP", "lcaAccountNumber", lcaAccountNumber, "");						
			eventMap.put("MISMATCHES", mismatchList);
			return reconBean;
		}
		
		if(secondLienHolderAccountNumber == "" || 
				lcaAccountNumber == "" || 
					!lcaAccountNumber.equalsIgnoreCase(secondLienHolderAccountNumber)) {
			addDataToList(mismatchList, "27", "MSP", "lcaAccountNumber", lcaAccountNumber, "");						
			eventMap.put("MISMATCHES", mismatchList);
			return reconBean;
		}
		
		String drlFile = "C:/projects/Learning/Drools/src/rules/TyingLinesLoans.drl";
		invokeRuleEngine(reconBean, drlFile);
		
		/* Verify Prior Mortgage Balance, on LCA, should be greater than zero - 
		   If equal to zero follow Mod/HAREFI process */
		BigDecimal intialValue = new BigDecimal("0");
		BigDecimal priorMortgageBalance = reconBean.getPriorMortgageBalance();
		BigDecimal firstMortgagePrincipalBalance = reconBean.getFirstMortgageCurrentPrincipalBalance();
		System.out.println("---- priorMortgageBalance ----" + priorMortgageBalance + 
								"----- firstMortgagePrincipalBalance is ----" + firstMortgagePrincipalBalance);
		if(priorMortgageBalance == null) {
			priorMortgageBalance =  new BigDecimal("0");
		}
		
		if(firstMortgagePrincipalBalance == null) {
			firstMortgagePrincipalBalance =  new BigDecimal("0");
		}
		
		if(intialValue.compareTo(priorMortgageBalance) == 0 || intialValue.compareTo(firstMortgagePrincipalBalance) == 0) {
			HamaRefinaceProcess modHamaRefinace = new HamaRefinaceProcess();
			modHamaRefinace.executeModHamaRefinaceProcess(reconBean);
			// End the process.
			return reconBean;
		}
		
		String drlFileExt = "C:/projects/Learning/Drools/src/rules/TyingLinesLoansExt.drl";
		invokeRuleEngine(reconBean, drlFileExt);
		
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
		
		// Prop Type code = 5, A, B, or D - Report an Alert and execute Remove HAMA Ind process
		if("5".equalsIgnoreCase(propertyTypeCode) || 
				"A".equalsIgnoreCase(propertyTypeCode) || 
					"B".equalsIgnoreCase(propertyTypeCode) ||
						"D".equalsIgnoreCase(propertyTypeCode)) {
			return executeHamaRemovalProcessRules(reconBean, firstMortgageAccountNumber);			
		}

		// Occupancy Code 4 or greater - Report an Alert and execute Remove HAMA Ind process
		if(occupancyCodeNum >= 4) {
			return executeHamaRemovalProcessRules(reconBean, firstMortgageAccountNumber);
		}
		
		// Living Units 5 or greater - Report an Alert and execute Remove HAMA Ind process
		if(livingUnitsNum >= 5) {
			return executeHamaRemovalProcessRules(reconBean, firstMortgageAccountNumber);
		}
		
		// Continuue with Misc process
		String drlFileMisc = "C:/projects/Learning/Drools/src/rules/HamaMisc.drl";
		invokeRuleEngine(reconBean, drlFileMisc);
		
		return reconBean;
	}
	
	private ReconciliationBean executeHamaRemovalProcessRules(ReconciliationBean reconBean, 
			String firstMortgageAccountNumber) throws Exception {
		String hamaRemovalDrlFile = "C:/projects/Learning/Drools/src/rules/HamaRemovalProcess.drl";
		invokeRuleEngine(reconBean, hamaRemovalDrlFile);
		
		return reconBean;
	}	
	
	private void invokeRuleEngine(ReconciliationBean reconBean, String drlFile) throws Exception {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        //kbuilder.add( ResourceFactory.newClassPathResource("Applicant.drl", DroolsTest.class), ResourceType.DRL );
        kbuilder.add(ResourceFactory.newFileResource(drlFile), ResourceType.DRL );            
        if(kbuilder.hasErrors()) {
        	System.out.println( kbuilder.getErrors().toString() );
        }
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
        StatelessKnowledgeSession ksession = kbase.newStatelessKnowledgeSession();
        ksession.execute(Arrays.asList(new Object[] {reconBean, new RuleDVO()}));
        //ksession.execute(reconBean);	
	}
	
	private boolean isAValidQPCARIndicator(String qpcarIndicator) throws Exception {
		if(qpcarIndicator == null && qpcarIndicator == "") {
			return true;
		}
		
		if( !"II".equalsIgnoreCase(qpcarIndicator) &&
			!"YI".equalsIgnoreCase(qpcarIndicator) &&
			!"NI".equalsIgnoreCase(qpcarIndicator) &&
			!"YE".equalsIgnoreCase(qpcarIndicator) &&	
			!"YY".equalsIgnoreCase(qpcarIndicator) &&
			!"YN".equalsIgnoreCase(qpcarIndicator) &&
			!"NE".equalsIgnoreCase(qpcarIndicator) &&
			!"NY".equalsIgnoreCase(qpcarIndicator) &&
			!"NN".equalsIgnoreCase(qpcarIndicator)) {
			return true;
		}
		return false;
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
