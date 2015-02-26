package no.hib.dat102.kontakt;

public class Datakontakt {

	private Medlem[] medlemmer = new Medlem[8];
	private int antall = 0;

	/**
	 * Utvider medlemstabellen
	 */
	private void utvidTabell() {
		Medlem[] nyMedlemmer = new Medlem[medlemmer.length * 2];
		for (int i = 0; i < antall; ++i) {
			nyMedlemmer[i] = medlemmer[i];
		}
		medlemmer = nyMedlemmer;
	}

	/**
	 * Finner et medlem
	 * 
	 * @param navn
	 *            Navnet til medlemmet
	 * @return Medlemmet
	 */
	public Medlem hentMedlem(String navn) {
		int indeks = finnMedlemsIndeks(navn);
		if (indeks == -1)
			return null;
		return medlemmer[indeks];
	}

	/**
	 * Finner et medlem
	 * 
	 * @param indeks
	 *            Indeksen til medlemmet
	 * @return Medlemmet
	 */
	public Medlem hentMedlem(int indeks) {
		return medlemmer[indeks];
	}

	/**
	 * Henter antall medlemmer
	 * 
	 * @return Antall medlemmer
	 */
	public int antallMedlemmer() {
		return antall;
	}

	/**
	 * Legger til et medlem
	 * 
	 * @param medlem
	 *            Medlemmet som skal legges til
	 */
	public void leggTilMedlem(Medlem medlem) {
		if (antall == medlemmer.length)
			utvidTabell();
		medlemmer[antall++] = medlem;
	}

	/**
	 * Finner indeksen til et medlem gitt navnet
	 * 
	 * @param navn
	 *            Navnet til medlemmet
	 * @return Medlemmets indeks
	 */
	public int finnMedlemsIndeks(String navn) {
		for (int i = 0; i < antall; ++i) {
			if (medlemmer[i].hentNavn().equals(navn))
				return i;
		}
		return -1;
	}

	/**
	 * Finner en gyldig partner for et medlem, og setter dem sammen
	 * 
	 * @param navn
	 *            Navnet på medlemmet du vil finne partner for
	 * @return Indeksen til partneren, eller -1 hvis ingen partner ble funnet
	 */
	public int finnPartnerFor(String navn) {
		Medlem medlem = medlemmer[finnMedlemsIndeks(navn)];
		if (medlem.hentStatusIndeks() != -1) {
			return -1;
		}
		for (int i = 0; i < antall; ++i) {
			if (medlem.passerTil(medlemmer[i])) {
				int indeks = finnMedlemsIndeks(navn);
				medlem.settStatusIndeks(i);
				medlemmer[i].settStatusIndeks(indeks);
				return i;
			}
		}
		return -1;
	}

	/**
	 * Avpartner et medlem Både medlemmet og medlemmets nåværende partner vil
	 * bli tilbakestilt til ingen partner.
	 * 
	 * @param navn
	 *            Navnet til medlemmet
	 */
	public void tilbakestillStatusIndeks(String navn) {
		Medlem medlem = medlemmer[finnMedlemsIndeks(navn)];
		int indeks = medlem.hentStatusIndeks();
		if (indeks != -1) {
			medlemmer[indeks].settStatusIndeks(-1);
			medlem.settStatusIndeks(-1);
		}
	}

}
