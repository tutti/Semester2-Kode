package unntak;

public class IngenEierException extends RuntimeException {
	private static final long serialVersionUID = -4954825732078924649L;
	
	public IngenEierException() {
		super("Ingen eier");
	}

	public IngenEierException(String melding) {
		super(melding);
	}
}
