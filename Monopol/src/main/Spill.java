package main;

import gui.Spillvindu;
import adt.RuteADT;

public class Spill {
	
	private static final int ANTALL_SPILLERE = 4; // TODO Fjern
	private static Spiller[] spillere;
	private static boolean vunnet = false;
	
	public static void main(String[] args) {
		Spillvindu vindu = new Spillvindu();
		// Sett opp to terninger
		Terninger terninger = new Terninger();
		terninger.leggTilTerninger(2);
		
		// Sett opp spillere og plasser alle p� start
		RuteADT start = Brett.hentRute(0);
		spillere = new Spiller[ANTALL_SPILLERE];
		for (int i=0; i<ANTALL_SPILLERE; ++i) {
			spillere[i] = new TestCPUSpiller("Spiller "+(i+1));
			spillere[i].plasser(start);
		}
		Bank.tilbakestill(spillere);
		
		int runder = 1000;
		
		// Fortsett � spille til noen vinner
		while (!vunnet && runder >= 0) {
			System.out.println("Runder: "+runder);
			int fortsattMed = 0;
			for (int i=0; i<spillere.length; ++i) {
				Spiller spiller = spillere[i];
				
				// Hopp over spilleren hvis den er konkurs
				if (spiller.erKonkurs()) continue;
				
				++fortsattMed;
				
				// En spillers tur
				spiller.tur();
				
				// Spilleren g�r inn i f�r-flytt-fasen (handelsfasen).
				spiller.handelsFase();
				
				// Kast terningen(e)
				int kast;
				do {
					kast = terninger.kast();
					// Spilleren blir flyttet det antall felt terningene viser
					// Hva som skjer p� feltet spilleren lander p�, vil
					// gj�res av feltet.
					spiller.flytt(kast);
				} while (terninger.alleLike());
				
				// Spilleren kan n� avslutte sin tur.
				spiller.sluttFase(kast);
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {}
			}
			if (fortsattMed <= 1) vunnet = true;
			
			--runder;
		}
		
		for (Spiller spiller : spillere) {
			System.out.print("Penger eid av "+spiller.navn()+": "+Bank.hentPengebel�p(spiller));
			System.out.println(spiller.erKonkurs() ? " (konkurs)" : "");
		}
		
		
	}
	
	public static Spiller[] hentSpillere() {
		return spillere;
	}
	
	public static void erkl�rKonkurs(Spiller spiller) {
		
	}
	
}
