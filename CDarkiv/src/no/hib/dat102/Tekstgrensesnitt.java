package no.hib.dat102;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import no.hib.dat102.adt.*;

public class Tekstgrensesnitt {

	/*
	 * Notis: CDarkivADT-grensesnittet har ingen metoder for � hente alle CDer,
	 * eller enkelt-CDer. Dette tekstgrensesnittet kommer rundt denne
	 * begrensningen ved � bruke s�kemetoden med en tom streng, som da
	 * returnerer alle CDer i arkivet. For � hente en gitt CD, brukes denne
	 * metoden fulgt av at tekstgrensesnittet leter gjennom CDene som kom
	 * tilbake selv.
	 */

	private static Scanner tastatur = new Scanner(System.in);
	private static CDarkivADT<CD> arkiv = null;
	private static Fil fil;

	/**
	 * Skriver ut CDer som matcher en gitt s�kestreng
	 * 
	 * @param s�k
	 */
	public static void skrivUtMatchende(String s�k) {
		// Hent CDer som matcher
		CD[] cder = arkiv.finnCDer(s�k);

		// Skriv ut alle CDer som kom tilbake
		System.out.println("CDer som stemmer med s�ket:");
		for (int i = 0; i < cder.length; ++i) {
			System.out.println(cder[i]);
		}
		System.out.println();
	}

	/**
	 * Skriv ut alle artister som inneholder en gitt s�kestreng
	 * 
	 * @param s�k
	 */
	public static void skrivUtForArtist(String s�k) {
		// Hent artister som matcher
		String[] artister = arkiv.finnArtister(s�k);

		// Skriv ut alle artistene
		System.out.println("Artister som stemmer med s�ket:");
		for (int i = 0; i < artister.length; ++i) {
			System.out.println(artister[i]);
		}
		System.out.println();
	}

	/**
	 * Skriver ut antall CDer totalt, og antall per sjanger
	 */
	public static void skrivUtStatistikk() {
		// Skriv ut totalt antall CDer
		System.out.println("Totalt antall CDer: " + arkiv.antallCDer());

		// Skriv ut antall CDer for hver sjanger
		for (Sjanger s : Sjanger.values()) {
			System.out.println("Antall CDer for " + s + ": "
					+ arkiv.antallCDer(s));
		}
	}

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

	/**
	 * Skriver ut menyen, venter p� brukerens valg, og returnerer valget
	 * 
	 * @return int Brukerens valgte meny
	 */
	private static int menyvalg() {
		System.out.println("1. Nytt arkiv");
		System.out.println("2. Last arkiv");
		System.out.println("3. Vis CD-er");
		System.out.println("4. Legg til CD");
		System.out.println("5. Endre CD");
		System.out.println("6. Fjern CD");
		System.out.println("7. S�k etter CD");
		System.out.println("8. S�k etter artist");
		System.out.println("9. Lagre arkiv");
		System.out.println("0. Avslutt");
		return nesteInt();
	}

	/**
	 * Menyvalg: Nytt arkiv Oppretter et nytt arkiv. Hvis arkivfilen finnes fra
	 * f�r, vil den overskrives.
	 * 
	 * @throws IOException
	 *             Kastes hvis filen ikke kan skrives.
	 */
	private static void menyvalg_nytt_arkiv() throws IOException {
		// Sp�r om filnavn til arkivet
		System.out.print("Skriv inn filnavn p� nytt arkiv: ");
		String filnavn = tastatur.nextLine();

		// Sp�r om st�rrelsen p� arkivet
		System.out.print("Skriv inn st�rrelsen p� arkivet: ");

		// Opprett fil og arkiv-objekt
		fil.nyFil(filnavn);
		fil.st�rrelse = nesteInt();
		arkiv = new CDarkiv<CD>(fil.st�rrelse);
	}

	/**
	 * Menyvalg: Last arkiv Leser inn informasjon fra en fil.
	 * 
	 * @throws IOException
	 *             Kastes hvis filen ikke finnes.
	 */
	private static void menyvalg_last_arkiv() throws IOException {
		// Les inn filnavn p� filen
		System.out.print("Skriv inn filnavn p� arkiv: ");
		String filnavn = tastatur.nextLine();
		try {
			// Pr�v � �pne filen
			fil.�pneFil(filnavn);
		} catch (FileNotFoundException e) {
			// Hvis filen ikke ble funnet, g� tilbake til hovedmenyen
			System.out.println("Fant ikke filen.");
			return;
		}
		// Les arkivet fra filen
		arkiv = fil.lesArkiv();

		// Skriv ut statistikk for filen
		skrivUtStatistikk();
	}

	/**
	 * Menyvalg: Vis CDer Viser en liste over alle CDene i arkivet
	 */
	private static void menyvalg_vis_cder() {
		if (arkiv == null) {
			// Hvis brukeren ikke har �pnet eller laget et arkiv, g� tilbake til
			// hovedmenyen.
			System.out.println("Du har ikke �pnet et arkiv.");
			return;
		}
		System.out.println("CDer i dette arkivet:");

		// Hent alle CDer fra arkivet og skriv dem ut.
		CD[] cder = arkiv.finnCDer("");
		for (int i = 0; i < cder.length; ++i) {
			System.out.println(cder[i]);
		}
		System.out.println();
	}

	/**
	 * Menyvalg: Legg til CD Sp�r brukeren om informasjon om en ny CD, og legger
	 * inn CDen.
	 */
	private static void menyvalg_legg_til_cd() {
		if (arkiv == null) {
			// Hvis brukeren ikke har �pnet eller laget et arkiv, g� tilbake til
			// hovedmenyen.
			System.out.println("Du har ikke �pnet et arkiv.");
			return;
		}
		// Sp�r brukeren om all informasjon som beh�ves for en ny CD.
		System.out.print("Skriv inn CD-nummer: ");
		int cdnummer = nesteInt();
		System.out.print("Skriv inn artist: ");
		String artist = tastatur.nextLine();
		System.out.print("Skriv inn navn p� CD: ");
		String navn = tastatur.nextLine();
		System.out.print("Skriv inn utgivelses�r: ");
		int �r = nesteInt();
		// For sjanger: Fortsett � sp�rre til en gyldig sjanger skrives inn.
		System.out.print("Skriv inn sjanger: ");
		String sjangerString;
		for (sjangerString = tastatur.nextLine().toUpperCase(); !Sjanger
				.contains(sjangerString); sjangerString = tastatur.nextLine()
				.toUpperCase()) {
			System.out.print("Ugyldig sjanger. Skriv inn sjanger: ");
		}
		Sjanger sjanger = Sjanger.valueOf(sjangerString);
		System.out.print("Skriv inn plateselskap: ");
		String plateselskap = tastatur.nextLine();

		// Opprett en ny CD med informasjonen
		CD cd = new CD(cdnummer, artist, navn, �r, sjanger, plateselskap);

		try {
			// Pr�v � legge CDen til i arkivet
			arkiv.leggTilCD(cd);
		} catch (ArrayIndexOutOfBoundsException e) {
			// Feilet: CD-arkivet hadde ikke mer plass
			System.out.println("CD-arkivet er fullt.");
		} catch (Exception e) {
			// Feilet: Arkivet har allerede en CD med det nummeret
			System.out.println("CD-nummeret finnes allerede i dette arkivet.");
		}
	}

	/**
	 * Skriver ut endre CD-menyen, venter p� brukerens valg, og returnerer
	 * valget.
	 * 
	 * @return Brukerens valg
	 */
	private static int menyvalg_endre_cd_undermeny() {
		System.out.println("1. Endre artist");
		System.out.println("2. Endre navn p� CD");
		System.out.println("3. Endre utgivelses�r");
		System.out.println("4. Endre sjanger");
		System.out.println("5. Endre plateselskap");
		System.out.println("0. Avslutt redigering");
		return nesteInt();
	}

	/**
	 * Menyvalg: Endre CD Sp�r om et CD-nummer, og bruker "endre CD"-menyen til
	 * � la brukeren endre en CDs detaljer
	 */
	private static void menyvalg_endre_cd() {
		if (arkiv == null) {
			// Hvis brukeren ikke har �pnet eller laget et arkiv, g� tilbake til
			// hovedmenyen.
			System.out.println("Du har ikke �pnet et arkiv.");
			return;
		}

		// Finn en CD med det oppgitte CD-nummeret
		CD[] cder = arkiv.finnCDer("");
		System.out.print("Skriv inn et CD-nummer: ");
		int cdnummer = nesteInt();
		CD cd = null;
		for (CD sjekkCD : cder) {
			if (sjekkCD.getCdnummer() == cdnummer) {
				cd = sjekkCD;
				break;
			}
		}

		// Hvis en CD med riktig nummer ikke fantes, g� tilbake til hovedmenyen
		if (cd == null) {
			System.out.println("Fant ingen CD med det CD-nummeret.");
			return;
		}

		// Vis "endre CD"-menyen til brukeren avslutter den.
		for (
			int valg = menyvalg_endre_cd_undermeny();
			valg != 0;
			valg = menyvalg_endre_cd_undermeny()
		) {
			switch (valg) {
			case 1:
				System.out.print("Skriv inn nytt artistnavn: ");
				cd.setArtist(tastatur.nextLine());
				break;
			case 2:
				System.out.print("Skriv inn nytt navn p� CD: ");
				cd.setNavn(tastatur.nextLine());
				break;
			case 3:
				System.out.print("Skriv inn nytt utgivelses�r: ");
				cd.set�r(nesteInt());
				break;
			case 4:
				System.out.print("Skriv inn ny sjanger: ");
				String sjangerString;
				for (sjangerString = tastatur.nextLine().toUpperCase(); !Sjanger
						.contains(sjangerString); sjangerString = tastatur
						.nextLine().toUpperCase()) {
					System.out.print("Ugyldig sjanger. Skriv inn ny sjanger: ");
				}
				cd.setSjanger(Sjanger.valueOf(sjangerString));
				break;
			case 5:
				System.out.print("Skriv inn nytt plateselskap: ");
				cd.setPlateselskap(tastatur.nextLine());
				break;
			}
		}
	}

	/**
	 * Menyvalg: Fjern CD Fjerner en CD fra arkivet
	 */
	private static void menyvalg_fjern_cd() {
		if (arkiv == null) {
			// Hvis brukeren ikke har �pnet eller laget et arkiv, g� tilbake til
			// hovedmenyen.
			System.out.println("Du har ikke �pnet et arkiv.");
			return;
		}

		// Sp�r brukeren om nummer p� en CD som skal slettes, og gi det til
		// CD-arkivet.
		System.out.print("Skriv inn nummeret p� CDen du vil fjerne: ");
		int cdnummer = nesteInt();
		arkiv.slettCD(cdnummer);
	}

	/**
	 * Menyvalg: S�k etter CD Sp�r om et s�keord, og skriver ut alle CDer som
	 * matcher.
	 */
	private static void menyvalg_s�k_etter_cd() {
		if (arkiv == null) {
			// Hvis brukeren ikke har �pnet eller laget et arkiv, g� tilbake til
			// hovedmenyen.
			System.out.println("Du har ikke �pnet et arkiv.");
			return;
		}

		// Sp�r brukeren om et s�keord, og gi det videre til CD-arkivet.
		System.out.print("Skriv inn et s�k: ");
		String s�k = tastatur.nextLine();
		skrivUtMatchende(s�k);
	}

	/**
	 * Menyvalg: S�k etter artist Sp�r om et s�keord, og skriver ut alle
	 * artister som matcher
	 */
	private static void menyvalg_s�k_etter_artist() {
		if (arkiv == null) {
			// Hvis brukeren ikke har �pnet eller laget et arkiv, g� tilbake til
			// hovedmenyen.
			System.out.println("Du har ikke �pnet et arkiv.");
			return;
		}

		// Sp�r brukeren om et s�keord, og gi det videre til CD-arkivet.
		System.out.print("Skriv inn et s�k: ");
		String s�k = tastatur.nextLine();
		skrivUtForArtist(s�k);
	}

	/**
	 * Menyvalg: Lagre arkiv Lagrer arkivet til en fil
	 * 
	 * @throws IOException
	 *             Kastes hvis filen ikke kan lagres
	 */
	private static void menyvalg_lagre_arkiv() throws IOException {
		if (arkiv == null) {
			// Hvis brukeren ikke har �pnet eller laget et arkiv, g� tilbake til
			// hovedmenyen.
			System.out.println("Du har ikke �pnet et arkiv.");
			return;
		}

		// Lagre arkivet
		fil.skrivArkiv(arkiv);
	}

	/**
	 * Hovedmetoden Bruker hovedmenyen til � la brukeren lese og redigere
	 * CD-arkiver
	 * 
	 * @param args
	 *            Brukes ikke
	 * @throws IOException
	 *             Kastes hvis en fil ikke kan leses og/eller skrives
	 */
	public static void main(String[] args) throws IOException {
		fil = new Fil();

		for (int valg = menyvalg(); valg != 0; valg = menyvalg()) {
			switch (valg) {
			case 1:
				menyvalg_nytt_arkiv();
				break;
			case 2:
				menyvalg_last_arkiv();
				break;
			case 3:
				menyvalg_vis_cder();
				break;
			case 4:
				menyvalg_legg_til_cd();
				break;
			case 5:
				menyvalg_endre_cd();
				break;
			case 6:
				menyvalg_fjern_cd();
				break;
			case 7:
				menyvalg_s�k_etter_cd();
				break;
			case 8:
				menyvalg_s�k_etter_artist();
				break;
			case 9:
				menyvalg_lagre_arkiv();
				break;
			}
		}
	}

}