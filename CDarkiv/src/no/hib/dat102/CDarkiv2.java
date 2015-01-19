package no.hib.dat102;

import no.hib.dat102.adt.CDarkivADT;

public class CDarkiv2<T extends CD> implements CDarkivADT<T> {
	
	private LinearNode<T> f�rste = null;
	private LinearNode<T> siste = null;
	private int antall = 0;
	private int maks = 0;
	
	/**
	 * Lager et nytt CD-arkiv med plass til et antall CD-er. Bruk 0 for ubegrenset plass.
	 * @param plass 0 for ubegrenset plass, eller et tall over 0 for � begrense plass til CDer.
	 */
	public CDarkiv2(int plass) {
		assert plass >= 0;
		maks = plass;
	}
	
	private boolean cdNummerIBruk(int cdnummer) {
		for (LinearNode<T> node=f�rste; node!=null; node=node.getNeste()) {
			if (node.getElement().getCdnummer() == cdnummer) return true;
		}
		return false;
	}

	@Override
	/**
	 * Legger til en CD i arkivet.
	 * @param cd CDen som skal legges til
	 */
	public void leggTilCD(T cd) {
		if (maks > 0 && antall >= maks) {
			throw new RuntimeException("Ikke plass til flere CDer");
		}
		if (cdNummerIBruk(cd.getCdnummer())) {
			throw new RuntimeException("CD-nummer er i bruk");
		}
		LinearNode<T> node = new LinearNode<T>(cd);
		if (f�rste == null) {
			f�rste = node;
		} else {
			siste.setNeste(node);
		}
		siste = node;
		++antall;
	}

	@Override
	/**
	 * Sletter en CD fra arkivet
	 * @param cdnummer Nummeret p� CDen som skal slettes
	 */
	public void slettCD(int cdnummer) {
		if (f�rste == null) return;
		if (f�rste.getElement().getCdnummer() == cdnummer) {
			f�rste = f�rste.getNeste();
			--antall;
			return;
		}
		LinearNode<T> forrige = f�rste;
		for (LinearNode<T> node=f�rste.getNeste(); node!=null; node=node.getNeste()) {
			if (node.getElement().getCdnummer() == cdnummer) {
				forrige.setNeste(node.getNeste());
				--antall;
				return;
			}
			forrige = node;
		}
	}

	@Override
	/**
	 * Finner CDer med navn som inneholder s�kestrengen
	 * @param s�k S�kestrengen
	 */
	public CD[] finnCDer(String s�k) {
		// Start med en tom array
		CD[] resultat = new CD[5];
		int antall = 0;
		
		// S�k gjennom alle CDer
		for (LinearNode<T> node=f�rste; node!=null; node=node.getNeste()) {
			if (node.getElement().getNavn().indexOf(s�k) >= 0) {
				// Hvis arrayen er for stor for et nytt element, doble st�rrelsen dens
				if (antall > resultat.length) {
					CD[] nyResultat = new CD[antall*2];
					for (int j=0; j<antall; ++j) {
						nyResultat[j] = resultat[j];
					}
					resultat = nyResultat;
				}
				// Hvis CDens navn matcher s�keargumentet, legg den inn i arrayen
				resultat[antall] = node.getElement();
				++antall;
			}
		}
		
		// Hvis det er tomme elementer p� slutten av arrayen, reduser arrayens st�rrelse
		if (antall < resultat.length) {
			CD[] nyResultat = new CD[antall];
			for (int i=0; i<antall; ++i) {
				nyResultat[i] = resultat[i];
			}
			resultat = nyResultat;
		}
		
		return resultat;
	}

	@Override
	public String[] finnArtister(String s�k) {
		// Start med en tom array
		String[] resultat = new String[5]; 
		int antall = 0;
		
		// S�k gjennom alle CDer
		for (LinearNode<T> node=f�rste; node!=null; node=node.getNeste()) {
			if (node.getElement().getArtist().indexOf(s�k) >= 0) {
				// Hvis arrayen er for stor for et nytt element, doble st�rrelsen dens
				if (antall > resultat.length) {
					String[] nyResultat = new String[antall*2];
					for (int j=0; j<antall; ++j) {
						nyResultat[j] = resultat[j];
					}
					resultat = nyResultat;
				}
				// Hvis CDens navn matcher s�keargumentet, legg den inn i arrayen
				resultat[antall] = node.getElement().getArtist();
				++antall;
			}
		}
		
		// Hvis det er tomme elementer p� slutten av arrayen, reduser arrayens st�rrelse
		if (antall < resultat.length) {
			String[] nyResultat = new String[antall];
			for (int i=0; i<antall; ++i) {
				nyResultat[i] = resultat[i];
			}
			resultat = nyResultat;
		}
		
		return resultat;
	}

	@Override
	public int antallCDer() {
		// TODO Auto-generated method stub
		return antall;
	}

	@Override
	public int antallCDer(Sjanger sjanger) {
		int antall = 0;
		for (LinearNode<T> node=f�rste; node!=null; node=node.getNeste()) 
			if (node.getElement().getSjanger() == sjanger)
				++antall;
		return antall;
	}

}
