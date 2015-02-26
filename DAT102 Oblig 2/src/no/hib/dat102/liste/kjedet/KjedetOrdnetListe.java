package no.hib.dat102.liste.kjedet;

import no.hib.dat102.liste.adt.OrdnetListeADT;

/**
 * Kjedet ordnet liste.
 *
 * @param <T>
 *            elementtypen
 */
public class KjedetOrdnetListe<T extends Comparable<T>> extends KjedetListe<T>
		implements OrdnetListeADT<T> {
	// Mangler konstruktør
	@Override
	public void leggTil(T element) {
		LinearNode<T> ny = new LinearNode<T>(element);

		if (antall == 0) {
			første = ny;
			siste = ny;
		} else if (element.compareTo(første.getElement()) <= 0) {
			ny.setNeste(første);
			første = ny;
		} else {
			LinearNode<T> aktuell = første;
			LinearNode<T> forrige = første;
			while (aktuell != null
					&& element.compareTo(aktuell.getElement()) > 0) {
				forrige = aktuell;
				aktuell = aktuell.getNeste();
			}
			ny.setNeste(aktuell);
			forrige.setNeste(ny);
			if (aktuell == null)
				siste = ny;
		}
		antall++;
	}

	//
	// Implementerer de to abstrakte metodene
	//

	@Override
	public boolean inneholder(T element) {
		LinearNode<T> denne = første;
		boolean resultat = false;
		while (denne != null && element.compareTo(denne.getElement()) > 0) {
			denne = denne.getNeste();
		}
		if (denne != null) {
			if (element.equals(denne.getElement())) {
				resultat = true;
			}
		} // ikke-funn
		return resultat;
	}

	@Override
	public T fjern(T element) {
		T svar = null;
		LinearNode<T> forrige = null, denne = første;
		while (denne != null && element.compareTo(denne.getElement()) > 0) {
			forrige = denne;
			denne = denne.getNeste();
		}
		if (denne != null && element.equals(denne.getElement())) { // funnet
			antall--;
			svar = denne.getElement();
			if (forrige == null) { // Første element
				første = første.getNeste();
				if (første == null) { // Tom liste
					siste = null;
				}
			} else { // Inni listen eller bak
				forrige.setNeste(denne.getNeste());
				if (denne == siste) { // bak
					siste = forrige;
				}
			}
		} // ikke-funn
		return svar;
	}

}
