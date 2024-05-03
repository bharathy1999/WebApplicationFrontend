package com.signProfile.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// this is singleton class there can be only one object is created
public class JdbcConnection {
	static Connection connection = null;

	public static Connection getJdbcConnection() {

		// if connection is null initially there is no connection .
		//connection.isClosed() is used if there is a connection is closed at the point the object of connection is alive but the connection is closed.At the time we need to create a new connection
		
		try {
			if (connection == null || connection.isClosed()) {
				// this is for dynamicall load a driver class 
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/newmysql", "abstract-programmer",
						"example-password");

			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return connection;
	}

	public static void closeTheConnection() {
		try {
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
