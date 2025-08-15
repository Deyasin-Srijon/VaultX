package com.tech.vaultx;

import java.util.Scanner;
import com.tech.vaultx.Views.*;

public class App {

	public static void main(String[] args) {
		System.out.println("Welcome to VaultX Banking App");
		
		int i;
		String s;
		
		Scanner sc = new Scanner(System.in);
		
		do {
			System.out.print("\nWho are you logging in as?"
					+ "\n1.User(Press 1) "
					+ "\n2.Admin(Press 2) "
					+ "\nEnter your choice: ");
			i = sc.nextInt();
			sc.nextLine();
			
			switch(i) {
				case 1:
					UserView.userMenuView(sc);
					break;
				default:
					System.out.println("Sorry! Wrong choice given");
					break;
			}
			
			System.out.print("Do you want to proceed again?(y/n): ");
			s = sc.nextLine();
		}while(s.equalsIgnoreCase("y"));
		
		sc.close();
	}

}
