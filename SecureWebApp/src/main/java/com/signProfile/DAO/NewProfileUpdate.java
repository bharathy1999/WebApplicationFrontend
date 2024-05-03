package com.signProfile.DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.signProfile.controller.JdbcConnection;


// this a POJO class for new user sign up 

public class NewProfileUpdate {

	public void updateNewProfile(String name,String password,String email,String secretkey){
		try {
			PreparedStatement  preparedStatement=JdbcConnection.getJdbcConnection().prepareStatement("insert into secureweb values(?,?,?,?)");
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, email);
			preparedStatement.setString(4, secretkey);
			System.out.println("success");
			preparedStatement.execute();
			System.out.println("Success");
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in NewProfileUpdate");
		}
		finally {
			JdbcConnection.closeTheConnection();
		}
		
	}
	
	
}
