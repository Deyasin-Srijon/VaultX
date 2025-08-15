package com.tech.vaultx.Views;

import java.util.Scanner;

import com.tech.vaultx.Controllers.UserController;
import com.tech.vaultx.CustomExceptions.WeakPasswordException;
import com.tech.vaultx.CustomExceptions.WrongPhoneNumberException;
import com.tech.vaultx.Models.User;
import com.tech.vaultx.Util.InputValidator;
import com.tech.vaultx.Util.PasswordValidator;

public class UserView {
	private UserController usercontroller = new UserController();
	// User operation menu
	public static void userMenuView(Scanner sc) {
		int i;
		String s;
		
		UserView userview = new UserView();
		
		do {
			System.out.print("\n1.Register new user(press 1)"
					+ "\n2.Login into user account(press 2)"
					+ "\n3.Delete user account(press 3)"
					+ "\nEnter your choice: ");
			i = sc.nextInt();
			sc.nextLine();
			
			switch(i) {
				case 1:
					userview.registerUserView(sc);
					break;
				case 2:
					userview.login(sc);
					break;
				case 3:
					userview.deleteUserView(sc);
					break;
				default:
					System.out.println("Sorry! Wrong choice given");
					break;
			}
			
			System.out.print("Do you want to proceed again?(y/n): ");
			s = sc.nextLine();
		}while(s.equalsIgnoreCase("y"));
	}
	
	// Registration User View
	public void registerUserView(Scanner sc) {
        System.out.print("Enter First Name: ");
        String first_name = sc.nextLine();
        
        System.out.print("Enter Last Name: ");
        String last_name = sc.nextLine();
        
        System.out.print("Enter Email: ");
        String email = sc.nextLine();
        
        String phn_no;
        // Phone Number Validation
        while(true) {
        	System.out.print("\nEnter Phone: ");
            phn_no = sc.nextLine();
            
            try {
            	InputValidator.check_Valid_PhoneNo(phn_no);
            	System.out.println("Phone Number accepted");
            	break;
            } catch(WrongPhoneNumberException e) {
            	System.out.println(e.getMessage());
            	InputValidator.message();
            }   
        }
        
        String user_password;
        // Strong Password Validation
        while (true) {
        	PasswordValidator.passwordMessage();
            System.out.print("\nGive a password: ");
            user_password = sc.nextLine();

            try {
                PasswordValidator.check_Strong_Password(user_password);
                System.out.println("Password accepted");
                break;
            } catch (WeakPasswordException e) {
                System.out.println(e.getMessage());
            }
        }        
        
        User user = new User(first_name, last_name, user_password, phn_no, email);
        usercontroller.createUser(user);
    }
	
	// Login view
	public void login(Scanner sc) {
		System.out.print("Enter Email: ");
        String email = sc.nextLine();
        System.out.print("\nEnter your password: ");
        String password = sc.nextLine();
        
        if(usercontroller.loginUser(password, email)) {
        	int i;
    		String s;
    		
    		UserView userview = new UserView();
    		
    		do {
    			System.out.print("\n1.View Profile(press 1)"
    					+ "\n2.Update User details(press 2)"
    					+ "\n3.Create a Bank Account(press 3)"
    					+ "\n4.Login to a Bank Account(press 4)"
    					+ "\nEnter your choice: ");
    			i = sc.nextInt();
    			sc.nextLine();
    			
    			switch(i) {
    				case 1:
    					userview.userProfileView(email, password);
    					break;
    				default:
    					System.out.println("Sorry! Wrong choice given");
    					break;
    			}
    			
    			System.out.print("Do you want to proceed again?(y/n): ");
    			s = sc.nextLine();
    		}while(s.equalsIgnoreCase("y"));
        }
	}
	
	// Delete User Profile
	public void deleteUserView(Scanner sc) {
		System.out.print("Enter Email: ");
        String email = sc.nextLine();
        System.out.print("\nEnter your password: ");
        String password = sc.nextLine();
        
        usercontroller.deleteUserProfile(email, password);
	}
	
	// User Profile View
	public void userProfileView(String email, String password) {
		User user = usercontroller.getUserProfile(password, email);
		
		if (user != null) {
			System.out.print("\nUser ID: " + user.getUserId());
			System.out.print("\nName: " + user.getFirst_name() + " " + user.getLast_name());
			System.out.print("\nDate of Birth: " + user.getDob());
			System.out.println("\nAadhar No: " + user.getAadhar_no());
		} else {
		    System.out.println("Invalid email or password");
		}		
	}
}
