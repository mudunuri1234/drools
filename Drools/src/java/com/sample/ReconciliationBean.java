package com.sample;

import java.util.*;
import java.math.BigDecimal;

public class ReconciliationBean {
	
	//Varibles used to determine account type
	private String accountType;
	private Date lcaNoteDate;
	private Date firstMortgageDate;
	private long deltaDays;
	private String determineTypeAction;
	
	//Varibles used for SIMO process
	private String hamaIndicator;
	private int clientNumner;
	private String slidCode;
	private BigDecimal firstMortgageCurrentPrincipalBalance;
	private String lcaStatus;
	private String mssNumber;
	private String firstMortgageAccountNumber;
	private String secondLienHolderAccountNumber;
	private String qpcarIndicator;
	private String phoneFlag;
	private String appraisalType;
	private BigDecimal propertyValue;
	private BigDecimal purchasePrice;	
	private BigDecimal appraisedValue;
	private BigDecimal priorMortgageBalance;
	private BigDecimal originalMortgageBalance;	
	private String recoveryCode;
	private String billCycle;
	private int lcaDue;
	private int combinedStatementIndicator;
	private String billMode;
	private String qhist;
	private String personCode;
	private String propertyTypeCode;
	private String occupancyCode;
	private String livingUnits;
	private String simoAction;	
	private String docCust;
	
	private String lastDigitAcctNum;
	private String lcaAccountNumber;	
	private String accountLastDigitCode;
	private String note;
	private BigDecimal minimumPrice;
	private String forbearanceIndicator;
	private BigDecimal principalPlusForberanceAmount;
		
	private List<RuleDVO> actions = new ArrayList<RuleDVO>();
	
	private List<RuleDVO> alertsList = new ArrayList<RuleDVO>();
	private List<RuleDVO> correctionsList = new ArrayList<RuleDVO>();
	private List<RuleDVO> shawUpdatesList = new ArrayList<RuleDVO>();
	private List<RuleDVO> alltelUpdatesList = new ArrayList<RuleDVO>();	
	private List<RuleDVO> mismatchList = new ArrayList<RuleDVO>();
	private List<RuleDVO> harpUpdatesList = new ArrayList<RuleDVO>();
		
	private Map<String, String> actionMap = new HashMap<String, String>();
	private Map<String, List<RuleDVO>> eventMap = new HashMap<String,  List<RuleDVO>>();
		
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public Date getFirstMortgageDate() {
		return firstMortgageDate;
	}
	public void setFirstMortgageDate(Date firstMortgageDate) {
		this.firstMortgageDate = firstMortgageDate;
	}
	public Date getLcaNoteDate() {
		return lcaNoteDate;
	}
	public void setLcaNoteDate(Date lcaNoteDate) {
		this.lcaNoteDate = lcaNoteDate;
	}
	public long getDeltaDays() {
		return deltaDays;
	}
	public void setDeltaDays(long deltaDays) {
		this.deltaDays = deltaDays;
	}
	public String getDetermineTypeAction() {
		return determineTypeAction;
	}
	public void setDetermineTypeAction(String determineTypeAction) {
		this.determineTypeAction = determineTypeAction;
	}
	public String getAppraisalType() {
		return appraisalType;
	}
	public void setAppraisalType(String appraisalType) {
		this.appraisalType = appraisalType;
	}
	public String getBillCycle() {
		return billCycle;
	}
	public void setBillCycle(String billCycle) {
		this.billCycle = billCycle;
	}
	public String getBillMode() {
		return billMode;
	}
	public void setBillMode(String billMode) {
		this.billMode = billMode;
	}
	public int getClientNumner() {
		return clientNumner;
	}
	public void setClientNumner(int clientNumner) {
		this.clientNumner = clientNumner;
	}
	public int getCombinedStatementIndicator() {
		return combinedStatementIndicator;
	}
	public void setCombinedStatementIndicator(int combinedStatementIndicator) {
		this.combinedStatementIndicator = combinedStatementIndicator;
	}
	public String getHamaIndicator() {
		return hamaIndicator;
	}
	public void setHamaIndicator(String hamaIndicator) {
		this.hamaIndicator = hamaIndicator;
	}
	public String getLcaStatus() {
		return lcaStatus;
	}
	public void setLcaStatus(String lcaStatus) {
		this.lcaStatus = lcaStatus;
	}
	public String getLivingUnits() {
		return livingUnits;
	}
	public void setLivingUnits(String livingUnits) {
		this.livingUnits = livingUnits;
	}
	public String getMssNumber() {
		return mssNumber;
	}
	public void setMssNumber(String mssNumber) {
		this.mssNumber = mssNumber;
	}
	public String getOccupancyCode() {
		return occupancyCode;
	}
	public void setOccupancyCode(String occupancyCode) {
		this.occupancyCode = occupancyCode;
	}
	public String getPersonCode() {
		return personCode;
	}
	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}
	public String getPhoneFlag() {
		return phoneFlag;
	}
	public void setPhoneFlag(String phoneFlag) {
		this.phoneFlag = phoneFlag;
	}
	public BigDecimal getPriorMortgageBalance() {
		return priorMortgageBalance;
	}
	public void setPriorMortgageBalance(BigDecimal priorMortgageBalance) {
		this.priorMortgageBalance = priorMortgageBalance;
	}
	public String getPropertyTypeCode() {
		return propertyTypeCode;
	}
	public void setPropertyTypeCode(String propertyTypeCode) {
		this.propertyTypeCode = propertyTypeCode;
	}
	public String getQpcarIndicator() {
		return qpcarIndicator;
	}
	public void setQpcarIndicator(String qpcarIndicator) {
		this.qpcarIndicator = qpcarIndicator;
	}
	public String getRecoveryCode() {
		return recoveryCode;
	}
	public void setRecoveryCode(String recoveryCode) {
		this.recoveryCode = recoveryCode;
	}
	public String getSecondLienHolderAccountNumber() {
		return secondLienHolderAccountNumber;
	}
	public void setSecondLienHolderAccountNumber(
			String secondLienHolderAccountNumber) {
		this.secondLienHolderAccountNumber = secondLienHolderAccountNumber;
	}
	public String getSlidCode() {
		return slidCode;
	}
	public void setSlidCode(String slidCode) {
		this.slidCode = slidCode;
	}
	public String getSimoAction() {
		return simoAction;
	}
	public void setSimoAction(String simoAction) {
		this.simoAction = simoAction;
	}
	public BigDecimal getFirstMortgageCurrentPrincipalBalance() {
		return firstMortgageCurrentPrincipalBalance;
	}
	public void setFirstMortgageCurrentPrincipalBalance(BigDecimal firstMortgageCurrentPrincipalBalance) {
		this.firstMortgageCurrentPrincipalBalance = firstMortgageCurrentPrincipalBalance;
	}
	public String getLastDigitAcctNum() {
		return lastDigitAcctNum;
	}
	public void setLastDigitAcctNum(String lastDigitAcctNum) {
		this.lastDigitAcctNum = lastDigitAcctNum;
	}
	public String getLcaAccountNumber() {
		return lcaAccountNumber;
	}
	public void setLcaAccountNumber(String lcaAccountNumber) {
		this.lcaAccountNumber = lcaAccountNumber;
	}
	public List<RuleDVO> getActions() {
		return actions;
	}
	public void setActions(List<RuleDVO> actions) {
		this.actions = actions;
	}
	public Map<String, String> getActionMap() {
		return actionMap;
	}
	public void setActionMap(Map<String, String> actionMap) {
		this.actionMap = actionMap;
	}
	public BigDecimal getPropertyValue() {
		return propertyValue;
	}
	public void setPropertyValue(BigDecimal propertyValue) {
		this.propertyValue = propertyValue;
	}
	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	public BigDecimal getAppraisedValue() {
		return appraisedValue;
	}
	public void setAppraisedValue(BigDecimal appraisedValue) {
		this.appraisedValue = appraisedValue;
	}
	public int getLcaDue() {
		return lcaDue;
	}
	public void setLcaDue(int lcaDue) {
		this.lcaDue = lcaDue;
	}
	public String getFirstMortgageAccountNumber() {
		return firstMortgageAccountNumber;
	}
	public void setFirstMortgageAccountNumber(String firstMortgageAccountNumber) {
		this.firstMortgageAccountNumber = firstMortgageAccountNumber;
	}
	public String getAccountLastDigitCode() {
		return accountLastDigitCode;
	}
	public void setAccountLastDigitCode(String accountLastDigitCode) {
		this.accountLastDigitCode = accountLastDigitCode;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public BigDecimal getMinimumPrice() {
		return minimumPrice;
	}
	public void setMinimumPrice(BigDecimal minimumPrice) {
		this.minimumPrice = minimumPrice;
	}
	public BigDecimal getOriginalMortgageBalance() {
		return originalMortgageBalance;
	}
	public void setOriginalMortgageBalance(BigDecimal originalMortgageBalance) {
		this.originalMortgageBalance = originalMortgageBalance;
	}
	public String getQhist() {
		return qhist;
	}
	public void setQhist(String qhist) {
		this.qhist = qhist;
	}
	public String getDocCust() {
		return docCust;
	}
	public void setDocCust(String docCust) {
		this.docCust = docCust;
	}
	public String getForbearanceIndicator() {
		return forbearanceIndicator;
	}
	public void setForberanceIndicator(String forbearanceIndicator) {
		this.forbearanceIndicator = forbearanceIndicator;
	}
	public BigDecimal getPrincipalPlusForberanceAmount() {
		return principalPlusForberanceAmount;
	}
	public void setPrincipalPlusForberanceAmount(
			BigDecimal principalPlusForberanceAmount) {
		this.principalPlusForberanceAmount = principalPlusForberanceAmount;
	}	
	public void setForbearanceIndicator(String forbearanceIndicator) {
		this.forbearanceIndicator = forbearanceIndicator;
	}
	public List<RuleDVO> getAlertsList() {
		return alertsList;
	}
	public void setAlertsList(List<RuleDVO> alertsList) {
		this.alertsList = alertsList;
	}
	public List<RuleDVO> getAlltelUpdatesList() {
		return alltelUpdatesList;
	}
	public void setAlltelUpdatesList(List<RuleDVO> alltelUpdatesList) {
		this.alltelUpdatesList = alltelUpdatesList;
	}
	public List<RuleDVO> getCorrectionsList() {
		return correctionsList;
	}
	public void setCorrectionsList(List<RuleDVO> correctionsList) {
		this.correctionsList = correctionsList;
	}
	public List<RuleDVO> getShawUpdatesList() {
		return shawUpdatesList;
	}
	public void setShawUpdatesList(List<RuleDVO> shawUpdatesList) {
		this.shawUpdatesList = shawUpdatesList;
	}
	public Map<String, List<RuleDVO>> getEventMap() {
		return eventMap;
	}
	public void setEventMap(Map<String, List<RuleDVO>> eventMap) {
		this.eventMap = eventMap;
	}
	public List<RuleDVO> getHarpUpdatesList() {
		return harpUpdatesList;
	}
	public void setHarpUpdatesList(List<RuleDVO> harpUpdatesList) {
		this.harpUpdatesList = harpUpdatesList;
	}
	public List<RuleDVO> getMismatchList() {
		return mismatchList;
	}
	public void setMismatchList(List<RuleDVO> mismatchList) {
		this.mismatchList = mismatchList;
	}

}
