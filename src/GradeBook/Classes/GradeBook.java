/* Name: Nic Mowll
 * Class: CodertoCraftsman
 * Assignment: GradeBook Part 1
 * Date: 10/18/2021
 */
package GradeBook.Classes;

import GradeBook.Exception.*;
import GradeBook.Classes.*;
import GradeBook.Interface.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GradeBook {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Print.Welcome();
		
		int size = -1;
		int arrayCounter = 0; //keeps track of position in array when adding/removing grades
		
		//retieves size of array
		while (size == -1) {
			size = getSize();
		}

		//initializes the array and sets all values to null
		AssignmentInterface[] array = new AssignmentInterface[size];
		for(int i = 0; i < array.length; i++) {
			array[i] = null;
		}
		
		boolean done = false; //used in keeping display up until user wants to go back to the menu
		String finish; //       ""      ""         ""           ""             ""            ""

		//uses a switchcase in a while loop to keep program active and allow user to pick actions
		int input = -1;
		while(input != 0) {
			Print.clearScreen(); 
			System.out.print("\nCreated GradeBook with " + size + " Grade Spaces!\n");
			Print.Menu(); //prints menu
			Print.Enter();
			
			input = -1;
			while (input == -1) {
				input = getInput(); //call getInput method which catches potential exceptions
			}
			
			switch(input) {
			case 0: //quits program
				Print.exit(); //exits
				break;
			case 1: //add grades
				array = addGrade(array, arrayCounter); //calls add grade function
				arrayCounter++;
				finish = " ";
				while(finish.equals(" ")) {
					finish = Print.finishGo(); //method finishGo keeps the display up and allows user to enter go to go back to menu
				}
				if(finish.equals("go")) {
					done = true;
				}
				break;
			case 2: //remove grade
				done = false;
				while (done == false) {
					array = removeGrade(array);
					finish = " ";
					while(finish.equals(" ")) {
						finish = Print.finishReEnter(); //allows user to re-enter if they incorrectly entered the wrong assignment name
					}
					if(finish.equals("go")) {
						done = true;
						boolean incremented = false; //this block of code makes sure the counter is in the right place after removing a grade
						for(int i = 0; i < array.length; i++) {
							if(incremented == false) {
								if(array[i] == null) {
									arrayCounter = i;
									incremented = true;
								}
							}
						}
					}
				}
				break;
			case 3: //print grades
				printGrades(array); //calls method to print grades
				finish = " ";
				while(finish.equals(" ")) {
					finish = Print.finishGo();
				}
				if(finish.equals("go")) {
					done = true;
				}
				break;
			case 4: //print average
				printAverage(array); //calls method to print grade avg
				finish = " ";
				while(finish.equals(" ")) {
					finish = Print.finishGo();
				}
				if(finish.equals("go")) {
					done = true;
				}
				break;
			case 5: //print highest/lowest
				printHighLow(array); //calls method to print highest and lowest grade scores
				finish = " ";
				while(finish.equals(" ")) {
					finish = Print.finishGo();
				}
				if(finish.equals("go")) {
					done = true;
				}
				break;
			case 6: //print quiz question average
				printQuizAvg(array); //calls method to print quiz question avg
				finish = " ";
				while(finish.equals(" ")) {
					finish = Print.finishGo();
				}
				if(finish.equals("go")) {
					done = true;
				}
				break;
			case 7: //print discussion associated readings
				printReadings(array); //calls method that prints all readings for the discussions
				finish = " ";
				while(finish.equals(" ")) {
					finish = Print.finishGo();
				}
				if(finish.equals("go")) {
					done = true;
				}
				break;
			case 8: //print program concepts
				printConcepts(array); //calls method that prints all concepts for Programs
				finish = " ";
				while(finish.equals(" ")) {
					finish = Print.finishGo();
				}
				if(finish.equals("go")) {
					done = true;
				}
				break;
			}
		}
	}
	
	
	//returns true if array is empty
	public static boolean isEmpty(AssignmentInterface[] array) {
		int counter = 0;
		for(int i = 0; i < array.length; i++) {
			if(array[i] != null) {
				counter++;
			}
		}
		if(counter == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	//returns true if array is full
	public static boolean isFull(AssignmentInterface[] array) {
		int counter = 0;
		for(int i = 0; i < array.length; i++) {
			if(array[i] != null) {
				counter++;
			}
		}
		if(counter == array.length) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	//gets the size for the array from user, in method to not clutter main method, catches errors
	public static int getSize() {
		Scanner sc = new Scanner(System.in);
		int size = -1; 
		
		try {
			size = sc.nextInt();
			if(size > 20 ^ size < 1) {
				size = -1;
				Print.WelcomeRedo();
			}
		}
		catch (InputMismatchException e){
			Print.WelcomeRedo();
		}
		return size;
	}
	
	
	//gets input from main menu from user, catches errors
	public static int getInput() {
		Scanner sc = new Scanner(System.in);
		
		try {
			int input = sc.nextInt();
			if(input < 0 ^ input > 8) {
				Print.clearScreen();
				Print.Menu();
				System.out.print("\n\nError: Entry must be number from 0-8.");
				Print.Enter();
				return -1;
			}
			else {
				return input;
			}
		}
		catch (InputMismatchException e) {
			Print.clearScreen();
			Print.Menu();
			System.out.print("\n\nError: Entry must be number from 0-6.");
			Print.Enter();
			return -1;
		}
	}
	
	
	//prompts user to enter a score for the assignment, catches errors
	public static double getScore(String assignment) {
		Scanner sc = new Scanner(System.in);
		double score;
		
		try {
			 score = sc.nextDouble();
			 if(score < 0 ^ score > 100) {
				 Print.clearScreen();
				 Print.setVar("Score (0-100)", assignment);
				 System.out.print("\nError: Incorrect Entry, must be between 0 and 100.");
				 Print.Enter();
				 return -1;
			 }
			 else {
				 return score;
			 }
		}
		catch (InputMismatchException e){
			Print.clearScreen();
			Print.setVar("Score (0-100)", assignment);
			System.out.print("\nError: Incorrect Entry, must be a valid number.");
			Print.Enter();
			return -1;
		}
		
		
	}
	
	
	//calculates Letter grade of assignment based on score
	public static char getLetter(double score) {
		if(score >= 90) {
			return 'A';
		}
		else if(score >= 80) {
			return 'B';
		}
		else if(score >= 70) {
			return 'C';
		}
		else if(score >= 60) {
			return 'D';
		}
		else {
			return 'F';
		}
	}
	
	
	//prompts user to enter name of assignment
	public static String getName(String assignment) {
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		return name;
	}
	
	
	//prompts user to enter due date for assignment
	public static String getDueDate(String assignment) {
		Scanner sc = new Scanner(System.in);
		String dueDate = sc.nextLine();
		char testChar;
		int digitCounter = 0;
		
		if(dueDate.length() == 10) {
			if(dueDate.charAt(2) == '/' && dueDate.charAt(5) == '/') {
				for(int i = 0; i < 10; i++) {
					if(i != 2 && i != 5) {
						testChar = dueDate.charAt(i);
						if(Character.isDigit(testChar)) {
							digitCounter++;
						}
					}
				}
				if(digitCounter == 8) {
					return dueDate;
				}
			}
		}
		Print.clearScreen();
		Print.setVar("Due Date (mm/dd/yyyy)", assignment);
		System.out.print("\nError: Entry must follow date format mm/dd/yyyy.");
		Print.Enter();
		return " ";
	}
	
	
	//prompts user to enter number of questions for assignment, catches errors
	public static int getNumQuestions(String assignment) {
		Scanner sc = new Scanner(System.in);
		int numQ; 
		
		try {
			numQ = sc.nextInt();
			if(numQ > 0) {
				return numQ;
			}
			else {
				Print.clearScreen();
				Print.setVar("Due Date", assignment);
				System.out.print("\nError: Entry must be a number greater than 0.");
				Print.Enter();
				return -1;
			}
		}
		catch (InputMismatchException e){
			Print.clearScreen();
			Print.setVar("Due Date", assignment);
			System.out.print("\nError: Entry must be a number greater than 0.");
			Print.Enter();
			return -1;
		}
	}
	
	
	//prompts user to enter reading for assignment
	public static String getReading(String assignment) {
		Print.clearScreen();
		Print.setVar("Reading Material", assignment);
		Print.Enter();
		
		Scanner sc = new Scanner(System.in);
		String reading = sc.nextLine();
		
		return reading;
	}
	
	
	//prompts user to enter concept for assignment
	public static String getConcept(String assignment) {
		Print.clearScreen();
		Print.setVar("Concept", assignment);
		Print.Enter();
		
		Scanner sc = new Scanner(System.in);
		String concept = sc.nextLine();
		
		return concept;
	}
	
	
	//adds a grade to grade book
	public static AssignmentInterface[] addGrade(AssignmentInterface[] array, int arrayCounter){
		//if full, throw exception
		try {
			if(isFull(array)) {
				throw new GradeBookFullException("Grade Book is full!");
			}
		}
		catch (GradeBookFullException e){
			System.out.print("\n\nGradeBookFullException: Grade Book full, cannot add grade.\n\n");
			return array;
		}
		
		//prints menu
		Scanner sc = new Scanner(System.in);
		Print.clearScreen();
		Print.addGrade();
		Print.Enter();
		
		int gradeNum = 0;
		boolean inBound = false;
		
		//catches errors in input
		do {
			try {
				gradeNum = sc.nextInt();
				if(gradeNum < 1 ^ gradeNum > 3) {
					Print.clearScreen();
					Print.addGrade();
					System.out.print("\n\nError: Invalid entry, must be from 1-3.\n");
					Print.Enter();
				}
				else {
					inBound = true;
				}
			}
			catch (InputMismatchException e) {
				Print.clearScreen();
				Print.addGrade();
				System.out.print("\n\nError: Invalid entry, must be a number.\n");
				Print.Enter();
				sc.reset();
				sc.next();
			}
		} while (inBound == false);
		
		//sets variables depending on type of AssignmentInterface
		if(gradeNum == 1) { //Quiz
			Quiz quiz = new Quiz();
			
			double score = -1;
			Print.clearScreen();
			Print.setVar("Score (0-100)", "Quiz");
			Print.Enter();
			while (score == -1) { 
				score = getScore("Quiz");
			}
			quiz.setScore(score);
			
			quiz.setLetter(getLetter(quiz.getScore()));
			
			Print.clearScreen();
			Print.setVar("Name", "Quiz");
			Print.Enter();
			String name = getName("Quiz");
			quiz.setName(name);
			
			String dueDate = " ";
			Print.clearScreen();
			Print.setVar("Due Date (mm/dd/yyyy)", "Quiz");
			Print.Enter();
			while (dueDate.equals(" ")) {
				dueDate = getDueDate("Quiz");
			}
			quiz.setDueDate(dueDate);
			
			int numQuestions = -1;
			Print.clearScreen();
			Print.setVar("Number of Questions", "Quiz");
			Print.Enter();
			while (numQuestions == -1) {
				numQuestions = getNumQuestions("Quiz");
			}
			quiz.setNumQuestions(numQuestions);
			
			Print.clearScreen();
			Print.printAssignment(quiz);

			array[arrayCounter] = quiz;
		}
		else if(gradeNum == 2) { //Discussion
			Discussion discussion = new Discussion();
			
			double score = -1;
			Print.clearScreen();
			Print.setVar("Score (0-100)", "Discussion");
			Print.Enter();
			while (score == -1) { 
				score = getScore("Discussion");
			}
			discussion.setScore(score);
			
			discussion.setLetter(getLetter(discussion.getScore()));
			
			Print.clearScreen();
			Print.setVar("Name", "Discussion");
			Print.Enter();
			String name = getName("Discussion");
			discussion.setName(name);
			
			String dueDate = " ";
			Print.clearScreen();
			Print.setVar("Due Date (mm/dd/yyyy)", "Discussion");
			Print.Enter();
			while (dueDate.equals(" ")) {
				dueDate = getDueDate("Discussion");
			}
			discussion.setDueDate(dueDate);
			
			Print.clearScreen();
			Print.setVar("Reading Material", "Discussion");
			Print.Enter();
			String reading = getReading("Discussion");
			discussion.setReading(reading);
			
			Print.clearScreen();
			Print.printAssignment(discussion);
			
			array[arrayCounter] = discussion;
		}
		else { //Program
			Program program = new Program();
			
			double score = -1;
			Print.clearScreen();
			Print.setVar("Score (0-100)", "Program");
			Print.Enter();
			while (score == -1) { 
				score = getScore("Program");
			}
			program.setScore(score);
			
			program.setLetter(getLetter(program.getScore()));
			
			Print.clearScreen();
			Print.setVar("Name", "Program");
			Print.Enter();
			String name = getName("Program");
			program.setName(name);
			
			String dueDate = " ";
			Print.clearScreen();
			Print.setVar("Due Date (mm/dd/yyyy)", "Program");
			Print.Enter();
			while (dueDate.equals(" ")) {
				dueDate = getDueDate("Program");
			}
			program.setDueDate(dueDate);
			
			Print.clearScreen();
			Print.setVar("Concept", "Program");
			Print.Enter();
			String concept = getConcept("Program");
			program.setConcept(concept);
			
			Print.clearScreen();
			Print.printAssignment(program);
			
			array[arrayCounter] = program;
		}
		return array;
	}
	
	
	//removes a designated grade from GradeBook
	public static AssignmentInterface[] removeGrade(AssignmentInterface[] array) {
		//if empty, throw exception
		try { 
			if(isEmpty(array)) {
				throw new GradeBookEmptyException("Grade Book empty");
			}
		}
		catch (GradeBookEmptyException e){
			System.out.print("\n\nGradeBookEmptyException: Grade Book is empty, cannot remove grade.\n\n");
			return array;
		}
		
		Scanner sc = new Scanner(System.in);
		
		//prints menu
		Print.removeGrade();
		Print.Enter();
		
		String name = sc.nextLine();
		
		//looks for assignment and deletes it based on two cases:
		//							1. assignment is at end
		//							2. assignment is in middle or at start
		int size = array.length;
		try {
			boolean found = false;
			for(int i = 0; i < size; i++) {
				if(array[i] != null) {
					if(array[i].getName().equals(name) && found == false) {
						found = true;
						if(i == size - 1) {
							array[i] = null;
						}
						else if (array[i + 1] == null) {
							array[i] = null;
						}
						else {
							for(int j = i; j < size; j++) {
								if(j != size - 1 && array[j + 1] != null) {
									array[j] = array[j + 1];
								}
								if(j != size - 1 && array[j+1] != null) {
									if(array[j + 1].equals(array[j])) {
										array[j + 1] = null;
									}
								}
							}
						}
					}
				}
			}
			//if not found, throws exception
			if(found == false) {
				throw new InvalidGradeException("Grade not found.");
			}
		}
		catch (InvalidGradeException e) {
			System.out.print("\n\nInvalidGradeException: Grade not found.\n\n");
			return array;
		}
		System.out.print("\n\nAssignment deleted!\n");
		return array;
	}
	
	
	//prints all grades and their attributes
	public static void printGrades(AssignmentInterface[] array) {
		//if empty, throw exception
		try { 
			if(isEmpty(array)) {
				throw new GradeBookEmptyException("Grade Book empty");
			}
		}
		catch (GradeBookEmptyException e){
			System.out.print("\n\nGradeBookEmptyException: Grade Book is empty, no grades to print.\n\n");
			return;
		}
		//prints  menu
		Print.clearScreen();
		System.out.print("Printed Grades\n");
		Print.Line();
		for(int i = 0; i < array.length; i++) {
			if(array[i] != null) {
				Print.printAssignment(array[i]);
			
			}
		}
	}
	
	
	//prints average of all grades
	public static void printAverage(AssignmentInterface[] array) {
		//if empty, throw exception
		try { 
			if(isEmpty(array)) {
				throw new GradeBookEmptyException("Grade Book empty");
			}
		}
		catch (GradeBookEmptyException e){
			System.out.print("\n\nGradeBookEmptyException: Grade Book is empty, no averages to take.\n\n");
			return;
		}
		
		//prints menu
		Print.clearScreen();
		double counter = 0;
		double sum = 0;
		
		//calculates avg
		for(int i=0; i < array.length; i++) {
			if(array[i] != null) {
				counter++;
				sum += array[i].getScore();
			}
		}
		
		double avg = sum/counter;
		int round = (int)(avg * 100);
		avg = (double)(round / 100.00);
		
		System.out.print("Printed Average of All Assignment Scores\n");
		Print.Line();
		System.out.print("\nAverage: " + avg + "\n");
	}
	
	
	//prints highest and lowest grades
	public static void printHighLow(AssignmentInterface[] array) {
		//if empty, throw exception
		try { 
			if(isEmpty(array)) {
				throw new GradeBookEmptyException("Grade Book empty");
			}
		}
		catch (GradeBookEmptyException e){
			System.out.print("\n\nGradeBookEmptyException: Grade Book is empty, cannot retrieve any scores.\n\n");
			return;
		}
		
		//prints menu
		Print.clearScreen();
		double lowest = 100;
		double highest = 0;
		int lowestIndex = 0;
		int highestIndex = 0;
		
		//locates highest and lowest scores
		for(int i = 0; i < array.length; i++) {
			if(array[i] != null) {
				if(array[i].getScore() < lowest) {
					lowestIndex = i;
					lowest = array[i].getScore();
				}
				if(array[i].getScore() > highest) {
					highestIndex = i;
					highest = array[i].getScore();
				}
			}
		}
		
		System.out.print("Printed Assignments with Highest and Lowest Score\n");
		Print.Line();
		System.out.print("\nHighest Score:\n\nName: " + array[highestIndex].getName());
		System.out.print("\n\nScore: " + array[highestIndex].getScore() + "\n");
		Print.Line();
		System.out.print("\nLowest Score:\n\nName: " + array[lowestIndex].getName());
		System.out.print("\n\nScore: " + array[lowestIndex].getScore() + "\n");
		Print.Line();
		
	}
	
	
	//prints quiz average
	public static void printQuizAvg(AssignmentInterface[] array) {
		//if empty, throw exception
		try { 
			if(isEmpty(array)) {
				throw new GradeBookEmptyException("Grade Book empty");
			}
		}
		catch (GradeBookEmptyException e){
			System.out.print("\n\nGradeBookEmptyException: Grade Book is empty, no average to take.\n\n");
			return;
		}
		
		//prints menu
		Print.clearScreen();
		double counter = 0;
		double sum = 0;
		
		//locates quizzes
		for(int i=0; i < array.length; i++) {
			if(array[i] != null) {
				if(array[i] instanceof Quiz) {
					counter++;
					sum += array[i].getScore();
				}
			}
		}
		
		//error catcher if there are no quizzes
		if(counter == 0) {
			System.out.print("\n\nThere are no quiz grades.\n");
			Print.Line();
			System.out.print("\n");
			return;
		}
		
		//calculates avg
		double avg = sum/counter;
		int round = (int)(avg * 100);
		avg = (double)(round / 100.00);
		
		System.out.print("Printed Average of All Quiz Scores\n");
		Print.Line();
		System.out.print("\nAverage: " + avg + "\n");
		
	}
	
	
	//prints all readings for discussions
	public static void printReadings(AssignmentInterface[] array) {
		//if empty, throw exception
		try { 
			if(isEmpty(array)) {
				throw new GradeBookEmptyException("Grade Book empty");
			}
		}
		catch (GradeBookEmptyException e){
			System.out.print("\n\nGradeBookEmptyException: Grade Book is empty, no Readings available.\n\n");
			return;
		}
		
		//prints menu
		Print.clearScreen();
		System.out.print("Printed Discussion Readings\n");
		Print.Line();
		int numLabel = 1;
		
		//locates discussions
		for(int i = 0; i < array.length; i++) {
			if(array[i] instanceof Discussion) {
				System.out.print("\n" + numLabel + ": " + array[i].printCustom());
				numLabel++;
			}
		}
		//error catcher if no discussions
		if(numLabel == 1) {
			System.out.print("\nThere are no Readings to print.\n");
		}
		else {
			System.out.print("\n");
		}
		Print.Line();
	}
	
	
	//prints all concepts for programs
	public static void printConcepts(AssignmentInterface[] array) {
		//if empty, throw exception
		try { 
			if(isEmpty(array)) {
				throw new GradeBookEmptyException("Grade Book empty");
			}
		}
		catch (GradeBookEmptyException e){
			System.out.print("\n\nGradeBookEmptyException: Grade Book is empty, no Concepts available.\n\n");
			return;
		}
		
		//prints menu
		Print.clearScreen();
		System.out.print("Printed Program Concepts\n");
		Print.Line();
		int counter = 1;
		
		//locates programs
		for(int i = 0; i < array.length; i++) {
			if(array[i] instanceof Program) {
				System.out.print("\n" + counter + ": " + array[i].printCustom());
				counter++;
			}
		}
		//error catcher if no programs
		if(counter == 1) {
			System.out.print("\nThere are no Concepts to print.\n");
		}
		else {
			System.out.print("\n");
		}
		Print.Line();
	}
}
