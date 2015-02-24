package no.hib.dat102.kontakt;

public class Datakontakt {

	private Medlem[] medlemmer = new Medlem[8];
	private int antall = 0;

	private void utvidTabell() {
		Medlem[] nyMedlemmer = new Medlem[medlemmer.length * 2];
		for (int i = 0; i < antall; ++i) {
			nyMedlemmer[i] = medlemmer[i];
		}
		medlemmer = nyMedlemmer;
	}
	
	public Medlem hentMedlem(String navn) {
		int indeks = finnMedlemsIndeks(navn);
		if (indeks == -1) return null;
		return medlemmer[indeks];
	}
	
	public Medlem hentMedlem(int indeks) {
		return medlemmer[indeks];
	}
	
	public int antallMedlemmer() {
		return antall;
	}

	public void leggTilMedlem(Medlem medlem) {
		if (antall == medlemmer.length)
			utvidTabell();
		medlemmer[antall++] = medlem;
	}

	public int finnMedlemsIndeks(String navn) {
		for (int i = 0; i < antall; ++i) {
			if (medlemmer[i].hentNavn().equals(navn))
				return i;
		}
		return -1;
	}
	
	public int finnPartnerFor(String navn) {
		Medlem medlem = medlemmer[finnMedlemsIndeks(navn)];
		if (medlem.hentStatusIndeks() != -1) {
			return -1;
		}
		for (int i=0; i<antall; ++i) {
			if (medlem.passerTil(medlemmer[i])) {
				int indeks = finnMedlemsIndeks(navn);
				medlem.settStatusIndeks(i);
				medlemmer[i].settStatusIndeks(indeks);
				return i;
			}
		}
		return -1;
	}
	
	public void tilbakestillStatusIndeks(String navn) {
		Medlem medlem = medlemmer[finnMedlemsIndeks(navn)];
		int indeks = medlem.hentStatusIndeks();
		if (indeks != -1) {
			medlemmer[indeks].settStatusIndeks(-1);
			medlem.settStatusIndeks(-1);
		}
	}

}
