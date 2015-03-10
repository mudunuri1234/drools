package com.sample;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A wrapper class that encapsulates benefit status list for an account number
 * and the Action information. Used as input for the rule engine.
 * 
 */
public class AIMBenefitStatusGroup implements Serializable {

	/**
	 * Enumeration of Benefit Action types.
	 */
	public static enum BenefitAction {
		/** Reject Benefit Status.*/
		REJECT,
		
		/** Post Benefit Status to Event History. */
		POST,
		
		/** Post Benefit Status and Activate Plan. */
		POST_AND_ACTIVATE_PLAN,
		
		/** Post Benefit Status and Suspend Plan. */
		POST_AND_SUSPEND_PLAN;
	}
	
	/**
	 * Enumeration for Reject Reasons.
	 */
	public static enum RejectReason {
		
		/** Single Event Invalid Combination Reason Code. */
		SINGLE_EVENT_INVALID_COMBINATION, 
		
		/** Multiple Event Invalid Combination Reason Code. */
		MULTIPLE_EVENT_INVALID_COMBINATION
	}
	
	/** Serialization UID. */
	private static final long serialVersionUID = 72238627990220733L;

	/** Account Number of the status group. */
	private String sysAcctNumber;
	
	/** Status of the plan the benefits associated with. */
	private String planStatus;
	
	/** Final action to take based on the benefit status. */
	private BenefitAction action;
	
	/** Reject Reason. */
	private RejectReason rejectReason;
	
	/** Benefit status list. */
	private List<AIMBenefitStatus> benefitStatuses = new ArrayList<AIMBenefitStatus>();

	/**
	 * Empty constructor that instantiates new {@link AIMBenefitStatusGroup}.
	 */
	public AIMBenefitStatusGroup() {
		super();
	}

	/**
	 * Instantiates new {@link AIMBenefitStatusGroup} with single benefit status.
	 * 
	 * @param status Benefit status.
	 * @param sysAcctNumber Account number.
	 * @param planStatus Plan status business code.
	 */
	public AIMBenefitStatusGroup(String sysAcctNumber, String planStatus, AIMBenefitStatus status) {
		this.sysAcctNumber = sysAcctNumber;
		this.planStatus = planStatus;
		if (benefitStatuses == null) {
			benefitStatuses = new ArrayList<AIMBenefitStatus>();
		}
		benefitStatuses.clear();
		benefitStatuses.add(status);
	}

	/**
	 * Instantiates new {@link AIMBenefitStatusGroup} with multiple benefit status info.
	 * 
	 * @param statuses Benefit Status list.
	 * @param sysAcctNumber Account number.
	 * @param planStatus Plan status business code.
	 */
	public AIMBenefitStatusGroup(String sysAcctNumber, String planStatus, List<AIMBenefitStatus> statuses) {
		this.sysAcctNumber = sysAcctNumber;
		this.planStatus = planStatus;
		this.benefitStatuses = statuses;
	}

	/**
	 * Gets the sys acct number.
	 * 
	 * @return the sys acct number
	 */
	public String getSysAcctNumber() {
		return sysAcctNumber;
	}

	/**
	 * Sets the sys acct number.
	 * 
	 * @param sysAcctNumber the new sys acct number
	 */
	public void setSysAcctNumber(String sysAcctNumber) {
		this.sysAcctNumber = sysAcctNumber;
	}

	/**
	 * Gets the benefit statuses.
	 * 
	 * @return the benefit statuses
	 */
	public List<AIMBenefitStatus> getBenefitStatuses() {
		return benefitStatuses;
	}

	/**
	 * Sets the benefit statuses.
	 * 
	 * @param benefitStatuses the new benefit statuses
	 */
	public void setBenefitStatuses(List<AIMBenefitStatus> benefitStatuses) {
		this.benefitStatuses = benefitStatuses;
	}

	/**
	 * Gets the action.
	 * 
	 * @return the action
	 */
	public BenefitAction getAction() {
		return action;
	}

	/**
	 * Sets the action.
	 * 
	 * @param action the new action
	 */
	public void setAction(BenefitAction action) {
		this.action = action;
	}

	/**
	 * Gets the plan status.
	 * 
	 * @return the plan status
	 */
	public String getPlanStatus() {
		return planStatus;
	}

	/**
	 * Sets the plan status.
	 * 
	 * @param planStatus the new plan status
	 */
	public void setPlanStatus(String planStatus) {
		this.planStatus = planStatus;
	}

	/**
	 * Gets the reject reason.
	 * 
	 * @return the reject reason
	 */
	public RejectReason getRejectReason() {
		return rejectReason;
	}

	/**
	 * Sets the reject reason.
	 * 
	 * @param rejectReason the new reject reason
	 */
	public void setRejectReason(RejectReason rejectReason) {
		this.rejectReason = rejectReason;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new StringBuilder()
					.append("[[Account Number = " + sysAcctNumber +"],")
					.append("[Plan Status = " + planStatus +"],")
					.append("[Benefit Status List = " + benefitStatuses +"],")
					.append("[Final Action = " + action + "],")
					.append("[Reject Reason = " + rejectReason + "]]")
					.toString();
	}
}
