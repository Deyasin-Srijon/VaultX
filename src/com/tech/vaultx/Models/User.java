package com.tech.vaultx.Models;

public class User {
	private long userId;
    private String first_name;
    private String last_name;
    private String user_password;
    private Address address;
    private String phn_no;
    private String dob;
    private String email;
    private String aadhar_no;
    
    
	public User(String first_name, String last_name, String user_password, String phn_no, String email) {
		setFirst_name(first_name);
		setLast_name(last_name);
		setUser_password(user_password);
		setPhn_no(phn_no);
		setEmail(email);
	}
	
	public User(long userId, String first_name, String last_name, String dob, String aadhar_no) {
		this.userId = userId;
		this.first_name = first_name;
		this.last_name = last_name;
		this.dob = dob;
		this.aadhar_no = aadhar_no;
	}


	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getPhn_no() {
		return phn_no;
	}
	public void setPhn_no(String phn_no) {
		this.phn_no = phn_no;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAadhar_no() {
		return aadhar_no;
	}
	public void setAadhar_no(String aadhar_no) {
		this.aadhar_no = aadhar_no;
	}
}
