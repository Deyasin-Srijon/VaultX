package com.tech.vaultx.Controllers;

import com.tech.vaultx.Models.User;
import com.tech.vaultx.Service.UserService;

public class UserController {
	private UserService userService = new UserService();
	
	// Creating a User Profile
    public void createUser(User user) {
        try {
            userService.registerUser(user);
            System.out.println("User registered successfully!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    // Login to user profile
    public boolean loginUser(String password, String email) {
    	try {
    		userService.validateUser(password, email);
    		System.out.println("User Logged in successfully!");
    		return true;
    	} catch(Exception e) {
    		System.out.println("Error: " + e.getMessage());
    		return false;
    	}
    }
    
    // Give user profile
    public User getUserProfile(String password, String email) {
    	try {
    		return userService.userDetails(password, email);
    	} catch(Exception e) {
    		System.out.println("Error: " + e.getMessage());
    		return null;
    	}
    }
    
    // Delete user profile
    public void deleteUserProfile(String email, String password) {
    	try {
    		userService.deleteUser(email, password);
    		System.out.println("User Profile deleted successfully!");
    	} catch(Exception e) {
    		System.out.println("Error: " + e.getMessage());
    	}
    }
}
