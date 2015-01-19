package no.hib.dat102;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Tekstgrensesnitt {
	
	private static Scanner tastatur = new Scanner(System.in);
	private static CDarkiv2<CD> arkiv = null;
	private static Fil fil;
	
	public static void skrivUtMatchende(String søk) {
		CD[] cder = arkiv.finnCDer(søk);
		System.out.println("CDer som stemmer med søket:");
		for (int i=0; i<cder.length; ++i) {
			System.out.println(cder[i]);
		}
		System.out.println();
	}
	
	public static void skrivUtForArtist(String søk) {
		String[] artister = arkiv.finnArtister(søk);
		System.out.println("Artister som stemmer med søket:");
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
				System.out.println("Du må skrive inn et heltall.");
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
		System.out.println("7. Søk etter CD");
		System.out.println("8. Søk etter artist");
		System.out.println("9. Lagre arkiv");
		System.out.println("0. Avslutt");
		return nesteInt();
	}
	
	private static void menyvalg_nytt_arkiv() throws IOException {
		System.out.print("Skriv inn filnavn på nytt arkiv: ");
		String filnavn = tastatur.nextLine();
		System.out.print("Skriv inn størrelsen på arkivet: ");
		fil.nyFil(filnavn);
		fil.størrelse = nesteInt();
		arkiv = new CDarkiv2<CD>(fil.størrelse);
	}
	
	private static void menyvalg_last_arkiv() throws IOException {
		System.out.print("Skriv inn filnavn på arkiv: ");
		String filnavn = tastatur.nextLine();
		try {
			fil.åpneFil(filnavn);
		} catch (FileNotFoundException e) {
			System.out.println("Fant ikke filen.");
			return;
		}
		arkiv = fil.lesArkiv();
	}
	
	private static void menyvalg_vis_cder() throws IOException {
		if (arkiv == null) {
			System.out.println("Du har ikke åpnet et arkiv.");
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
			System.out.println("Du har ikke åpnet et arkiv.");
			return;
		}
		System.out.print("Skriv inn CD-nummer: ");
		int cdnummer = nesteInt();
		System.out.print("Skriv inn artist: ");
		String artist = tastatur.nextLine();
		System.out.print("Skriv inn navn på CD: ");
		String navn = tastatur.nextLine();
		System.out.print("Skriv inn utgivelsesår: ");
		int år = nesteInt();
		System.out.print("Skriv inn sjanger: ");
		String sjangerString;
		for (sjangerString = tastatur.nextLine().toUpperCase(); !Sjanger.contains(sjangerString); sjangerString = tastatur.nextLine().toUpperCase()) {
			System.out.print("Ugyldig sjanger. Skriv inn sjanger: ");
		}
		Sjanger sjanger = Sjanger.valueOf(sjangerString);
		System.out.print("Skriv inn plateselskap: ");
		String plateselskap = tastatur.nextLine();
		
		CD cd = new CD(cdnummer, artist, navn, år, sjanger, plateselskap);
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
		System.out.println("2. Endre navn på CD");
		System.out.println("3. Endre utgivelsesår");
		System.out.println("4. Endre sjanger");
		System.out.println("5. Endre plateselskap");
		System.out.println("0. Avslutt redigering");
		return nesteInt();
	}
	
	private static void menyvalg_endre_cd() throws IOException {
		if (arkiv == null) {
			System.out.println("Du har ikke åpnet et arkiv.");
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
				System.out.print("Skriv inn nytt navn på CD: ");
				cd.setNavn(tastatur.nextLine());
				break;
			case 3:
				System.out.print("Skriv inn nytt utgivelsesår: ");
				cd.setÅr(nesteInt());
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
			System.out.println("Du har ikke åpnet et arkiv.");
			return;
		}
		System.out.print("Skriv inn nummeret på CDen du vil fjerne: ");
		int cdnummer = nesteInt();
		arkiv.slettCD(cdnummer);
	}
	
	private static void menyvalg_søk_etter_cd() throws IOException {
		if (arkiv == null) {
			System.out.println("Du har ikke åpnet et arkiv.");
			return;
		}
		System.out.print("Skriv inn et søk: ");
		String søk = tastatur.nextLine();
		skrivUtMatchende(søk);
	}
	
	private static void menyvalg_søk_etter_artist() throws IOException {
		if (arkiv == null) {
			System.out.println("Du har ikke åpnet et arkiv.");
			return;
		}
		System.out.print("Skriv inn et søk: ");
		String søk = tastatur.nextLine();
		skrivUtForArtist(søk);
	}
	
	private static void menyvalg_lagre_arkiv() throws IOException {
		if (arkiv == null) {
			System.out.println("Du har ikke åpnet et arkiv.");
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
				menyvalg_søk_etter_cd();
				break;
			case 8:
				menyvalg_søk_etter_artist();
				break;
			case 9:
				menyvalg_lagre_arkiv();
				break;
			}
		}
	}

}