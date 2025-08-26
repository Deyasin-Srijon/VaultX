package com.tech.vaultx.Views;

import java.math.BigDecimal;
import java.util.Scanner;

import com.tech.vaultx.Controllers.AccountController;
import com.tech.vaultx.CustomExceptions.WeakPasswordException;
import com.tech.vaultx.Models.Account;
import com.tech.vaultx.Models.Branch;
import com.tech.vaultx.Models.User;
import com.tech.vaultx.Util.PasswordValidator;

public class AccountView {
	private AccountController accountcontroller = new AccountController();
	
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
}
