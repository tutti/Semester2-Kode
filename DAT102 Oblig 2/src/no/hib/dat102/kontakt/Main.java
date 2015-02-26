package no.hib.dat102.kontakt;

import java.util.Iterator;

public class Main {

	private final static String DIVISOR = "-------------------------";
	private static String hobbynavnSpørsmål = "Skriv inn en hobby, avslutt med tom linje: ";

	public static void main(String[] args) {

		// Datakontakt-objektet
		Datakontakt kontakt = new Datakontakt();

		// Menyene som vises i dette programmet
		Meny hovedmeny = new Meny("Legg til medlem", "Rediger medlem",
				"Vis alle medlemmer", "Skriv ut parliste", "Avslutt");
		Meny medlemmeny = new Meny("Legg til hobby", "Fjern hobby",
				"Finn partner", "Fjern partner", "Avslutt redigering");

		// Vis hovedmenyen til brukeren velger "avslutt"
		for (String valg = hovedmeny.menyvalg(); !valg.equals("Avslutt"); valg = hovedmeny
				.menyvalg()) {
			String navn;
			Medlem medlem;
			switch (valg) {
			case "Legg til medlem":
				// Hvis brukeren vil legge til et medlem:

				// Få navnet til medlemmet
				navn = Meny.spørOmTekst("Medlemmets navn: ");

				// Opprett medlem
				medlem = new Medlem(navn);
				kontakt.leggTilMedlem(medlem);

				// Legg inn hobbyer
				for (String hobbynavn = Meny.spørOmTekst(hobbynavnSpørsmål); !hobbynavn
						.equals(""); hobbynavn = Meny
						.spørOmTekst(hobbynavnSpørsmål)) {
					medlem.leggTilHobby(new Hobby(hobbynavn));
				}
				break;
			case "Rediger medlem":
				// Hvis brukeren vil redigere et medlem:

				// Hent medlemmet brukeren vil redigere
				navn = Meny.spørOmTekst("Medlemmets navn: ");
				medlem = kontakt.hentMedlem(navn);
				if (medlem == null) {
					System.out.println("Fant ikke medlem.");
					break;
				}

				// Fortsett å vise undermenyen til brukeren avslutter redigering
				for (String undervalg = medlemmeny.menyvalg(); !undervalg
						.equals("Avslutt redigering"); undervalg = medlemmeny
						.menyvalg()) {
					String hobbynavn;
					switch (undervalg) {
					case "Legg til hobby":
						// Hvis brukeren vil legge til en hobby, hent navn på
						// hobby og legg den til.
						hobbynavn = Meny.spørOmTekst("Skriv inn en hobby: ");
						medlem.leggTilHobby(new Hobby(hobbynavn));
						break;
					case "Fjern hobby":
						// Hvis brukeren vil fjerne en hobby, hent navn på hobby
						// og fjern den.
						hobbynavn = Meny.spørOmTekst("Skriv inn en hobby: ");
						medlem.fjernHobby(new Hobby(hobbynavn));
						break;
					case "Finn partner":
						// Hvis brukeren vil finne en partner til medlemmet:
						if (medlem.hentStatusIndeks() != -1) {
							// Hvis medlemmet allerede har en partner, avbryt.
							System.out.println("Medlemmet har en partner.");
							break;
						}

						// Prøv å parre medlemmet med et annet medlem
						int indeks = kontakt.finnPartnerFor(navn);
						if (indeks == -1) {
							System.out.println("Kunne ikke finne en partner.");
						} else {
							// Hvis en partner ble funnet, skriv den ut.
							Medlem medlem2 = kontakt.hentMedlem(indeks);
							System.out.println("Fant partner: "
									+ medlem2.hentNavn());
						}
						break;
					case "Fjern partner":
						// Hvis brukeren vil fjerne medlemmets partner, gjør
						// det.
						kontakt.tilbakestillStatusIndeks(navn);
						System.out.println("Partner fjernet.");
						break;
					}
				}
				break;
			case "Vis alle medlemmer":
				// Hvis brukeren vil vise alle medlemmer:
				for (int i = 0; i < kontakt.antallMedlemmer(); ++i) {
					// For hvert medlem...
					System.out.println(DIVISOR);

					// Skriv ut medlemmets navn
					medlem = kontakt.hentMedlem(i);
					System.out.println(medlem.hentNavn());

					// Skriv ut medlemmets hobbyer
					System.out.println("Hobbyer:");
					for (Iterator<Hobby> hobbyer = medlem.hentHobbyer(); hobbyer
							.hasNext(); System.out.println(hobbyer.next()))
						;

					// Hvis medlemmet har en partner, skriv den ut.
					int indeks = medlem.hentStatusIndeks();
					if (indeks != -1) {
						Medlem medlem2 = kontakt.hentMedlem(indeks);
						System.out.println("Partner: " + medlem2.hentNavn());
					}
				}
				System.out.println(DIVISOR);
				break;
			case "Skriv ut parliste":
				System.out.println(DIVISOR);
				for (int i = 0; i < kontakt.antallMedlemmer(); ++i) {
					// For hvert medlem:
					medlem = kontakt.hentMedlem(i);

					// Hvis medlemmet har en partner med indeks etter sin egen,
					// skriv ut paret.
					// Hvis indeksen er mindre enn medlemmets indeks, har
					// medlemmet enten ingen partner (indeks -1) eller paret har
					// allerede blitt skrevet ut.
					int indeks = medlem.hentStatusIndeks();
					if (medlem.hentStatusIndeks() > i) {
						Medlem medlem2 = kontakt.hentMedlem(indeks);
						System.out.println(medlem.hentNavn() + " og "
								+ medlem2.hentNavn());
					}
				}
				System.out.println(DIVISOR);
				break;
			}
		}
	}

}
