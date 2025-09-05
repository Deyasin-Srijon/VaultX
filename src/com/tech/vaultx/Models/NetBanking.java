package com.tech.vaultx.Models;

public class NetBanking implements Comparable<NetBanking> {
	int banking_id;
    String username;
    String user_password;
    String pincode;
    
    public NetBanking(String username, String user_password) {
    	this.username = username;
		this.user_password = user_password;
	}
	public NetBanking(String username, String user_password, String pincode) {
		this(username, user_password);
		this.pincode = pincode;
	}
	public NetBanking(int banking_id, String username, String user_password, String pincode) {
		this(username, user_password, pincode);
		this.banking_id = banking_id;
	}
	
	
	public int getBanking_id() {
		return banking_id;
	}
	public void setBanking_id(int banking_id) {
		this.banking_id = banking_id;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	
	@Override
	public int compareTo(NetBanking netbanking) {
		if(netbanking.getUsername().equals(this.getUsername()) && netbanking.getUser_password().equals(this.getUser_password()))
			return 1;
		else
			return 0;
	}
}
