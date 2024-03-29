/* Name: Nic Mowll
 * Class: CodertoCraftsman
 * Assignment: GradeBook Part 2
 * Date: 11/29/2021
 */
package GradeBook.Exception;

public class InvalidGradeException extends Exception{ //exception for when grade entered is invalid
	private static final long serialVersionUID = 1L;

	public InvalidGradeException(String message) {
		super(message);
	}
}
