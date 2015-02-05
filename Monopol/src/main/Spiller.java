package main;

import java.util.ArrayList;

import ruter.EiendomRute;
import ruter.GateRute;
import adt.RuteADT;

public abstract class Spiller {
	
	private String navn;
	private int fengsel;
	private int rute;
	
	public boolean slippUtAvFengselKort = false;
	public ArrayList<EiendomRute> eiendommer;
	
	/**
	 * Oppretter en ny spiller med et navn
	 * @param navn Spillerens navn
	 */
	public Spiller(String navn) {
		this.navn = navn;
		eiendommer = new ArrayList<EiendomRute>();
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
	
	/**
	 * Henter spillerens navn.
	 * @return Spillerens navn
	 */
	public final String navn() {
		return navn;
	}
	
	/**
	 * Setter spilleren i fengsel.
	 */
	public final void settIFengsel() {
		fengsel = 3;
	}
	
	/**
	 * Henter tiden spilleren har igjen i fengsel.
	 * @return Antall runder spilleren skal fortsatt være i fengsel
	 */
	public final int tidIFengsel() {
		return fengsel;
	}
	
	/**
	 * Legger til en eiendom til spilleren
	 * @param eiendom Eiendommen spilleren har anskaffet
	 */
	public final void leggTilEiendom(EiendomRute eiendom) {
		eiendommer.add(eiendom);
	}
	
	/**
	 * Fjerner en eiendom fra spilleren
	 * @param eiendom Eiendommen spilleren har tapt/solgt
	 */
	public final void fjernEiendom(EiendomRute eiendom) {
		eiendommer.remove(eiendom);
	}
	
	/**
	 * Henter alle eiendommene spilleren eier
	 * @return En array med eiendommene spilleren eier
	 */
	public final EiendomRute[] hentEiendommer() {
		return (EiendomRute[]) eiendommer.toArray();
	}
	
	/**
	 * Henter antall hus spilleren eier på alle sine gater.
	 * @return Antall hus spilleren eier
	 */
	public final int antallHus() {
		int sum = 0;
		for (EiendomRute rute : eiendommer) {
			if (rute.getClass().equals(GateRute.class)) {
				int hus = ((GateRute)rute).antallHus();
				if (hus < 5) sum += hus;
			}
		}
		return sum;
	}
	
	/**
	 * Henter antall hoteller spilleren eier på alle sine gater.
	 * @return Antall hoteller spilleren eier
	 */
	public final int antallHoteller() {
		int sum = 0;
		for (EiendomRute rute : eiendommer) {
			if (rute.getClass().equals(GateRute.class)) {
				int hus = ((GateRute)rute).antallHus();
				if (hus == 5) sum += 1;
			}
		}
		return sum;
	}
	
	/**
	 * Plasserer spilleren direkte på en rute. Ruten spilleren blir plassert
	 * på vil kunne reagere, men ikke ruten som blir forlatt eller noen av dem
	 * som blir passert.
	 * @param rute
	 */
	public final void plasser(RuteADT rute) {
		int rutenummer = Brett.hentRutenummer(rute);
		this.rute = rutenummer;
		rute.spillerLander(this, 0); // TODO: Fiks kast
	}
	
	/**
	 * Flytter spilleren et antall plasser. Rutene spilleren forlater, passerer
	 * og lander på vil kunne reagere.
	 * @param plasser
	 */
	public final void flytt(int plasser) {
		int gammelRute = rute;
		rute = (rute+plasser)%Brett.ANTALL_RUTER;
		Brett.hentRute(gammelRute).spillerForlater(this,  plasser);
		if (plasser > 0) {
			for (int i=0; i<plasser; ++i) {
				Brett.hentRute((gammelRute+i)%Brett.ANTALL_RUTER)
					.spillerPasserer(this, plasser);
			}
		}
		
		Brett.hentRute(rute).spillerLander(this, Math.max(plasser, 0)); // TODO Fiks kast
	}
	
	/**
	 * Flytter spilleren til en gitt rute. Rutene spilleren forlater, passerer
	 * og lander på vil kunne reagere.
	 * @param rute
	 */
	public final void flyttTil(RuteADT rute) {
		int nåRute = this.rute;
		Brett.hentRute(nåRute).spillerForlater(this, 0); //TODO Fiks kast
		for (int i=0; i<=Brett.ANTALL_RUTER; ++i) {
			RuteADT nesteRute = Brett.hentRute((nåRute+i)%Brett.ANTALL_RUTER);
			nesteRute.spillerPasserer(this, 0); // TODO: Fiks kast
			if (nesteRute == rute) {
				nesteRute.spillerLander(this, 0); // TODO: Fiks kast
				return;
			}
		}
		throw new RuntimeException("Ruten er ikke på brettet");
	}
	
	/**
	 * Finner avstanden mellom spilleren og en gitt rute.
	 * Antar at spilleren skal bevege seg fremover.
	 * @param finnRute Ruten som skal finnes
	 * @return Antall felt frem til ruten
	 */
	public final int avstandTil(RuteADT finnRute) {
		int avstand = 0;
		while (avstand <= Brett.ANTALL_RUTER) {
			if (Brett.hentRute((rute+++avstand)%Brett.ANTALL_RUTER) == finnRute)
				return avstand;
		}
		throw new RuntimeException("Ruten er ikke på brettet");
	}
	
	/**
	 * Avgjør om spilleren ønsker å kjøpe en gitt (ukjøpt) eiendom.
	 * @param rute Eiendommen spilleren kan kjøpe.
	 * @return true hvis spilleren vil kjøpe, false hvis ikke.
	 */
	public abstract boolean vilKjøpe(EiendomRute rute);
	
	/**
	 * Avgjør om spilleren vil by på en rute som er under auksjon.
	 * Returnerer budet, eller 0 dersom spilleren ikke vil by.
	 * @param rute Eiendommen spilleren kan by på.
	 * @param forrigeBud Eksisterende bud spilleren må overgå.
	 * @return Spillerens bud, eller 0 dersom spilleren avslår.
	 */
	public abstract int vilBy(EiendomRute rute, int forrigeBud);
	
	/**
	 * Den delen av turen som skjer før spilleren flytter.
	 * Spilleren kan kjøpe og selge hus og hoteller, pantsette eller
	 * utløse gater, samt foreslå handel for andre spillere.
	 */
	public abstract void handelsFase();
	
}
