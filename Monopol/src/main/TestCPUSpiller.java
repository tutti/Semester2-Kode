package main;

import ruter.EiendomRute;

/**
 * En test-spiller laget for testing.
 * Denne er ikke laget for å ta gode avgjørelser.
 * Spilleren forsøker å beholde 500 penger,
 * men vil ellers kjøpe alt som er tilgjengelig.
 * @author tutti
 *
 */
public class TestCPUSpiller extends Spiller {
	
	public TestCPUSpiller(String navn) {
		super(navn);
	}

	@Override
	public boolean vilKjøpe(EiendomRute rute) {
		// Kjøp gaten hvis spilleren har råd
		return (rute.pris() < Bank.hentPengebeløp(this) - 500);
	}

	@Override
	public int vilBy(EiendomRute rute, int forrigeBud) {
		// Ikke by på noe.
		return 0;
	}

	@Override
	public void handelsFase() {
		// Kjøp hus på de billigste gatene det er mulig.
	}

}
