package com.tech.vaultx.CustomExceptions;

@SuppressWarnings("serial")
public class InValidPinCode extends Exception{
	public InValidPinCode(String message) {
		super(message);
	}
}
