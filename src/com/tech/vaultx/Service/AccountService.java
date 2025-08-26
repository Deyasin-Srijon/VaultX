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
}
