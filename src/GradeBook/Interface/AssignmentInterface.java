/* Name: Nic Mowll
 * Class: CodertoCraftsman
 * Assignment: GradeBook Part 2
 * Date: 11/29/2021
 */
package GradeBook.Interface;

import java.util.Date;

public interface AssignmentInterface { //Interface for different types of assignments
	
	public double getScore(); //returns score
	public char getLetter(); //returns letter grade
	public String getName(); //returns name
	public Date getDueDate(); //returns due date
	public String getDueDateFormat(); //gets date formatted for Printing
	public void setScore(double score); //sets score
	public void setLetter(char letter); //sets letter grade
	public void setName(String name); //sets name
	public void setDueDate(Date date); //sets due date
	public String toString(); //returns string of vars
	public String printCustom(); //returns String that contains variable unique to assignment object
	public String printAssType(); //prints the type of assingment
	public String getDueDateFormatToFile(); //gets date formatted for putting into a file
}
