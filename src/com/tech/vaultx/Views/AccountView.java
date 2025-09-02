package com.tech.vaultx.Views;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

import com.tech.vaultx.Controllers.AccountController;
import com.tech.vaultx.CustomExceptions.WeakPasswordException;
import com.tech.vaultx.Models.Account;
import com.tech.vaultx.Models.Branch;
import com.tech.vaultx.Models.User;
import com.tech.vaultx.Util.PasswordValidator;

public class AccountView {
	private AccountController accountcontroller = new AccountController();
	private ATMView atmView = new ATMView();
	
	// Open A New Account View
	public void newAccountView(Scanner sc, User user) {
		// account type user input
		int n = 0;
		String account_type = "";
		do {
			System.out.print("\nChoose Your Account Type \n1.Current(Press 1) \n2.Savings(Press 2) \nEnter Your choice: ");
			n = sc.nextInt();
			sc.nextLine();
		}while(n != 1 && n != 2);
		account_type = (n == 1) ? "current" : "savings";
        
        // branch name user input
        do {
			Branch.listOfBranch();
			System.out.print("\nEnter Your choice: ");
			n = sc.nextInt();
			sc.nextLine();
		}while(n < 1 || n > 10);
        String branch_name = Branch.getBranchName(n-1);
        
        // amount user input
        BigDecimal amount = BigDecimal.ZERO;
        BigDecimal limit = new BigDecimal("2000");

        while (true) {
            System.out.print("Enter at least 2000rs. as initial amount to open a new account: ");
            String input = sc.nextLine();

            try {
                amount = new BigDecimal(input);
                if (amount.compareTo(limit) >= 0) {
                    System.out.println("Amount Accepted.");
                    break;
                } else {
                    System.out.println("Warning: Amount is less than 2000. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid amount entered. Please enter a valid number.");
            }
        }
        
        // account entry password input
        String acc_password;
        // Strong Password Validation
        while (true) {
        	PasswordValidator.passwordMessage();
            System.out.print("\nGive a password for you account: ");
            acc_password = sc.nextLine();

            try {
                PasswordValidator.check_Strong_Password(acc_password);
                System.out.println("Password accepted");
                break;
            } catch (WeakPasswordException e) {
                System.out.println(e.getMessage());
            }
        }
        
        Account account = new Account(user.getUserId(), branch_name, account_type, acc_password, amount);
        accountcontroller.createAccount(account);
	}

	// Account Login view
	public void loginAccountView(User user, Scanner sc) {
		if(accountcontroller.isExistAccount(user.getUserId())) {
			String accNo;
			String accPassword;
			do {
				System.out.print("\nEnter Your Account No: ");
				accNo = sc.nextLine();
				System.out.print("Enter Your Account Password: ");
				accPassword = sc.nextLine();
			} while(!accountcontroller.loginAccount(user.getUserId(), accNo, accPassword));
			
			Account account = accountcontroller.getAccountDetails(user.getUserId(), accNo, accPassword);
			
			int i;
			String s;
			
			AccountView accountView = new AccountView();
			
			do {
				System.out.print("\n1.View Account Details(press 1)"
						+ "\n2. Update Account Password(press 2)"
						+ "\n3. Issue a new ATM card(press 3)"
						+ "\nEnter your choice: ");
				i = sc.nextInt();
				sc.nextLine();
				
				switch(i) {
				case 1:
					accountView.accountView(account);
					break;
				case 2:
					accountView.updateAccountPasswordView(sc, account);
					break;
				case 3:
					atmView.newATMView(sc, account);
					break;
				default:
					System.out.println("Sorry! Wrong choice given");
					break;
				}
				
				System.out.print("Do you want to proceed again?(y/n): ");
				s = sc.nextLine();
			}while(s.equalsIgnoreCase("y"));
		}
		else {
			System.out.println("No accounts exists for user ID: " + user.getUserId());
		}
	}

	// Account Details View
	public void accountView(Account account) {
		if (account.getStatus() != "deactivated") {
			System.out.print("\nAccount ID: " + account.getAcc_id());
			System.out.print("\nAccount No: " + account.getAccount_no());
			System.out.print("\nIFSC Code: " + account.getIfsc_code());
			System.out.print("\nBranch Name: " + account.getBranch_name());
			System.out.println("\nAccount Type: "  + account.getAccount_type());
			if(account.getAtm() == null)
				System.out.print("\nNote: You have Not issued any ATM Card.");
			else
				System.out.print("\nNote: ATM card issued on this account");
			
			if(account.getNetBanking() == null)
				System.out.println("\nNote: NetBanking facility is not issued on this account");
			else
				System.out.println("\nNote: NetBanking facility is issued on this account");
			
		} else {
		    System.out.println("Warning! Your Account Got Deactivated!");
		}	
	}
	
	// Update Account Type View
	public void updateAccountPasswordView(Scanner sc, Account account) {
		String password;
        // Strong Password Validation
        while (true) {
        	PasswordValidator.passwordMessage();
            System.out.print("\nGive a new password for you account: ");
            password = sc.nextLine();

            try {
                PasswordValidator.check_Strong_Password(password);
                System.out.println("Password accepted");
                break;
            } catch (WeakPasswordException e) {
                System.out.println(e.getMessage());
            }
        }
        
        accountcontroller.updateAccountPassword(password, account);
	}

	// List of all Bank Accounts for A User ID
	public void listOfAccounts(long userId) {
		ArrayList<Account> accounts = accountcontroller.getAccountList(userId);
		
		if (accounts.isEmpty()) {
	        System.out.println("No accounts found for user ID: " + userId);
	    } else {
	        System.out.println("Accounts for User ID: " + userId);
	        for (Account account : accounts) {
	            System.out.println("\n--------------------------------------");
	            System.out.println("Account ID     : " + account.getAcc_id());
	            System.out.println("IFSC Code      : " + account.getIfsc_code());
	            System.out.println("Branch Name    : " + account.getBranch_name());
	            System.out.println("Account Type   : " + account.getAccount_type());
	            System.out.println("Status         : " + account.getStatus());
	        }
	        System.out.println("\n--------------------------------------");
	    }
	}

	// Delete an Account
	public void deleteAccountView(long userId, Scanner sc) {
		if(accountcontroller.isExistAccount(userId)) {
			System.out.print("\nEnter Your Account No: ");
			String accNo = sc.nextLine();
			System.out.print("Enter Your Account Password: ");
			String accPassword = sc.nextLine();
			
			accountcontroller.deleteAccount(userId, accNo, accPassword);
		}else {
			System.out.println("No accounts exists for user ID: " + userId);
		}
	}
}
