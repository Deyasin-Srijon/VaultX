package com.tech.vaultx.Util;

import com.tech.vaultx.CustomExceptions.WrongPhoneNumberException;

public class InputValidator {
	public static void check_Valid_PhoneNo(String phn_no) throws WrongPhoneNumberException{
		int j;
		
		if(phn_no.length() != 10)
			throw new WrongPhoneNumberException("\nError: A Phone no. should have 10 digits.");
		else {			
			for(j = 0; j < phn_no.length(); j++) {
				if(phn_no.charAt(j) < 48 || phn_no.charAt(j) > 57) {
					break;
				}
			}
			if(j < phn_no.length())
				throw new WrongPhoneNumberException("\nError: A Phone no. should contain numeric digit only.");
		}
	}
	
	public static void message() {
		System.out.println("\nPlease enter a valid Phone no.");
	}
}
