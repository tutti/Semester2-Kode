package no.hib.dat102.liste.kjedet;

import no.hib.dat102.liste.adt.ListeADT;

/**
 * Klasse for ordnet liste implementert med kjedet struktur En abstrakt brukes ofte ved arv der den er en superklasse.
 * En kan en ikke lage objekter av en slik klasse. En abstrakt klasse kan ha abstrakte metoder, men ikke nødvendigvis.
 * En klasse som har minst en abstrakt klasse må være abtstrakt
 *
 * @param <T> elementtypen
 */
public abstract class KjedetListe<T> implements ListeADT<T> {
    protected int antall;
    protected LinearNode<T> første, siste;

    /**
     * Lager en ny tom liste.
     */
    public KjedetListe() {
        antall = 0;
        første = null;
        siste = null;
    }

    // En abstrakt metode er en plassholder for en metode som
    // vil bli definert i avledet klasse. En abstrakt metode kan ikke være private

    @Override
    public abstract boolean inneholder(T element);

    @Override
    public abstract T fjern(T element);

    @Override
    public T fjernFørste() {
        T svar = null;
        if (!erTom()) {
            svar = første.getElement();
            første = første.getNeste();
            antall--;
        }
        if(erTom())
        	siste = null;
        
        return svar;
    }

    @Override
    public T fjernSiste() {
        T svar = null;
        if (!erTom()) {
            svar = siste.getElement();
            antall--;
            if (antall == 0) {
                første = null;
                siste = null;
            } else {
                siste = første;
                for (int i = 1; i < antall; i++) {
                    siste = siste.getNeste();
                }
                siste.setNeste(null);
            }
        }
        return svar;
    }

    @Override
    public T første() {
        T svar = null;
        if (!erTom()) {
            svar = første.getElement();
        }
        return svar;
    }

    @Override
    public T siste() {
        T svar = null;
        if (!erTom()) {
            svar = siste.getElement();
        }
        return svar;
    }

    @Override
    public boolean erTom() {
        return antall == 0;
    }

    @Override
    public int antall() {
        return antall;
    }
}
