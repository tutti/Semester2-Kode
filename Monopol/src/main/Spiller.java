package main;

import java.util.ArrayList;
import ruter.EiendomRute;
import ruter.GateRute;
import adt.RuteADT;
import adt.UIADT;

public abstract class Spiller {
	
	private boolean konkurs;
	
	protected String navn;
	protected int fengsel;
	protected int rute;
	
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
		Spill.ui.hendelse(UIADT.SPILLER_TUR_START, this);
	}
	
	/**
	 * Spilleren skylder banken mer enn spilleren eier, og er konkurs.
	 */
	public void konkurs() {
		konkurs = true;
		for (EiendomRute eiendom : eiendommer) {
			eiendom.settEier(null);
			eiendom.tilbakestill();
			Spill.ui.hendelse(UIADT.SPILLER_FJERN_EIENDOM, this, eiendom);
		}
		eiendommer.clear();
		Spill.ui.hendelse(UIADT.SPILLER_KONKURS, this);
	}
	
	/**
	 * Spilleren skylder en annen spiller mer enn den eier, og er konkurs.
	 * @param spiller
	 */
	public void konkurs(Spiller spiller) {
		konkurs = true;
		for (EiendomRute eiendom : eiendommer) {
			eiendom.settEier(spiller);
			spiller.leggTilEiendom(eiendom);
			Spill.ui.hendelse(UIADT.SPILLER_FJERN_EIENDOM, this, eiendom);
		}
		Bank.betale(this, spiller, Bank.hentPengebel�p(this));
		eiendommer.clear();
		Spill.ui.hendelse(UIADT.SPILLER_KONKURS, this);
	}
	
	public boolean erKonkurs() {
		return konkurs;
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
		Spill.ui.hendelse(UIADT.SPILLER_FENGSEL, this);
	}
	
	/**
	 * Henter tiden spilleren har igjen i fengsel.
	 * @return Antall runder spilleren skal fortsatt v�re i fengsel
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
		Spill.ui.hendelse(UIADT.SPILLER_MOTTA_EIENDOM, this, eiendom);
	}
	
	/**
	 * Fjerner en eiendom fra spilleren
	 * @param eiendom Eiendommen spilleren har tapt/solgt
	 */
	public final void fjernEiendom(EiendomRute eiendom) {
		eiendommer.remove(eiendom);
		Spill.ui.hendelse(UIADT.SPILLER_FJERN_EIENDOM, this, eiendom);
	}
	
	/**
	 * Henter alle eiendommene spilleren eier
	 * @return En array med eiendommene spilleren eier
	 */
	public final EiendomRute[] hentEiendommer() {
		return (EiendomRute[]) eiendommer.toArray();
	}
	
	/**
	 * Henter antall hus spilleren eier p� alle sine gater.
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
	 * Henter antall hoteller spilleren eier p� alle sine gater.
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
	 * Plasserer spilleren direkte p� en rute. Ruten spilleren blir plassert
	 * p� vil kunne reagere, men ikke ruten som blir forlatt eller noen av dem
	 * som blir passert.
	 * @param rute
	 */
	public final void plasser(RuteADT rute) {
		int rutenummer = Brett.hentRutenummer(rute);
		this.rute = rutenummer;
		rute.spillerLander(this, 0); // TODO: Fiks kast
		Spill.ui.hendelse(UIADT.SPILLER_PLASSERE, this, rute);
	}
	
	/**
	 * Flytter spilleren et antall plasser. Rutene spilleren forlater, passerer
	 * og lander p� vil kunne reagere.
	 * @param plasser
	 */
	public final void flytt(int plasser) {
		int gammelRute = rute;
		rute = (rute+plasser)%Brett.ANTALL_RUTER;
		Brett.hentRute(gammelRute).spillerForlater(this,  plasser);
		if (plasser > 0) {
			for (int i=1; i<plasser; ++i) {
				Brett.hentRute((gammelRute+i)%Brett.ANTALL_RUTER)
					.spillerPasserer(this, plasser);
			}
		}
		
		Brett.hentRute(rute).spillerLander(this, Math.max(plasser, 0)); // TODO Fiks kast
		Spill.ui.hendelse(UIADT.SPILLER_PLASSERE, this, Brett.hentRute(rute));
	}
	
	/**
	 * Flytter spilleren til en gitt rute. Rutene spilleren forlater, passerer
	 * og lander p� vil kunne reagere.
	 * @param rute
	 */
	public final void flyttTil(RuteADT rute) {
		int n�Rute = this.rute;
		Brett.hentRute(n�Rute)
			.spillerForlater(this, 0); //TODO Fiks kast
		for (int i=1; i<=Brett.ANTALL_RUTER; ++i) {
			RuteADT nesteRute = Brett.hentRute((n�Rute+i)%Brett.ANTALL_RUTER);
			nesteRute.spillerPasserer(this, 0); // TODO: Fiks kast
			if (nesteRute == rute) {
				nesteRute.spillerLander(this, 0); // TODO: Fiks kast
				Spill.ui.hendelse(UIADT.SPILLER_PLASSERE, this, rute);
				return;
			}
		}
		throw new RuntimeException("Ruten er ikke p� brettet");
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
			if (Brett.hentRute((rute+(++avstand))%Brett.ANTALL_RUTER) == finnRute)
				return avstand;
		}
		throw new RuntimeException("Ruten er ikke p� brettet");
	}
	
	/**
	 * Avgj�r om spilleren �nsker � kj�pe en gitt (ukj�pt) eiendom.
	 * @param rute Eiendommen spilleren kan kj�pe.
	 * @return true hvis spilleren vil kj�pe, false hvis ikke.
	 */
	public abstract boolean vilKj�pe(EiendomRute rute);
	
	/**
	 * Avgj�r om spilleren vil by p� en rute som er under auksjon.
	 * Returnerer budet, eller 0 dersom spilleren ikke vil by.
	 * @param rute Eiendommen spilleren kan by p�.
	 * @param forrigeBud Eksisterende bud spilleren m� overg�.
	 * @return Spillerens bud, eller 0 dersom spilleren avsl�r.
	 */
	public abstract int vilBy(EiendomRute rute, int forrigeBud);
	
	/**
	 * Den delen av turen som skjer f�r spilleren flytter.
	 * Spilleren kan kj�pe og selge hus og hoteller, pantsette eller
	 * utl�se gater, samt foresl� handel for andre spillere.
	 */
	public abstract void handelsFase();
	
//	public void sluttFase(int kast) {}
	
}
