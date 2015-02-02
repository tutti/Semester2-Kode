package unntak;

public class IkkeEiendomException extends RuntimeException {
	private static final long serialVersionUID = 5416518632903691883L;
	
	public IkkeEiendomException() {
		super("Ikke en eiendom");
	}

	public IkkeEiendomException(String melding) {
		super(melding);
	}
}
