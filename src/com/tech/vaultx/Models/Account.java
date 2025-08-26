package com.tech.vaultx.Models;

import java.math.BigDecimal;

public class Account {
	private long acc_id;
	private long user_id;
	private String account_no;
	private String ifsc_code;
	private String branch_name;
	private String account_type;
	private String profile_password;
	private int atm_id;
	private int banking_id;
	private BigDecimal amount;
	private String status;
	
	
	public Account(long user_id, String branch_name, String account_type,
			String profile_password, BigDecimal amount) {
		this.user_id = user_id;
		this.branch_name = branch_name;
		this.account_type = account_type;
		this.profile_password = profile_password;
		this.amount = amount;
	}
	
	
	public long getAcc_id() {
		return acc_id;
	}
	public void setAcc_id(long acc_id) {
		this.acc_id = acc_id;
	}
	
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	
	public String getAccount_no() {
		return account_no;
	}
	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}
	
	public String getIfsc_code() {
		return ifsc_code;
	}
	public void setIfsc_code(String ifsc_code) {
		this.ifsc_code = ifsc_code;
	}
	
	public String getBranch_name() {
		return branch_name;
	}
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
	
	public String getAccount_type() {
		return account_type;
	}
	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}
	
	public String getProfile_password() {
		return profile_password;
	}
	public void setProfile_password(String profile_password) {
		this.profile_password = profile_password;
	}
	
	public int getAtm_id() {
		return atm_id;
	}
	public void setAtm_id(int atm_id) {
		this.atm_id = atm_id;
	}
	
	public int getBanking_id() {
		return banking_id;
	}
	public void setBanking_id(int banking_id) {
		this.banking_id = banking_id;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
