package adt;

import unntak.IkkeEiendomException;
import unntak.IngenEierException;
import unntak.KanIkkeBetaleException;
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
	 * Pantsetter en gate.
	 * Gatens eier f�r utbetalt halve eiendommens kostnad.
	 * @throws IkkeEiendomException Hvis ruten ikke er en eiendom
	 */
	public default void pantsett() throws IkkeEiendomException {
		throw new IkkeEiendomException();
	}
	
	/**
	 * L�ser ut en pantsatt eiendom. Dette vil koste eieren pantsettelsesverdien
	 * pluss 10%. Vil kaste en feil dersom eieren ikke har r�d til dette.
	 * @throws IkkeEiendomException Hvis ruten ikke er en eiendom
	 */
	public default void utl�s() throws
		IkkeEiendomException,
		IngenEierException,
		KanIkkeBetaleException
	{
		throw new IkkeEiendomException();
	}
	
	/**
	 * Kalles n�r en spiller forlater en rute. Merk at ruter ikke er p�krevd �
	 * vite hvilke spillere som er p� dem; de rutene som vil vite dette, m�
	 * bruke metodene spillerForlater() og spillerLander() til � holde oversikt.
	 * @param spiller Spilleren som forlater ruten
	 * @param kast Kastet spilleren forlater ruten med
	 */
	public default void spillerForlater(Spiller spiller, int kast) {
		// Ingen handling
	}
	
	/**
	 * Kalles n�r en spiller passerer en rute. Spillere antas � "passere" en
	 * rute de lander p�, men ikke en rute de forlater.
	 * @param spiller Spilleren som passerer ruten
	 * @param kast Kastet spilleren passerer ruten med
	 */
	public default void spillerPasserer(Spiller spiller, int kast) {
		// Ingen handling
	}
	
	/**
	 * Kalles n�r en spiller lander p� en rute. Merk at ruter ikke er p�krevd �
	 * vite hvilke spillere som er p� dem; de rutene som vil vite dette, m�
	 * bruke metodene spillerForlater() og spillerLander() til � holde oversikt.
	 * @param spiller Spilleren som lander p� ruten
	 * @param kast Kastet spilleren lander p� ruten med
	 */
	public default void spillerLander(Spiller spiller, int kast) {
		// Ingen handling
	}
}
