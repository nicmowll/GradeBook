/* Name: Nic Mowll
 * Class: CodertoCraftsman
 * Assignment: GradeBook Part 1
 * Date: 10/18/2021
 */
package GradeBook.Interface;

public interface AssignmentInterface { //Interface for different types of assignments
	
	public double getScore(); //returns score
	public char getLetter(); //returns letter grade
	public String getName(); //returns name
	public String getDueDate(); //returns due date
	public void setScore(double score); //sets score
	public void setLetter(char letter); //sets letter grade
	public void setName(String name); //sets name
	public void setDueDate(String date); //sets due date
	public String toString(); //returns string of vars
	public String printCustom(); //returns String that contains variable unique to assignment object
}
