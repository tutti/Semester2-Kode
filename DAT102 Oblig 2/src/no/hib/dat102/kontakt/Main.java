package no.hib.dat102.kontakt;

import java.util.Iterator;

public class Main {

	private final static String DIVISOR = "-------------------------";
	private static String hobbynavnSpørsmål = "Skriv inn en hobby, avslutt med tom linje: ";

	public static void main(String[] args) {

		Datakontakt kontakt = new Datakontakt();

		Meny hovedmeny = new Meny("Legg til medlem", "Rediger medlem",
				"Vis alle medlemmer", "Skriv ut parliste", "Avslutt");
		Meny medlemmeny = new Meny("Legg til hobby", "Fjern hobby",
				"Finn partner", "Fjern partner", "Avslutt redigering");

		for (String valg = hovedmeny.menyvalg(); !valg.equals("Avslutt"); valg = hovedmeny
				.menyvalg()) {
			String navn;
			Medlem medlem;
			switch (valg) {
			case "Legg til medlem":
				navn = Meny.spørOmTekst("Medlemmets navn: ");
				medlem = new Medlem(navn);
				kontakt.leggTilMedlem(medlem);

				for (String hobbynavn = Meny.spørOmTekst(hobbynavnSpørsmål); !hobbynavn
						.equals(""); hobbynavn = Meny
						.spørOmTekst(hobbynavnSpørsmål)) {
					medlem.leggTilHobby(new Hobby(hobbynavn));
				}
				break;
			case "Rediger medlem":
				navn = Meny.spørOmTekst("Medlemmets navn: ");
				medlem = kontakt.hentMedlem(navn);
				if (medlem == null) {
					System.out.println("Fant ikke medlem.");
					break;
				}

				for (String undervalg = medlemmeny.menyvalg(); !undervalg
						.equals("Avslutt redigering"); undervalg = medlemmeny
						.menyvalg()) {
					String hobbynavn;
					switch (undervalg) {
					case "Legg til hobby":
						hobbynavn = Meny.spørOmTekst("Skriv inn en hobby: ");
						medlem.leggTilHobby(new Hobby(hobbynavn));
						break;
					case "Fjern hobby":
						hobbynavn = Meny.spørOmTekst("Skriv inn en hobby: ");
						medlem.fjernHobby(new Hobby(hobbynavn));
						break;
					case "Finn partner":
						if (medlem.hentStatusIndeks() != -1) {
							System.out.println("Medlemmet har en partner.");
							break;
						}
						int indeks = kontakt.finnPartnerFor(navn);
						if (indeks == -1) {
							System.out.println("Kunne ikke finne en partner.");
						} else {
							Medlem medlem2 = kontakt.hentMedlem(indeks);
							System.out.println("Fant partner: "
									+ medlem2.hentNavn());
						}
						break;
					case "Fjern partner":
						kontakt.tilbakestillStatusIndeks(navn);
						System.out.println("Partner fjernet.");
						break;
					}
				}
				break;
			case "Vis alle medlemmer":
				for (int i = 0; i < kontakt.antallMedlemmer(); ++i) {
					System.out.println(DIVISOR);
					medlem = kontakt.hentMedlem(i);
					System.out.println(medlem.hentNavn());
					System.out.println("Hobbyer:");
					for (Iterator<Hobby> hobbyer = medlem.hentHobbyer(); hobbyer
							.hasNext(); System.out.println(hobbyer.next()))
						;
					int indeks = medlem.hentStatusIndeks();
					if (indeks != -1) {
						Medlem medlem2 = kontakt.hentMedlem(indeks);
						System.out.println("Partner: " + medlem2.hentNavn());
					}
				}
				System.out.println(DIVISOR);
				break;
			case "Skriv ut parliste":
				break;
			}
		}
	}

}
