package no.hib.dat102.kontakt;

import java.util.Iterator;
import no.hib.dat102.mengde.kjedet.KjedetMengde;

public class Medlem {
	private String navn;
	private KjedetMengde<Hobby> hobbyer;
	private int statusIndeks = -1;

	/**
	 * Lager et nytt medlem
	 * 
	 * @param navn
	 *            Medlemmets navn
	 */
	public Medlem(String navn) {
		settNavn(navn);
		hobbyer = new KjedetMengde<Hobby>();
	}

	/**
	 * Endrer navnet til medlemmet
	 * 
	 * @param navn
	 *            Medlemmets nye navn
	 */
	public void settNavn(String navn) {
		this.navn = navn;
	}

	/**
	 * Henter navnet til medlemmet
	 * 
	 * @return Navnet til medlemmet
	 */
	public String hentNavn() {
		return navn;
	}

	/**
	 * Endrer medlemmets statusindeks
	 * 
	 * @param indeks
	 *            Medlemmets nye statusindeks
	 */
	public void settStatusIndeks(int indeks) {
		statusIndeks = indeks;
	}

	/**
	 * Henter medlemmets statusindeks
	 * 
	 * @return Medlemmes statusindeks
	 */
	public int hentStatusIndeks() {
		return statusIndeks;
	}

	/**
	 * Legger til en hobby til medlemmet
	 * 
	 * @param hobby
	 *            Hobbyen som skal legges til
	 */
	public void leggTilHobby(Hobby hobby) {
		hobbyer.leggTil(hobby);
	}

	/**
	 * Sjekker om medlemmet har en gitt hobby
	 * 
	 * @param hobby
	 *            Hobbyen som skal sjekkes
	 * @return Om medlemmet har hobbyen
	 */
	public boolean harHobby(Hobby hobby) {
		return hobbyer.inneholder(hobby);
	}

	/**
	 * Fjerner en hobby fra en medlem
	 * 
	 * @param hobby
	 *            Hobbyen som skal fjernes
	 */
	public void fjernHobby(Hobby hobby) {
		hobbyer.fjern(hobby);
	}

	/**
	 * Returnerer en Iterator over medlemmets hobbyer
	 * 
	 * @return Medlemmets hobbyer
	 */
	public Iterator<Hobby> hentHobbyer() {
		return hobbyer.oppramser();
	}

	/**
	 * Sjekker om medlemmet passer til et annet medlem
	 * 
	 * @param m2
	 *            Det andre medlemmet
	 * @return Om medlemmene passer sammen
	 */
	public boolean passerTil(Medlem m2) {
		if (this.equals(m2))
			return false;
		return hobbyer.erLik(m2.hobbyer);
	}
}
