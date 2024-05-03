package com.signProfile.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.signProfile.controller.JdbcConnection;
import com.signProfile.model.EncryptModel;


//after authentication of user this class objects provide a user encrypted email address and the corresponding secret key
public class GetProfile {

  public EncryptModel retrieveProfile(String name,String password){
	  // if there is no such data in database its return null
	  EncryptModel encryptModel=null;
	  try {
		PreparedStatement preparedStatement=
				JdbcConnection.getJdbcConnection().prepareStatement("Select * from secureweb where username=? AND password=?");
		preparedStatement.setString(1,name);
		preparedStatement.setString(2,password);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next()) {
			encryptModel=new EncryptModel( resultSet.getString(3),resultSet.getString(4));
		}
		
		} 
	  catch (SQLException e) {
			e.printStackTrace();
		}
	  finally {
		JdbcConnection.closeTheConnection();
	  }
		
		return encryptModel;
	}
	
	
}
