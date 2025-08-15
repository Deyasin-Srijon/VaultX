package com.tech.vaultx.CustomExceptions;

@SuppressWarnings("serial")
public class WrongPhoneNumberException extends Exception{
	public WrongPhoneNumberException(String message){
		super(message);
	}
}
