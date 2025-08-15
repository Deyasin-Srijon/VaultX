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
}
