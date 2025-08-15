package com.tech.vaultx.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tech.vaultx.Models.User;

public class UserDAO {
	private Connection conn;

    public UserDAO() {
        conn = DBConnection.getConnection();
    }

    // User Profile Database Insertion
    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO users (first_name, last_name, user_password, phn_no, email) VALUES (?, ?, ?, ?, ?)";
        
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, user.getFirst_name());
        ps.setString(2, user.getLast_name());
        ps.setString(3, user.getUser_password());
        ps.setString(4, user.getPhn_no());
        ps.setString(5, user.getEmail());
        
        int rowAffected = ps.executeUpdate();
        
        if(rowAffected == 0)
			System.out.println("Unable to Insert the Data");
		else
			System.out.println("Data Inserted successfully");
    }
    
    // Check a user profile exists or not
    public boolean checkUserExists(User user) throws SQLException {
        String sql = "SELECT * FROM users WHERE email = ?";
        
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, user.getEmail());
        
        ResultSet rs = ps.executeQuery();
        
        return rs.next();
    }
}
