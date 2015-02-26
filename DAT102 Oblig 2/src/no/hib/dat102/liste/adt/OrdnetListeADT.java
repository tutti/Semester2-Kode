package no.hib.dat102.liste.adt;

/**
 * Definerer grensenitt til en ordnet samling liste. Bare elementer som kan sammenlignes blir lagret. Nytt element blir
 * lagt til en på riktig plass slik at listen er ordnet også etter at det nye elementet er lagt til.
 *
 * Klassen implementerer interfacet' Comparable.Parameter er T. Metode leggTil trenger å sammenligen elementene. Arv
 * mellom ADT'er
 *
 * @param <T> elementtypen
 */
public interface OrdnetListeADT<T extends Comparable<T>> extends ListeADT<T> {

    /**
     * Legger til det spesifiserte elementet til denne listen.
     *
     * @param element de nye elementet
     */
    void leggTil(T element);
}
