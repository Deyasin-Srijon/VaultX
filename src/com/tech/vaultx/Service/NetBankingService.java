package com.tech.vaultx.Service;

import java.math.BigDecimal;
import java.sql.SQLException;

import com.tech.vaultx.Dao.NetBankingDAO;
import com.tech.vaultx.Dao.TransactionDAO;
import com.tech.vaultx.Models.Account;
import com.tech.vaultx.Models.NetBanking;

public class NetBankingService {
	private NetBankingDAO netbankingDAO = new NetBankingDAO();
	private TransactionDAO transactionDAO = new TransactionDAO();

	// Register New NetBanking Service
	public void registerNetBankingService(NetBanking netbanking, Account account) throws SQLException {
		netbankingDAO.registerNetBankingDAO(netbanking, account);
	}

	// Details of NetBanking Service
	public NetBanking netbankingDetailsService(int banking_id) throws SQLException {
		return netbankingDAO.netbankingDetailsDAO(banking_id);
	}

	// Deactivate NetBanking
	public void deactivateNetBankingService(Account account) throws SQLException {
		netbankingDAO.deactivateNetBankingDAO(account.getNetBanking().getBanking_id());
		account.setNetBanking(null);
	}

	// Change NetBanking Password
	public void changePasswordService(NetBanking netbanking, String password) throws SQLException {
		netbankingDAO.updatePasswordDAO(netbanking, password);
		netbanking.setUser_password(password);
	}

	// Change NetBanking UserName
	public void changeUserNameService(NetBanking netbanking, String username) throws SQLException {
		netbankingDAO.updateUserNameDAO(netbanking, username);
		netbanking.setUsername(username);		
	}

	// Change NetBanking Pincode
	public void changePincodeService(NetBanking netbanking, String pincode) throws SQLException {
		netbankingDAO.updatePincodeDAO(netbanking, pincode);
		netbanking.setPincode(pincode);
	}

	// Update amount after Transaction
	public void updateAmountService(BigDecimal amount, String receiverAccNo, String senderAccNo) throws SQLException {
		transactionDAO.updateDebitAmountDAO(amount, senderAccNo);
		transactionDAO.updateCreditAmountDAO(amount, receiverAccNo);
		transactionDAO.insertTransaction(amount, senderAccNo, receiverAccNo, "Netbanking");
	}
}
