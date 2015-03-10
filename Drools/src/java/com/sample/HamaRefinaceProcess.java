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

public class HamaRefinaceProcess {
	
	public HamaRefinaceProcess() {
	}
	
	public ReconciliationBean executeModHamaRefinaceProcess(ReconciliationBean reconBean) throws Exception {
		Map<String, List<RuleDVO>> eventMap = reconBean.getEventMap();
		List<RuleDVO> alertsList = reconBean.getAlertsList();
		List<RuleDVO> mismatchList = reconBean.getMismatchList();
		String hamaIndicator = reconBean.getHamaIndicator();
						
		// Verify HAMA Indicator on LCA is equal to "Y"
		if("N".equalsIgnoreCase(hamaIndicator)) {	
			addDataToList(alertsList, "41", "SHAW", "hamaIndicator", hamaIndicator, "");						
			eventMap.put("ALERTS", alertsList);
			return reconBean;
		}
		
		// Invoke Rule engine for MOD/HREFI
		String drlFile = "C:/projects/Learning/Drools/src/rules/ModHamaRefinance.drl";
		invokeRuleEngine(reconBean, drlFile);

		// Verify mss number is null or not equal to 13 digits, MSS# = MTG acct #
		String mssNumber = reconBean.getMssNumber();
		String firstMortgageAccountNumber = reconBean.getFirstMortgageAccountNumber();
		if(mssNumber == null || firstMortgageAccountNumber == null) {
			addDataToList(alertsList, "44", "SHAW", "firstMortgageAccountNumber", firstMortgageAccountNumber, "");						
			eventMap.put("MISMATCHES", mismatchList);
			return reconBean;
		}
		
		if(mssNumber.length() != 13 || 
				mssNumber.equals("0000000000000") || 
					!mssNumber.equalsIgnoreCase(firstMortgageAccountNumber)) {
			addDataToList(mismatchList, "44", "SHAW", "mssNumber", mssNumber, "");						
			eventMap.put("MISMATCHES", mismatchList);
			return reconBean;
		}
		
		// Verify - Mtg Prin Bal = 0
		BigDecimal intialValue = new BigDecimal("0");
		BigDecimal firstMortgagePrincipalBalance = reconBean.getFirstMortgageCurrentPrincipalBalance();
		if(firstMortgagePrincipalBalance == null) {
			firstMortgagePrincipalBalance = new BigDecimal("0");
		}		
		if(intialValue.compareTo(firstMortgagePrincipalBalance) == 0 ) {
			addDataToList(alertsList, "45", "MSP", "firstMortgagePrincipalBalance", firstMortgagePrincipalBalance+"", "");						
			eventMap.put("ALERTS", alertsList);
			return reconBean;
		}
		
		return reconBean;
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
	
	private void invokeRuleEngine(ReconciliationBean reconBean, String drlFile) throws Exception {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
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
	
}
