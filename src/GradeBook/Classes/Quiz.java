package GradeBook.Classes;

import GradeBook.Interface.AssignmentInterface;

public class Quiz implements AssignmentInterface{
	private double score;
	private char letter;
	private String name;
	private String dueDate;
	
	private int numQuestions;
	
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
		String str = "\n  Name:           " + this.getName() + 
				"\n  Score:          " + this.getScore() + "/100" + "\n  Letter Grade:   " + 
				this.getLetter() + "\n  # of Questions: " + this.getNumQuestions() + 
				"\n  Due Date:       " + this.getDueDate() + "\n";
		
		return str;
	}
	
	public int getNumQuestions() {
		return this.numQuestions;
	}
	
	public void setNumQuestions(int numQuestions) {
		this.numQuestions = numQuestions;
	}

	@Override
	public String printOther() { //returns numQuestions
		String numQ = "" + this.getNumQuestions();
		return numQ;
	}

}
