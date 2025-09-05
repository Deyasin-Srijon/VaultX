package com.tech.vaultx.Views;

import java.math.BigDecimal;
import java.util.Scanner;

import com.tech.vaultx.Controllers.ATMController;
import com.tech.vaultx.Models.ATM;
import com.tech.vaultx.Models.Account;
import com.tech.vaultx.Util.InputValidator;

public class ATMView {
	private ATMController atmController = new ATMController();
	
	// New ATM card Issue 
	public void newATMView(Scanner sc, Account account) {
		if(account.getAtm() != null)
			System.out.println("\nNote: ATM Card already issued for this Account");
		else {
			String pincode;
			System.out.println("\nWelcome to VaultX ATM Service");
			
			while(true) {
				System.out.print("Give a 6 digit pin for your atm card: ");
				pincode = sc.nextLine();
				
				if(InputValidator.check_Valid_Pin(pincode))
					break;
			}
			
			ATM atm = new ATM(pincode);
			atmController.issueATMCard(atm, account);
		}
	}

	// ATM card Details View 
	public void atmDetailsView(Scanner sc, Account account) {
		if(account.getAtm() == null)
			System.out.println("Note: ATM Card is not issued on this Account");
		else {
			while(true) {
				System.out.print("\nEnter Your ATM PinCode: ");
				String pincode = sc.nextLine();
				
				if(pincode.equals(account.getAtm().getPincode())) {
					System.out.println("\nATM Card NO: " + account.getAtm().getCard_no());
					System.out.println("CVV: " + account.getAtm().getCvv());
					System.out.println("Expiry Date: " + account.getAtm().getExp_date());
					break;
				} else {
					System.out.println("Warning: Please Enter Correct Password");
				}
			}
		}
	}

	// Block a ATM card
	public void blockCardView(Scanner sc, Account account) {
		if(account.getAtm() == null)
			System.out.println("Note: ATM Card is not issued on this Account");
		else {
			while(true) {
				System.out.print("\nEnter Your ATM PinCode: ");
				String pincode = sc.nextLine();
				
				if(pincode.equals(account.getAtm().getPincode())) {
					atmController.blockATMCard(account);
					break;
				} else {
					System.out.println("Warning: Please Enter Correct Password");
				}
			}
		}
	}

	// ATM functionality view
	public void atmFuncView(Scanner sc, Account account) {
		if(account.getAtm() == null)
			System.out.println("Note: ATM Card is not issued on this Account");
		else {
			System.out.println("\nWelcome to VaultX ATM Service");
			int i;
			String s;
			
			ATMView atmView = new ATMView();
			
			do {
				System.out.print("\n1. Change Pin Code(press 1)"
						+ "\n2. WithDraw Cash(press 2)"
						+ "\n3. Deposit Cash(press 3)"
						+ "\nEnter your choice: "); 
				i = sc.nextInt();
				sc.nextLine();
				
				switch(i) {
				case 1:
					atmView.changeATMPinView(sc, account.getAtm());
					break;
				case 2:
					atmView.cashWithDrawView(sc, account);
					break;
				case 3:
					atmView.cashDepositView(sc, account);
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

	// ATM PINCode Change View
	public void changeATMPinView(Scanner sc, ATM atm) {
		String pincode;
		
		while(true) {
			System.out.print("Give a 6 digit pin for your atm card: ");
			pincode = sc.nextLine();
			
			if(InputValidator.check_Valid_Pin(pincode)) {
				if(pincode.equals(atm.getPincode()))
					System.out.println("\nNote: Pincode can't same as previous");
				else
					break;
			}
		}
		atmController.changePincode(atm, pincode);
	}
	
	// WithDraw Cash View
	public void cashWithDrawView(Scanner sc, Account account) {
		System.out.print("Enter your Card No: ");
		String cardno = sc.nextLine();
		System.out.print("Enter CVV: ");
		String cvv = sc.nextLine();
		System.out.print("Enter Expiry Date: ");
		String expdate = sc.nextLine();
		
		ATM atm = new ATM(cardno, cvv, expdate);
		
		while(true) {
			if(atm.compareTo(account.getAtm()) == 0) {
				System.out.println("\nWarning! Please enter correct Card Details");
			}
			else {
				System.out.print("\nEnter Amount: ");
				String input = sc.nextLine();
				BigDecimal amount = new BigDecimal(input);
				
				if(amount.compareTo(account.getAmount()) <= 0) {
					int count = 0;
					String pincode = "";
					while(count < 3) {
						System.out.print("\nEnter pincode for withdraw cash: ");
						pincode = sc.nextLine();
						if(pincode.equals(account.getAtm().getPincode()))
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
						atmController.cashWithDrawService(amount, account.getAccount_no());
					}
				} else {
					System.out.println("Error: Can't WithDraw Cash. Not Enough amount on Account!");
				}
				break;
			}
		}
	}
	
	// Deposit Cash View
	public void cashDepositView(Scanner sc, Account account) {
		System.out.print("Enter your Card No: ");
		String cardno = sc.nextLine();
		System.out.print("Enter CVV: ");
		String cvv = sc.nextLine();
		System.out.print("Enter Expiry Date: ");
		String expdate = sc.nextLine();
			
		ATM atm = new ATM(cardno, cvv, expdate);
			
		while(true) {
			if(atm.compareTo(account.getAtm()) == 0) {
				System.out.println("\nWarning! Please enter correct Card Details");
			}
			else {
				System.out.print("\nEnter Amount: ");
				String input = sc.nextLine();
				BigDecimal amount = new BigDecimal(input);
					
				int count = 0;
				String pincode = "";
				while(count < 3) {
					System.out.print("\nEnter pincode for deposit cash: ");
					pincode = sc.nextLine();
					if(pincode.equals(account.getAtm().getPincode()))
						break;
					else
						System.out.println("\nWarning! Wrong Password Given");
					count++;
				}
				if(count >= 3) {
					System.out.println("\nYou have exited 3 attempts. Try later!");
				}
				else {
					account.setAmount(account.getAmount().add(amount));
					atmController.cashDepositService(amount, account.getAccount_no());
				}
				break;
			}
		}
	}
}
