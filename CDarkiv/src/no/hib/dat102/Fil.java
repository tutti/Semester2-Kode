package no.hib.dat102;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;
import no.hib.dat102.adt.*;

/**
 * En klasse for lesing og skriving av CD-arkiver
 * @author P�l V�rdal Gjerde
 *
 */
public class Fil {
	
	private String filnavn;
	public int st�rrelse = 0;
	private BufferedReader leser = null;
	
	/**
	 * �pner en fil for lesing, og beholder filnavnet s� skriving kan utf�res.
	 * Filen �pnes ikke for skriving her, men det vil gj�res automatisk i
	 * skrivArkiv().
	 * @param filnavn String Et filnavn som skal �pnes
	 * @throws IOException Hvis filen ikke fantes eller ikke kunne �pnes av
	 * andre grunner
	 */
	public void �pneFil(String filnavn) throws IOException {
		// Lukk filen hvis den allerede er �pen
		try { lukkFil(); } catch (Exception e) {}
		
		// Behold filnavnet og �pne filen for lesing
		this.filnavn = filnavn;
		leser = new BufferedReader(new FileReader(filnavn));
		leser.mark(0);
	}
	
	/**
	 * Oppretter en ny fil og �pner den for lesing. Hvis filen finnes fra f�r,
	 * vil den overskrives.
	 * Filen �pnes ikke for skriving her, men det vil gj�res automatisk i
	 * skrivArkiv().
	 * @param filnavn String Et filnavn som skal opprettes.
	 * @throws IOException
	 */
	public void nyFil(String filnavn) throws IOException {
		// Lukk filen som allerede er �pen, hvis en er.
		try { lukkFil(); } catch (Exception e) {}
		
		// Skriv en tom fil til den nye filen
		PrintWriter skriver = new PrintWriter(new FileWriter(filnavn));
		skriver.close();
		
		// �pne filen for lesing
		�pneFil(filnavn);
	}
	
	/**
	 * Lukker filen.
	 * @throws IOException Hvis filen ikke kan lukkes.
	 */
	public void lukkFil() throws IOException {
		if (leser == null) return;
		leser.close();
		leser = null;
	}
	
	/**
	 * Leser et CD-arkiv fra filen som er �pnet.
	 * @return Et CD-arkiv
	 * @throws IOException Hvis ingen fil er �pnet, eller filen ikke kunne leses
	 * @throws NumberFormatException Hvis filen ikke er riktig formatert
	 */
	public CDarkivADT<CD> lesArkiv() throws IOException, NumberFormatException {
		// Sjekk om en fil er �pen, kast en feil hvis ikke.
		if (leser == null)
			throw new FileNotFoundException("Ingen fil er �pnet");
		
		// G� tilbake til begynnelsen av filen
		leser.reset();
		
		// Les det f�rste tegnet; det er separatoren i filen
		String div = String.valueOf((char)leser.read());
		
		// Les resten av f�rste linje; det er maksgrensen for CDer i arkivet.
		st�rrelse = Integer.valueOf(leser.readLine());
		
		// Opprett et CD-arkiv som skal tilsvare filen
		CDarkivADT<CD> arkiv = new CDarkiv<CD>(st�rrelse);
		
		// Les gjennom linjene
		for (
			String linje = leser.readLine();
			linje != null;
			linje = leser.readLine()
		) {
			// Les inn linjen og del den opp p� separatoren
			String[] lest = linje.split(Pattern.quote(div));
			
			// Legg inn en CD med informasjonen fra linjen
			try {
				arkiv.leggTilCD(new CD(
						Integer.valueOf(lest[0]),
						lest[1],
						lest[2],
						Integer.valueOf(lest[3]),
						Sjanger.valueOf(lest[4]),
						lest[5]
				));
			} catch (Exception e) {}
		}
		return arkiv;
	}
	
	/**
	 * Skriver et CD-arkiv til en �pnet fil
	 * @param arkiv Et CD-arkiv som skal skrives
	 * @throws IOException Hvis ingen fil er �pen, eller filen ikke kan skrives
	 * til
	 */
	public void skrivArkiv(CDarkivADT<CD> arkiv) throws IOException {
		if (leser == null)
			throw new FileNotFoundException("Ingen fil er �pnet");
		
		// Hent alle CDene
		CD[] cder = arkiv.finnCDer("");

		// Velg en separator som ikke er i bruk i noen av CD-navnene
		char div = 10000;
		for (CD cd : cder) {
			while (
				cd.getNavn().indexOf(div) >= 0
				|| cd.getArtist().indexOf(div) >= 0
				|| cd.getPlateselskap().indexOf(div) >= 0
			) {
				++div;
			}
		}
		
		// Skriv hver enkelt CD til filen, en CD per linje, med separatoren
		// mellom alle feltene
		PrintWriter skriver = new PrintWriter(new FileWriter(filnavn));
		skriver.print(div);
		skriver.println(st�rrelse);
		for (CD cd : cder) {
			skriver.print(cd.getCdnummer());
			skriver.print(div);
			skriver.print(cd.getArtist());
			skriver.print(div);
			skriver.print(cd.getNavn());
			skriver.print(div);
			skriver.print(cd.get�r());
			skriver.print(div);
			skriver.print(cd.getSjanger());
			skriver.print(div);
			skriver.println(cd.getPlateselskap());
		}
		skriver.close();
	}
	
}