import java.util.Arrays;
import java.util.Random;

public class Oppg4<T extends Comparable<T>> {

	private static Random rnd = new Random();
	private static final int MIN = 10;
	private static final int ANTALL_ELEMENTER = 100000;
	
	public boolean testSortering(T[] tab) {
		for (int i=1; i<tab.length; ++i) {
			if (tab[i].compareTo(tab[i-1]) < 0) {
				return false;
			}
		}
		return true;
	}

	private void bytt(T[] tab, int pos1, int pos2) {
		T temp = tab[pos1];
		tab[pos1] = tab[pos2];
		tab[pos2] = temp;
	}

	public void innstikk(T[] tab) {
		for (int i = 0; i < tab.length; ++i) {
			// Gå gjennom tabellen
			for (int j = i; j > 0 && tab[j].compareTo(tab[j - 1]) <= 0; --j) {
				// Flytt hvert tall fremover i tabellen til det er på riktig
				// plass.
				bytt(tab, j, j - 1);
			}
		}
	}

	public void utvalg(T[] tab) {
		for (int i = 0; i < tab.length; ++i) {
			// Gå gjennom tabellen
			int minste = i;
			for (int j = i + 1; j < tab.length; ++j) {
				// Finn minste usorterte tall og bytt det med tallet som pekeren
				// står på
				if (tab[j].compareTo(tab[minste]) <= 0) {
					minste = j;
				}
			}
			bytt(tab, minste, i);
		}
	}

	public void boble(T[] tab) {
		// Gå gjennom stadig mindre deler av tabellen
		for (int slutt = tab.length; slutt > 0; --slutt) {
			for (int i = 0; i < slutt - 1; ++i) {
				// Hvis to tall ikke står i riktig rekkefølge, bytt om på dem
				if (tab[i].compareTo(tab[i + 1]) > 0) {
					bytt(tab, i, i + 1);
				}
			}
		}
	}

	public void quicksort(T[] tab) {
		// Kall til den rekursive versjonen
		quicksort(tab, 0, tab.length - 1);
	}

	private void quicksort(T[] tab, int start, int slutt) {
		if (start >= slutt)
			// Hvis det bare er ett (eller ingen) tall, er det ingenting å
			// sortere.
			return;

		// Bruk første tall som pivottall
		T pivot = tab[start];

		// Hold to pekere, samt et flagg for om noe har blitt funnet
		int venstrePos = start;
		int høyrePos = slutt + 1;
		boolean funnet = false;

		// Gå fra venstre, og se etter tall som er større enn pivot
		while (venstrePos < høyrePos && venstrePos < slutt) {
			++venstrePos;
			if (tab[venstrePos].compareTo(pivot) > 0) {
				// Hvis et slikt tall finnes, gå fra høyre for å finne et tall
				// som er mindre enn pivot
				while (høyrePos > venstrePos) {
					--høyrePos;
					if (tab[høyrePos].compareTo(pivot) < 0) {
						// Hvis et slikt tall også finnes uten at pekerne
						// krysser, bytt om på dem.
						bytt(tab, venstrePos, høyrePos);
						funnet = true;
						break;
					}
				}
			}
		}

		// Hvis minst ett tall ble byttet om, er venstrepekeren ett steg for
		// langt til høyre nå.
		if (funnet)
			--venstrePos;
		if (tab[start].compareTo(tab[venstrePos]) >= 0)
			bytt(tab, start, venstrePos);

		// Sorter de to halvdelene av tabellen
		quicksort(tab, start, venstrePos - 1);
		quicksort(tab, høyrePos, slutt);
	}

	public void flette(T[] tab) {
		// Kall til den rekursive versjonen
		flette(tab, 0, tab.length);
	}

	private void flette(T[] tab, int start, int slutt) {
		// Hvis tabellen består av ett tall, skal ingenting gjøres.
		if (slutt - start <= 1)
			return;

		// Hvis tabellen består av 2 tall, gjør en enkel sammenligning.
		if (slutt - start == 2) {
			if (tab[start].compareTo(tab[slutt - 1]) > 0)
				bytt(tab, start, slutt - 1);
			return;
		}

		// Hvis det er mer enn to tall i tabellen, gjør en full flettesortering.
		// Begynn med å finne halvveispunktet og flettesortere deltabellene.
		int halvveis = (start + slutt) / 2;
		flette(tab, start, halvveis);
		flette(tab, halvveis, slutt);

		// Deretter, flett sammen verdiene. Bruk en hjelpetabell til dette.
		Object[] temp = new Object[slutt - start];

		// To pekere - en starter helt til venstre, en annen i midten.
		int peker1 = start;
		int peker2 = halvveis;

		for (int i = 0; i < slutt - start; ++i) {
			if (peker1 >= halvveis) {
				// Hvis peker 1 har gått forbi sine verdier, er det kun peker 2s
				// verdier som gjenstår.
				temp[i] = tab[peker2++];
			} else if (peker2 >= slutt
					|| tab[peker1].compareTo(tab[peker2]) < 0) {
				// Hvis peker 2 har gått forbi sine verdier, er det kun peker 1s
				// verdier som gjenstår. Hvis ikke, sammenlign verdiene og sett
				// inn den minste.
				// (peker 2 har gått forbi og peker 1 har minst verdi har samme
				// resultat, så de kombineres her)
				temp[i] = tab[peker1++];
			} else {
				temp[i] = tab[peker2++];
			}
		}

		// Flytt verdiene fra hjelpetabellen inn i den ordentlige tabellen.
		for (int i = 0; i < temp.length; ++i) {
			tab[i + start] = (T) temp[i];
		}
	}

	public void kvikkSortNy(T[] data) {
		kvikkSort2(data, 0, data.length - 1);
		innstikk(data);
	}

	public void kvikkSort2(T[] tab, int start, int slutt) {
		if (start - slutt + 1 > MIN) {// antall elementer > MIN
			// Bruk første tall som pivottall
			T pivot = tab[start];

			// Hold to pekere, samt et flagg for om noe har blitt funnet
			int venstrePos = start;
			int høyrePos = slutt + 1;
			boolean funnet = false;

			// Gå fra venstre, og se etter tall som er større enn pivot
			while (venstrePos < høyrePos && venstrePos < slutt) {
				++venstrePos;
				if (tab[venstrePos].compareTo(pivot) > 0) {
					// Hvis et slikt tall finnes, gå fra høyre for å finne et
					// tall
					// som er mindre enn pivot
					while (høyrePos > venstrePos) {
						--høyrePos;
						if (tab[høyrePos].compareTo(pivot) < 0) {
							// Hvis et slikt tall også finnes uten at pekerne
							// krysser, bytt om på dem.
							bytt(tab, venstrePos, høyrePos);
							funnet = true;
							break;
						}
					}
				}
			}

			// Hvis minst ett tall ble byttet om, er venstrepekeren ett steg for
			// langt til høyre nå.
			if (funnet)
				--venstrePos;
			if (tab[start].compareTo(tab[venstrePos]) >= 0)
				bytt(tab, start, venstrePos);

			// Sorter de to halvdelene av tabellen
			kvikkSort2(tab, start, venstrePos - 1);
			kvikkSort2(tab, høyrePos, slutt);
		}

	}

	public static void main(String[] args) {
		Oppg4<Integer> sortering = new Oppg4<Integer>();

		Integer[] tilfeldig = new Integer[ANTALL_ELEMENTER];
		for (int i = 0; i < tilfeldig.length; ++i) {
			tilfeldig[i] = rnd.nextInt();
		}

		Integer[] kopi;
		long tid1, tid2;

		System.out.println("Tidmåling:");

		System.out.print("Innstikk:      ");
		kopi = Arrays.copyOf(tilfeldig, tilfeldig.length);
		tid1 = System.nanoTime();
		sortering.innstikk(kopi);
		tid2 = System.nanoTime();
		System.out.println(((tid2 - tid1) / 1000000) + "ms");
		if (!sortering.testSortering(kopi))
			System.out.println("FEIL");

		System.out.print("Utvalg:        ");
		kopi = Arrays.copyOf(tilfeldig, tilfeldig.length);
		tid1 = System.nanoTime();
		sortering.utvalg(kopi);
		tid2 = System.nanoTime();
		System.out.println(((tid2 - tid1) / 1000000) + "ms");
		if (!sortering.testSortering(kopi))
			System.out.println("FEIL");

		System.out.print("Boble:         ");
		kopi = Arrays.copyOf(tilfeldig, tilfeldig.length);
		tid1 = System.nanoTime();
		sortering.boble(kopi);
		tid2 = System.nanoTime();
		System.out.println(((tid2 - tid1) / 1000000) + "ms");
		if (!sortering.testSortering(kopi))
			System.out.println("FEIL");

		System.out.print("Quicksort (1): ");
		kopi = Arrays.copyOf(tilfeldig, tilfeldig.length);
		tid1 = System.nanoTime();
		sortering.quicksort(kopi);
		tid2 = System.nanoTime();
		System.out.println(((tid2 - tid1) / 1000000) + "ms");
		if (!sortering.testSortering(kopi))
			System.out.println("FEIL");

		System.out.print("Quicksort (2): ");
		kopi = Arrays.copyOf(tilfeldig, tilfeldig.length);
		tid1 = System.nanoTime();
		sortering.kvikkSortNy(kopi);
		tid2 = System.nanoTime();
		System.out.println(((tid2 - tid1) / 1000000) + "ms");
		if (!sortering.testSortering(kopi))
			System.out.println("FEIL");

		System.out.print("Flette:        ");
		kopi = Arrays.copyOf(tilfeldig, tilfeldig.length);
		tid1 = System.nanoTime();
		sortering.flette(kopi);
		tid2 = System.nanoTime();
		System.out.println(((tid2 - tid1) / 1000000) + "ms");
		if (!sortering.testSortering(kopi))
			System.out.println("FEIL");
	}
}
