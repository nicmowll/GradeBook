/* Name: Nic Mowll
 * Class: CodertoCraftsman
 * Assignment: GradeBook Part 2
 * Date: 11/29/2021
 */
package GradeBook.Classes;

import GradeBook.Exception.*;
import GradeBook.Classes.*;
import GradeBook.Interface.*;
import GradeBook.IO.*;
import GradeBook.DB.GradeBookDB;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Comparator;

public class GradeBook {
	public static void main(String[] args) throws ParseException {
		//initializes the list and sets all values to null
		ArrayList<AssignmentInterface> list = new ArrayList<AssignmentInterface>();
		boolean welcome = true;

		//uses a switchcase in a while loop to keep program active and allow user to pick actions
		int input = -1;
		while(input != 0) {
			Console.clearScreen();
			if(welcome) {
				Console.Welcome();
				welcome = false;
			}
			Console.Menu(); //prints menu
			
			input = Console.getIntWithBounds(System.in, 0, 8);
			
			switch(input) {
			case 0: //quits program
				Console.exit(); //exits
				break;
			case 1: //add grades
				list = addGrade(System.in, list); //calls add grade function
				Console.finishGo(System.in);
				break;
			case 2: //remove grade
				list = removeGrade(list);
				Console.finishGo(System.in);
				break;
			case 3: //print grades
				printGrades(list); //calls method to print grades
				Console.finishGo(System.in);
				break;
			case 4: //print to file
				GradeBookIO.printToFile(list);
				Console.finishGo(System.in);
				break;
			case 5: //read from file
				Console.readFromFileMenu();
				String fileName = Console.getString(System.in);
				list = addListOfGrades(list, GradeBookIO.readFromFile(fileName));
				Console.finishGo(System.in);
				break;
			case 6: //to MySQL
				toMySQL(list);
				Console.finishGo(System.in);
				break;
			case 7: //from MySQL
				list = fromMySQL(list);
				Console.finishGo(System.in);
				break;
			case 8: //MySQL Search
				searchMySQL();
				Console.finishGo(System.in);
				break;
			}
		}
	}
	
	
	//returns true if array is empty
	public static boolean isEmpty(ArrayList<AssignmentInterface> list) {
		if(list.isEmpty()) return true;
		else return false;
	}
	
	//deep copies an array list of assignments
	public static ArrayList<AssignmentInterface> deepCopyList(ArrayList<AssignmentInterface> list) {
		ArrayList<AssignmentInterface> copyList = new ArrayList<AssignmentInterface>();
		for(AssignmentInterface a: list) copyList.add(a);
		return copyList;
	}
	
	//sorts an assignment list by score (least to greatest)
	public static ArrayList<AssignmentInterface> sortByScore(ArrayList<AssignmentInterface> list) {
		ArrayList<Double> scoreList = new ArrayList<Double>();
		ArrayList<AssignmentInterface> copyList = deepCopyList(list);
		for(AssignmentInterface a: copyList) scoreList.add(a.getScore());

		Collections.sort(scoreList);
		
		ArrayList<AssignmentInterface> sortedList = new ArrayList<AssignmentInterface>();
		for(int scoreIndex = 0; scoreIndex < scoreList.size(); scoreIndex++) {
			for(int gradeIndex = 0; gradeIndex < copyList.size(); gradeIndex++) {
				if(copyList.get(gradeIndex).getScore() == scoreList.get(scoreIndex)) {
					sortedList.add(copyList.get(gradeIndex));
					copyList.remove(gradeIndex);
				}
			}
		}

		return sortedList;
	}
	
	//sorts an assignment list by letter grade (A to F)
	public static ArrayList<AssignmentInterface> sortByLetter(ArrayList<AssignmentInterface> list) {
		ArrayList<Character> letterList = new ArrayList<Character>();
		ArrayList<AssignmentInterface> copyList = deepCopyList(list);
		for(AssignmentInterface a: copyList) letterList.add(a.getLetter());
		
		Collections.sort(letterList);
		
		ArrayList<AssignmentInterface> sortedList = new ArrayList<AssignmentInterface>();
		for(int letterIndex = 0; letterIndex < letterList.size(); letterIndex++) {
			for(int gradeIndex = 0; gradeIndex < copyList.size(); gradeIndex++) {
				if(copyList.get(gradeIndex).getLetter() == letterList.get(letterIndex)) {
					sortedList.add(copyList.get(gradeIndex));
					copyList.remove(gradeIndex);
				}
			}
		}
		return sortedList;
	}
	
	//sorts an assignment list by name in alphabetical order
	public static ArrayList<AssignmentInterface> sortByName(ArrayList<AssignmentInterface> list) {
		ArrayList<String> nameList = new ArrayList<String>();
		ArrayList<AssignmentInterface> copyList = deepCopyList(list);
		for(AssignmentInterface a: copyList) nameList.add(a.getName().toLowerCase());

		Collections.sort(nameList);

		ArrayList<AssignmentInterface> sortedList = new ArrayList<AssignmentInterface>();
		for(int nameIndex = 0; nameIndex < nameList.size(); nameIndex++) {
			for(int gradeIndex = 0; gradeIndex < copyList.size(); gradeIndex++) {
				if(copyList.get(gradeIndex).getName().toLowerCase().equals(nameList.get(nameIndex))) {
					sortedList.add(copyList.get(gradeIndex));
					copyList.remove(gradeIndex);
				}
			}
		}
		return sortedList;
	}
	
	//sorts an assignment list by date from past to present
	public static ArrayList<AssignmentInterface> sortByDate(ArrayList<AssignmentInterface> list) {
		ArrayList<AssignmentInterface> sortedList = deepCopyList(list);
		
		Collections.sort(sortedList, new Comparator<AssignmentInterface>() {
			public int compare(AssignmentInterface a1, AssignmentInterface a2) {
				return a1.getDueDate().compareTo(a2.getDueDate());
			}
		});

		return sortedList;
	}
	
	//prompts user to enter a score for the assignment, catches errors
	public static double getScore(InputStream inputStream, String assignment) {
		double score;

		Console.setVar("Score (Between 0 and 100)", assignment);
		score = Console.getDoubleWithBounds(inputStream, 0, 100);
		return score;
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
	public static String getName(InputStream inputStream, String assignment) {
		Console.setVar("Name", assignment);
		String name = Console.getString(inputStream);
		return name;
	}
		
	//prompts user to enter due date for assignment
	public static Date getDueDate(InputStream inputStream, String assignment) {
		Console.setVar("Due Date (MM-DD-YYYY)", assignment);
		Date date = new Date();
		date = Console.getParsedDate(inputStream);
		return date;
	}
		
	//prompts user to enter number of questions for assignment, catches errors
	public static int getNumQuestions(InputStream inputStream, String assignment) {
		Console.setVar("Number of Questions (Between 0 and 500)", assignment);
		int numQuestions = Console.getIntWithBounds(inputStream, 0, 500);
		return numQuestions;
	}
		
	//prompts user to enter reading for assignment
	public static String getReading(InputStream inputStream, String assignment) {
		Console.setVar("Reading Material", assignment);
		String reading = Console.getString(inputStream);
		return reading;
	}
	
	//prompts user to enter concept for assignment
	public static String getConcept(InputStream inputStream, String assignment) {
		Console.setVar("Concept", assignment);
		String concept = Console.getString(inputStream);
		return concept;
	}
		
	//adds a grade to grade book
	public static ArrayList<AssignmentInterface> addGrade(InputStream inputStream, ArrayList<AssignmentInterface> list){
		//prints menu
		Console.addGrade();
		int gradeNum = Console.getIntWithBounds(inputStream, 1, 3);
		
		//sets variables depending on type of AssignmentInterface
		if(gradeNum == 1) { //Quiz
			Quiz quiz = new Quiz();
			
			double score = getScore(inputStream, "Quiz");
			quiz.setScore(score);
			
			quiz.setLetter(getLetter(quiz.getScore()));
			
			String name = getName(inputStream, "Quiz");
			quiz.setName(name);
			
			Date dueDate = getDueDate(inputStream, "Quiz");
			quiz.setDueDate(dueDate);
			
			int numQuestions = getNumQuestions(inputStream, "Quiz");
			quiz.setNumQuestions(numQuestions);
			
			Console.clearScreen();
			Console.printAssignment(quiz);

			list.add(quiz);
		}
		else if(gradeNum == 2) { //Discussion
			Discussion disc = new Discussion();
			
			double score = getScore(inputStream, "Discussion");
			disc.setScore(score);
			
			disc.setLetter(getLetter(disc.getScore()));
			
			String name = getName(inputStream, "Discussion");
			disc.setName(name);
			
			Date dueDate = getDueDate(inputStream, "Discussion");
			disc.setDueDate(dueDate);
			
			String reading = getReading(inputStream, "Discussion");
			disc.setReading(reading);
			
			Console.clearScreen();
			Console.printAssignment(disc);

			list.add(disc);
		}
		else { //Program
			Program program = new Program();
			
			double score = getScore(inputStream, "Program");
			program.setScore(score);
			
			program.setLetter(getLetter(program.getScore()));
			
			String name = getName(inputStream, "Program");
			program.setName(name);
			
			Date dueDate = getDueDate(inputStream, "Program");
			program.setDueDate(dueDate);
			
			String concept = getConcept(inputStream, "Program");
			program.setConcept(concept);
			
			Console.clearScreen();
			Console.printAssignment(program);

			list.add(program);
		}
		return list;
	}
		
	//removes a designated grade from GradeBook
	public static ArrayList<AssignmentInterface> removeGrade(ArrayList<AssignmentInterface> list) {
		//if empty, throw exception
		try { 
			if(isEmpty(list)) {
				throw new GradeBookEmptyException("Grade Book empty");
			}
		}
		catch (GradeBookEmptyException e){
			System.out.print("\n\nGradeBookEmptyException: Grade Book is empty, cannot remove grade.\n\n");
			return list;
		}
		
		//prints menu
		Console.removeGrade();
		String name = Console.getString(System.in);
		
		//trys iterating through list and if found, deletes
		try {
			boolean found = false;
			for(int index = 0; index < list.size(); index++) {
				if(name.equals(list.get(index).getName())) {
					list.remove(index);
					name = ""; //makes it so it only removes the first instance of grade
					found = true;
				}
			}
			if(!found) {
				throw new InvalidGradeException("Grade not found.");
			}
		}
		catch (InvalidGradeException e) {
			System.out.print("\n\nInvalidGradeException: Grade not found.\n\n");
			return list;
		}
		System.out.print("\n\nAssignment deleted!\n");
		return list;
	}
		
	//prints all grades and their attributes
	public static void printGrades(ArrayList<AssignmentInterface> list) {
		//if empty, throw exception
		try { 
			if(isEmpty(list)) {
				throw new GradeBookEmptyException("Grade Book empty");
			}
		}
		catch (GradeBookEmptyException e){
			System.out.print("\n\nGradeBookEmptyException: Grade Book is empty, no grades to print.\n\n");
			return;
		}
		//prints menu
		Console.printGradesMenu();
		int input = Console.getIntWithBounds(System.in, 1, 4);
		ArrayList<AssignmentInterface> sortedList = new ArrayList<AssignmentInterface>();
		
		if(input == 1) {
			sortedList = sortByScore(list);
		}
		else if(input == 2) {
			sortedList = sortByLetter(list);
		}
		else if(input == 3) {
			sortedList = sortByName(list);
		}
		else {
			sortedList = sortByDate(list);
		}
		
		Console.clearScreen();
		System.out.print("Printed Grades\n");
		Console.Line();
		for(AssignmentInterface a: sortedList) {
			Console.printAssignment(a);
		}
	}
	
	//adds a list of grades to an already existing list: essentially combines two lists
	public static ArrayList<AssignmentInterface> addListOfGrades(ArrayList<AssignmentInterface> list, ArrayList<AssignmentInterface> addList) {
		if(addList.isEmpty()) {
			return list;
		}
		for(AssignmentInterface a: addList) list.add(a);
		return list;
	}
	
	//moves current grades to the user's MySQL DB
	public static void toMySQL(ArrayList<AssignmentInterface> list) {
		//if empty, throw exception
		try { 
			if(isEmpty(list)) {
				throw new GradeBookEmptyException("Grade Book empty");
			}
		}
		catch (GradeBookEmptyException e){
			System.out.print("\n\nGradeBookEmptyException: Grade Book is empty, no grades to add to DB.\n\n");
			return;
		}
				
		String username = GradeBookDB.getUsername();
		String password = GradeBookDB.getPassword();
		
		if(GradeBookDB.createTable(username, password)) {
			if(GradeBookDB.addGradesToDB(list, username, password)) {
				Console.clearScreen();
				System.out.print("Successfully added grades to the DB.\n");
				Console.Line();
			}
			else System.out.print("\nError: Could not add grades to DB, try again.\n");
		}
		else System.out.print("\nError: Could not add grades to DB, try again.\n");
	}
	
	//takes any currently unadded grades from the DB and copies them into the program
	public static ArrayList<AssignmentInterface> fromMySQL(ArrayList<AssignmentInterface> list) {
		String username = GradeBookDB.getUsername();
		String password = GradeBookDB.getPassword();
		
		ArrayList<AssignmentInterface> newList = new ArrayList<AssignmentInterface>();
		ArrayList<AssignmentInterface> DBList = new ArrayList<AssignmentInterface>();
		
		if(GradeBookDB.createTable(username, password)) {
			DBList = GradeBookDB.pullUnaddedGradesFromDB(list, username, password);
			if(DBList == null) return list;
			newList = addListOfGrades(list, DBList);
		}
		else return list;
		
		return newList;
	}
	
	//searches the DB under a criteria that the user provides
	public static void searchMySQL() {
		String username = GradeBookDB.getUsername();
		String password = GradeBookDB.getPassword();
		
		ArrayList<AssignmentInterface> searchedList = new ArrayList<AssignmentInterface>();
		searchedList = GradeBookDB.mySQLSearch(username, password);
		if(searchedList == null) return;
		
		else if (searchedList.isEmpty()) {
				Console.clearScreen();
				System.out.print("No Grades were found from your search.\n");
				Console.Line();
				return;
		}
		printGrades(searchedList);
	}
}
