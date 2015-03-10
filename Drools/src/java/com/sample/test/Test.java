package com.sample.test;

public class Test {

	public static void main(String args[]) {
		Test t = new Test();
		t.getConnection();
	}
	 	
	private java.sql.Connection getConnection() {
		java.sql.Connection  con = null;
		try{
             Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
             con = java.sql.DriverManager.getConnection("jdbc:sqlserver://localhost:1433", "sa", "spr123");
             if(con!=null) {
            	 System.out.println("Connection Successful!");
             }
        }catch(Exception e){
             e.printStackTrace();
             System.out.println("Error Trace in getConnection() : " + e.getMessage());
       }
        return con;
    }

	
}
