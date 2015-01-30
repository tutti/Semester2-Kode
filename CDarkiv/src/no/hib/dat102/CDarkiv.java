package no.hib.dat102;

import no.hib.dat102.adt.CDarkivADT;

public class CDarkiv<T extends CD> implements CDarkivADT<T> {
	
	private CD[] cder;
	private int posisjon;
	
	/**
	 *  Oppretter et nytt arkiv med plass til et gitt antall CDer
	 * @param plass Antall CDer arkivet kan inneholde
	 */
	public CDarkiv(int plass) {
		cder = new CD[plass];
		posisjon = 0;
	}

	@Override
	public void leggTilCD(T cd) {
		for (int i=0; i<posisjon; ++i) {
			if (cder[i].getCdnummer() == cd.getCdnummer()) {
				throw new ArrayIndexOutOfBoundsException(
					"CD-nummeret finnes allerede i dette arkivet."
				);
			}
		}
		cder[posisjon] = cd;
		++posisjon;
	}

	@Override
	public void slettCD(int cdnummer) {
		int funnet = -1;
		for (int i=0; i<cder.length; ++i) {
			if (funnet >= 0) {
				cder[i-1] = cder[i];
			}
			else if (cder[i].getCdnummer() == cdnummer) {
				funnet = i;
			}
		}
		if (funnet >= 0) --posisjon;
	}

	@Override
	public CD[] finnCDer(String søk) {
		// Start med en tom array
		CD[] resultat = new CD[5]; 
		int antall = 0;
		
		// Søk gjennom alle CDer
		for (int i=0; i<posisjon; ++i) {
			CD cd = cder[i];
			if (cd.getNavn().indexOf(søk) >= 0) {
				// Hvis arrayen er for stor for et nytt element,
				// doble størrelsen dens
				if (antall > resultat.length) {
					CD[] nyResultat = new CD[antall*2];
					for (int j=0; j<antall; ++j) {
						nyResultat[j] = resultat[j];
					}
					resultat = nyResultat;
				}
				// Hvis CDens navn matcher søkeargumentet,
				// legg den inn i arrayen
				resultat[antall] = cd;
				++antall;
			}
		}
		
		// Hvis det er tomme elementer på slutten av arrayen,
		// reduser arrayens størrelse
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
	public String[] finnArtister(String søk) {
		// Start med en tom array
		String[] resultat = new String[5]; 
		int antall = 0;
		
		// Søk gjennom alle CDer
		for (int i=0; i<posisjon; ++i) {
			CD cd = cder[i];
			if (cd.getArtist().indexOf(søk) >= 0) {
				// Hvis arrayen er for stor for et nytt element,
				// doble størrelsen dens
				if (antall > resultat.length) {
					String[] nyResultat = new String[antall*2];
					for (int j=0; j<antall; ++j) {
						nyResultat[j] = resultat[j];
					}
					resultat = nyResultat;
				}
				// Hvis artistens navn matcher søkeargumentet,
				// legg den inn i arrayen
				resultat[antall] = cd.getArtist();
				++antall;
			}
		}
		
		// Hvis det er tomme elementer på slutten av arrayen,
		// reduser arrayens størrelse
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
		return posisjon;
	}

	@Override
	public int antallCDer(Sjanger sjanger) {
		int antall = 0;
		for (int i=0; i<posisjon; ++i) {
			CD cd = cder[i];
			if (cd.getSjanger() == sjanger)
				++antall;
		}
		return antall;
	}

}