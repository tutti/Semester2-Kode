package code;

public class InvalidLifeFileException extends RuntimeException {
	
	private static final long serialVersionUID = -922048782043547610L;
	
	public InvalidLifeFileException() {
		super("Invalid Life file");
	}
	
	public InvalidLifeFileException(String message) {
		super(message);
	}
	
}
