package com.sudhanshu.jobportal;

import java.util.Scanner;

public class MainOperation
{ 
	    static Scanner sc = new Scanner(System.in);
	    
	    public static void main(String[] args) {
	        // Call the main method that displays the menu
	        mianOps();
	    }

	    public static void mianOps()
	    
	    {
	        while (true) 
	        {
	            System.out.println("Press 1.New Registration Details\nPress 2. Add Education Deatils"
	                    + "\nPress 3. Provide Your Experience Details\nPress 4.  Provide Feedback To US\n"
	                    + "Press 5 for quit");
	            int input = sc.nextInt();
	            switch (input)
	            {
	                
	                case 1:
	                    AllOperation.registerOperations(); //studentOperations
	                    System.out.println("=================================");
	                    break;

	                case 2:
	                    AllOperation.EducationOperations(); //EducationOperations
	                    System.out.println("=================================");
	                    break;
	                case 3:
	                    AllOperation.experienceOperations(); 
	                    System.out.println("=================================");
	                    break;
	                case 4:
	                    AllOperation.feedbackOperation(); 
	                    
	                    break;
	                case 5:
	                    System.exit(0);
	                    break;

	                default:
	                    System.out.println("invalid input");

	            }

	        }
	    }
}
