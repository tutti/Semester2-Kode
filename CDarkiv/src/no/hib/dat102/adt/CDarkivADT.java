package no.hib.dat102.adt;

import no.hib.dat102.*;

public interface CDarkivADT<T extends CD> {
	public void leggTilCD(T cd) throws Exception;
	public void slettCD(int cdnummer);
	public CD[] finnCDer(String s�k);
	public String[] finnArtister(String s�k);
	public int antallCDer();
	public int antallCDer(Sjanger sjanger);
}