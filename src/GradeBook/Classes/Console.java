/* Name: Nic Mowll
 * Class: CodertoCraftsman
 * Assignment: GradeBook Part 2
 * Date: 11/29/2021
 */
package GradeBook.Classes;

import GradeBook.Interface.*;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Console { //methods for strictly console based print statements. Mostly for looks
	public static void mySQLSearchMenu() { //menu for mySQLSearch option
		clearScreen();
		System.out.print("MySQL Search\n");
		Line();
		System.out.print("\n  1: All Quizzes"
					   + "\n  2: All Programs"
					   + "\n  3: All Discussions"
					   + "\n  4: All Grades within a Certain Score Range"
					   + "\n  5: All Grades within a Certain Due Date Range"
					   + "\n  6: All Grades with an Even Score\n");
		Line();
		System.out.print("\nPlease specify what grades you would like to search for. (Pick a number)");
	}
	
	public static void readFromFileMenu() { //menu for read from file option
		clearScreen();
		System.out.print("Read From File\n");
		Line();
		System.out.print("\nWhat is the exact name of the file you wish to read from?\n");
	}
	
	public static void printToFileMenu(String fileName) { //menu for print to file option
		clearScreen();
		System.out.print("\nSuccessfully printed assignments to output file: " + fileName + "\n");
		Line();
	}
	
	public static Date getParsedDate(InputStream inputStream) { //gets a parsed date from console input
		Scanner sc = new Scanner(inputStream);
		SimpleDateFormat ft = new SimpleDateFormat("MM-dd-yyyy");
		
		Enter();
		String input = sc.nextLine();
		Date date = new Date();
		try {date = ft.parse(input);}
		catch(ParseException e) {
			System.out.print("\nError: Entry was in incorrect format, try again.");
			date = getParsedDate(inputStream);
		}
		return date;
	}
	
	public static String getString(InputStream inputStream) { //gets a String from console input
		Scanner sc = new Scanner(inputStream);
		
		Enter();
		String input = sc.nextLine();
		return input;
	}
	
	public static double getDoubleWithBounds(InputStream inputStream, double low, double high) { //gets a double with bounds from console input
		Scanner sc = new Scanner(inputStream);
		
		Enter();
		double input;
		try {
			input = sc.nextDouble();
			if(input <= high && input >= low) return input;
			else {
				System.out.print("\nError: Entry was out of bounds, try again.");
				input = getDoubleWithBounds(inputStream, low, high);
				return input;
			}
		}
		catch(InputMismatchException e) {
			System.out.print("\nError: Entry was not a number, try again.");
			sc.next();
			input = getDoubleWithBounds(inputStream, low, high);
			return input;
		}
	}
	
	public static int getIntWithBounds(InputStream inputStream, int low, int high) { //gets an integer with bounds from console input
		Scanner sc = new Scanner(inputStream);
		
		Enter();
		int input;
		try {
			input = sc.nextInt();
			if(input <= high && input >= low) return input;
			else {
				System.out.print("\nError: Entry was out of bounds, try again.");
				input = getIntWithBounds(inputStream, low, high);
				return input;
			}
		}
		catch(InputMismatchException e) {
			System.out.print("\nError: Entry was not an Integer, try again.");
			sc.next();
			input = getIntWithBounds(inputStream, low, high);
			return input;
		}
	}
	
	public static void Menu() { //main menu
		System.out.print("\nGradeBook Menu\n");
		Line();
		System.out.print("\n  1: Add Grades");
		System.out.print("\n  2: Remove Grades");
		System.out.print("\n  3: Print Grades");
		System.out.print("\n  4: Print to File");
		System.out.print("\n  5: Read from File");
		System.out.print("\n  6: To MySQL");
		System.out.print("\n  7: From MySQL");
		System.out.print("\n  8: MySQL Search\n");
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
		System.out.print("\nWhat type of grade would you like to add? (Enter number)");
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
	
	public static void printGradesMenu() { //menu for print grades sort option
		clearScreen();
		System.out.print("Print Grades\n");
		System.out.print("\nHow would you like the list to be sorted?\n");
		Line();
		System.out.print("\n  1: Score (Least to Greatest)");
		System.out.print("\n  2: Letter (Best to Worst)");
		System.out.print("\n  3: Name (Alphabetical Order)");
		System.out.print("\n  4: Due Date (Oldest to Newest)\n");
		Line();
		
	}
	
	public static void finishGo(InputStream inputStream) { //prints at end of action to hold display until user enters go
		Scanner sc = new Scanner(inputStream);
		
		System.out.print("\nEnter 'go' if you wish to proceed: ");
		
		if((sc.nextLine()).equals("go")) {
			return;
		}
		else {
			System.out.print("\n\nError: Input was not \"go\". Please retry.\n");
			finishGo(inputStream);
			return;
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
