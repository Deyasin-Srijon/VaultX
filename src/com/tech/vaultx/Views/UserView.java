package com.tech.vaultx.Views;

import java.util.Scanner;

import com.tech.vaultx.Controllers.UserController;
import com.tech.vaultx.CustomExceptions.WeakPasswordException;
import com.tech.vaultx.CustomExceptions.WrongPhoneNumberException;
import com.tech.vaultx.Models.Address;
import com.tech.vaultx.Models.User;
import com.tech.vaultx.Util.InputValidator;
import com.tech.vaultx.Util.PasswordValidator;

public class UserView {
	private UserController usercontroller = new UserController();
	private AccountView accountView = new AccountView();
	
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
            	InputValidator.phoneMessage();
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
		String email;
		String password;
		do {
			System.out.print("\nEnter Email: ");
	        email = sc.nextLine();
	        System.out.print("Enter your password: ");
	        password = sc.nextLine();
		}while(!usercontroller.loginUser(password, email));
		
		User user = usercontroller.getUserProfile(password, email);
        
        int i;
    	String s;
    		
    	UserView userview = new UserView();
    		
    	do {
    		System.out.print("\n1.View Profile(press 1)"
    				+ "\n2.Update User details(press 2)"
    				+ "\n3.Create a Bank Account(press 3)"
    				+ "\n4.Login to a Bank Account(press 4)"
    				+ "\n5.All Bank Account on this Profile(press 5)"
    				+ "\n6.Delete an Account(press 6)"
    				+ "\nEnter your choice: ");
    		i = sc.nextInt();
    		sc.nextLine();
    			
    		switch(i) {
    			case 1:
    				userview.userProfileView(user);
    				break;
    			case 2:
    				userview.profileUpdateView(sc, email, password);
    				break;
    			case 3:
    				accountView.newAccountView(sc, user);
    				break;
    			case 4:
    				accountView.loginAccountView(user, sc);
    				break;
    			case 5:
    				accountView.listOfAccounts(user.getUserId());
    				break;
    			default:
    				System.out.println("Sorry! Wrong choice given");
    				break;
    		}
    			
    		System.out.print("Do you want to proceed again?(y/n): ");
    		s = sc.nextLine();
    	}while(s.equalsIgnoreCase("y"));
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
	public void userProfileView(User user) {
		if (user != null) {
			System.out.print("\nUser ID: " + user.getUserId());
			System.out.print("\nName: " + user.getFirst_name() + " " + user.getLast_name());
			System.out.print("\nDate of Birth: " + user.getDob());
			if(user.getAddress() != null)
				System.out.print("\nAddress: " + user.getAddress().getCity() + ", " + user.getAddress().getState() + ", " + Address.getCountry());	
			System.out.println("\nAadhar No: " + user.getAadhar_no());
		} else {
		    System.out.println("Invalid email or password");
		}		
	}
	
	// User Profile Update View
	public void profileUpdateView(Scanner sc, String email, String password) {
		int i;
		String s;
		
		UserView userview = new UserView();
		
		do {
			System.out.print("\n1.Update DOB on profile(press 1)"
					+ "\n2.Update Aadhar No on profile(press 2)"
					+ "\n3.Update Address on profile(press 3)"
					+ "\nEnter your choice: ");
			i = sc.nextInt();
			sc.nextLine();
			
			switch(i) {
				case 1:
					userview.dobUpdateView(sc, email, password);
					break;
				case 2:
					userview.aadharUpdateView(sc, email, password);
					break;
				case 3:
					userview.addressUpdateView(sc, email, password);
					break;
				default:
					System.out.println("Sorry! Wrong choice given");
					break;
			}
			
			System.out.print("Do you want to proceed again?(y/n): ");
			s = sc.nextLine();
		}while(s.equalsIgnoreCase("y"));
	}
	
	// User DOB update view
	public void dobUpdateView(Scanner sc, String email, String password) {
		String dob = "";
		
		do {
			System.out.print("\nEnter your Date of Birth(yyyy-mm-dd): ");
			dob = sc.nextLine();
		} while(!InputValidator.check_Valid_DOB(dob));
		
		usercontroller.updateUserDOB(dob, email, password);
	}
	
	// User Aadhar update view 
	public void aadharUpdateView(Scanner sc, String email, String password) {
		String aadhar = "";
		
		do {
			System.out.print("\nEnter your Aadhar Card No.: ");
			aadhar = sc.nextLine();
		} while(!InputValidator.check_Valid_Aadhar(aadhar));
		
		usercontroller.updateUserAadhar(aadhar, email, password);
	}
	
	// User Address update
	public void addressUpdateView(Scanner sc, String email, String password) {
		System.out.print("\nEnter your state: ");
		String state = sc.nextLine();
		System.out.print("\nEnter your city: ");
		String city = sc.nextLine();
		
		usercontroller.updateUserAddress(city, state, email, password);
	}
}
