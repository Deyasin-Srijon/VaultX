package com.tech.vaultx.Service;

import java.sql.SQLException;

import com.tech.vaultx.Dao.NetBankingDAO;
import com.tech.vaultx.Models.Account;
import com.tech.vaultx.Models.NetBanking;

public class NetBankingService {
	private NetBankingDAO netbankingDAO = new NetBankingDAO();

	// Register New NetBanking Service
	public void registerNetBankingService(NetBanking netbanking, Account account) throws SQLException {
		netbankingDAO.registerNetBankingDAO(netbanking, account);
	}

	// Details of NetBanking Service
	public NetBanking netbankingDetailsService(int banking_id) throws SQLException {
		return netbankingDAO.netbankingDetailsDAO(banking_id);
	}
}
