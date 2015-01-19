package no.hib.dat102.adt;

import no.hib.dat102.*;

public interface CDarkivADT<T extends CD> {
	// Legg til en CD i arkivet
	public void leggTilCD(T cd);
	
	// Fjern en CD fra arkivet
	public void slettCD(int cdnummer);
	
	// Finn CDer basert p� en del av navnene
	public CD[] finnCDer(String s�k);
	
	// Finn artister basert p� en del av navnene
	public String[] finnArtister(String s�k);
	
	// Finn totalt antall CDer
	public int antallCDer();
	
	// Finn antall CDer for en gitt sjanger
	public int antallCDer(Sjanger sjanger);
}