package unntak;

/**
 * Kastes i situasjoner der en spiller må betale for seg, men ikke kan.
 * Skal KUN kastes dersom en situasjon har oppstått som ikke skal kunne
 * oppstå hvis spilleren ikke kan betale, f.eks. utløse pantsatt gate uten
 * å ha råd (dette skal ikke forsøkes hvis spilleren ikke har råd). I de
 * fleste tilfeller der spilleren ikke kan betale for seg, skal enten
 * salg av hus og gater startes, eller spilleren skal slås konkurs - uten
 * at noen av disse er unntak.
 * @author tutti
 *
 */
public class KanIkkeBetaleException extends RuntimeException {

	private static final long serialVersionUID = -5951818351480067516L;
	
	public KanIkkeBetaleException() {
		super("Spiller kan ikke betale");
	}

	public KanIkkeBetaleException(String melding) {
		super(melding);
	}

}
