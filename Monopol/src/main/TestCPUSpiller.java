package main;

import ruter.EiendomRute;
import ruter.GateRute;

/**
 * En test-spiller laget for testing.
 * Denne er ikke laget for å ta gode avgjørelser.
 * Spilleren forsøker å beholde 500 penger,
 * men vil ellers kjøpe alt som er tilgjengelig.
 * @author tutti
 *
 */
public class TestCPUSpiller extends Spiller {
	
	private final int TRYGGHET = 500;
	
	public TestCPUSpiller(String navn) {
		super(navn);
	}

	@Override
	public boolean vilKjøpe(EiendomRute rute) {
		// Kjøp gaten hvis spilleren har råd
		boolean valg = (rute.pris() <= Bank.hentPengebeløp(this) - TRYGGHET);
		if (valg) {
			System.out.println(navn+" kjøper "+rute.navn()+" for "+rute.pris()+".");
		}
		return valg;
	}

	@Override
	public int vilBy(EiendomRute rute, int forrigeBud) {
		// Ikke by på noe.
		return 0;
	}

	@Override
	public void handelsFase() {
		// Forsøk å kjøpe ett hus på hver eiendom
		for (EiendomRute eiendom : eiendommer) {
			if (Bank.hentPengebeløp(this) < TRYGGHET) break;
			if (eiendom instanceof GateRute) {
				GateRute gate = (GateRute)eiendom;
				if (
					gate.kanKjøpeHus()
					&& Bank.hentPengebeløp(this) > gate.husPris()
				) {
					gate.kjøpHus();
					System.out.println("Hus: "+navn+" kjøper hus på "+gate.navn()+" ("+gate.antallHus()+")");
				}
			}
		}
	}
	
//	@Override
	public void sluttFase(int kast) {
		System.out.println(navn+" flyttet til "+rute+" med kast "+kast+". ("+Bank.hentPengebeløp(this)+")");
	}
	
	@Override
	public void konkurs() {
		super.konkurs();
		System.out.println(navn+" er konkurs.");
	}

}
