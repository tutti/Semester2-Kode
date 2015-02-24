package no.hib.dat102.kontakt;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Meny {
	
	private static Scanner tastatur = new Scanner(System.in);
	
	private String[] menyvalg;
	
	/**
	 * Intern metode som sørger for at brukeren skriver inn et heltall,
	 * istedenfor å kaste en feil
	 * 
	 * @return Heltallet brukeren tastet inn
	 */
	private static int nesteInt() {
		// Start med en "null"-verdi
		Integer r = null;

		// Fortsett å prøve til en gyldig verdi blir funnet
		while (r == null) {
			try {
				// Les en int
				r = tastatur.nextInt();
			} catch (InputMismatchException e) {
				// Hvis verdien som ble lest ikke er en gyldig int,
				// skriv ut en feilmelding, les inn resten av linjen,
				// og prøv igjen.
				System.out.println("Du må skrive inn et heltall.");
				tastatur.nextLine();
			}
		}
		// Les ut resten av linjen for å unngå problemer videre i koden
		tastatur.nextLine();
		return r;
	}
	
	public static String spørOmTekst(String spørsmål) {
		System.out.print(spørsmål);
		return tastatur.nextLine();
	}
	
	public static int spørOmTall(String spørsmål) {
		System.out.print(spørsmål);
		return nesteInt();
	}
	
	public Meny(String ... menyvalg) {
		this.menyvalg = menyvalg;
	}
	
	public String menyvalg() {
		for (int i=1; i<= menyvalg.length; ++i) {
			System.out.println(i+". "+menyvalg[i-1]);
		}
		System.out.print("Velg menypunkt: ");
		int valg = nesteInt();
		while (valg < 1 || valg > menyvalg.length) {
			System.out.print("Ugyldig valg. Velg menypunkt: ");
			valg = nesteInt();
		}
		return menyvalg[valg-1];
	}
	
}
