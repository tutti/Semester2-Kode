package no.hib.dat102;
/**
 * Definerer interface til en datastruktur stabel.
 * 
 * @param <T> elementtypen
 */
public interface StabelADT<T> {

    /**
     * Legger på et element på ttoppe av stabelen.
     * 
     * @param element the element
     */
    void push(T element);

    /**
     * Fjerner og returnerer toppelementet fra stabelen.
     * 
     * @return elementet som stables av
     * @throws EmptyCollectionException når stabelen er tom
     */
    T pop() throws EmptyCollectionException;

    /**
     * Returnerer toppelemnetet uten å fjerne det fra stabelen.
     * 
     * @return elementet på toppen av stabelen
     * @throws EmptyCollectionException når stabelen er tom
     */
    T peek() throws EmptyCollectionException;

    /**
     * Returnerer sann hvis stabelen ikke inneholder noen elemnter.
     * 
     * @return sann hvis tom stabel
     */
    boolean erTom();

    /**
     * Returnerer antall elemnter på stabelen.
     * 
     * @return antall element på stabelen
     */
    int antall();

    
}

