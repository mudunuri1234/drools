package com.sample;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

	
	public static void main(String args[]) {
		Test t = new Test();
		
		String test = "011234456789";
		String output = test.substring(test.length()-1, test.length());		
		System.out.println("---- true ----" + output);
		
		//System.out.println( "--------- Date -------" + t.convertDateToFormat("00018730", null, null));
	}
	
	
	
	public String convertDateToFormat(String dateToConvert, String inPattern, String toPattern) {
			
			if (inPattern == null) {
				inPattern = "yyyyMMdd";
			}
			
			if (toPattern == null) {
				toPattern = "MMddyyyy";
			}
			
			if (dateToConvert != null) {
				SimpleDateFormat fromFormat = new SimpleDateFormat(inPattern);
				Date date = null;
				try {
					date = fromFormat.parse(dateToConvert);
				} catch (ParseException e) {
					e.printStackTrace();
				}	
			
				if (date != null) {
					SimpleDateFormat toFormat = new SimpleDateFormat(toPattern);
					String dateStr = toFormat.format(date);
					return dateStr;
				}
			}
			return null;
	}
	
	
}
