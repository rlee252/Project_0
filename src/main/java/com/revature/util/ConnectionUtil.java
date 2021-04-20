package com.revature.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.mariadb.jdbc.Driver;

public class ConnectionUtil {
	
	public static Connection getConnection() throws SQLException {
		
		DriverManager.registerDriver(new Driver());
		
		String username="root";
		String password="turtle123";
		
		String url = "jdbc:mariadb://localhost:3306/my_first_database";
		
		return DriverManager.getConnection(url,username,password);
	}
}
