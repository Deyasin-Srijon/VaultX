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

	// ATM card Details View 
	public void atmDetailsView(Scanner sc, Account account) {
		if(account.getAtm() == null)
			System.out.println("Note: ATM Card is not issued on this Account");
		else {
			while(true) {
				System.out.print("\nEnter Your ATM PinCode: ");
				String pincode = sc.nextLine();
				
				if(pincode.equals(account.getAtm().getPincode())) {
					System.out.println("\nATM Card NO: " + account.getAtm().getCard_no());
					System.out.println("CVV: " + account.getAtm().getCvv());
					System.out.println("Expiry Date: " + account.getAtm().getExp_date());
					break;
				} else {
					System.out.println("Warning: Please Enter Correct Password");
				}
			}
		}
	}

	// Block a ATM card
	public void blockCardView(Scanner sc, Account account) {
		if(account.getAtm() == null)
			System.out.println("Note: ATM Card is not issued on this Account");
		else {
			while(true) {
				System.out.print("\nEnter Your ATM PinCode: ");
				String pincode = sc.nextLine();
				
				if(pincode.equals(account.getAtm().getPincode())) {
					atmController.blockATMCard(account);
					break;
				} else {
					System.out.println("Warning: Please Enter Correct Password");
				}
			}
		}
	}
}
