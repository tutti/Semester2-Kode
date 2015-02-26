package no.hib.dat102.mengde.tabell;

import no.hib.dat102.mengde.adt.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class TabellMengde<T> implements MengdeADT<T> {
	// ADT-en Mengde implementert som tabell
	//
	private final static Random tilf = new Random();
	private final static int STDK = 100;
	private int antall;
	private T[] tab;

	public TabellMengde() {
		this(STDK);
	}

	public TabellMengde(int start) {
		antall = 0;
		tab = (T[]) (new Object[start]);
	}

	@Override
	public int antall() {
		return antall;
	}

	@Override
	public boolean erTom() {
		return (antall == 0);
	}

	@Override
	public void leggTil(T element) {
		if (inneholder(element))
			return;
		settInn(element);
	}
	
	private void settInn(T element) {
		if (antall == tab.length)
			utvidKapasitet();
		tab[antall++] = element;
	}

	private void utvidKapasitet() {
		T[] hjelpetabell = (T[]) (new Object[2 * tab.length]);
		for (int i = 0; i < tab.length; i++) {
			hjelpetabell[i] = tab[i];
		}
		tab = hjelpetabell;
	}

	@Override
	public T fjernTilfeldig() {
		T svar = null;
		if (antall > 0) {
			int indeks = tilf.nextInt(antall);
			svar = tab[indeks];
			tab[indeks] = tab[antall - 1];
			antall--;
		}
		return svar;
	}

	@Override
	public T fjern(T element) {
		// Søker etter og fjerner element.Retur med null ved ikke-funn
		//
		int pos = -1;
		T svar = null;
		if (!erTom()) {
			for (int i = 0; (i < antall) && (pos == -1); i++) {
				if (tab[i].equals(element))
					pos = i;
			}

			if (pos != -1) {
				svar = tab[pos];
				tab[pos] = tab[antall - 1];
				tab[antall - 1] = null;
				antall--;

			}
		}
		return svar;
	}

	@Override
	public MengdeADT<T> union(MengdeADT<T> m2) {
		
		// Ny utregning (svar på 1g og 1h):
		// Denne metoden bruker HashMap for å redusere kompleksiteten til O(n).
		HashMap<T, Boolean> map = new HashMap<T, Boolean>();

		// Gå gjennom denne mengden og marker alle elementene i HashMap'et.
		for (int i=0; i<antall; ++i) {
			map.put(tab[i], true);
		}

		// Gå gjennom den andre mengden og marker alle elementene der også.
		// Elementer som finnes i begge vil markeres to ganger, men vil fortsatt
		// bare finnes en gang.
		for (Iterator<T> teller = m2.oppramser(); teller.hasNext(); map.put(
				teller.next(), true))
			;

		// Opprett et nytt mengdeobjekt, legg inn alle elementene i HashMap'et,
		// og returner det.
		TabellMengde<T> begge = new TabellMengde<T>();
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
	}

	@Override
	public MengdeADT<T> snitt(MengdeADT<T> m2) {
		HashMap<T, Boolean> map = new HashMap<T, Boolean>();

		// Gå gjennom denne mengden og marker alle elementene i HashMap'et som
		// false.
		for (int i=0; i<antall; ++i) {
			map.put(tab[i], false);
		}

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
		TabellMengde<T> snitt = new TabellMengde<T>();
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
		for (int i=0; i<antall; ++i) {
			map.put(tab[i], true);
		}

		// Gå gjennom den andre mengden og fjern alle elementer hvis de finnes.
		// Etter dette vil ingen av elementene som finnes i den andre mengden
		// være markert.
		for (Iterator<T> teller = m2.oppramser(); teller.hasNext(); map
				.remove(teller.next()))
			;

		// Opprett et nytt mengdeobjekt, legg inn alle elementene i HashMap'et,
		// og returner det.
		TabellMengde<T> differans = new TabellMengde<T>();
		for (T element : map.keySet()) {
			differans.settInn(element);
		}
		return differans;
	}

	@Override
	public boolean inneholder(T element) {
		int pos = -1;
		for (int i = 0; (i < antall) && (pos == -1); i++) {
			if (tab[i].equals(element))
				pos = i;
		}
		return (pos != -1);
	}

	@Override
	public boolean erLik(MengdeADT<T> m2) {
		boolean likeMengder = true;
		T element;

		if (antall() == m2.antall()) {
			Iterator<T> teller = m2.oppramser();

			while (likeMengder && teller.hasNext()) {
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
	public void leggTilAlle(MengdeADT<T> m2) {
		Iterator<T> teller = m2.oppramser();
		while (teller.hasNext())
			leggTil(teller.next());
	}

	@Override
	public Iterator<T> oppramser() {
		return new TabellIterator<T>(tab, antall);
	}

}// class
