/* Name: Nic Mowll
 * Class: CodertoCraftsman
 * Assignment: GradeBook Part 2
 * Date: 11/29/2021
 */
package GradeBook.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.lang.annotation.Annotation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import GradeBook.Classes.*;
import GradeBook.Interface.AssignmentInterface;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
public class AddGradeTest {
	
	private static ArrayList<AssignmentInterface> testList = new ArrayList<AssignmentInterface>(); //test list of grades
	private static Quiz testQuiz = new Quiz(); //3 test grades of each type
	private static Discussion testDiscussion = new Discussion();
	private static Program testProgram = new Program();
	
	@BeforeAll
	public static void setUp() { //assigns values to 3 different test grades
		SimpleDateFormat ft = new SimpleDateFormat("MM-dd-yyyy");
		Date dueDate = new Date();
		try{dueDate = ft.parse("06-23-2021");}
		catch (ParseException e) {};
		
		testQuiz.setName("testQuiz");
		testQuiz.setScore(89.5);
		testQuiz.setLetter('B');
		testQuiz.setDueDate(dueDate);
		testQuiz.setNumQuestions(5);
		
		testDiscussion.setName("testDiscussion");
		testDiscussion.setScore(94.0);
		testDiscussion.setLetter('A');
		testDiscussion.setDueDate(dueDate);
		testDiscussion.setReading("Test Reading");
		
		testProgram.setName("testProgram");
		testProgram.setScore(75);
		testProgram.setLetter('C');
		testProgram.setDueDate(dueDate);
		testProgram.setConcept("Test Concept");
	}
	//NOTE: Test order is: quiz, discussion, program
	
	@Test //To test, run and enter the information for the test assignment
	@Order(1)
	public void testAddQuiz() {
		testList = GradeBook.addGrade(System.in, testList);
		assertTrue("Testing adding a quiz grade and making sure it appears in list", existsInList(testList, testQuiz));
	}	
	
	@Test //To test, run and enter the information for the test assignment
	@Order(2)
	public void testAddDiscussion() {
		testList = GradeBook.addGrade(System.in, testList);
		assertTrue("Testing adding a discussion grade and making sure it appears in list", existsInList(testList, testDiscussion));
	}
	
	@Test //To test, run and enter the information for the test assignment
	@Order(3)
	public void testAddProgram() {
		testList = GradeBook.addGrade(System.in, testList);
		assertTrue("Testing adding a program grade and making sure it appears in list", existsInList(testList, testProgram));
	}	
	
	//helper function that returns true if the assignment exists in the list
	public boolean existsInList(ArrayList<AssignmentInterface> testList, AssignmentInterface assignment) {
		for(AssignmentInterface a: testList) {
			if(a.getName().equals(assignment.getName())
					&& a.getDueDate().equals(assignment.getDueDate())
					&& a.getScore() == assignment.getScore()
					&& a.printCustom().equals(assignment.printCustom())) return true;
		}
		return false;
	}

}
