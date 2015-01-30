package no.hib.dat102;

public interface KøADT<T> {
	/**
	 * Dytter et nytt element inn på slutten av køen
	 * @param element Elementet som skal inn i køen
	 */
	public void innKø(T element);
	
	/**
	 * Fjerner første element i køen og returnerer det
	 * @return Første element i køen
	 */
	public T utKø() throws EmptyCollectionException;
	
	/**
	 * Henter første element i køen uten å fjerne det
	 * @return Første element i køen
	 */
	public T front() throws EmptyCollectionException;
	
	/**
	 * Sjekker om køen er tom
	 * @return true hvis køen er tom, false hvis ikke
	 */
	public boolean erTom();
	
	/**
	 * Returnerer antall elementer i køen
	 * @return Antall elementer i køen
	 */
	public int getAntall();
}
