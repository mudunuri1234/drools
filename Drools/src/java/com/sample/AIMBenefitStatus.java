package com.sample;

import java.io.Serializable;

/**
 * A DTO class used to transfer AIM benefit status information.
 * 
 */
public class AIMBenefitStatus implements Serializable {

	/** Enumeration of Benefit Status.
	 */
	public enum BenefitStatus {
		P, /** Pending. */
		
		A, /** Active. */
		
		T, /** Terminated. */
		
		D, /** Denied. */
		
		O; /** Open. */
	}
	
	/** Serialization iD. */
	private static final long serialVersionUID = 8333333810224100289L;

	/** Unique AIM Benefit Number */
	private String benefitNumber;
	
	/** Status of AIM Benefit Request. */
	private BenefitStatus status;

	/**
	 * No argument construction to instantiates a new aIM benefit status.
	 */
	public AIMBenefitStatus() {

	}

	/**
	 * Constructor to instantiate {@link AIMBenefitStatus} instance.
	 * 
	 * @param benefitNumber Benefit number.
	 * @param status Benefit status.
	 */
	public AIMBenefitStatus(String benefitNumber, BenefitStatus status) {
		super();
		this.benefitNumber = benefitNumber;
		this.status = status;
	}

	/**
	 * Gets the benefit number.
	 * 
	 * @return the benefit number
	 */
	public String getBenefitNumber() {
		return benefitNumber;
	}

	/**
	 * Sets the benefit number.
	 * 
	 * @param benefitNumber the new benefit number
	 */
	public void setBenefitNumber(String benefitNumber) {
		this.benefitNumber = benefitNumber;
	}

	/**
	 * Gets the status.
	 * 
	 * @return the status
	 */
	public BenefitStatus getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 * 
	 * @param status the new status
	 */
	public void setStatus(BenefitStatus status) {
		this.status = status;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new StringBuilder()
					.append("[[Benefit Number = " + benefitNumber + "],")
					.append("[Benefit Status="+ status + "]]")
					.toString();
	}
}
