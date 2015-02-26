package balansering;

//********************************************************************
//  
//  Definerer interface til en datastruktur stabel.
//********************************************************************

public interface StabelADT<T> {
	/**
	 * Legger på et element på toppen av stabelen.
	 * 
	 * @element element legges på stabelen
	 */
	public void push(T element);

	/**
	 * Fjerner og returnerer toppelementet fra stabelen.
	 * 
	 * @return T element på toppen av stabelen eller null hvis tom
	 */
	public T pop();

	/**
	 * Returnerer toppelementet uten å fjerne det fra stabelen.
	 * 
	 * @return T element på toppen av stabelen eller null hvis tom
	 */
	public T peek();

	/**
	 * Avgjør om stabelen er tom.
	 * 
	 * @return boolean true hvis stabelen ikke inneholder noen elementer.
	 */
	public boolean erTom();

	/**
	 * Returnerer antall elementer på stabelen.
	 * 
	 * @return int antall elementer på stabelen
	 */
	public int antall();

	/**
	 * Returnerer en strengrepresentasjon av elementene på stabelen.
	 * 
	 * @return String representasjon av stabelen
	 */
	public String toString();
}
