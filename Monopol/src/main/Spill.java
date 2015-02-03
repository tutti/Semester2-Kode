package main;

import adt.RuteADT;

public class Spill {
	
	private static final int ANTALL_SPILLERE = 4; // TODO Fjern
	
	public static void main(String[] args) {
		Terninger terninger = new Terninger();
		terninger.leggTilTerning();
		terninger.leggTilTerning();
		
		RuteADT start = Brett.hentRute(0);
		
		Spiller[] spillere = new Spiller[ANTALL_SPILLERE];
		for (int i=0; i<ANTALL_SPILLERE; ++i) {
			spillere[i].plasser(start);
		}
		
	}
	
}
