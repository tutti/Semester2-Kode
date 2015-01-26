package no.hib.dat102;
/**
 * Tabellimplementasjon av en stabel.
 * 
 * @param <T> elementtypen
 */
public class TabellStabel<T> implements StabelADT<T> {
    private static final int MAKS = 100;
    private int topp; // indikerer neste plass
    private T[] stabel;

    /**
     * Oppretter en tom stabel.
     */
    public TabellStabel() {
        topp = 0;
        stabel = (T[]) (new Object[MAKS]);
    }

    /**
     * Oppretter en tom stabel med en spesifisert kapasitet.
     * 
     * @param startKapasitet initiell størrelse
     */
    public TabellStabel(int startKapasitet) {
        topp = 0;
        stabel = (T[]) (new Object[startKapasitet]);
    }

    /**
     * Legger til det spesifiserte elementet på toppen av stabelen, utvider kapasitetet til stabelen hvis nødvendig.
     * 
     * @param element elementet
     */
    
    public void push(T element) {
        if (antall() == stabel.length) {
            utvid();
        }

        stabel[topp] = element;
        topp++;
    }

    /**
     * Fjerner toppelementet og returnerer en referanse til den. Hvis stabelen er tom fra før, så kastes unntak
     * 
     * @return elementet som avstables
     * @throws EmptyCollectionException tom stabel
     */    
    public T pop() throws EmptyCollectionException {
        T resultat = null;
        if (erTom()) {
            throw new EmptyCollectionException("Stabel");
        }

        topp--;
        resultat = stabel[topp];
        stabel[topp] = null;
        return resultat;
    }

    /**
     * Returnerer toppelementet uten å fjerne det.. Hvis stabelen er tom fra før, så kastes unntak
     * 
     * @return toppelement
     * @throws EmptyCollectionException tom stabel
     */    
    public T peek() throws EmptyCollectionException {
        if (erTom()) {
            throw new EmptyCollectionException("Stabel");
        }

        return stabel[topp - 1];
    }

    /**
     * Returnerer sann hvis stabelen er tom og usann ellers.
     * 
     * @return sann hvis tom stabel
     */    
    public boolean erTom() {
        return (topp == 0);
    }

    /**
     * Returnerer antall elementer.
     * 
     * @return antall elementer på stabelen
     */   
    public int antall() {
        return topp;
    }

   
    /**
     * Oppretter en ny tabell for å lagre innholdet.
     */
    private void utvid() {
        T[] hjelpeTabell = (T[]) (new Object[stabel.length * 2]);

        for (int indeks = 0; indeks < stabel.length; indeks++) {
            hjelpeTabell[indeks] = stabel[indeks];
        }

        stabel = hjelpeTabell;
    }
}
