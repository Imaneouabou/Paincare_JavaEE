package com.project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnection {

  
private static Connection connection;

static {
	final String user= "root";
	final String password= "";
	final String db_name = "projet_jee";
	
	final String url= "jdbc:mysql://localhost:3306/" +db_name +"?serverTimezone=UTC";
	
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		connection = DriverManager.getConnection(url, user, password);
	
	} catch(Exception e) {
		throw new RuntimeException(e);
	}
}

public synchronized static Connection connection() {
	return connection;
}

public static PreparedStatement preparedStatement (String query) {
	try {
		return connection().prepareStatement(query);
	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
}
}