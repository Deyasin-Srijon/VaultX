package com.tech.vaultx.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.tech.vaultx.Models.ATM;
import com.tech.vaultx.Models.Account;

public class ATMDAO {
	private Connection conn;

    public ATMDAO() {
        conn = DBConnection.getConnection();
    }
    
	// Is this card no Exist
	public boolean cardNoExists(String cardno) throws SQLException {
		String sql = "SELECT COUNT(*) FROM atm WHERE card_no = ?";
        
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, cardno);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        
        return rs.getInt(1) > 0;
	}

	// New Card Issue
	public void newCardDAO(ATM atm, Account account) throws SQLException {
		String sql1 = "INSERT INTO atm (card_no, cvv, exp_date, pincode) VALUES (?, ?, ?, ?)";

		PreparedStatement ps = conn.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);

	    ps.setString(1, atm.getCard_no());
	    ps.setString(2, atm.getCvv());
	    ps.setString(3, atm.getExp_date());
	    ps.setString(4, atm.getPincode());

	    ps.executeUpdate();
	    
	    // Get the generated ATM ID
	    ResultSet rs = ps.getGeneratedKeys();
	    int atm_id = 0;
	    if (rs.next()) {
	    	atm_id = rs.getInt(1);
	        atm.setAtm_id(atm_id);
	    }
	    
	    String sql2 = "UPDATE accounts SET atm_id = LAST_INSERT_ID() WHERE acc_id = ?";
	    
	    ps = conn.prepareStatement(sql2);

	    ps.setLong(1, account.getAcc_id());

	    ps.executeUpdate();
	}

	// ATM Card Details 
	public ATM atmDetailsDAO(int atm_id) throws SQLException {
		String sql = "SELECT * FROM atm WHERE atm_id = ?";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setInt(1, atm_id);
		
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			String card_no = rs.getString("card_no");
			String cvv = rs.getString("cvv");
			String exp_date = rs.getString("exp_date");
			String pincode = rs.getString("pincode");
			
			return new ATM(atm_id, card_no, cvv, exp_date, pincode);
		}
		else
			return null;
	}

	// Block ATM Card
	public void blockATMCardDAO(int atm_id) throws SQLException {
		String sql = "DELETE FROM atm WHERE atm_id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ps.setInt(1, atm_id);
        
        ps.executeUpdate();
	}

	// Update pincode of ATM Card
	public void updatePincodeDAO(ATM atm, String pincode) throws SQLException {
		String sql = "UPDATE atm SET pincode = ? WHERE atm_id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ps.setString(1, pincode);
        ps.setInt(2, atm.getAtm_id());
        
        ps.executeUpdate();
	}
}
