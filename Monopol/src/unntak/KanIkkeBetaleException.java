package unntak;

/**
 * Kastes i situasjoner der en spiller m� betale for seg, men ikke kan.
 * Skal KUN kastes dersom en situasjon har oppst�tt som ikke skal kunne
 * oppst� hvis spilleren ikke kan betale, f.eks. utl�se pantsatt gate uten
 * � ha r�d (dette skal ikke fors�kes hvis spilleren ikke har r�d). I de
 * fleste tilfeller der spilleren ikke kan betale for seg, skal enten
 * salg av hus og gater startes, eller spilleren skal sl�s konkurs - uten
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
