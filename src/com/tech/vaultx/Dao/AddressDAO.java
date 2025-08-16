package com.tech.vaultx.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tech.vaultx.Models.Address;

public class AddressDAO {
	private Connection conn;

    public AddressDAO() {
        conn = DBConnection.getConnection();
    }
	
	// Check if Address is inserted
	public int isAddress(String email, String password) throws SQLException {
		int addrId = 0;
	    	
	    String sql = "SELECT address_id FROM users WHERE email = ? AND user_password = ?";
	    PreparedStatement ps = conn.prepareStatement(sql);
	    ps.setString(1, email);
	    ps.setString(2, password);
	        
	    ResultSet rs = ps.executeQuery();
	        
	    if(rs.next()) {
	    	addrId = rs.getInt(1);
	    }
	        
	    return addrId;
	}

	// Insert Address details
	public void insertAddress(String city, String state, String email, String password) throws SQLException {
		String sql1 = "INSERT INTO address (city, state) VALUES (?, ?)";
	    	
	    PreparedStatement ps = conn.prepareStatement(sql1);
	    ps.setString(1, city);
	    ps.setString(2, state);
	        
	    ps.executeUpdate();
	        
	    String sql2 = "UPDATE users SET address_id = LAST_INSERT_ID() WHERE email = ? AND user_password = ?";
	    ps = conn.prepareStatement(sql2);
	    ps.setString(1, email);
	    ps.setString(2, password);
	        
	    ps.executeUpdate();
	}

	// Update Address Details
	public void updateAddress(String city, String state, String email, String password) throws SQLException {
		String sql = "UPDATE address SET city = ?, state = ? WHERE addr_id = (SELECT address_id FROM users WHERE email = ? AND user_password = ?)";
	    	
	    PreparedStatement ps = conn.prepareStatement(sql);
	    ps.setString(1, city);
	    ps.setString(2, state);
	    ps.setString(3, email);
	    ps.setString(4, password);
	        
	    ps.executeUpdate();
			
	}

	public Address viewAddress(String email, String password) throws SQLException {
		String city = "";
		String state = "";
		
		String sql = "SELECT city, state FROM address WHERE addr_id = (SELECT address_id FROM users WHERE email = ? AND user_password = ?)";
    	
	    PreparedStatement ps = conn.prepareStatement(sql);
	    ps.setString(1, email);
	    ps.setString(2, password);
	        
	    ResultSet rs = ps.executeQuery();
	    if(rs.next()) {
	    	city = rs.getString(1);
	    	state = rs.getString(2);
	    }
	    
		return new Address(state, city);
	}
}
