package GradeBook.Exception;

public class GradeBookFullException extends Exception{ //exception for when gradebook is full
	private static final long serialVersionUID = 1L;

	public GradeBookFullException(String message) {
		super(message);
	}
}
