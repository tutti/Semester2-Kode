package main;

import ruter.EiendomRute;
import ruter.GateRute;

/**
 * En test-spiller laget for testing.
 * Denne er ikke laget for � ta gode avgj�relser.
 * Spilleren fors�ker � beholde 500 penger,
 * men vil ellers kj�pe alt som er tilgjengelig.
 * @author tutti
 *
 */
public class TestCPUSpiller extends Spiller {
	
	private final int TRYGGHET = 500;
	
	public TestCPUSpiller(String navn) {
		super(navn);
	}

	@Override
	public boolean vilKj�pe(EiendomRute rute) {
		// Kj�p gaten hvis spilleren har r�d
		boolean valg = (rute.pris() <= Bank.hentPengebel�p(this) - TRYGGHET);
		if (valg) {
			System.out.println(navn+" kj�per "+rute.navn()+" for "+rute.pris()+".");
		}
		return valg;
	}

	@Override
	public int vilBy(EiendomRute rute, int forrigeBud) {
		// Ikke by p� noe.
		return 0;
	}

	@Override
	public void handelsFase() {
		// Fors�k � kj�pe ett hus p� hver eiendom
		for (EiendomRute eiendom : eiendommer) {
			if (Bank.hentPengebel�p(this) < TRYGGHET) break;
			if (eiendom instanceof GateRute) {
				GateRute gate = (GateRute)eiendom;
				if (
					gate.kanKj�peHus()
					&& Bank.hentPengebel�p(this) > gate.husPris()
				) {
					gate.kj�pHus();
					System.out.println("Hus: "+navn+" kj�per hus p� "+gate.navn()+" ("+gate.antallHus()+")");
				}
			}
		}
	}
	
//	@Override
	public void sluttFase(int kast) {
		System.out.println(navn+" flyttet til "+rute+" med kast "+kast+". ("+Bank.hentPengebel�p(this)+")");
	}
	
	@Override
	public void konkurs() {
		super.konkurs();
		System.out.println(navn+" er konkurs.");
	}

}
