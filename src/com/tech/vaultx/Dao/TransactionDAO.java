package com.tech.vaultx.Dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionDAO {
	private Connection conn;
	
	public TransactionDAO() {
		conn = DBConnection.getConnection();
	}

	// update Debit Amount 
	public void updateDebitAmountDAO(BigDecimal amount, String acc_no) throws SQLException {
		String sql = "UPDATE accounts SET amount = amount - ? where account_no = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
        
        ps.setBigDecimal(1, amount);
        ps.setString(2, acc_no);
        
        ps.executeUpdate();
	}
	
	// update Credit Amount
	public void updateCreditAmountDAO(BigDecimal amount, String acc_no) throws SQLException {
		String sql = "UPDATE accounts SET amount = amount + ? where account_no = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
        
        ps.setBigDecimal(1, amount);
        ps.setString(2, acc_no);
        
        ps.executeUpdate();
	}
	
	//Insert Transaction Details
	public void insertTransaction(BigDecimal amount, String senderAccNo, String receiverAccNo, String mode) throws SQLException {
	        String sql1 = "INSERT INTO transactions () VALUES ()";
	        PreparedStatement ps1 = conn.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
	        ps1.executeUpdate();
	        ResultSet rs = ps1.getGeneratedKeys();
	        rs.next();
	        long transactionId = rs.getLong(1);

	        String sql2 = "INSERT INTO transaction_entries (transaction_id, account_no, entry_type, tran_mode, amount) VALUES (?, ?, ?, ?, ?)";
	        if(senderAccNo != null) {
	        	PreparedStatement ps2 = conn.prepareStatement(sql2);
	        	ps2.setLong(1, transactionId);
	        	ps2.setString(2, senderAccNo);
	        	ps2.setString(3, "debit");
	        	ps2.setString(4, mode);
	        	ps2.setBigDecimal(5, amount);
	        	ps2.executeUpdate();
	        }

	        if(receiverAccNo != null) {
	        	PreparedStatement ps3 = conn.prepareStatement(sql2);
	        	ps3.setLong(1, transactionId);
	        	ps3.setString(2, receiverAccNo);
	        	ps3.setString(3, "credit");
	        	ps3.setString(4, mode);
	        	ps3.setBigDecimal(5, amount);
	        	ps3.executeUpdate();
	        }
	}
}
