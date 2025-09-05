package com.tech.vaultx.Service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.tech.vaultx.Dao.ATMDAO;
import com.tech.vaultx.Dao.AccountDAO;
import com.tech.vaultx.Dao.NetBankingDAO;
import com.tech.vaultx.Models.Account;
import com.tech.vaultx.Util.RandomNoGenerator;

public class AccountService {
	private AccountDAO accountDAO = new AccountDAO();
	private ATMDAO atmDAO = new ATMDAO();
	private NetBankingDAO netbankingDAO = new NetBankingDAO();
	
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

	// All Account List
	public ArrayList<Account> accountList(long userId) throws SQLException {
		return accountDAO.listAccountDAO(userId);
	}

	// Check there is any account exist for a User
	public boolean checkAccountService(long userId) throws SQLException {
		return accountDAO.checkAccountExistDAO(userId);
	}

	// Close an Account for a User
	public void deleteAccountService(long userId, String accNo, String accPassword) throws SQLException {
		new AccountService().validateAccount(userId, accNo, accPassword);
		int atm_id = accountDAO.isATMActivated(userId, accNo, accPassword);
		if(atm_id != 0)
			atmDAO.blockATMCardDAO(atm_id);
		
		int banking_id = accountDAO.isNetBankingActivated(userId, accNo, accPassword);
		if(banking_id != 0)
			netbankingDAO.deactivateNetBankingDAO(banking_id);
		accountDAO.deleteAccountDAO(userId, accNo, accPassword);
	}

	// Update Account Password
	public void updatePasswordService(String password, Account account) throws SQLException {
		if(accountDAO.previousPasswordDAO(account).equals(password))
			throw new IllegalArgumentException("Password can't same as previous");
		else
			accountDAO.updatePasswordDAO(password, account);
	}
}
