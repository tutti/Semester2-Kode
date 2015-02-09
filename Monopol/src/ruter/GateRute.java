package ruter;

import main.Bank;
import adt.RuteADT;

public class GateRute extends EiendomRute {
	
	private int[] leie = {0, 0, 0, 0, 0, 0};
	private int antallHus = 0;
	
	public GateRute(String navn, int pris) {
		super(navn, pris);
	}
	
	@Override
	public void tilbakestill() {
		super.tilbakestill();
		antallHus = 0;
	}
	
	@Override
	public void settLeie(int[] leie) {
		if (leie.length != 6)
			throw new RuntimeException("Feil antall leieverdier");
		this.leie = leie;
	}
	
	@Override
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
	
	@Override
	public int beregnLeie(int kast) {
		if (eier == null) throw new RuntimeException("Ingen eier");
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
	 * Sjekker IKKE om spilleren har råd.
	 * @return
	 */
	public boolean kanKjøpeHus() {
		if (eier == null) return false;
		if (!farge.eierAlle(eier)) return false;
		if (antallHus >= 5) return false;
		RuteADT[] gater = farge.hentRuter();
		for (RuteADT rute : gater) {
			if (!(rute instanceof GateRute)) return false;
			GateRute gate = (GateRute)rute;
			if (gate.antallHus < antallHus) return false;
		}
		return true;
	}
	
	public boolean kanSelgeHus() {
		if (eier == null) return false;
		if (!farge.eierAlle(eier)) return false;
		if (antallHus == 0) return false;
		GateRute[] gater = (GateRute[]) farge.hentRuter();
		for (GateRute gate : gater) {
			if (gate.antallHus > antallHus) return false;
		}
		return true;
	}
	
	public int husPris() {
		return farge.hentHusPris();
	}
	
	/**
	 * Kjøper ett hus på gaten. Hvis spilleren har 4 hus, bygg hotell.
	 */
	public void kjøpHus() throws RuntimeException {
		if (!kanKjøpeHus()) throw new RuntimeException("Kan ikke kjøpe hus");
		int pris = farge.hentHusPris();
		Bank.betale(eier, pris);
		++antallHus;
	}
	
	/**
	 * Selger ett hus på gaten. Hvis spilleren har hotell, fjern hotellet
	 * og bygg 4 hus. Spilleren får tilbake halve husets verdi
	 */
	public void selgHus() {
		if (!kanSelgeHus()) throw new RuntimeException("Kan ikke selge hus");
		int pris = farge.hentHusPris();
		Bank.motta(eier, pris/2);
		--antallHus;
	}
	
	public int antallHus() {
		return antallHus;
	}
	
}
