/* Name: Nic Mowll
 * Class: CodertoCraftsman
 * Assignment: GradeBook Part 1
 * Date: 10/18/2021
 */
package GradeBook.Classes;

import GradeBook.Interface.AssignmentInterface;

public class Quiz implements AssignmentInterface{
	private double score; //score of assignmemt up to 100
	private char letter; //letter grade
	private String name; //name of assignment
	private String dueDate; //due date of assignment in mm/dd/yyyy format
	
	private int numQuestions; //number of quiz questions
	
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
	
	//returns string of neatly formatted variables
	@Override
	public String toString() { //returns string that contains variable list
		String str = "\n  Name:           " + this.getName() + 
				"\n  Score:          " + this.getScore() + "/100" + "\n  Letter Grade:   " + 
				this.getLetter() + "\n  # of Questions: " + this.getNumQuestions() + 
				"\n  Due Date:       " + this.getDueDate() + "\n";
		
		return str;
	}
	
	//returns number of questions
	public int getNumQuestions() {
		return this.numQuestions;
	}
	
	//sets number of questions
	public void setNumQuestions(int numQuestions) {
		this.numQuestions = numQuestions;
	}

	//returns string of number of questions
	@Override
	public String printCustom() { //returns numQuestions in String
		String numQ = "" + this.getNumQuestions();
		return numQ;
	}

}
