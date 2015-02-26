package no.hib.dat102.liste.tabell;

import no.hib.dat102.liste.adt.*;

//********************************************************************
//  TabellOrdnetListe.java
//
//  Representerer en tabell av en ordnet liste.
//********************************************************************

public class TabellOrdnetListe<T extends Comparable<T>> extends TabellListe<T>
		implements OrdnetListeADT<T> {
	/******************************************************************
	 * Opprette en tom liste med standard kapasitet..
	 ******************************************************************/
	public TabellOrdnetListe() {
		super();
	}

	/******************************************************************
	 * Oppretter en tom liste med spesifisert kapasitet.
	 ******************************************************************/
	public TabellOrdnetListe(int startKapasitet) {
		// Konstruktøren til basisklassen må alltid kalles først!
		super(startKapasitet);
	}

	/******************************************************************
	 * Legger til et spesifisert element til denne listen. slik at listen
	 * fortsatt er sortert.
	 ******************************************************************/
	// OBS!leggTil-metoden er effektiv fordi den ikke sammenligner
	// unødvendig mange ganger. Den utnytter at elementene er ordnet.

	// Vi lager to metoder i denne klassen , fjern og inneholder som
	// overkjører superklassens

	public void leggTil(T element) {
		if (antall() == liste.length)
			utvid(); // arves
		// Klassen som skal bruke metoden må implementere Comparable
		int i = 0;
		while (i < bak && element.compareTo(liste[i]) > 0) {
			i++;
		}
		// i peker nå på plassen der elementet skal inn

		for (int j = bak; j > i; j--) {// Må lage plass ved å skifte bakover
			liste[j] = liste[j - 1]; // de elementene på plassene f.o.m indeks i
		}
		liste[i] = element;
		bak++;
	}

	// Implementer de to abstrakte metodene
	@Override
	public boolean inneholder(T element) {
		return (finn(element) != IKKE_FUNNET);
	}

	@Override
	//
	public T fjern(T element) {
		T resultat = null;

		int indeks = finn(element);

		if (indeks != IKKE_FUNNET) {
			resultat = liste[indeks];
			bak--;
			/** skifter elementene etter det vi fjernet en plass opp */
			for (int i = indeks; i < bak; i++)
				liste[i] = liste[i + 1];
			liste[bak] = null;
		}// if

		return resultat;
	}

	private int finn(T el) {
		if (bak == 0) return -1;
		if (el.compareTo(liste[0]) < 0) return -1;
		if (el.compareTo(liste[bak-1]) > 0) return -1;
		int nedreGrense = 0;
		int øvreGrense = bak;

		int søkepunkt;
		for (søkepunkt = (øvreGrense - nedreGrense) / 2; !liste[søkepunkt]
				.equals(el); søkepunkt = (øvreGrense + nedreGrense) / 2) {
			if (liste[søkepunkt].compareTo(el) > 0)
				øvreGrense = søkepunkt;
			else
				nedreGrense = søkepunkt;
			if (nedreGrense == øvreGrense) return -1;
		}

		return søkepunkt;
	}

}// class

