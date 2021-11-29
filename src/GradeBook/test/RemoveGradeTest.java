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
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class) //orders tests
class RemoveGradeTest {
	private static ArrayList<AssignmentInterface> testList = new ArrayList<AssignmentInterface>(); //test list of grades
	
	@BeforeAll //creates two grades with same name and adds them to list
	public static void setUp() {
		SimpleDateFormat ft = new SimpleDateFormat("MM-dd-yyyy");
		Date dueDate = new Date();
		try{dueDate = ft.parse("06-23-2021");}
		catch (ParseException e) {}; 
		
		Quiz testQuiz = new Quiz();
		testQuiz.setName("Quiz");
		testQuiz.setScore(70);
		testQuiz.setLetter('C');
		testQuiz.setDueDate(dueDate);
		testQuiz.setNumQuestions(5);
		
		testList.add(testQuiz);
		
		Quiz testQuiz2 = new Quiz();;
		testQuiz2.setName("Quiz");
		testQuiz2.setScore(75);
		testQuiz2.setLetter('C');
		testQuiz2.setDueDate(dueDate);
		testQuiz2.setNumQuestions(10);
		
		testList.add(testQuiz2);
		System.out.print(testList.size());
	}
	
	@Test
	@Order(1)
	public void testRemoveOneGradeWithTwoNames() {
		testList = GradeBook.removeGrade(testList);
		assertEquals("Testing removing a grade when gradebook has two grades with same name", 1, testList.size());
	}
	
	@Test
	@Order(2)
	public void testRemoveOneGrade() {
		testList = GradeBook.removeGrade(testList);
		assertTrue("Testing removing a grade when gradebook has one grade", testList.isEmpty());
	}
}
