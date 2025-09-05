package com.tech.vaultx.Views;

import java.math.BigDecimal;
import java.util.Scanner;

import com.tech.vaultx.Controllers.AccountController;
import com.tech.vaultx.Controllers.NetBankingController;
import com.tech.vaultx.CustomExceptions.WeakPasswordException;
import com.tech.vaultx.Models.Account;
import com.tech.vaultx.Models.NetBanking;
import com.tech.vaultx.Util.InputValidator;
import com.tech.vaultx.Util.PasswordValidator;

public class NetBankingView {
	private NetBankingController netbankingcontroller = new NetBankingController();
	private AccountController accountcontroller = new AccountController();
	
	// NetBanking Service Register View
	public void newNetBankingView(Scanner sc, Account account) {
		if(account.getNetBanking() != null)
			System.out.println("\nNote: NetBanking Service already available on this Account");
		else {
			System.out.println("\nWelcome to VaultX NetBanking Service");
			
			System.out.print("\nEnter Your Username: ");
			String username = sc.nextLine();
			
			// Netbanking entry password input
	        String password;
	        // Strong Password Validation
	        while (true) {
	        	PasswordValidator.passwordMessage();
	            System.out.print("\nGive a password for NetBanking: ");
	            password = sc.nextLine();

	            try {
	                PasswordValidator.check_Strong_Password(password);
	                System.out.println("Password accepted");
	                break;
	            } catch (WeakPasswordException e) {
	                System.out.println(e.getMessage());
	            }
	        }
	        
			String pincode;
			
			while(true) {
				System.out.print("Give a 6 digit pin for your netbanking transaction: ");
				pincode = sc.nextLine();
				
				if(InputValidator.check_Valid_Pin(pincode))
					break;
			}
			
			NetBanking netbanking = new NetBanking(username, password, pincode);
			netbankingcontroller.registerNewNetBanking(netbanking, account);
		}
	}
	
	// Login to NetBanking Service
	public void loginNetBankingView(Scanner sc, Account account, NetBanking netbanking) {
		if(netbanking == null)
			System.out.println("Note: NetBanking Service not available on this Account");
		else {
			while(true) {
				System.out.println("\nWelcome to VaultX NetBanking Service");
				
				System.out.print("\nEnter UserName: ");
				String username = sc.nextLine();
				System.out.print("Enter Password: ");
				String password = sc.nextLine();
				
				NetBanking n = new NetBanking(username, password);
				
				if(n.compareTo(netbanking) == 1) {
					break;
				} else {
					System.out.println("Warning: Please Enter Correct Password & UserName");
				}
			}
			
			int i;
			String s;
			
			NetBankingView netbankingview = new NetBankingView();
			
			do {
				System.out.print("\n1. Change NetBanking Password(press 1)"
						+ "\n2. Change NetBanking UserName(press 2)"
						+ "\n3. Change NetBanking Pincode(press 3)"
						+ "\n4. Fund Transfer(press 4)"
						+ "\n5. Check Bank Balance(press 5)"
						+ "\nEnter your choice: "); 
				i = sc.nextInt();
				sc.nextLine();
				
				switch(i) {
				case 1:
					netbankingview.changePasswordView(sc, netbanking);
					break;
				case 2:
					netbankingview.changeUserNameView(sc, netbanking);
					break;
				case 3:
					netbankingview.changePincodeView(sc, netbanking);
					break;
				case 4:
					netbankingview.fundTransferview(sc, account, netbanking);
					break;
				case 5:
					netbankingview.bankBalanceView(sc, account);
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

	// Change Netbanking Password
	public void changePasswordView(Scanner sc, NetBanking netbanking) {
		String password;
        // Strong Password Validation
        while (true) {
        	PasswordValidator.passwordMessage();
            System.out.print("\nGive a password for you account: ");
            password = sc.nextLine();

            try {
                PasswordValidator.check_Strong_Password(password);
                if(password.equals(netbanking.getUser_password()))
                	System.out.println("\nNote: Password can't same as Previous One");
                else {
                	System.out.println("Password accepted");
                	break;
                }
            } catch (WeakPasswordException e) {
                System.out.println(e.getMessage());
            }
        }
        
        netbankingcontroller.changePassword(netbanking, password);
	}
	
	// Change UserName
	public void changeUserNameView(Scanner sc, NetBanking netbanking) {
		String username;
		
		while (true) {
			System.out.print("\nEnter New UserName: ");
			username = sc.nextLine();
			if(username.equals(netbanking.getUsername()))
            	System.out.println("\nNote: UserName can't same as Previous One");
            else {
            	break;
            }
        }
		
		System.out.println("\nNote: To Change UserName you need to Change the Password also");
		String password;
		// Strong Password Validation
		while (true) {
			PasswordValidator.passwordMessage();
			System.out.print("\nGive a password for you account: ");
			password = sc.nextLine();
			
			try {
				PasswordValidator.check_Strong_Password(password);
				if(password.equals(netbanking.getUser_password()))
					System.out.println("\nNote: Password can't same as Previous One");
				else {
					System.out.println("Password accepted");
					break;
				}
			} catch (WeakPasswordException e) {
				System.out.println(e.getMessage());
			}
		}
		
		netbankingcontroller.changeUserName(netbanking, username, password);
	}
	
	// Change NetBanking PinCode
	public void changePincodeView(Scanner sc, NetBanking netbanking) {
		String pincode;
		
		while(true) {
			System.out.print("Give a new 6 digit pin for NetBanking: ");
			pincode = sc.nextLine();
			
			if(InputValidator.check_Valid_Pin(pincode)) {
				if(pincode.equals(netbanking.getPincode()))
					System.out.println("\nNote: Pincode can't same as previous");
				else
					break;
			}
		}
		netbankingcontroller.changePincode(netbanking, pincode);
	}

	// Deactivate NetBanking View
	public void deactivateNetBankingView(Scanner sc, Account account) {
		if(account.getNetBanking() == null)
			System.out.println("Note: NetBanking Service not available on this Account");
		else {
			while(true) {
				System.out.print("\nEnter UserName: ");
				String username = sc.nextLine();
				System.out.print("Enter Password: ");
				String password = sc.nextLine();
				
				if(username.equals(account.getNetBanking().getUsername()) && password.equals(account.getNetBanking().getUser_password())) {
					netbankingcontroller.deactivateNetBanking(account);
					break;
				} else {
					System.out.println("Warning: Please Enter Correct Password");
				}
			}
		}
	}
	
	// FundTransfer View
	private void fundTransferview(Scanner sc, Account account, NetBanking netbanking) {
		System.out.print("\nEnter Account No. to Fund Transfer: ");
		String acc_no = sc.nextLine();
		
		if(!acc_no.equals(account.getAccount_no())) {
			if(accountcontroller.isExistAccount(acc_no)) {
				System.out.print("\nEnter Amount: ");
				String input = sc.nextLine();
				BigDecimal amount = new BigDecimal(input);
				
				if(amount.compareTo(account.getAmount()) <= 0) {
					int count = 0;
					String pincode = "";
					while(count < 3) {
						System.out.print("\nEnter pincode for payment: ");
						pincode = sc.nextLine();
						if(pincode.equals(netbanking.getPincode()))
							break;
						else
							System.out.println("\nWarning! Wrong Password Given");
						count++;
					}
					if(count >= 3) {
						System.out.println("\nYou have exited 3 attempts. Try later!");
					}
					else {
						account.setAmount(account.getAmount().subtract(amount));
						netbankingcontroller.fundTransfer(amount, acc_no, account);
					}
				} else {
					System.out.println("Error: Can't Proceed Fund Transfer. Not Enough amount on Account!");
				}
			}
		} else {
			System.out.println("\nAccount No. can't same as Your Account No.");
		}
	}
	
	// Check Bank Balance View
	public void bankBalanceView(Scanner sc, Account account) {
		int count = 0;
		while(count < 3) {
			System.out.print("\nEnter pincode: ");
			String pincode = sc.nextLine();
			if(pincode.equals(account.getNetBanking().getPincode()))
				break;
			else
				System.out.println("\nWarning! Wrong Password Given");
			count++;
		}
		if(count >= 3) {
			System.out.println("\nYou have exited 3 attempts. Try later!");
		}
		else {
			System.out.println("\nThe Amount is: " + account.getAmount());
		}
	}
}