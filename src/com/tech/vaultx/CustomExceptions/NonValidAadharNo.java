package com.tech.vaultx.CustomExceptions;

@SuppressWarnings("serial")
public class NonValidAadharNo extends Exception{
	public NonValidAadharNo(String message) {
		super(message);
	}
}
