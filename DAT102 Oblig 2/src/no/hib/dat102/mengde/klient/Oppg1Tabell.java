package no.hib.dat102.mengde.klient;

import java.util.Scanner;

import no.hib.dat102.mengde.adt.MengdeADT;
import no.hib.dat102.mengde.tabell.TabellMengde;

public class Oppg1Tabell {
	private static final String DIVISOR = "------------------------------";

	public static void main(String[] args) {
		String[] ordliste = { "en", "to", "tre", "fire", "fem", "seks", "sju",
				"åtte", "ni", "ti" };

		MengdeADT<String> t1 = new TabellMengde<String>();
		MengdeADT<String> t2 = new TabellMengde<String>();
		for (String s : ordliste) {
			t1.leggTil(s);
		}

		Scanner keyboard = new Scanner(System.in);
		System.out.println("Skriv inn ord, avslutt med zzz:");
		for (String line = keyboard.nextLine(); !line.equals("zzz"); line = keyboard
				.nextLine()) {
			t2.leggTil(line);
			System.out.println('"' + line + "\" er "
					+ (t1.inneholder(line) ? "" : "ikke ") + "i ordlisten.");

		}

		System.out.println(DIVISOR);
		System.out.println("UNION:");

		MengdeADT<String> union = t1.union(t2);
		while (!union.erTom())
			System.out.println(union.fjernTilfeldig());

		System.out.println(DIVISOR);
		System.out.println("SNITT:");

		MengdeADT<String> snitt = t1.snitt(t2);
		while (!snitt.erTom())
			System.out.println(snitt.fjernTilfeldig());

		System.out.println(DIVISOR);
		System.out.println("DIFFERANS:");

		MengdeADT<String> differans = t1.differans(t2);
		while (!differans.erTom())
			System.out.println(differans.fjernTilfeldig());
		
		keyboard.close();
	}
}
