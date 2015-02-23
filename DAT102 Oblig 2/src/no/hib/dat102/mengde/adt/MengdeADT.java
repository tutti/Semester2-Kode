package no.hib.dat102.mengde.adt;
import java.util.*;
import java.util.Iterator;
public interface MengdeADT<T> {
 // Interface som definerer alle operasjoner i en ADT
 // med navn MengdeADT (en datasamling, en høynivå datastruktur)
 
	/**
	 * Legger til et objekt av klasse T til dette mengde-objektet hvis det ikke
	 * fins fra før
	 * 
	 * @param element som skal legges til
	 */
	void leggTil(T element);
	
	
	/**
	 * Legger til mengden m2 til dette mengde-objektet. De elementene i m2 som fins
	 * fra før i denne mengden bli ikke lagt til.
	 * 
	 * @param m2 mengden som skal legges til
	 */
	 public void leggTilAlle(MengdeADT<T> m2);

	/**
	 * Fjerner og returnerer et tilfeldig element fra mengden
	 * 
	 * @return returnerer det tilfeldige elementet ellers returneres null
	 */
	T fjernTilfeldig();

	/**
	 * Fjerner og returnerer det gitte elementet fra mengden hvis det fins,
	 * ellers returneres null
	 * 
	 * @param element elemenentet som skal fjernes
	 * @return elementet som fjernes
	 */
	T fjern(T element) ;

	/**
	 * Danner unionen av denne mengden og parameteren 
	 * @param m2 er mengden det skal lages union med. 
	 * @return er union av mengden m2 og this-mengden
	 */
	MengdeADT<T> union(MengdeADT<T> m2);
	
	/**
	 * Finner snittet av to mengder
	 * @param m2 Den andre mengden
	 * @return Snittet av denne mengden og en annen
	 */
	MengdeADT<T> snitt(MengdeADT<T> m2);
	
	/**
	 * Finner differansen av to mengder
	 * @param m2 Den andre mengden
	 * @return Differansen mellom denne mengden og den andre
	 */
	MengdeADT<T> differans(MengdeADT<T> m2);

	/**
	 * Returnerer true dersom mengden inneholder gitt element
	 * @param element er det gitte elementet
	 * @return sann hvis lementet fins ellers usann
	 */
	boolean inneholder(T element);
	
	/**
	 * Tester om this-mengden og parameteren inneholder nøyaktig
	 * de samme elementene
	 * @param m2 mengden som testes
	 * @return sann hvis de to mengden er like ellers usann
	 */
	boolean erLik(MengdeADT<T> m2);

	/**
	 * Tester om mengden er tom (dvs. har 0 element)
	 * @return sann hvis mendgen er tom ellers usann
	 */
	boolean erTom();
	
	/**
	 * Finn antall elementer i mengden
	 * @return antall i mengden
	 */
	int antall();

	/**
	 * Oppretter et 'oppramsobjekt' som kan brukes til å gjennomgå
	 * alle elementer i mengden etter tur en gang
	 * @return et oppramsobjket
	 */
	Iterator<T> oppramser();

}// ADT
