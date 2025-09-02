package com.tech.vaultx.Views;

import java.util.Scanner;

import com.tech.vaultx.Controllers.ATMController;
import com.tech.vaultx.Models.ATM;
import com.tech.vaultx.Models.Account;
import com.tech.vaultx.Util.InputValidator;

public class ATMView {
	private ATMController atmController = new ATMController();
	
	// New ATM card Issue 
	public void newATMView(Scanner sc, Account account) {
		if(account.getAtm() != null)
			System.out.println("\nNote: ATM Card already issued for this Account");
		else {
			String pincode;
			System.out.println("\nWelcome to VaultX ATM Service");
			
			while(true) {
				System.out.print("Give a 6 digit pin for your atm card: ");
				pincode = sc.nextLine();
				
				if(InputValidator.check_Valid_Pin(pincode))
					break;
			}
			
			ATM atm = new ATM(pincode);
			atmController.issueATMCard(atm, account);
		}
	}
}
