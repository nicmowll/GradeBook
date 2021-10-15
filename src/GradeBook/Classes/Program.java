package GradeBook.Classes;

import GradeBook.Interface.*;

public class Program implements AssignmentInterface{ //Program assignment
	private double score;
	private char letter;
	private String name;
	private String dueDate;
	
	private String concept;
	
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
				"\n  Concept:      " + this.getConcept() +
				"\n  Due Date:     " + this.getDueDate() + "\n";
		
		return str;
	}
	
	public String getConcept() {
		return this.concept;
	}
	
	public void setConcept(String concept) {
		this.concept = concept;
	}
	
	@Override
	public String printOther() { //returns concept
		String concept = "" + this.getConcept();
		return concept;
	}

}
