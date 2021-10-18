/* Name: Nic Mowll
 * Class: CodertoCraftsman
 * Assignment: GradeBook Part 1
 * Date: 10/18/2021
 */
package GradeBook.Exception;

public class GradeBookEmptyException extends Exception{ //exception for when gradebook is empty
	private static final long serialVersionUID = 1L;

	public GradeBookEmptyException(String message) {
		super(message);
	}
}
