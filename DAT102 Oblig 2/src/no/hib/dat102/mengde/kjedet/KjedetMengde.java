package no.hib.dat102.mengde.kjedet;

import no.hib.dat102.mengde.adt.*;
//********************************************************************
// Kjedet implementasjon av en mengde. 
//********************************************************************
import java.util.*;

public class KjedetMengde<T> implements MengdeADT<T> {
	private static Random rand = new Random();
	private int antall; // antall elementer i mengden
	private LinearNode<T> start;

	/**
	 * Oppretter en tom mengde.
	 */
	public KjedetMengde() {
		antall = 0;
		start = null;
	}//

	@Override
	public void leggTil(T element) {
		if (inneholder(element))
			return;
		settInn(element);
	}

	private void settInn(T element) {
		LinearNode<T> ny = new LinearNode<T>(element);
		ny.setNeste(start);
		start = ny;
		++antall;
	}

	@Override
	public void leggTilAlle(MengdeADT<T> m2) {
		Iterator<T> teller = m2.oppramser();
		while (teller.hasNext()) {
			leggTil(teller.next());
		}
	}

	@Override
	public T fjernTilfeldig() {
		LinearNode<T> forgjenger, aktuell;
		T resultat = null;
		if (!erTom()) {
			int valg = rand.nextInt(antall) + 1;
			if (valg == 1) {
				resultat = start.getElement();
				start = start.getNeste();
			} else {
				forgjenger = start;
				for (int nr = 2; nr < valg; nr++) {
					forgjenger = forgjenger.getNeste();
				}
				aktuell = forgjenger.getNeste();
				resultat = aktuell.getElement();
				forgjenger.setNeste(aktuell.getNeste());
			}
			antall--;
		}// if
		return resultat;
	}//

	@Override
	public T fjern(T element) {
		boolean funnet = false;
		LinearNode<T> forgjenger, aktuell = null;
		T resultat = null;
		if (!erTom()) {
			if (start.getElement().equals(element)) {// Sletter foran
				resultat = start.getElement();
				start = start.getNeste();
				antall--;
			} else { // Gjennomgår den kjedete strukturen
				forgjenger = start;
				aktuell = start.getNeste();
				for (int søk = 1; søk < antall && !funnet; søk++) {
					if (aktuell.getElement().equals(element))
						funnet = true;
					else {
						forgjenger = aktuell;
						aktuell = aktuell.getNeste();
					}
				}
				if (funnet) {
					resultat = aktuell.getElement(); // Sletter midt inni eller
					// bak
					forgjenger.setNeste(aktuell.getNeste());
					antall--;
				}
			}// if –else
		}// if ikke-tom
		return resultat;
	}//

	@Override
	public MengdeADT<T> union(MengdeADT<T> m2) {
		// Gammel utregning (kommentert ut):
		// KjedetMengde<T> begge = new KjedetMengde<T>();
		// LinearNode<T> aktuell = start;
		// while (aktuell != null) {
		// begge.leggTil(aktuell.getElement());
		// aktuell = aktuell.getNeste();
		// }// while
		// Iterator<T> teller = m2.oppramser();
		// while (teller.hasNext())
		// begge.leggTil(teller.next());
		// return begge;

		// Ny utregning (svar på 1g og 1h):
		// Denne metoden bruker HashMap for å redusere kompleksiteten til O(n).
		HashMap<T, Boolean> map = new HashMap<T, Boolean>();

		// Gå gjennom denne mengden og marker alle elementene i HashMap'et.
		for (LinearNode<T> aktuell = start; aktuell != null; aktuell = aktuell
				.getNeste())
			map.put(aktuell.getElement(), true);

		// Gå gjennom den andre mengden og marker alle elementene der også.
		// Elementer som finnes i begge vil markeres to ganger, men vil fortsatt
		// bare finnes en gang.
		for (Iterator<T> teller = m2.oppramser(); teller.hasNext(); map.put(
				teller.next(), true))
			;

		// Opprett et nytt mengdeobjekt, legg inn alle elementene i HashMap'et,
		// og returner det.
		KjedetMengde<T> begge = new KjedetMengde<T>();
		for (T element : map.keySet()) {
			begge.settInn(element);
		}
		return begge;

		// Dette forbedrer koden i både del 1 og del 2. Ved å bruke en HashMap,
		// er koden redusert til O(n), siden den kun må gjennom hvert element i
		// hver mengde 2 ganger (1 gang for å legge inn i HashMap, 1 gang for å
		// gjøre om igjen til mengde). Det er ikke nødvendig å sjekke om et
		// element allerede har blitt plassert i unionen, siden i et HashMap vil
		// dette bare føre til at nøkkelen blir overskrevet med seg selv.
	}//

	@Override
	public MengdeADT<T> snitt(MengdeADT<T> m2) {
		HashMap<T, Boolean> map = new HashMap<T, Boolean>();

		// Gå gjennom denne mengden og marker alle elementene i HashMap'et som
		// false.
		for (LinearNode<T> aktuell = start; aktuell != null; aktuell = aktuell
				.getNeste())
			map.put(aktuell.getElement(), false);

		// Gå gjennom den andre mengden og marker alle elementer som allerede
		// finnes der som true. Dette vil føre til at kun elementer som finnes i
		// begge mengdene vil være markert true.
		for (Iterator<T> teller = m2.oppramser(); teller.hasNext();) {
			T element = teller.next();
			if (map.containsKey(element) && map.get(element).equals(false))
				map.put(element, true);
		}

		// Opprett et nytt mengdeobjekt, legg inn alle elementene som er markert
		// true, og returner mengden.
		KjedetMengde<T> snitt = new KjedetMengde<T>();
		for (T element : map.keySet()) {
			if (map.get(element))
				snitt.settInn(element);
		}
		return snitt;
	}

	@Override
	public MengdeADT<T> differans(MengdeADT<T> m2) {
		HashMap<T, Boolean> map = new HashMap<T, Boolean>();

		// Gå gjennom denne mengden og marker alle elementene i HashMap'et.
		for (LinearNode<T> aktuell = start; aktuell != null; aktuell = aktuell
				.getNeste())
			map.put(aktuell.getElement(), true);

		// Gå gjennom den andre mengden og fjern alle elementer hvis de finnes.
		// Etter dette vil ingen av elementene som finnes i den andre mengden
		// være markert.
		for (Iterator<T> teller = m2.oppramser(); teller.hasNext(); map
				.remove(teller.next()))
			;

		// Opprett et nytt mengdeobjekt, legg inn alle elementene i HashMap'et,
		// og returner det.
		KjedetMengde<T> differans = new KjedetMengde<T>();
		for (T element : map.keySet()) {
			differans.settInn(element);
		}
		return differans;
	}

	@Override
	public boolean inneholder(T element) {
		boolean funnet = false;
		LinearNode<T> aktuell = start;
		for (int søk = 0; søk < antall && !funnet; søk++) {
			if (aktuell.getElement().equals(element))
				funnet = true;
			else
				aktuell = aktuell.getNeste();
		}
		return funnet;
	}

	@Override
	public boolean erLik(MengdeADT<T> m2) {
		boolean likeMengder = true;
		T element = null;
		if (antall() == m2.antall()) {
			Iterator<T> teller = m2.oppramser();
			while (teller.hasNext() && likeMengder) {
				element = teller.next();
				if (!this.inneholder(element)) {
					likeMengder = false;
				}// if
			}// while
		}// if
		else {
			likeMengder = false;
		}
		return likeMengder;
	}

	@Override
	public boolean erTom() {
		return (antall() == 0);
	}

	@Override
	public int antall() {
		return antall;
	}

	@Override
	public Iterator<T> oppramser() {
		return new KjedetIterator<T>(start);
	}

}// class
