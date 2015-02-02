package adt;

import unntak.IkkeEiendomException;
import unntak.IngenEierException;
import unntak.KanIkkeBetaleException;
import main.Farge;
import main.Spiller;

public interface RuteADT {
	/**
	 * Sjekker om en rute er en eiendom (kan eies av en spiller).
	 * @return true hvis ruten er en eiendom, false hvis ikke.
	 */
	public default boolean erEiendom() {
		return false;
	}
	
	/**
	 * Sjekker om en eiendom har en eier.
	 * @return true hvis eiendommen har en eier, false hvis ikke.
	 * @throws IkkeEiendomException Hvis ruten ikke er en eiendom
	 */
	public default boolean harEier() throws IkkeEiendomException {
		throw new IkkeEiendomException();
	}
	
	/**
	 * Henter eiendommens eier.
	 * @return Eiendommens eier
	 * @throws IkkeEiendomException Hvis ruten ikke er en eiendom
	 * @throws IngenEierException Hvis eiendommen ikke har en eier
	 */
	public default Spiller hentEier() throws
		IkkeEiendomException,
		IngenEierException
	{
		throw new IkkeEiendomException();
	}
	
	/**
	 * Setter eiendommens eier.
	 * @param spiller Eiendommens nye eier
	 * @throws IkkeEiendomException Hvis ruten ikke er en eiendom
	 */
	public default void settEier(Spiller spiller) throws IkkeEiendomException {
		throw new IkkeEiendomException();
	}
	
	/**
	 * Setter leieverdiene for en gate.
	 * Tar en array med 6 leieverdier.
	 * Gjelder kun for GATER, ikke alle eiendommer.
	 * @param leie En array med 6 leieverdier
	 * @throws IkkeEiendomException Hvis ruten ikke er en eiendom
	 */
	public default void settLeie(int[] leie) throws IkkeEiendomException {
		throw new IkkeEiendomException();
	}
	
	/**
	 * Setter leieverdiene for en gate.
	 * Gjelder kun for GATER, ikke alle eiendommer.
	 * @param tom Leien for en ubebygget gate (før dobling)
	 * @param hus1 Leie for gate med 1 hus
	 * @param hus2 Leie for gate med 2 hus
	 * @param hus3 Leie for gate med 3 hus
	 * @param hus4 Leie for gate med 4 hus
	 * @param hotell Leie for gate med hotell
	 * @throws IkkeEiendomException Hvis ruten ikke er en eiendom
	 */
	public default void settLeie (
		int tom,
		int hus1,
		int hus2,
		int hus3,
		int hus4,
		int hotell
	) throws IkkeEiendomException {
		throw new IkkeEiendomException();
	}
	
	/**
	 * Pantsetter en gate.
	 * Gatens eier får utbetalt halve eiendommens kostnad.
	 * @throws IkkeEiendomException Hvis ruten ikke er en eiendom
	 */
	public default void pantsett() throws IkkeEiendomException {
		throw new IkkeEiendomException();
	}
	
	/**
	 * Løser ut en pantsatt eiendom. Dette vil koste eieren pantsettelsesverdien
	 * pluss 10%. Vil kaste en feil dersom eieren ikke har råd til dette.
	 * @throws IkkeEiendomException Hvis ruten ikke er en eiendom
	 */
	public default void utløs() throws
		IkkeEiendomException,
		IngenEierException,
		KanIkkeBetaleException
	{
		throw new IkkeEiendomException();
	}
	
	/**
	 * Kalles når en spiller forlater en rute. Merk at ruter ikke er påkrevd å
	 * vite hvilke spillere som er på dem; de rutene som vil vite dette, må
	 * bruke metodene spillerForlater() og spillerLander() til å holde oversikt.
	 * @param spiller Spilleren som forlater ruten
	 * @param kast Kastet spilleren forlater ruten med
	 */
	public default void spillerForlater(Spiller spiller, int kast) {
		// Ingen handling
	}
	
	/**
	 * Kalles når en spiller passerer en rute. Spillere antas å "passere" en
	 * rute de lander på, men ikke en rute de forlater.
	 * @param spiller Spilleren som passerer ruten
	 * @param kast Kastet spilleren passerer ruten med
	 */
	public default void spillerPasserer(Spiller spiller, int kast) {
		// Ingen handling
	}
	
	/**
	 * Kalles når en spiller lander på en rute. Merk at ruter ikke er påkrevd å
	 * vite hvilke spillere som er på dem; de rutene som vil vite dette, må
	 * bruke metodene spillerForlater() og spillerLander() til å holde oversikt.
	 * @param spiller Spilleren som lander på ruten
	 * @param kast Kastet spilleren lander på ruten med
	 */
	public default void spillerLander(Spiller spiller, int kast) {
		// Ingen handling
	}

	/**
	 * Setter en rutes fargegruppe
	 * Det anses som gyldig å sette en rutes fargegruppe selv om ruten ikke
	 * er en gate. Ruten må likevel være en eiendom.
	 * @param farge Fargen ruten skal ha
	 * @throws IkkeEiendomException Hvis ruten ikke er en eiendom
	 */
	public default void settFarge(Farge farge) throws IkkeEiendomException {
		throw new IkkeEiendomException();
	}
}
