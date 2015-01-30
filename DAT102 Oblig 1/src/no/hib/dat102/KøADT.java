package no.hib.dat102;

public interface K�ADT<T> {
	/**
	 * Dytter et nytt element inn p� slutten av k�en
	 * @param element Elementet som skal inn i k�en
	 */
	public void innK�(T element);
	
	/**
	 * Fjerner f�rste element i k�en og returnerer det
	 * @return F�rste element i k�en
	 */
	public T utK�() throws EmptyCollectionException;
	
	/**
	 * Henter f�rste element i k�en uten � fjerne det
	 * @return F�rste element i k�en
	 */
	public T front() throws EmptyCollectionException;
	
	/**
	 * Sjekker om k�en er tom
	 * @return true hvis k�en er tom, false hvis ikke
	 */
	public boolean erTom();
	
	/**
	 * Returnerer antall elementer i k�en
	 * @return Antall elementer i k�en
	 */
	public int getAntall();
}
