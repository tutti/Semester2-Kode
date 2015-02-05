package main;

import ruter.EiendomRute;

/**
 * En test-spiller laget for testing.
 * Denne er ikke laget for � ta gode avgj�relser.
 * Spilleren fors�ker � beholde 500 penger,
 * men vil ellers kj�pe alt som er tilgjengelig.
 * @author tutti
 *
 */
public class TestCPUSpiller extends Spiller {
	
	public TestCPUSpiller(String navn) {
		super(navn);
	}

	@Override
	public boolean vilKj�pe(EiendomRute rute) {
		// Kj�p gaten hvis spilleren har r�d
		return (rute.pris() < Bank.hentPengebel�p(this) - 500);
	}

	@Override
	public int vilBy(EiendomRute rute, int forrigeBud) {
		// Ikke by p� noe.
		return 0;
	}

	@Override
	public void handelsFase() {
		// Kj�p hus p� de billigste gatene det er mulig.
	}

}
