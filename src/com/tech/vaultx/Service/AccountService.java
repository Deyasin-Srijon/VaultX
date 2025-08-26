package com.tech.vaultx.Service;

import java.sql.SQLException;

import com.tech.vaultx.Dao.AccountDAO;
import com.tech.vaultx.Models.Account;
import com.tech.vaultx.Util.RandomNoGenerator;

public class AccountService {
	private AccountDAO accountDAO = new AccountDAO();
	
	// Register new Account 
	public void registerAccount(Account account) throws SQLException{
		String accNo;
        do {
            accNo = RandomNoGenerator.randomNoGenerator(10);
        } while (accountDAO.accountNoExists(accNo));
        account.setAccount_no(accNo);
        account.setIfsc_code("vaultx" + RandomNoGenerator.randomNoGenerator(5));
        
        accountDAO.createAccountDAO(account);
	}

	// Login to a User Bank Account
	public void validateAccount(long userId, String accNo, String password) throws SQLException {
		if (!accountDAO.checkAccountValidity(userId, accNo, password)) {
    		throw new IllegalArgumentException("Bank Account not found. Password or Account No Mismatched!");
    	}
	}

	// Get Account Details
	public Account accountDetails(long userId, String accNo, String password) throws SQLException {
		Account account = accountDAO.viewAccount(userId, accNo, password);
    	return account;
	}
}
