package person;

import java.util.InputMismatchException;
import java.util.Scanner;

import no.hib.dat102.liste.adt.OrdnetListeADT;
import no.hib.dat102.liste.kjedet.KjedetOrdnetListe;

public class Oppg5c {

	private static Scanner tastatur = new Scanner(System.in);

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

	public static void main(String[] args) {
		OrdnetListeADT<Person> liste = new KjedetOrdnetListe<Person>();
		for (int i = 0; i < 5; ++i) {
			System.out.println("Person " + (i + 1));
			System.out.print("Skriv inn fornavn: ");
			String fornavn = tastatur.nextLine();
			System.out.print("Skriv inn etternavn: ");
			String etternavn = tastatur.nextLine();
			System.out.print("Skriv inn f�dsels�r: ");
			int f�dsels�r = nesteInt();
			liste.leggTil(new Person(fornavn, etternavn, f�dsels�r));
			System.out.println();
		}

		while (!liste.erTom()) {
			System.out.println(liste.fjernF�rste());
		}
	}

}
