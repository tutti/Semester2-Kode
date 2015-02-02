package main;

import java.util.HashMap;

/**
 * Banken håndterer alle penger i spillet, selv de spillerne selv eier.
 * Dette er ulikt virkelig monopol, der spillerne selv håndterer sine
 * egne penger.
 * @author tutti
 *
 */
public class Bank {
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
		
	}
	
	/**
	 * Betaler et beløp fra banken til en spiller
	 * @param spiller Spilleren som skal ha penger
	 * @param beløp Beløpet spilleren skal motta
	 */
	public static void motta(Spiller spiller, int beløp) {
		int nåBeløp = pengebeholdning.get(spiller);
		pengebeholdning.put(spiller, nåBeløp+beløp);
	}
	
	public static int hentPengebeløp(Spiller spiller) {
		return pengebeholdning.get(spiller);
	}
}
