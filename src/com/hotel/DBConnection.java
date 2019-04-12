package com.hotel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	static Connection connectDB()  throws ClassNotFoundException{
		Connection connect = null;
		String url = "jdbc:mysql://localhost:3306/hotel";
		String username = "root";
		String password = "root";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(url, username, password);
		} catch (SQLException ex) {
			System.out.println("in exec");
			System.out.println(ex.getMessage());
		}
		return connect;
	}

}
