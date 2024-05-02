package com.signProfile.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {
	static Connection connection=null;
	
    public static Connection getJdbcConnection() {
    	
    	try {
			if(connection==null||connection.isClosed()){
				
			
			  Class.forName("com.mysql.cj.jdbc.Driver"); 
			  connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/newmysql","abstract-programmer", "example-password");
			
			
			}
		} catch (SQLException |ClassNotFoundException e) {
			e.printStackTrace();
		}
    	
    	return connection;
    }
    
    public static void closeTheConnection() {
    	try {
    	connection.close();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    }
    
    
}
