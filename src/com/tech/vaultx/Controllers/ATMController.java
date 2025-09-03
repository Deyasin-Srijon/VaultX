package com.tech.vaultx.Controllers;

import com.tech.vaultx.Models.ATM;
import com.tech.vaultx.Models.Account;
import com.tech.vaultx.Service.ATMService;

public class ATMController {
	private ATMService atmService = new ATMService();
	
	// Issue New ATM Card 
	public void issueATMCard(ATM atm, Account account) {
		try {
			atmService.issueNewCardService(atm, account);
			System.out.println("\nAccount created successfully!");
			account.setAtm(atm);
			System.out.print("\nATM Card No.: " + atm.getCard_no());
            System.out.print("\nCVV: " + atm.getCvv());
            System.out.println("\nExpiry Date: " + atm.getExp_date());
            System.out.println("\nNote: It is recommended that please write down this info and Don't share this info to anyone.");
		}catch(Exception e) {
			System.out.print("Error: " + e.getMessage());
		}
	}

	// ATM Card Details
	public ATM getATMDetails(int atm_id) {
		if(atm_id == 0)
			return null;
		else {
			try {
				return atmService.atmDetailsService(atm_id);
			} catch(Exception e) {
				System.out.print("Error: " + e.getMessage());
				return null;
			}
		}
	}

	// Block ATM card
	public void blockATMCard(Account account) {
		try {
			atmService.blockATMCardService(account);
			System.out.println("\nATM Card Blocked Successfully!");
		} catch(Exception e) {
			System.out.print("Error: " + e.getMessage());
		}
	}

	// Change ATM pincode
	public void changePincode(ATM atm, String pincode) {
		try {
			atmService.changePincodeService(atm, pincode);
			System.out.println("\nATM Card PinCode changed Successfully!");
		}catch(Exception e) {
			System.out.print("Error: " + e.getMessage());
		}
	}
}
