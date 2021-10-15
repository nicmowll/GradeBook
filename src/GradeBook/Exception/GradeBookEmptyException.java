package GradeBook.Exception;

public class GradeBookEmptyException extends Exception{ //exception for when gradebook is empty
	private static final long serialVersionUID = 1L;

	public GradeBookEmptyException(String message) {
		super(message);
	}
}
