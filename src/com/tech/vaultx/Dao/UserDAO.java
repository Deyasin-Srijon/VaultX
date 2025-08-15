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
        
        ps.executeUpdate();
    }
    
    // Check a user profile exists or not
    public boolean checkUserExists(User user) throws SQLException {
        String sql = "SELECT * FROM users WHERE email = ?";
        
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, user.getEmail());
        
        ResultSet rs = ps.executeQuery();
        
        return rs.next();
    }
    
    // Check login with correct email & password
    public boolean checkUserValidity(String password, String email) throws SQLException {
        String sql = "SELECT * FROM users WHERE email = ? AND user_password = ?";
        
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, email);
        ps.setString(2, password);
        
        ResultSet rs = ps.executeQuery();
        
        return rs.next();
    }
    
    // View user profile
    public User viewProfile(String email, String password) throws SQLException {
        String sql = "SELECT user_id, first_name, last_name, dob, aadhar_no FROM users WHERE email = ? AND user_password = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, email);
        ps.setString(2, password);
        
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            int id = rs.getInt("user_id");
            String first_name = rs.getString("first_name");
            String last_name = rs.getString("last_name");
            
            java.sql.Date date = rs.getDate(4);
            String dob = date != null ? date.toString() : null;
            
            String aadhar = rs.getString("aadhar_no");
            
            return new User(id, first_name, last_name, dob, aadhar);
        } else {
            return null;
        }
    }
    
    // Delete user profile
    public void deleteProfile(String email, String password) throws SQLException {
    	String sql = "DELETE FROM users WHERE email = ? AND user_password = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, email);
        ps.setString(2, password);
        
        ps.executeUpdate();
    }
}
