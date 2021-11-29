/* Name: Nic Mowll
 * Class: CodertoCraftsman
 * Assignment: GradeBook Part 2
 * Date: 11/29/2021
 */
package GradeBook.Classes;

import GradeBook.Interface.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Discussion implements AssignmentInterface{
	private double score; //assignment score out of 100
	private char letter; //letter grade
	private String name; //name of assignment
	private Date dueDate; //due date in mm/dd/yyyy format
	
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
	public Date getDueDate() {
		return this.dueDate;
	}
	
	//gets a formatted String date
	@Override
	public String getDueDateFormat() {
		SimpleDateFormat ft = 
		new SimpleDateFormat("MM-dd-Y");
		
		String dateString = "" + ft.format(this.dueDate);
		return dateString;
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
	public void setDueDate(Date date) {
		this.dueDate = date;
	}
	
	//returns string of neatly displayed variables
	@Override
	public String toString() { //returns string that contains variable list
		String str = "\n  Name:         " + this.getName() +
				"\n  Score:        " + this.getScore() + "/100" +
				"\n  Letter Grade: " + this.getLetter() +
				"\n  Reading:      " + this.getReading() +
				"\n  Due Date:     " + this.getDueDateFormat() + "\n";
		
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
	
	//returns string with assignment type
	@Override
	public String printAssType() {
		String assType = "Discussion";
		return assType;
	}
	
	//gets a String formatted date for putting into files
	@Override
	public String getDueDateFormatToFile() {
		SimpleDateFormat ft = 
		new SimpleDateFormat("Y-MM-dd");
				
		String dateString = "" + ft.format(this.dueDate);
		return dateString;
	}
}
