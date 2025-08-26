package com.tech.vaultx.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tech.vaultx.Models.Account;

public class AccountDAO {
	private Connection conn;

    public AccountDAO() {
        conn = DBConnection.getConnection();
    }

    // Check Account exists or not 
    public boolean accountNoExists(String accNo) throws SQLException {
        String sql = "SELECT COUNT(*) FROM accounts WHERE account_no = ?";
        
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, accNo);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        
        return rs.getInt(1) > 0;
    }

    // Create Account on DB
	public void createAccountDAO(Account account) throws SQLException {
		String sql = "INSERT INTO accounts (user_id, account_no, ifsc_code, branch_name, account_type, profile_password, amount) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setLong(1, account.getUser_id());
		pstmt.setString(2, account.getAccount_no());
        pstmt.setString(3, account.getIfsc_code());
        pstmt.setString(4, account.getBranch_name());
        pstmt.setString(5, account.getAccount_type());
        pstmt.setString(6, account.getProfile_password());
        pstmt.setBigDecimal(7, account.getAmount());
        
        pstmt.executeUpdate();
	}
    
    
}
