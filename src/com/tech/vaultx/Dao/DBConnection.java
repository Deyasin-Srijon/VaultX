package com.tech.vaultx.Dao;

import java.sql.*;

public class DBConnection {
private static Connection connection;
	
	public static Connection getConnection() {
		try {
			if(connection == null) {
				//driver class load
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				//create a connection
				String url = "jdbc:mysql://localhost:3306/vaultx";
				String username = "root";
				String password = "Dnsrijon_25";
				
				connection = DriverManager.getConnection(url, username, password);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public static void closeResource(PreparedStatement statement, Connection connect) throws SQLException {
		statement.close();
		connect.close();
	}
}
