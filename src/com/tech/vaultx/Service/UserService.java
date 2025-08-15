package com.tech.vaultx.Service;

import java.sql.SQLException;

import com.tech.vaultx.Dao.UserDAO;
import com.tech.vaultx.Models.User;

public class UserService {
	private UserDAO userDAO = new UserDAO();
	
	// Register a User Profile
    public void registerUser(User user) throws SQLException {
    	// Verification of user
        if (user.getEmail().contains("@")) {
            if(userDAO.checkUserExists(user))
            	throw new IllegalArgumentException("User Already exists!");
            else
            	userDAO.addUser(user);
        } else {
            throw new IllegalArgumentException("Invalid email!");
        }
    }
    
    // Login to a User Profile
    public void validateUser(String password, String email) throws SQLException {
    	if (!userDAO.checkUserValidity(password, email)) {
    		throw new IllegalArgumentException("User Profile not found. Password or Email Mismatched!");
    	}	
    }
    
    // Show a User Profile
    public User userDetails(String password, String email) throws SQLException {
    	return userDAO.viewProfile(email, password);
    }
    
    // Delete User Profile
    public void deleteUser(String email, String password) throws SQLException {
    	new UserService().validateUser(password, email);
    	userDAO.deleteProfile(email, password);
    }
}
