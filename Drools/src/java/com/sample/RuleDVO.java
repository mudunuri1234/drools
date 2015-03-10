package com.sample;

public class RuleDVO {
	
	//private  String rulecode;
	//private  String actioncode;
	private  String exceptioncode;
	private  String fieldName;	
	private  String fieldOldVale;
	private  String fieldNewValue;
	private  String sourceSystem;
	
	public RuleDVO() {		
	}

	public String getExceptioncode() {
		return exceptioncode;
	}

	public void setExceptioncode(String exceptioncode) {
		this.exceptioncode = exceptioncode;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getSourceSystem() {
		return sourceSystem;
	}

	public void setSourceSystem(String sourceSystem) {
		this.sourceSystem = sourceSystem;
	}

	public String getFieldNewValue() {
		return fieldNewValue;
	}

	public void setFieldNewValue(String fieldNewValue) {
		this.fieldNewValue = fieldNewValue;
	}

	public String getFieldOldVale() {
		return fieldOldVale;
	}

	public void setFieldOldVale(String fieldOldVale) {
		this.fieldOldVale = fieldOldVale;
	}

		
	

}
