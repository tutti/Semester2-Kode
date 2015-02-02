package main;

import adt.RuteADT;

public abstract class Spiller {
	private String navn;
	private int fengsel;
	private int rute;
	
	public Spiller(String navn) {
		this.navn = navn;
	}
	
	/**
	 * Markerer at spilleren skal ta sin tur.
	 */
	public void tur() {
		if (fengsel > 0) --fengsel;
	}
	
	/**
	 * Slår en spiller konkurs.
	 * Spiller-objektet sender beskjed til alle andre komponenter som trenger
	 * å vite dette.
	 */
	public void konkurs() {
		// TODO: Send signal til spill om å fjerne spilleren
	}
	
	public final String hentNavn() {
		return navn;
	}
	
	public final void settIFengsel() {
		fengsel = 3;
	}
	
	public final int tidIFengsel() {
		return fengsel;
	}
	
	/**
	 * Plasserer spilleren direkte på en rute. Ruten spilleren blir plassert
	 * på vil kunne reagere, men ikke ruten som blir forlatt eller noen av dem
	 * som blir passert.
	 * @param rute
	 */
	public void plasser(RuteADT rute) {
		int rutenummer = Brett.hentRutenummer(rute);
		this.rute = rutenummer;
		rute.spillerLander(this, 0); // TODO: Fiks kast
	}
	
	/**
	 * Flytter spilleren et antall plasser. Rutene spilleren forlater, passerer
	 * og lander på vil kunne reagere.
	 * @param plasser
	 */
	public void flytt(int plasser) {
		int gammelRute = rute;
		rute = (rute+plasser)%Brett.ANTALL_RUTER;
		for (int i=0; i<plasser; ++i) {
			Brett.hentRute((gammelRute+i)%Brett.ANTALL_RUTER)
				.spillerPasserer(this, plasser);
		}
		Brett.hentRute(rute).spillerLander(this, plasser);
	}
	
	/**
	 * Flytter spilleren til en gitt rute. Rutene spilleren forlater, passerer
	 * og lander på vil kunne reagere.
	 * @param rute
	 */
	public void flytt(RuteADT rute) {
		int nåRute = this.rute;
		for (int i=0; i<=Brett.ANTALL_RUTER; ++i) {
			RuteADT nesteRute = Brett.hentRute((nåRute+i)%Brett.ANTALL_RUTER);
			nesteRute.spillerPasserer(this, 0); // TODO: Fiks kast
			if (nesteRute == rute) {
				nesteRute.spillerLander(this, 0); // TODO: Fiks kast
				return;
			}
		}
		throw new RuntimeException("Rute ikke funnet");
	}
}
