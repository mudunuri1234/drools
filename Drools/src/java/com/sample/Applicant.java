package com.sample;

import java.util.Date;

public class Applicant {
	
	private String name;
	private int age;
	private boolean valid;
	private Date dateApplied;
	
	public Applicant() {		
	}
	
	public Applicant(String name, int age, boolean valid) {
		this.name = name;
		this.age = age;
		this.valid = valid;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public Date getDateApplied() {
		return dateApplied;
	}

	public void setDateApplied(Date dateApplied) {
		this.dateApplied = dateApplied;
	}
	
}
