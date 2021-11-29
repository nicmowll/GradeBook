/* Name: Nic Mowll
 * Class: CodertoCraftsman
 * Assignment: GradeBook Part 2
 * Date: 11/29/2021
 */
package GradeBook.DB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import GradeBook.Classes.Console;
import GradeBook.DB.DBUtil;
import GradeBook.Classes.*;
import GradeBook.Interface.*;

public class GradeBookDB {
	//All queries for the search function
	private static final String ALL_QUIZZES = "SELECT * FROM Gradebook " //gets all quizzes
			+ "WHERE Type = 'Quiz'";
	private static final String ALL_PROGRAMS = "SELECT * FROM Gradebook " //gets all programs
			+ "WHERE Type = 'Program'";
	private static final String ALL_DISCUSSIONS = "SELECT * FROM Gradebook " //gets all discussions
			+ "WHERE Type = 'Discussion'";
	private static final String EVEN_SCORES = "SELECT * FROM Gradebook " //gets all assignments with even scores
			+ "WHERE MOD(Score, 2) = 0";
	private static final String ALL = "SELECT * FROM Gradebook"; //gets all assignments
	
	//gets assignments with scores in a certain range
	public static String getQueryScoreRange(double lowBound, double highBound) { 
		String query = ALL + " WHERE Score >= " + lowBound + " AND Score <= " + highBound;
		return query;
	}
	
	//gets assignments with dates in a certain range
	public static String getQueryDateRange(Date lowBound, Date highBound) {
		java.sql.Date sqlLowBound = new java.sql.Date(lowBound.getTime());
		java.sql.Date sqlHighBound = new java.sql.Date(highBound.getTime());
		String query = ALL + " WHERE Date BETWEEN '" + sqlLowBound + "' AND '" + sqlHighBound + "'";
		return query;
	}
	
	//gets username from user for DB
	public static String getUsername() {
		Console.clearScreen();
		System.out.print("Please enter your AWS username.\n");
		Console.Line();
		return Console.getString(System.in);
	}
	
	//gets password from user for DB
	public static String getPassword() {
		Console.clearScreen();
		System.out.print("Please enter your AWS password.\n");
		Console.Line();
		return Console.getString(System.in);
	}
	
	//creates a Gradebook table in DB if it doesn't already exist
	public static boolean createTable(String username, String password) {
		try {
			String sql = "CREATE TABLE IF NOT EXISTS Gradebook "
				+ "(ID INT NOT NULL AUTO_INCREMENT,"
				+ "Type VARCHAR(50),"
				+ "Name VARCHAR(255),"
				+ "Score DOUBLE,"
				+ "Letter VARCHAR(1),"
				+ "Date DATE,"
				+ "Custom VARCHAR(255),"
				+ "PRIMARY KEY(ID));";
		
			Connection connection = DBUtil.getConnection(username, password);
			if(connection == null) {
				throw new SQLException();
			}
		
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.executeUpdate();
			DBUtil.closeConnection();
			return true;
		}
		catch (SQLException e) {
			return false;
		}
	}
	
	//adds a list of grades in program to the DB
	public static boolean addGradesToDB(ArrayList<AssignmentInterface> list, String username, String password) {
		try {
			String sql = "INSERT INTO Gradebook"
					+ " (Type, Name, Score, Letter, Date, Custom)"
					+ " VALUES (?, ?, ?, ?, ?, ?)";
			
			Connection connection = DBUtil.getConnection(username, password);
			if(connection == null) {
				throw new SQLException();
			}
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			for(AssignmentInterface a: list) {
				ps.setString(1, a.printAssType());
				ps.setString(2, a.getName());
				ps.setDouble(3, a.getScore());
				ps.setString(4, a.getLetter() + "");
				java.sql.Date sqlDate = new java.sql.Date(a.getDueDate().getTime());
				ps.setDate(5, sqlDate);
				ps.setString(6, a.printCustom());
				ps.executeUpdate();
			}
			DBUtil.closeConnection();
			return true;
		}
		catch (SQLException e) {
			return false;
		}
	}
	
	//takes unadded grades from DB and returns a list of grades
	public static ArrayList<AssignmentInterface> pullUnaddedGradesFromDB(ArrayList<AssignmentInterface> list, String username, String password) {
		ArrayList<AssignmentInterface> newList = new ArrayList<AssignmentInterface>();
		
		try {
			String sql = "SELECT * FROM Gradebook ORDER BY ID";
			
			Connection connection = DBUtil.getConnection(username, password);
			if(connection == null) {
				throw new SQLException();
			}
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			boolean exists = false;
			
			while(rs.next()) {
				String type = rs.getString("Type");
				String name = rs.getString("Name");
				double score = rs.getDouble("Score");
				char letter = rs.getString("Letter").charAt(0);
				Date dueDate = new Date();
				dueDate = rs.getDate("Date");
				String custom = rs.getString("Custom");
				
				for(AssignmentInterface a: list) {
					if(name.equals(a.getName()) && score == a.getScore() && letter == a.getLetter() 
							&& dueDate.equals(a.getDueDate()) && custom.equals(a.printCustom())) exists = true;
				}
				
				if(!exists) {
					if(type.equals("Quiz")) {
						int numQuestions = Integer.parseInt(custom);
						
						Quiz quiz = new Quiz();
						quiz.setName(name);
						quiz.setScore(score);
						quiz.setLetter(letter);
						quiz.setDueDate(dueDate);
						quiz.setNumQuestions(numQuestions);
						
						newList.add(quiz);
					}
					else if(type.equals("Program")) {
						String concept = custom;
						
						Program prog = new Program();
						prog.setName(name);
						prog.setScore(score);
						prog.setLetter(letter);
						prog.setDueDate(dueDate);
						prog.setConcept(concept);
						
						newList.add(prog);
					}
					else if(type.equals("Discussion")) {
						String reading = custom;
						
						Discussion disc = new Discussion();
						disc.setName(name);
						disc.setScore(score);
						disc.setLetter(letter);
						disc.setDueDate(dueDate);
						disc.setReading(reading);
						
						newList.add(disc);
					}
				}
			}
			Console.clearScreen();
			System.out.print("\nSuccessfully added any previously unadded Grades from the DB\n");
			Console.Line();
			DBUtil.closeConnection();
			return newList;
		}
		catch (SQLException e) {
			Console.clearScreen();
			System.out.print("\nError: Could not access grades from the DB\n");
			Console.Line();
			return null;
		}
	}
	
	//searches for grades depending on inputted criteria and returns a list of those grades
	public static ArrayList<AssignmentInterface> mySQLSearch(String username, String password) {
		try {
			Connection connection = DBUtil.getConnection(username, password);
			if(connection == null) {
				throw new SQLException();
			}
		
			Console.mySQLSearchMenu();
			int input = Console.getIntWithBounds(System.in, 1, 6);
		
			ArrayList<AssignmentInterface> list = new ArrayList<AssignmentInterface>();
			String query;
		
			if(input == 1) { //All quizzes
				query = ALL_QUIZZES;
			}
			else if(input == 2) { //All programs
				query = ALL_PROGRAMS;
			}
			else if(input == 3) { //All discussions
				query = ALL_DISCUSSIONS;
			}
			else if(input == 4) { //score range
				Console.clearScreen();
				System.out.print("Enter low score bound. (from 0 to 99.999)\n");
				Console.Line();
				double lowBound = Console.getDoubleWithBounds(System.in, 0, 99.999);
				Console.clearScreen();
				System.out.print("Enter high score bound. (from low bound to 100)\n");
				Console.Line();
				double highBound = Console.getDoubleWithBounds(System.in, lowBound, 100);
			
				query = getQueryScoreRange(lowBound, highBound);
			}
			else if(input == 5) { //date range
				Console.clearScreen();
				System.out.print("Enter low date bound. (MM-DD-YYYY)\n");
				Console.Line();
				Date lowBound = new Date();
				lowBound = Console.getParsedDate(System.in);
				Console.clearScreen();
				System.out.print("Enter high date bound, must be after the first date. (MM-DD-YYYY)\n");
				Console.Line();
				Date highBound = new Date();
				highBound = Console.getParsedDate(System.in);
			
				boolean dateWorks = false;
				if(lowBound.before(highBound)) dateWorks = true;
				while(!dateWorks) {
					Console.clearScreen();
					System.out.print("Error: Higher Bound date was not after Lower Bound. Try again\n");
					System.out.print("\nEnter low date bound. (MM-DD-YYYY)\n");
					Console.Line();
					lowBound = Console.getParsedDate(System.in);
					Console.clearScreen();
					System.out.print("Enter high date bound, must be after the first date. (MM-DD-YYYY)\n");
					Console.Line();
					highBound = Console.getParsedDate(System.in);
				
					if(lowBound.before(highBound)) dateWorks = true;
				}
			
				query = getQueryDateRange(lowBound, highBound);
			}
			else { //even scores
				query = EVEN_SCORES;
			}
		
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String type = rs.getString("Type");
				String name = rs.getString("Name");
				double score = rs.getDouble("Score");
				char letter = rs.getString("Letter").charAt(0);
				Date dueDate = new Date();
				dueDate = rs.getDate("Date");
				String custom = rs.getString("Custom");
				
				if(type.equals("Quiz")) {
					int numQuestions = Integer.parseInt(custom);
					
					Quiz quiz = new Quiz();
					quiz.setName(name);
					quiz.setScore(score);
					quiz.setLetter(letter);
					quiz.setDueDate(dueDate);
					quiz.setNumQuestions(numQuestions);
					
					list.add(quiz);
				}
				else if(type.equals("Program")) {
					String concept = custom;
					
					Program prog = new Program();
					prog.setName(name);
					prog.setScore(score);
					prog.setLetter(letter);
					prog.setDueDate(dueDate);
					prog.setConcept(concept);
					
					list.add(prog);
				}
				else if(type.equals("Discussion")) {
					String reading = custom;
					
					Discussion disc = new Discussion();
					disc.setName(name);
					disc.setScore(score);
					disc.setLetter(letter);
					disc.setDueDate(dueDate);
					disc.setReading(reading);
					
					list.add(disc);
				}
			}
			DBUtil.closeConnection();
			return list;
		}
		catch (SQLException e) {
			return null;
		}
	}
}
