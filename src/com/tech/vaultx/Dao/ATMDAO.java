package com.tech.vaultx.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

	    PreparedStatement ps = conn.prepareStatement(sql1);

	    ps.setString(1, atm.getCard_no());
	    ps.setString(2, atm.getCvv());
	    ps.setString(3, atm.getExp_date());
	    ps.setString(4, atm.getPincode());

	    ps.executeUpdate();
	    
	    String sql2 = "UPDATE accounts SET atm_id = LAST_INSERT_ID() WHERE acc_id = ?";
	    
	    ps = conn.prepareStatement(sql2);

	    ps.setLong(1, account.getAcc_id());

	    ps.executeUpdate();
	}
}
