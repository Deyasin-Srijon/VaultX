package com.tech.vaultx.Util;

import com.tech.vaultx.CustomExceptions.WeakPasswordException;

public class PasswordValidator {
	public static void check_Strong_Password(String password) throws WeakPasswordException{
		int i, j;
		if(password.length() < 8 || password.length() > 13)
			throw new WeakPasswordException("\nError: Password must be at least 8 characters long & maximum contains 13 characters.");
		else {
			for(i = 0; i < 4; i++) {
				for(j = 0; j < password.length(); j++) {
					if(i == 0) {
						if(password.charAt(j) >= 48 && password.charAt(j) <= 57) {
							break;
						}
					}
					
					if(i == 1) {	
						if(password.charAt(j) >= 65 && password.charAt(j) <= 90)  {
							break;
						}
					}
					
					if(i == 2) {	
						if(password.charAt(j) >= 97 && password.charAt(j) <= 122)  {
							break;
						}
					}
					
					if(i == 3) {
						if((password.charAt(j) >= 33 && password.charAt(j) <= 47) || (password.charAt(j) >= 58 && password.charAt(j) <= 64) || (password.charAt(j) >= 91 && password.charAt(j) <= 96) || (password.charAt(j) >= 123 && password.charAt(j) <= 126)) {
							break;
						}
					}
				}
				if(j >= password.length()) 
					break;
			}
			
			if (i == 0)
				throw new WeakPasswordException("\nError: Password must contain at least one digit.");
			if (i == 1)
	            throw new WeakPasswordException("\nError: Password must contain at least one uppercase letter.");
	        if (i == 2) 
	            throw new WeakPasswordException("\nError: Password must contain at least one lowercase letter.");
	        if (i == 3) {
	            throw new WeakPasswordException("\nError: Password must contain at least one special character.");
	        }
		}
	}
	
	public static void passwordMessage() {
		System.out.println("\nPlease remember your given password should be a Strong Password. \nA Strong Password should contain - \n1.Minimum 8 characters & Maximum 13 characters \n2.Minimum 1 Lower case \n3.Minimum 1 Upper case \n4.Minimum 1 Numeric digit \n5.Minimum 1 Special Character");
	}
}
