package com.MatrixWeb.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {
	private static Connection connection;
	public static Connection getConnection() throws SQLException {
		if(connection != null && !connection.isClosed()) {
			return connection;
		} else {
			try {
				Class.forName("org.postgresql.Driver");
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
			
//			String url = "jdbc:postgresql://{HOST LINK}:PORT/DATABASE NAME"; 
			// Your host url from DBeaver .. AWS Database
			String url = "jdbc:postgresql://javafsproject0.clbty7q43rig.us-east-1.rds.amazonaws.com:5432/Bank"; 
			String username = "postgresProject0";
			String password = "javafsproject0";
			connection = DriverManager.getConnection(url, username, password);
			return connection;
		}
	}
	
	public static void main(String[] args) {
//		This is for testing .. REMEMBER i can have multiple main methods. 
//		This one is for testing the database connection.
		try {
			getConnection();
			System.out.println("YOU'RE CONNECTED");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
