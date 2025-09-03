package com.tech.vaultx.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.tech.vaultx.Models.Account;
import com.tech.vaultx.Models.NetBanking;

public class NetBankingDAO {
	private Connection conn;

    public NetBankingDAO() {
        conn = DBConnection.getConnection();
    }

    // Register New NetBanking Service
	public void registerNetBankingDAO(NetBanking netbanking, Account account) throws SQLException {
		String sql1 = "INSERT INTO netbanking (username, user_password, pincode) VALUES (?, ?, ?)";

		PreparedStatement ps = conn.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);

	    ps.setString(1, netbanking.getUsername());
	    ps.setString(2, netbanking.getUser_password());
	    ps.setString(3, netbanking.getPincode());

	    ps.executeUpdate();
	    
	    // Get the generated NetBanking ID
	    ResultSet rs = ps.getGeneratedKeys();
	    int netbankingId = 0;
	    if (rs.next()) {
	        netbankingId = rs.getInt(1);
	        netbanking.setBanking_id(netbankingId);
	    }
	    
	    String sql2 = "UPDATE accounts SET banking_id = LAST_INSERT_ID() WHERE acc_id = ?";
	    
	    ps = conn.prepareStatement(sql2);

	    ps.setLong(1, account.getAcc_id());

	    ps.executeUpdate();
	}

	// Details of NetBanking
	public NetBanking netbankingDetailsDAO(int banking_id) throws SQLException {
		String sql = "SELECT * FROM netbanking WHERE banking_id = ?";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setInt(1, banking_id);
		
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			String username = rs.getString("username");
			String user_password = rs.getString("user_password");
			String pincode = rs.getString("pincode");
			
			return new NetBanking(banking_id, username, user_password, pincode);
		}
		else
			return null;
	}
}
