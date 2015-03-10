package com.sample;

public class Status {

	private String status;
	private String action;
	
	private Integer totalEvents;
	private String denied = "false";
	private String pending = "false";
	private String approved = "false";
	private String terminated = "false";
	private String open = "false";
	private String benefitNumber;
	
		
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getApproved() {
		return approved;
	}
	public void setApproved(String approved) {
		this.approved = approved;
	}
	public String getDenied() {
		return denied;
	}
	public void setDenied(String denied) {
		this.denied = denied;
	}
	public String getPending() {
		return pending;
	}
	public void setPending(String pending) {
		this.pending = pending;
	}
	public String getTerminated() {
		return terminated;
	}
	public void setTerminated(String terminated) {
		this.terminated = terminated;
	}
	public Integer getTotalEvents() {
		return totalEvents;
	}
	public void setTotalEvents(Integer totalEvents) {
		this.totalEvents = totalEvents;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public String getBenefitNumber() {
		return benefitNumber;
	}
	public void setBenefitNumber(String benefitNumber) {
		this.benefitNumber = benefitNumber;
	}		
	
}
