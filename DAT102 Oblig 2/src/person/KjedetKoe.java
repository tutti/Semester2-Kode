package person;

//********************************************************************
// 
//  Representer en kjedet samling kø.
//********************************************************************

public class KjedetKoe<T> implements KoeADT<T> {
	private int antall;
	private LinearNode<T> front, bak;

	/******************************************************************
	 * Oppretter en tom kø.
	 ******************************************************************/
	public KjedetKoe() {
		antall = 0;
		front = bak = null;
	}

	/******************************************************************
	 * Legger inn et spesifisert element i køen.
	 ******************************************************************/
	public void innKoe(T element) {
		LinearNode<T> nyNode = new LinearNode<T>(element);

		if (erTom()) {
			front = nyNode;
		} else {
			bak.setNeste(nyNode);
		}
		bak = nyNode;
		antall++;
	}

	/******************************************************************
	 * Fjerner og returnerer elementet foran i køen.
	 ******************************************************************/
	public T utKoe() {
		T resultat = null;
		if (!erTom()) {
			resultat = front.getElement();
			front = front.getNeste();
			antall--;
		}

		if (erTom()) {
			bak = null;
		}
		return resultat;
	}

	/******************************************************************
	 * Returnerer elementet foran i køen ute å fjerne det.
	 ******************************************************************/
	public T foerste() {
		T resultat = null;
		if (!erTom()) {
			resultat = front.getElement();
		}
		return resultat;
	}

	/******************************************************************
	 * Returnerer sann hvis køen er tom, usann ellers.
	 ******************************************************************/
	public boolean erTom() {
		return (antall == 0);
	}

	/******************************************************************
	 * Returnerer antall elementer i køen.
	 ******************************************************************/
	public int antall() {
		return antall;
	}

	/******************************************************************
	 * Returnerer en strengrepresentasjon av elementene i køen..
	 ******************************************************************/
	public String toString() {
		String resultat = "";
		LinearNode<T> aktuell = front;

		while (aktuell != null) {
			resultat = resultat + (aktuell.getElement()).toString() + "\n";
			aktuell = aktuell.getNeste();
		}

		return resultat;
	}
}// class

