package ruter;

import adt.RuteADT;

public class GateRute extends EiendomRute {
	
	private int[] leie = {0, 0, 0, 0, 0, 0};
	private int antallHus = 0;
	
	public GateRute(String navn, int pris) {
		super(navn, pris);
	}
	
	public void settLeie(int[] leie) {
		if (leie.length != 6)
			throw new RuntimeException("Feil antall leieverdier");
		this.leie = leie;
	}
	
	public void settLeie(
		int tom,
		int hus1,
		int hus2,
		int hus3,
		int hus4,
		int hotell
	) {
		this.leie[0] = tom;
		this.leie[1] = hus1;
		this.leie[2] = hus2;
		this.leie[3] = hus3;
		this.leie[4] = hus4;
		this.leie[5] = hotell;
	}
	
	public int beregnLeie(int kast) {
		if (antallHus > 0) {
			return leie[antallHus];
		}
		RuteADT[] gater = farge.hentRuter();
		for (RuteADT gate : gater) {
			if (!gate.harEier()) return leie[0];
			if (gate.hentEier() != this.eier) return leie[0];
		}
		return 2*leie[0];
	}
	
	/**
	 * Bygger ett hus.
	 */
	public void byggHus() {
		// TODO Lag kontroller på antall hus
		++antallHus;
	}
	
	/**
	 * River ett hus.
	 */
	public void rivHus() {
		// TODO Lag kontroller på antall hus
		--antallHus;
	}
	
	public int antallHus() {
		return antallHus;
	}
	
}
