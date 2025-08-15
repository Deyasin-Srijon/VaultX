package com.tech.vaultx.Views;

import java.util.Scanner;

import com.tech.vaultx.Controllers.UserController;
import com.tech.vaultx.CustomExceptions.WeakPasswordException;
import com.tech.vaultx.CustomExceptions.WrongPhoneNumberException;
import com.tech.vaultx.Models.User;
import com.tech.vaultx.Util.InputValidator;
import com.tech.vaultx.Util.PasswordValidator;

public class UserView {
	// User operation menu
	public static void userMenuView(Scanner sc) {
		int i;
		String s;
		
		UserView userview = new UserView();
		
		do {
			System.out.print("\n1.Register new user(press 1)"
					+ "\n2.Login into user account(press 2)"
					+ "\nEnter your choice: ");
			i = sc.nextInt();
			sc.nextLine();
			
			switch(i) {
				case 1:
					userview.registerUserView(sc);
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
            System.out.print("\nEnter your password: ");
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
        new UserController().createUser(user);
    }
}
