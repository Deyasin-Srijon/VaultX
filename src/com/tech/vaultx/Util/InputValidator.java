package com.tech.vaultx.Util;

import com.tech.vaultx.CustomExceptions.NonValidAadharNo;
import com.tech.vaultx.CustomExceptions.WrongDOBInput;
import com.tech.vaultx.CustomExceptions.WrongPhoneNumberException;

public class InputValidator {
	// Valid Phone no checker
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
	
	public static void phoneMessage() {
		System.out.println("\nPlease enter a valid Phone no.");
	}
	
	// Valid DOB checker
	@SuppressWarnings("finally")
	public static boolean check_Valid_DOB(String dob){
		boolean valid = false;
		
		try {
			// ✅ Check pattern manually: must be 4-2-2 digits with hyphens
	        if (!dob.matches("\\d{4}-\\d{2}-\\d{2}")) {
	        	throw new WrongDOBInput("Format must be yyyy-mm-dd");
	        }

	        // ✅ Extract parts
	        String[] parts = dob.split("-");
	        int year = Integer.parseInt(parts[0]);
	        int month = Integer.parseInt(parts[1]);
	        int day = Integer.parseInt(parts[2]);

	        // Year check (reasonable range, e.g., 1900–current year)
	        if (year < 1900 || year > 2025) {
	        	throw new WrongDOBInput("Invalid year. Use between 1900 and 2025.");
	        }

	        // Month check
	        if (month < 1 || month > 12) {
	        	throw new WrongDOBInput("Invalid month (must be 01-12).");
	        }

	        // Days in each month
	        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

	        // Leap year adjustment
	        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
	            daysInMonth[1] = 29; // February = 29 days
	        }

	        // Day check
	        if (day < 1 || day > daysInMonth[month - 1]) {
	        	throw new WrongDOBInput("Invalid day for the given month.");
	        }

	        valid = true; // Passed all checks
		} catch(WrongDOBInput e) {
			System.out.println(e.getMessage());
		} catch(Exception e) {
			System.out.println("Invalid number format: " + e.getMessage());
		}
		finally {
			return valid;
		}
	}
	
	// Valid Aadhar no checker
	public static boolean check_Valid_Aadhar(String aadhar) {
		try {
			if(!aadhar.matches("\\d{12}"))
				throw new NonValidAadharNo("Aadhar No. should be of 12 digits.");
			return true;
		} catch(NonValidAadharNo e){
			System.out.println(e.getMessage());
			return false;
		}
	}
}
