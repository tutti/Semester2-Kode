package main;

import java.util.HashMap;

import adt.UIADT;

/**
 * Banken håndterer alle penger i spillet, selv de spillerne selv eier.
 * Dette er ulikt virkelig monopol, der spillerne selv håndterer sine
 * egne penger.
 * @author tutti
 *
 */
public class Bank {
	public static final int MULTIPLIKATOR = 20;
	
	private static HashMap<Spiller, Integer> pengebeholdning;
	
	public static void tilbakestill(Spiller[] spillere) {
		pengebeholdning = new HashMap<Spiller, Integer>();
		for (Spiller spiller : spillere) {
			pengebeholdning.put(spiller, 1500);
		}
	}
	
	/**
	 * Betaler et beløp fra en spiller til banken
	 * @param spiller Spilleren som skal betale
	 * @param beløp Beløpet som skal betales
	 */
	public static void betale(Spiller spiller, int beløp) {
		int nåBeløp = pengebeholdning.get(spiller);
		if (nåBeløp < beløp) {
			spiller.konkurs();
			return;
		}
		pengebeholdning.put(spiller, nåBeløp-beløp);
		Spill.ui.fortell(UIADT.BANK_PENGER_ENDRET, spiller);
	}
	
	/**
	 * Betaler et beløp fra en spiller til en annen
	 * @param spiller1 Spilleren som skal betale
	 * @param spiller2 Spilleren som skal motta pengene
	 * @param beløp Beløpet som skal betales
	 */
	public static void betale(Spiller spiller1, Spiller spiller2, int beløp) {
		int nåBeløp1 = pengebeholdning.get(spiller1);
		int nåBeløp2 = pengebeholdning.get(spiller2);
		
		if (nåBeløp1 < beløp) {
			spiller1.konkurs();
			pengebeholdning.put(spiller2, nåBeløp2+nåBeløp1);
			return;
		}
		pengebeholdning.put(spiller1, nåBeløp1-beløp);
		pengebeholdning.put(spiller2, nåBeløp2+beløp);
		Spill.ui.fortell(UIADT.BANK_PENGER_ENDRET, spiller1);
		Spill.ui.fortell(UIADT.BANK_PENGER_ENDRET, spiller2);
		
	}
	
	/**
	 * Betaler et beløp fra banken til en spiller
	 * @param spiller Spilleren som skal ha penger
	 * @param beløp Beløpet spilleren skal motta
	 */
	public static void motta(Spiller spiller, int beløp) {
		int nåBeløp = pengebeholdning.get(spiller);
		pengebeholdning.put(spiller, nåBeløp+beløp);
		Spill.ui.fortell(UIADT.BANK_PENGER_ENDRET, spiller);
	}
	
	/**
	 * Henter pengebeløpet en spiller eier. Endrer ingenting.
	 * @param spiller Spilleren som eier pengene
	 * @return Pengene spilleren eier
	 */
	public static int hentPengebeløp(Spiller spiller) {
		return pengebeholdning.get(spiller);
	}
	
	/**
	 * Skriver ut et pengebeløp multiplisert med spillets multiplikator
	 * og med lokalt prefix/suffix.
	 * @param pengebeløp
	 * @return
	 */
	public static String skrivUtPengebeløp(int pengebeløp) {
		int modifisertBeløp = pengebeløp * MULTIPLIKATOR;
		return "kr " + modifisertBeløp;
	}
}
