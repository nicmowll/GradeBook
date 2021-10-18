/* Name: Nic Mowll
 * Class: CodertoCraftsman
 * Assignment: GradeBook Part 1
 * Date: 10/18/2021
 */
package GradeBook.Classes;

import GradeBook.Interface.*;

public class Discussion implements AssignmentInterface{
	private double score; //assignment score out of 100
	private char letter; //letter grade
	private String name; //name of assignment
	private String dueDate; //due date in mm/dd/yyyy format
	
	private String reading; //reading material
	
	//returns score
	@Override
	public double getScore() {
		return this.score;
	}

	//returns letter grade
	@Override
	public char getLetter() {
		return this.letter;
	}

	//returns name
	@Override
	public String getName() {
		return this.name;
	}

	//returns due date
	@Override
	public String getDueDate() {
		return this.dueDate;
	}

	//sets score
	@Override
	public void setScore(double score) {
		this.score = score;
	}

	//sets letter grade
	@Override
	public void setLetter(char letter) {
		this.letter = letter;
	}

	//sets name
	@Override
	public void setName(String name) {
		this.name = name;
	}

	//sets due date
	@Override
	public void setDueDate(String date) {
		this.dueDate = date;
	}
	
	//returns string of neatly displayed variables
	@Override
	public String toString() { //returns string that contains variable list
		String str = "\n  Name:         " + this.getName() +
				"\n  Score:        " + this.getScore() + "/100" +
				"\n  Letter Grade: " + this.getLetter() +
				"\n  Reading:      " + this.getReading() +
				"\n  Due Date:     " + this.getDueDate() + "\n";
		
		return str;
	}
	
	//returns reading
	public String getReading() {
		return this.reading;
	}
	
	//sets reading
	public void setReading(String reading) {
		this.reading = reading;
	}
	
	//returns String of reading
	@Override
	public String printCustom() { //returns reading in String
		String reading = "" + this.getReading();
		return reading;
	}

}
