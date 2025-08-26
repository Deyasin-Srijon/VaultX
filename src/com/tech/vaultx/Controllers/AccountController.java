package com.tech.vaultx.Controllers;

import com.tech.vaultx.Models.Account;
import com.tech.vaultx.Service.AccountService;

public class AccountController {
	private AccountService accountservice = new AccountService(); 
	
	// create new account
    public void createAccount(Account account) {
        try {
        	accountservice.registerAccount(account);
            System.out.print("\nAccount created successfully!");
            System.out.print("\nAccount no: " + account.getAccount_no());
            System.out.print("\nIFSC Code: " + account.getIfsc_code());
            System.out.println("\nNote: It is recommended that please write down this info and Don't share this info to anyone.");
        } catch (Exception e) {
        	System.out.print("Error: " + e.getMessage());
        }
    }
    
    // Login to user Bank Account
    public boolean loginAccount(long userId, String accNo, String password) {
    	try {
    		accountservice.validateAccount(userId, accNo, password);
    		System.out.println("Successfully Logged In to the Account!");
    		return true;
    	} catch(Exception e) {
    		System.out.println("\nError: " + e.getMessage());
    		return false;
    	}
    }

    // Get an Account Details
	public Account getAccountDetails(long userId, String accNo, String Password) {
		try {
    		return accountservice.accountDetails(userId, accNo, Password);
    	} catch(Exception e) {
    		System.out.println("Error: " + e.getMessage());
    		return null;
    	}
	}
}
