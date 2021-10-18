/* Name: Nic Mowll
 * Class: CodertoCraftsman
 * Assignment: GradeBook Part 1
 * Date: 10/18/2021
 */
package GradeBook.Classes;

import GradeBook.Interface.*;
import java.util.Scanner;

public class Print { //methods for strictly console based print statements. Mostly for looks
	
	public static void Menu() { //main menu
		System.out.print("\nGradeBook Menu\n");
		Line();
		System.out.print("\n  1: Add Grades");
		System.out.print("\n  2: Remove Grades");
		System.out.print("\n  3: Print Grades");
		System.out.print("\n  4: Print Average");
		System.out.print("\n  5: Print Highest/Lowest Score(s)");
		System.out.print("\n  6: Print Quiz Question Average");
		System.out.print("\n  7: Print Discussion Associated Readings");
		System.out.print("\n  8: Print Program Concepts\n");
		Line();
		System.out.print("\nPlease specify which action you want to do or enter '0' to quit.");
	}
	
	public static void exit() { //exit program message
		clearScreen();
		System.out.print("\nThank you for using the GradeBook\n");
		Line();
		System.out.print("\nGoodbye!\n");
	}
	
	public static void Enter() { //print statement where user can enter input
		System.out.print("\n\n\nEnter Answer Here: ");
	}
	
	public static void Welcome() { //welcome message
		clearScreen();
		System.out.print("\nWelcome to the GradeBook\n");
		Line();
		System.out.print("\nSpecify # of grades in GradeBook (max: 20). ");	
		Enter();
	}
	
	public static void WelcomeRedo() { //welcome message when first input is invalid
		clearScreen();
		System.out.print("\nWelcome to the GradeBook\n");
		Line();
		System.out.print("\nError: Entry was not a number or more than 20, please retry.\n");
		System.out.print("\nSpecify # of grades in GradeBook (max: 20). ");	
		Enter();
	}
	
	public static void Line() { //prints a gui line
		System.out.print("\n==================================================================\n");
	}
	
	public static void clearScreen() { //clears screen
		for(int i = 0; i<50; i++) {
			System.out.print("\n");
		}
	}
	
	public static void addGrade() { //menu for adding grade
		clearScreen();
		System.out.print("Add Grade\n");
		Line();
		System.out.print("\n  1: Quiz");
		System.out.print("\n  2: Discussion");
		System.out.print("\n  3: Program\n");
		Line();
		System.out.print("\nWhat type of grade would you like to add?");
	}
	
	public static void removeGrade() { //menu for removing grade
		clearScreen();
		System.out.print("Remove Grade\n");
		Line();
		System.out.print("\nWhat is the name of the grade you wish to remove? (Must match exactly)");
	}
	
	public static void setVar(String varName, String assignment) { //menu for setting a variable for an assignment object
		clearScreen();
		System.out.print("Set Value for the " + varName + " for your " + assignment + "\n");
		Line();
	}
	
	public static String finishReEnter() { //prints at end of action to ask user if done and gives option to re-enter
		Scanner sc = new Scanner(System.in);
		System.out.print("\nEnter 'go' if you wish to proceed or 're-enter' to delete another: ");
		String input = sc.nextLine();
		
		if(input.equals("go") ^ input.equals("re-enter")) {
			return input;
		}
		else {
			return " ";
		}
	}
	
	public static String finishGo() { //prints at end of action to hold display until user enters go
		Scanner sc = new Scanner(System.in);
		System.out.print("\nEnter 'go' if you wish to proceed: ");
		String input = sc.nextLine();
		
		if(input.equals("go")) {
			return input;
		}
		else {
			return " ";
		}
	}
	
	public static void printAssignment(AssignmentInterface assignment) { //prints an assignment and its variables
		if(assignment instanceof Quiz) {
			System.out.print("\nQuiz:\n\n");
		}
		else if(assignment instanceof Program) {
			System.out.print("\nProgram:\n\n");
		}
		else {
			System.out.print("\nDiscussion:\n\n");
		}
		System.out.print(assignment.toString());
		Line();
	}
	
}
