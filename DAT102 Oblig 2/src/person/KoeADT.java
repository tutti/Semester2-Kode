package person;

//**************************************************************************
//  KøADT.java      
//  Definerer interface til en samling kø. Sist oppdatert 24.01
//***************************************************************************

public interface KoeADT<T> {
	/** Legge til et element bak i køen. */
	public void innKoe(T element);

	/** Fjerner og returnerer elementet foran i køen. */
	public T utKoe();

	/** Returnerer elementet foran i køen uten å fjerne det fra køen. */
	/******************************************************************
	 * public T foerste()
	 * 
	 * /** Returns sann hvis denne køene ikke inneholder noen elementer..
	 */
	public boolean erTom();

	/** Returnerer antall elementer i køen. */
	public int antall();

}//