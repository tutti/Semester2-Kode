package no.hib.dat102.kontakt;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Meny {
	
	private static Scanner tastatur = new Scanner(System.in);
	
	private String[] menyvalg;
	
	/**
	 * Intern metode som s�rger for at brukeren skriver inn et heltall,
	 * istedenfor � kaste en feil
	 * 
	 * @return Heltallet brukeren tastet inn
	 */
	private static int nesteInt() {
		// Start med en "null"-verdi
		Integer r = null;

		// Fortsett � pr�ve til en gyldig verdi blir funnet
		while (r == null) {
			try {
				// Les en int
				r = tastatur.nextInt();
			} catch (InputMismatchException e) {
				// Hvis verdien som ble lest ikke er en gyldig int,
				// skriv ut en feilmelding, les inn resten av linjen,
				// og pr�v igjen.
				System.out.println("Du m� skrive inn et heltall.");
				tastatur.nextLine();
			}
		}
		// Les ut resten av linjen for � unng� problemer videre i koden
		tastatur.nextLine();
		return r;
	}
	
	public static String sp�rOmTekst(String sp�rsm�l) {
		System.out.print(sp�rsm�l);
		return tastatur.nextLine();
	}
	
	public static int sp�rOmTall(String sp�rsm�l) {
		System.out.print(sp�rsm�l);
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
