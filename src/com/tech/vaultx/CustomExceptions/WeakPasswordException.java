package com.tech.vaultx.CustomExceptions;

@SuppressWarnings("serial")
public class WeakPasswordException extends Exception{
	public WeakPasswordException(String message) {
		super(message);
	}
}
