package main;

import gui.Spillvindu;
import adt.RuteADT;
import adt.UIADT;

public class Spill {
	
	public static final UIADT ui = new Spillvindu();
	
	private static final int ANTALL_SPILLERE = 2; // TODO Fjern
	public static final int MAKS_SPILLERE = 4;
	private static Spiller[] spillere;
	private static boolean vunnet = false;
	private static Spiller spillerTur;
	
//	private static void vent() {
//		try {
//			Thread.sleep(10);
//		} catch (InterruptedException e) {}
//	}
	
	public static void main(String[] args) {
		
//		while (!ui.harStartet()) vent();
		
		// Sett opp to terninger
		Terninger terninger = new Terninger();
		terninger.leggTilTerninger(2);
		
		// Sett opp spillere og plasser alle på start
		RuteADT start = Brett.hentRute(0);
		spillere = new Spiller[ANTALL_SPILLERE];
		for (int i=0; i<ANTALL_SPILLERE; ++i) {
			spillere[i] = new TestCPUSpiller("Spiller "+(i+1));
			spillere[i].plasser(start);
		}
		Bank.tilbakestill(spillere);
		
		int runder = 1000;
		
		// Fortsett å spille til noen vinner
		while (!vunnet && runder >= 0) {
			System.out.println("Runder: "+runder);
			int fortsattMed = 0;
			for (int i=0; i<spillere.length; ++i) {
				Spiller spiller = spillere[i];
				
				// Hopp over spilleren hvis den er konkurs
				if (spiller.erKonkurs()) continue;
				
				++fortsattMed;
				
				// En spillers tur
				spillerTur = spiller;
				spiller.tur();
				
				// Spilleren går inn i før-flytt-fasen (handelsfasen).
				spiller.handelsFase();
				
				// Kast terningen(e)
				int kast;
				do {
					kast = terninger.kast();
					// Spilleren blir flyttet det antall felt terningene viser
					// Hva som skjer på feltet spilleren lander på, vil
					// gjøres av feltet.
					spiller.flytt(kast);
				} while (terninger.alleLike());
				
				// Spilleren kan nå avslutte sin tur.
//				spiller.sluttFase(kast);
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {}
			}
			if (fortsattMed <= 1) vunnet = true;
			
			--runder;
		}
		
		for (Spiller spiller : spillere) {
			System.out.print("Penger eid av "+spiller.navn()+": "+Bank.hentPengebeløp(spiller));
			System.out.println(spiller.erKonkurs() ? " (konkurs)" : "");
		}
		
		
	}
	
	/**
	 * Henter spilleren som har sin tur nå.
	 * @return Spilleren som har sin tur nå.
	 */
	public static Spiller hentSpiller() {
		return spillerTur;
	}
	
	public static Spiller[] hentSpillere() {
		return spillere;
	}
	
	public static void erklærKonkurs(Spiller spiller) {
		
	}
	
}
