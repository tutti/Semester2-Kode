package no.hib.dat102.adt;

import no.hib.dat102.*;

public interface CDarkivADT<T extends CD> {
	/**
	 *  Legg til en CD i arkivet
	 * @param cd CD CDen du vil legge til
	 */
	public void leggTilCD(T cd);
	
	/**
	 *  Fjern en CD fra arkivet
	 * @param cdnummer int CD-nummeret til CDen du vil fjerne
	 */
	public void slettCD(int cdnummer);
	
	/**
	 *  Finn CDer basert p� en del av navn(ene). Case-insensitiv
	 * @param s�k String S�kestrengen du vil s�ke etter.
	 * @return CD[] En array av alle CDer som matcher. Har ingen overfl�dige array-posisjoner.
	 */
	public CD[] finnCDer(String s�k);
	
	/**
	 *  Finn artister basert p� en del av navnene
	 * @param s�k String S�kestrengen du vil s�ke etter.
	 * @return String[] En array av alle artister som matcher. Har ingen overfl�dige array-posisjoner.
	 */
	public String[] finnArtister(String s�k);
	
	/**
	 *  Finn totalt antall CDer
	 * @return int Totalt antall CDer
	 */
	public int antallCDer();
	
	/**
	 *  Finn antall CDer for en gitt sjanger
	 * @param sjanger Sjanger en sjanger � s�ke etter
	 * @return int Antall CDer med gitt sjanger
	 */
	public int antallCDer(Sjanger sjanger);
}