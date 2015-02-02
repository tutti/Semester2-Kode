package main;

import java.util.HashMap;

/**
 * Banken h�ndterer alle penger i spillet, selv de spillerne selv eier.
 * Dette er ulikt virkelig monopol, der spillerne selv h�ndterer sine
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
	 * Betaler et bel�p fra en spiller til banken
	 * @param spiller Spilleren som skal betale
	 * @param bel�p Bel�pet som skal betales
	 */
	public static void betale(Spiller spiller, int bel�p) {
		int n�Bel�p = pengebeholdning.get(spiller);
		if (n�Bel�p < bel�p) {
			spiller.konkurs();
			return;
		}
		pengebeholdning.put(spiller, n�Bel�p-bel�p);
	}
	
	/**
	 * Betaler et bel�p fra en spiller til en annen
	 * @param spiller1 Spilleren som skal betale
	 * @param spiller2 Spilleren som skal motta pengene
	 * @param bel�p Bel�pet som skal betales
	 */
	public static void betale(Spiller spiller1, Spiller spiller2, int bel�p) {
		int n�Bel�p1 = pengebeholdning.get(spiller1);
		int n�Bel�p2 = pengebeholdning.get(spiller2);
		
		if (n�Bel�p1 < bel�p) {
			spiller1.konkurs();
			pengebeholdning.put(spiller2, n�Bel�p2+n�Bel�p1);
			return;
		}
		pengebeholdning.put(spiller1, n�Bel�p1-bel�p);
		pengebeholdning.put(spiller2, n�Bel�p2+bel�p);
		
	}
	
	/**
	 * Betaler et bel�p fra banken til en spiller
	 * @param spiller Spilleren som skal ha penger
	 * @param bel�p Bel�pet spilleren skal motta
	 */
	public static void motta(Spiller spiller, int bel�p) {
		int n�Bel�p = pengebeholdning.get(spiller);
		pengebeholdning.put(spiller, n�Bel�p+bel�p);
	}
	
	public static int hentPengebel�p(Spiller spiller) {
		return pengebeholdning.get(spiller);
	}
}
