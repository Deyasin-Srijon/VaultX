package com.tech.vaultx.Views;

import java.util.Scanner;

import com.tech.vaultx.Controllers.NetBankingController;
import com.tech.vaultx.CustomExceptions.WeakPasswordException;
import com.tech.vaultx.Models.Account;
import com.tech.vaultx.Models.NetBanking;
import com.tech.vaultx.Util.InputValidator;
import com.tech.vaultx.Util.PasswordValidator;

public class NetBankingView {
	private NetBankingController netbankingcontroller = new NetBankingController();
	
	// NetBanking Service Register View
	public void newNetBankingView(Scanner sc, Account account) {
		if(account.getNetBanking() != null)
			System.out.println("\nNote: NetBanking Service already available on this Account");
		else {
			System.out.println("\nWelcome to VaultX NetBanking Service");
			
			System.out.print("\nEnter Your Username: ");
			String username = sc.nextLine();
			
			// Netbanking entry password input
	        String password;
	        // Strong Password Validation
	        while (true) {
	        	PasswordValidator.passwordMessage();
	            System.out.print("\nGive a password for you account: ");
	            password = sc.nextLine();

	            try {
	                PasswordValidator.check_Strong_Password(password);
	                System.out.println("Password accepted");
	                break;
	            } catch (WeakPasswordException e) {
	                System.out.println(e.getMessage());
	            }
	        }
	        
			String pincode;
			
			while(true) {
				System.out.print("Give a 6 digit pin for your netbanking transaction: ");
				pincode = sc.nextLine();
				
				if(InputValidator.check_Valid_Pin(pincode))
					break;
			}
			
			NetBanking netbanking = new NetBanking(username, password, pincode);
			netbankingcontroller.registerNewNetBanking(netbanking, account);
		}
	}
}
