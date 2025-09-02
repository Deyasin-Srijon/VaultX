package com.tech.vaultx.Models;

public class ATM {
	private int atm_id;
    private String card_no;
    private String cvv;
    private String exp_date;
	private String pincode;
    
    
    public ATM(String pincode) {
    	this.pincode = pincode;
    }
	public ATM(int atm_id, String card_no, String cvv, String exp_date, String pincode) {
		this.atm_id = atm_id;
		this.card_no = card_no;
		this.cvv = cvv;
		this.exp_date = exp_date;
		this.pincode = pincode;
	}



	public int getAtm_id() {
		return atm_id;
	}
	public void setAtm_id(int atm_id) {
		this.atm_id = atm_id;
	}
	
	public String getCard_no() {
		return card_no;
	}
	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}
	
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	
	public String getExp_date() {
		return exp_date;
	}
	public void setExp_date(String exp_date) {
		this.exp_date = exp_date;
	}

	public String getPincode() {
		return pincode;
	}
}
