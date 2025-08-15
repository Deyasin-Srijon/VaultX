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
}
