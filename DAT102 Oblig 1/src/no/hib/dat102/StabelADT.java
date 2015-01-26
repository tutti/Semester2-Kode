package no.hib.dat102;
/**
 * Definerer interface til en datastruktur stabel.
 * 
 * @param <T> elementtypen
 */
public interface StabelADT<T> {

    /**
     * Legger p� et element p� ttoppe av stabelen.
     * 
     * @param element the element
     */
    void push(T element);

    /**
     * Fjerner og returnerer toppelementet fra stabelen.
     * 
     * @return elementet som stables av
     * @throws EmptyCollectionException n�r stabelen er tom
     */
    T pop() throws EmptyCollectionException;

    /**
     * Returnerer toppelemnetet uten � fjerne det fra stabelen.
     * 
     * @return elementet p� toppen av stabelen
     * @throws EmptyCollectionException n�r stabelen er tom
     */
    T peek() throws EmptyCollectionException;

    /**
     * Returnerer sann hvis stabelen ikke inneholder noen elemnter.
     * 
     * @return sann hvis tom stabel
     */
    boolean erTom();

    /**
     * Returnerer antall elemnter p� stabelen.
     * 
     * @return antall element p� stabelen
     */
    int antall();

    
}

