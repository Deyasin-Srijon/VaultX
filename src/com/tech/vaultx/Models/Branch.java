package com.tech.vaultx.Models;

public class Branch {
	private static String[] branchNames = {
		    "Downtown Central Branch",
		    "Greenwood Park Branch",
		    "Riverside Branch",
		    "Hilltop Branch",
		    "Lakeside Branch",
		    "Silver Oak Branch",
		    "Maple Street Branch",
		    "Sunset Valley Branch",
		    "Grand Plaza Branch",
		    "Heritage Square Branch"
		};
	
	public static String getBranchName(int n) {
		return branchNames[n];
	}
	
	public static void listOfBranch() {
		System.out.println("\nChoose Branch from these Bank branches: ");
        for (int i = 0; i < branchNames.length; i++) {
            System.out.print("\n" + (i+1) + ". " + branchNames[i] + "(Press " + (i+1) + ")");
        }
	}
}
