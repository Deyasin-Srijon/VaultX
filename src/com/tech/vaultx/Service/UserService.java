package com.tech.vaultx.Service;

import java.sql.SQLException;

import com.tech.vaultx.Dao.AddressDAO;
import com.tech.vaultx.Dao.UserDAO;
import com.tech.vaultx.Models.User;

public class UserService {
	private UserDAO userDAO = new UserDAO();
	private AddressDAO addrDAO = new AddressDAO();
	
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
    
    // Update User DOB
    public void updateDOBService(String dob, String email, String password) throws SQLException{
    	if(userDAO.isDOB(email, password) == null) {
    		userDAO.updateDOB(dob, email, password);
    	}
    	else
    		throw new IllegalArgumentException("You can't update dob once you have gave it!");
    }

    // Update User Aadhar no
	public void updateAadharService(String aadhar, String email, String password) throws SQLException {
		if(userDAO.isAadhar(email, password) == null) {
    		userDAO.updateAadhar(aadhar, email, password);
    	}
		else
			throw new IllegalArgumentException("You can't update Aadhar no. once you have gave it!");
	}

	// Update User Address no
	public void updateAddressService(String city, String state, String email, String password) throws SQLException {
		if(addrDAO.isAddress(email, password) == 0) {
			addrDAO.insertAddress(city, state, email, password);
		}
		else {
			addrDAO.updateAddress(city, state, email, password);
		}
	}
}
