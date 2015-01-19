package no.hib.dat102.adt;

import no.hib.dat102.*;

public interface CDarkivADT<T extends CD> {
	// Legg til en CD i arkivet
	public void leggTilCD(T cd);
	
	// Fjern en CD fra arkivet
	public void slettCD(int cdnummer);
	
	// Finn CDer basert på en del av navnene
	public CD[] finnCDer(String søk);
	
	// Finn artister basert på en del av navnene
	public String[] finnArtister(String søk);
	
	// Finn totalt antall CDer
	public int antallCDer();
	
	// Finn antall CDer for en gitt sjanger
	public int antallCDer(Sjanger sjanger);
}