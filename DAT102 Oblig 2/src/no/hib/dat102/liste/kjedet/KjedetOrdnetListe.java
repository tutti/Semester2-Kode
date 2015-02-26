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
	// Mangler konstrukt�r
	@Override
	public void leggTil(T element) {
		LinearNode<T> ny = new LinearNode<T>(element);

		if (antall == 0) {
			f�rste = ny;
			siste = ny;
		} else if (element.compareTo(f�rste.getElement()) <= 0) {
			ny.setNeste(f�rste);
			f�rste = ny;
		} else {
			LinearNode<T> aktuell = f�rste;
			LinearNode<T> forrige = f�rste;
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
		LinearNode<T> denne = f�rste;
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
		LinearNode<T> forrige = null, denne = f�rste;
		while (denne != null && element.compareTo(denne.getElement()) > 0) {
			forrige = denne;
			denne = denne.getNeste();
		}
		if (denne != null && element.equals(denne.getElement())) { // funnet
			antall--;
			svar = denne.getElement();
			if (forrige == null) { // F�rste element
				f�rste = f�rste.getNeste();
				if (f�rste == null) { // Tom liste
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
