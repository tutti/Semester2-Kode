package main;

import adt.RuteADT;

public class Spill {
	
	private static final int ANTALL_SPILLERE = 4; // TODO Fjern
	private static Spiller[] spillere;
	private static boolean vunnet = false;
	
	public static void main(String[] args) {
		// Sett opp to terninger
		Terninger terninger = new Terninger();
		terninger.leggTilTerninger(2);
		
		// Sett opp spillere og plasser alle p� start
		RuteADT start = Brett.hentRute(0);
		spillere = new Spiller[ANTALL_SPILLERE];
		for (int i=0; i<ANTALL_SPILLERE; ++i) {
			spillere[i] = new TestCPUSpiller("");
			spillere[i].plasser(start);
		}
		Bank.tilbakestill(spillere);
		
		// Fortsett � spille til noen vinner
		while (!vunnet) {
			for (int i=0; i<spillere.length; ++i) {
				Spiller spiller = spillere[i];
				
				// En spillers tur
				spiller.tur();
				
				// Spilleren g�r inn i f�r-flytt-fasen (handelsfasen).
				spiller.handelsFase();
				
				// Kast terningen(e)
				int kast = terninger.kast();
				
				// Spilleren blir flyttet det antall felt terningene viser
				// Hva som skjer p� feltet spilleren lander p�, vil
				// gj�res av feltet.
				spiller.flytt(kast);
			}
		}
	}
	
	public static Spiller[] hentSpillere() {
		return spillere;
	}
	
	public static void erkl�rKonkurs(Spiller spiller) {
		
	}
	
}
