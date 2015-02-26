package person;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Oppg5b {

	private static Scanner tastatur = new Scanner(System.in);

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

	public static void main(String[] args) {
		KjedetKoe<Person> kø = new KjedetKoe<Person>();
		for (int i = 0; i < 5; ++i) {
			System.out.println("Person " + (i + 1));
			System.out.print("Skriv inn fornavn: ");
			String fornavn = tastatur.nextLine();
			System.out.print("Skriv inn etternavn: ");
			String etternavn = tastatur.nextLine();
			System.out.print("Skriv inn fødselsår: ");
			int fødselsår = nesteInt();
			kø.innKoe(new Person(fornavn, etternavn, fødselsår));
			System.out.println();
		}

		while (!kø.erTom()) {
			System.out.println(kø.utKoe());
		}
	}

}
