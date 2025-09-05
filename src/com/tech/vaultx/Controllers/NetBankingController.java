package com.tech.vaultx.Controllers;

import java.math.BigDecimal;

import com.tech.vaultx.Models.Account;
import com.tech.vaultx.Models.NetBanking;
import com.tech.vaultx.Service.NetBankingService;

public class NetBankingController {
	private NetBankingService netbankingservice = new NetBankingService();

	// New NetBanking Register
	public void registerNewNetBanking(NetBanking netbanking, Account account) {
		try {
			netbankingservice.registerNetBankingService(netbanking, account);
			System.out.println("\nNetBanking Service Registered successfully!");
			account.setNetBanking(netbanking);
            System.out.println("\nNote: It is recommended that please remember your username & password and Don't share this info to anyone.");
		}catch(Exception e) {
			System.out.print("Error: " + e.getMessage());
		}
	}
	
	// NetBanking Details
	public NetBanking getNetBankingDetails(int banking_id) {
		if(banking_id == 0)
			return null;
		else {
			try {
				return netbankingservice.netbankingDetailsService(banking_id);
			} catch(Exception e) {
				System.out.print("Error: " + e.getMessage());
				return null;
			}
		}
	}

	// Deactivate NetBanking
	public void deactivateNetBanking(Account account) {
		try {
			netbankingservice.deactivateNetBankingService(account);
			System.out.println("\nNetBanking Deactivated Successfully!");
		} catch(Exception e) {
			System.out.print("Error: " + e.getMessage());
		}
	}

	// Change NetBanking Password 
	public void changePassword(NetBanking netbanking, String password) {
		try {
			netbankingservice.changePasswordService(netbanking, password);
			System.out.println("\nNetBanking Password Updated Successfully!");
		} catch(Exception e) {
			System.out.print("Error: " + e.getMessage());
		}
	}

	// Change NetBanking UserName
	public void changeUserName(NetBanking netbanking, String username, String password) {
		try {
			netbankingservice.changeUserNameService(netbanking, username);
			netbankingservice.changePasswordService(netbanking, password);
			System.out.println("\nNetBanking UserName & Password Updated Successfully!");
		} catch(Exception e) {
			System.out.print("Error: " + e.getMessage());
		}
	}

	// Change NetBanking Pincode
	public void changePincode(NetBanking netbanking, String pincode) {
		try {
			netbankingservice.changePincodeService(netbanking, pincode);
			System.out.println("\nNetbanking PinCode changed Successfully!");
		}catch(Exception e) {
			System.out.print("Error: " + e.getMessage());
		}
	}

	// Fund Transfer functionality
	public void fundTransfer(BigDecimal amount, String acc_no, Account account) {
		try {
			netbankingservice.updateAmountService(amount, acc_no, account.getAccount_no());
			System.out.println("\nTransaction Successful!");
		} catch(Exception e) {
			System.out.print("Error: " + e.getMessage());
		}
	}
}
