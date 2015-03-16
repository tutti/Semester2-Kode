//********************************************************************
//  DobbeKjedetlOrdnetListeS.java       
//
//  Representerer en dobbeltkedetliste med midtpeker.
//********************************************************************
;

class DobbelKjedetOrdnetListeS<T extends Comparable<T>> {

	private DobbelNode<T> f�rste;
	private DobbelNode<T> midten;
	private DobbelNode<T> siste;
	private int antall;

	/******************************************************************
	 * Oppretter en tom liste.
	 ******************************************************************/
	// Konstrukt�r

	DobbelKjedetOrdnetListeS(T minVerdi, T maksVerdi) {

		// F�rste node
		DobbelNode<T> nyNode1 = new DobbelNode<T>(minVerdi);
		// nyNode1.settElement(minVerdi);
		f�rste = nyNode1;
		midten = f�rste;

		// Siste node
		DobbelNode<T> nyNode2 = new DobbelNode<T>();
		nyNode2.setElement(maksVerdi);
		nyNode1.setNeste(nyNode2);
		nyNode2.setForrige(nyNode1);
		siste = nyNode2;

		antall = 0;
	}

	// ***********************************************************************************
	// *
	// *
	// ***********************************************************************************

	public void leggTil(T el) {
		// TODO... Fyll ut

		// Setter inn ordnet f�r den noden p peker p�
		DobbelNode<T> p;

		if ((el.compareTo(f�rste.getElement()) <= 0)
				|| (el.compareTo(siste.getElement()) >= 0)) {
			// Ugyldig. Alternativt kan vi ha det som et forkrav!
			System.out.println("Ugyldig verdi. verdi > " + f�rste.getElement()
					+ "verdi < " + siste.getElement());

		} else { // Kun lovlige verdier

			antall++;

			if (el.compareTo(midten.getElement()) >= 0) {// Finn plass i siste
															// halvdel
				p = midten.getNeste();
			} else { // Finn plass i f�rste halvdel
				p = f�rste.getNeste();
			}

			while (el.compareTo(p.getElement()) >= 0) {
				p = p.getNeste();
			}

			// Setter inn:
			// Innsett foran noden som p peker p�
			DobbelNode<T> node = new DobbelNode<T>(el);
			node.setNeste(p);
			node.setForrige(p.getForrige());
			p.getForrige().setNeste(node);
			p.setForrige(node);

			// Oppdaterer ny midten
			nyMidten();

		}// else lovlige

	}//

	// **********************************************************************************
	// Hjelpemetode til � finne ny midten
	// Mindre effektiv fordi vi m� gjennomgp� ca halve listen
	// *********************************************************************************
	private void nyMidten() {
		int midtNR = antall / 2;
		DobbelNode<T> p = f�rste.getNeste();
		for (int i = 1; i <= midtNR; i++) {
			p = p.getNeste();
		}
		midten = p;
	}//

	// ***********************************************************************************
	// *
	// *
	// ***********************************************************************************
	public boolean fins(T el) {
		boolean funnet = false;
		DobbelNode<T> p;
		if ((el.compareTo(f�rste.getElement()) <= 0)
				|| (el.compareTo(siste.getElement()) >= 0)) {
			// Ugyldig. Alternativt kan vi ha det som et forkrav!
			System.out.println("Ugyldig verdi. verdi > " + f�rste.getElement()
					+ "verdi < " + siste.getElement());

		} else { // Kun lovlige verdier
			if (el.compareTo(midten.getElement()) >= 0) { // Let i siste halvdel
				p = midten; // Midten defineres � tilh�re siste del
			} else { // Let i f�rste halvdel
				p = f�rste.getNeste();
			}

			while (el.compareTo(p.getElement()) > 0) {
				p = p.getNeste();
			}// while

			// Test p� funnet
			if (el.compareTo(p.getElement()) == 0) {
				funnet = true;
			}
		}// else
		return funnet;
	}//

	// ***********************************************************************************
	// *
	// *
	// ***********************************************************************************

	public T fjern(T el) {
		T resultat = null;
		DobbelNode<T> p = null;
		boolean funnet = false;

		if ((el.compareTo(f�rste.getElement()) <= 0)
				|| (el.compareTo(siste.getElement()) >= 0)) {
			// Ugyldig. Alternativt kan vi ha det som et forkrav!
			System.out.println("Ugyldig verdi. verdi > " + f�rste.getElement()
					+ "verdi < " + siste.getElement());

		} else { // Kun lovlige verdier
			p = finn(el);
			if (p != null) funnet = true;

			if (funnet) {
				// Tar ut
				antall = antall - 1;
				p.getNeste().setForrige(p.getForrige());
				p.getForrige().setNeste(p.getNeste());

				// Oppadtere midten
				nyMidten();

				resultat = p.getElement();

			}// funnet

		}// lovlige
		return resultat;
	}//
		// ***********************************************************************************
		// *

	// *
	// ***********************************************************************************

	public DobbelNode<T> finn(T el) {
		DobbelNode<T> p;
		DobbelNode<T> slutt;
		DobbelNode<T> funnet = null;
		if (el.compareTo(midten.getElement()) >= 0) {
			p = midten;
			slutt = siste;
		} else {
			p = f�rste;
			slutt = midten;
		}
		
		while (funnet == null && p != slutt) {
			if (el.equals(p.getElement())) {
				funnet = p;
			}
			p = p.getNeste();
		}
		
		return funnet;
	}

	public void skrivListe() {
		DobbelNode<T> p = f�rste;

		while (p != null) {
			System.out.print(p.getElement() + " ");
			p = p.getNeste();
		}

		System.out.println("Forste:" + f�rste.getElement() + " Midten:"
				+ midten.getElement() + " Siste:" + siste.getElement());
		System.out.println();

	}//

}// class

