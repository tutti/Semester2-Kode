package no.hib.dat102.kontakt;

public class Hobby {

	private String hobbyNavn;

	/**
	 * Lager et Hobby-objekt
	 * 
	 * @param navn
	 *            Navnet til hobbyen
	 */
	public Hobby(String navn) {
		hobbyNavn = navn;
	}

	@Override
	public String toString() {
		return '<' + hobbyNavn + '>';
	}

	@Override
	public boolean equals(Object h2) {
		if (!(h2 instanceof Hobby))
			return false;
		return hobbyNavn.equals(((Hobby) h2).hobbyNavn);
	}

}
