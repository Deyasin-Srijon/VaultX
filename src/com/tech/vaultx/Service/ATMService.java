package com.tech.vaultx.Service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.tech.vaultx.Dao.ATMDAO;
import com.tech.vaultx.Dao.TransactionDAO;
import com.tech.vaultx.Models.ATM;
import com.tech.vaultx.Models.Account;
import com.tech.vaultx.Util.RandomNoGenerator;

public class ATMService {
	private ATMDAO atmDAO = new ATMDAO();
	private TransactionDAO transactionDAO = new TransactionDAO();

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

	// Block ATM card
	public void blockATMCardService(Account account) throws SQLException {
		atmDAO.blockATMCardDAO(account.getAtm().getAtm_id());
		account.setAtm(null);
	}

	// Change ATM pincode
	public void changePincodeService(ATM atm, String pincode) throws SQLException {
		atmDAO.updatePincodeDAO(atm, pincode);
		atm.setPincode(pincode);
	}

	// Update Amount after cash WithDraw
	public void updateWithdrawAmountService(BigDecimal amount, String account_no) throws SQLException {
		transactionDAO.updateDebitAmountDAO(amount, account_no);
		transactionDAO.insertTransaction(amount, account_no, null, "ATM");
	}
	
	// Update Amount after cash Deposit
	public void updateDepositAmountService(BigDecimal amount, String account_no) throws SQLException {
		transactionDAO.updateCreditAmountDAO(amount, account_no);
		transactionDAO.insertTransaction(amount, null, account_no, "ATM");
	}
}
