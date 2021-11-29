/* Name: Nic Mowll
 * Class: CodertoCraftsman
 * Assignment: GradeBook Part 2
 * Date: 11/29/2021
 */
package GradeBook.IO;

import GradeBook.Classes.*;
import GradeBook.Classes.Console;
import GradeBook.Exception.*;
import GradeBook.Interface.*;

import java.io.*;
import java.nio.file.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class GradeBookIO {
	public static final String DELIM = "\t"; //delim for file format
	
	//takes current grades and formats them into an external uniquely named file
	public static void printToFile(ArrayList<AssignmentInterface> list) {
		try {if(GradeBook.isEmpty(list)) throw new GradeBookEmptyException("Grade Book empty");}
		catch (GradeBookEmptyException e){
			System.out.print("\n\nGradeBookEmptyException: Grade Book is empty, there is no point in adding nothing to a file.\n\n");
			return;
		}
		
		String fileString = generateUniqueName();
		String dirString = "GradeTextFiles";
		Path filePath = Paths.get(dirString, fileString);
		
		try {if(Files.notExists(filePath)) Files.createFile(filePath);}
		catch (IOException e) {
			System.out.println(e + ", Could not create File, try again.");
			return;
		}
		
		File file = filePath.toFile();
		
		try (PrintWriter out = new PrintWriter(
							  new BufferedWriter(
							  new FileWriter(file, true)))) { 
			for(AssignmentInterface a: list) {
				out.println(a.printAssType() + "\t" + a.getName() + "\t" + a.getScore() + "\t"
						+ a.getLetter() + "\t" + a.getDueDateFormatToFile() + "\t" + a.printCustom());
			}
		}
		catch (IOException e) {
			System.out.println(e + ", Could not write to file, try again.");
			return;
		}
		
		Console.printToFileMenu(fileString);
	}
	
	//generates a unique file name based on the exact time it was created
	public static String generateUniqueName() {
		SimpleDateFormat ft = new SimpleDateFormat("'GradeBook'-ddMMyy-hhmmss.SSS.'txt'");
		Date date = new Date();
		String fileName = ft.format(date);
		return fileName;
	}
	
	//reads grades from formatted file, creates grade objects and returns a list of those grades
	public static ArrayList<AssignmentInterface> readFromFile(String fileName) {
		ArrayList<AssignmentInterface> list = new ArrayList<AssignmentInterface>();
		Path filePath = Paths.get("GradeTextFiles", fileName);
		try {if(Files.notExists(filePath)) throw new FileNotFoundException("\nError: File not Found.");}
		catch(FileNotFoundException e) {
			System.out.println("\nError: File not found, try again");
			return list;
		}
		
		File file = filePath.toFile();
		
		try (BufferedReader in = new BufferedReader(
								 new FileReader(file))) {
			String line;
			while((line = in.readLine()) != null) {
				String[] fields = line.split(DELIM);
				
				double score = Double.parseDouble(fields[2]);
				char letter = fields[3].charAt(0);
				Date dueDate = new Date();
				dueDate = getParsedDateFromFile(fields[4]);
				
				if(fields[0].equals("Quiz")) {
					Quiz quiz = new Quiz();
					quiz.setName(fields[1]);
					quiz.setLetter(letter);
					quiz.setScore(score);
					quiz.setDueDate(dueDate);
					quiz.setNumQuestions(Integer.parseInt(fields[5]));
					list.add(quiz);
				}
				else if(fields[0].equals("Program")) {
					Program prog = new Program();
					prog.setName(fields[1]);
					prog.setLetter(letter);
					prog.setScore(score);
					prog.setDueDate(dueDate);
					prog.setConcept(fields[5]);
					list.add(prog);
				}
				else if(fields[0].equals("Discussion")) {
					Discussion disc = new Discussion();
					disc.setName(fields[1]);
					disc.setLetter(letter);
					disc.setScore(score);
					disc.setDueDate(dueDate);
					disc.setReading(fields[5]);
					list.add(disc);
				}
			}
		}
		catch (IOException e) {
			System.out.println("\nError: Could not read from file, try again");
			return list;
		}
		
		System.out.print("\nSuccessfully added grades from the file.\n");
		return list;
	}
	
	//returns date based on String from file
	public static Date getParsedDateFromFile(String dateString) throws IOException {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

		Date date = new Date();
		try {date = ft.parse(dateString);}
		catch(ParseException e) {
			throw new IOException();
		}
		return date;
	}
}
