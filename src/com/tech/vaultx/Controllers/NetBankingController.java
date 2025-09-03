package com.tech.vaultx.Controllers;

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
}
