package GradeBook.Classes;

import GradeBook.Interface.*;

public class Discussion implements AssignmentInterface{
	private double score;
	private char letter;
	private String name;
	private String dueDate;
	
	private String reading;
	
	@Override
	public double getScore() {
		return this.score;
	}

	@Override
	public char getLetter() {
		return this.letter;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getDueDate() {
		return this.dueDate;
	}

	@Override
	public void setScore(double score) {
		this.score = score;
	}

	@Override
	public void setLetter(char letter) {
		this.letter = letter;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setDueDate(String date) {
		this.dueDate = date;
	}
	
	@Override
	public String toString() { //returns string that contains variable list
		String str = "\n  Name:         " + this.getName() +
				"\n  Score:        " + this.getScore() + "/100" +
				"\n  Letter Grade: " + this.getLetter() +
				"\n  Reading:      " + this.getReading() +
				"\n  Due Date:     " + this.getDueDate() + "\n";
		
		return str;
	}
	
	public String getReading() {
		return this.reading;
	}
	
	public void setReading(String reading) {
		this.reading = reading;
	}
	
	@Override
	public String printOther() { //returns reading
		String reading = "" + this.getReading();
		return reading;
	}

}
