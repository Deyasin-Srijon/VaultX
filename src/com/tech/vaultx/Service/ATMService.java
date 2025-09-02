package com.tech.vaultx.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.tech.vaultx.Dao.ATMDAO;
import com.tech.vaultx.Models.ATM;
import com.tech.vaultx.Models.Account;
import com.tech.vaultx.Util.RandomNoGenerator;

public class ATMService {
	private ATMDAO atmDAO = new ATMDAO();

	// New Card Issue Service
	public void issueNewCardService(ATM atm, Account account) throws SQLException {
		String cardno;
        do {
            cardno = RandomNoGenerator.randomNoGenerator(16);
        } while (atmDAO.cardNoExists(cardno));
        
        String cvv = RandomNoGenerator.randomNoGenerator(3);
        // Generate expiry date = today + 3 years
        LocalDate expiryDate = LocalDate.now().plusYears(3);

        // Format as MM/yy (commonly used on ATM cards)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
        String exp_date = expiryDate.format(formatter);

        // Now you can set values into atm object
        atm.setCard_no(cardno);
        atm.setCvv(cvv);
        atm.setExp_date(exp_date);

        atmDAO.newCardDAO(atm, account);
	}

	// ATM card Details Service
	public ATM atmDetailsService(int atm_id) throws SQLException {
		return atmDAO.atmDetailsDAO(atm_id);
	}
}
