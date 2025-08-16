package com.tech.vaultx.CustomExceptions;

@SuppressWarnings("serial")
public class WrongDOBInput extends Exception{
	public WrongDOBInput(String message) {
		super(message);
	}
}
