package com.tech.vaultx.Models;

public class Address {
	private static String country = "India";
	private String state;
	private String city;
	
	
	public Address(String state, String city) {
		this.state = state;
		this.city = city;
	}
	
	
	public static String getCountry() {
		return country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
}
