package no.hib.dat102;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Tekstgrensesnitt {
	
	private static Scanner tastatur = new Scanner(System.in);
	private static CDarkiv2<CD> arkiv = null;
	private static Fil fil;
	
	public static void skrivUtMatchende(String s�k) {
		CD[] cder = arkiv.finnCDer(s�k);
		System.out.println("CDer som stemmer med s�ket:");
		for (int i=0; i<cder.length; ++i) {
			System.out.println(cder[i]);
		}
		System.out.println();
	}
	
	public static void skrivUtForArtist(String s�k) {
		String[] artister = arkiv.finnArtister(s�k);
		System.out.println("Artister som stemmer med s�ket:");
		for (int i=0; i<artister.length; ++i) {
			System.out.println(artister[i]);
		}
		System.out.println();
	}
	
	public static void skrivUtStatistikk() {
		System.out.println("Totalt antall CDer: "+arkiv.antallCDer());
		for (Sjanger s : Sjanger.values()) {
			System.out.println("Antall CDer for "+s+": "+arkiv.antallCDer(s));
		}
	}
	
	private static int nesteInt() {
		Integer r = null;
		while (r == null) {
			try {
				r = tastatur.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Du m� skrive inn et heltall.");
				tastatur.nextLine();
			}
		}
		tastatur.nextLine();
		return r;
	}
	
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
	
	private static void menyvalg_nytt_arkiv() throws IOException {
		System.out.print("Skriv inn filnavn p� nytt arkiv: ");
		String filnavn = tastatur.nextLine();
		System.out.print("Skriv inn st�rrelsen p� arkivet: ");
		fil.nyFil(filnavn);
		fil.st�rrelse = nesteInt();
		arkiv = new CDarkiv2<CD>(fil.st�rrelse);
	}
	
	private static void menyvalg_last_arkiv() throws IOException {
		System.out.print("Skriv inn filnavn p� arkiv: ");
		String filnavn = tastatur.nextLine();
		try {
			fil.�pneFil(filnavn);
		} catch (FileNotFoundException e) {
			System.out.println("Fant ikke filen.");
			return;
		}
		arkiv = fil.lesArkiv();
	}
	
	private static void menyvalg_vis_cder() throws IOException {
		if (arkiv == null) {
			System.out.println("Du har ikke �pnet et arkiv.");
			return;
		}
		System.out.println("CDer i dette arkivet:");
		CD[] cder = arkiv.finnCDer("");
		for (int i=0; i<cder.length; ++i) {
			System.out.println(cder[i]);
		}
		System.out.println();
	}
	
	private static void menyvalg_legg_til_cd() throws IOException {
		if (arkiv == null) {
			System.out.println("Du har ikke �pnet et arkiv.");
			return;
		}
		System.out.print("Skriv inn CD-nummer: ");
		int cdnummer = nesteInt();
		System.out.print("Skriv inn artist: ");
		String artist = tastatur.nextLine();
		System.out.print("Skriv inn navn p� CD: ");
		String navn = tastatur.nextLine();
		System.out.print("Skriv inn utgivelses�r: ");
		int �r = nesteInt();
		System.out.print("Skriv inn sjanger: ");
		String sjangerString;
		for (sjangerString = tastatur.nextLine().toUpperCase(); !Sjanger.contains(sjangerString); sjangerString = tastatur.nextLine().toUpperCase()) {
			System.out.print("Ugyldig sjanger. Skriv inn sjanger: ");
		}
		Sjanger sjanger = Sjanger.valueOf(sjangerString);
		System.out.print("Skriv inn plateselskap: ");
		String plateselskap = tastatur.nextLine();
		
		CD cd = new CD(cdnummer, artist, navn, �r, sjanger, plateselskap);
		try {
			arkiv.leggTilCD(cd);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("CD-arkivet er fullt.");
		} catch (Exception e) {
			System.out.println("CD-nummeret finnes allerede i dette arkivet.");
		}
	}
	
	private static int menyvalg_endre_cd_undermeny() {
		System.out.println("1. Endre artist");
		System.out.println("2. Endre navn p� CD");
		System.out.println("3. Endre utgivelses�r");
		System.out.println("4. Endre sjanger");
		System.out.println("5. Endre plateselskap");
		System.out.println("0. Avslutt redigering");
		return nesteInt();
	}
	
	private static void menyvalg_endre_cd() throws IOException {
		if (arkiv == null) {
			System.out.println("Du har ikke �pnet et arkiv.");
			return;
		}
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
		if (cd == null) {
			System.out.println("Fant ingen CD med det CD-nummeret.");
			return;
		}
		for (int valg=menyvalg_endre_cd_undermeny(); valg!=0; valg=menyvalg_endre_cd_undermeny()) {
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
				for (sjangerString = tastatur.nextLine().toUpperCase(); !Sjanger.contains(sjangerString); sjangerString = tastatur.nextLine().toUpperCase()) {
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
	
	private static void menyvalg_fjern_cd() throws IOException {
		if (arkiv == null) {
			System.out.println("Du har ikke �pnet et arkiv.");
			return;
		}
		System.out.print("Skriv inn nummeret p� CDen du vil fjerne: ");
		int cdnummer = nesteInt();
		arkiv.slettCD(cdnummer);
	}
	
	private static void menyvalg_s�k_etter_cd() throws IOException {
		if (arkiv == null) {
			System.out.println("Du har ikke �pnet et arkiv.");
			return;
		}
		System.out.print("Skriv inn et s�k: ");
		String s�k = tastatur.nextLine();
		skrivUtMatchende(s�k);
	}
	
	private static void menyvalg_s�k_etter_artist() throws IOException {
		if (arkiv == null) {
			System.out.println("Du har ikke �pnet et arkiv.");
			return;
		}
		System.out.print("Skriv inn et s�k: ");
		String s�k = tastatur.nextLine();
		skrivUtForArtist(s�k);
	}
	
	private static void menyvalg_lagre_arkiv() throws IOException {
		if (arkiv == null) {
			System.out.println("Du har ikke �pnet et arkiv.");
			return;
		}
		fil.skrivArkiv(arkiv);
	}

	public static void main(String[] args) throws IOException {
		fil = new Fil();
		
		for (int valg=menyvalg(); valg!=0; valg=menyvalg()) {
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