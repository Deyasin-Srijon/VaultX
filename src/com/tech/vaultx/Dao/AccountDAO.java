package com.tech.vaultx.Dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

	// Check login with correct account no & password
	public boolean checkAccountValidity(long userId, String accNo, String password) throws SQLException {
		String sql = "SELECT * FROM accounts WHERE user_id = ? AND account_no = ? AND profile_password = ?";
        
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, userId);
        ps.setString(2, accNo);
        ps.setString(3, password);
        
        ResultSet rs = ps.executeQuery();
        
        return rs.next();
	}

	// View Account Details
	public Account viewAccount(long userId, String accNo, String password) throws SQLException {
		String sql = "SELECT acc_id, user_id, account_no, ifsc_code, branch_name, account_type, amount, current_status FROM accounts WHERE user_id = ? AND account_no = ? AND profile_password = ?";
		
		PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, userId);
        ps.setString(2, accNo);
        ps.setString(3, password);
        
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            long accId = rs.getLong("acc_id");
            long userid = rs.getLong("user_id");
            String accountNo = rs.getString("account_no");
            String ifsc = rs.getString("ifsc_code");
            String branch = rs.getString("branch_name");
            String accType = rs.getString("account_type");
            BigDecimal amount = rs.getBigDecimal("amount");
            String status = rs.getString("current_status");
            
            return new Account(accId, userid, accountNo, ifsc, branch, accType, amount, status);
        } else {
            return null;
        }
	}

	// List on Accounts on UserId
	public ArrayList<Account> listAccountDAO(long userId) throws SQLException {
		ArrayList<Account> accounts = new ArrayList<Account>();
		String sql = "SELECT acc_id, ifsc_code, branch_name, account_type, current_status FROM accounts WHERE user_id = ?";
		
		PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, userId);
        
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()) {
        	long accId = rs.getLong("acc_id");
            String ifsc = rs.getString("ifsc_code");
            String branch = rs.getString("branch_name");
            String accType = rs.getString("account_type");
            String status = rs.getString("current_status");
            
            Account account = new Account(accId, ifsc, branch, accType, status);
            accounts.add(account);
        }
        
        return accounts;
	}
	
	// Check there is any account exists for a User
	public boolean checkAccountExistDAO(long userId) throws SQLException {
		String sql = "SELECT acc_id, ifsc_code, branch_name, account_type, current_status FROM accounts WHERE user_id = ?";
		
		PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, userId);
        
        ResultSet rs = ps.executeQuery();
        
        return rs.next();
	}

	// Delete a Bank Account
	public void deleteAccountDAO(long userId, String accNo, String password) throws SQLException {
		String sql = "DELETE FROM accounts WHERE user_id = ? AND account_no = ? AND profile_password = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, userId);
        ps.setString(2, accNo);
        ps.setString(3, password);
        
        ps.executeUpdate();
	}
}
