package no.hib.dat102;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

public class Fil {
	
	public char[] divisorer = {
			',', ':', '|', '#', '¤', '&'
	};
	
	private String filnavn;
	public int størrelse = 0;
	private BufferedReader leser = null;
	
	public void åpneFil(String filnavn) throws IOException {
		try { lukkFil(); } catch (Exception e) {}
		this.filnavn = filnavn;
		leser = new BufferedReader(new FileReader(filnavn));
		leser.mark(0);
	}
	
	public void nyFil(String filnavn) throws IOException {
		try { lukkFil(); } catch (Exception e) {}
		PrintWriter skriver = new PrintWriter(new FileWriter(filnavn));
		skriver.close();
		åpneFil(filnavn);
	}
	
	public void lukkFil() throws IOException {
		if (leser == null) return;
		leser.close();
		leser = null;
	}
	
	public CDarkiv2<CD> lesArkiv() throws IOException {
		if (leser == null) throw new FileNotFoundException("Ingen fil er åpnet");
		leser.reset();
		String div = String.valueOf((char)leser.read());
		størrelse = Integer.valueOf(leser.readLine());
		CDarkiv2<CD> arkiv = new CDarkiv2<CD>(størrelse);
		for (String linje = leser.readLine(); linje != null; linje = leser.readLine()) {
			String[] lest = linje.split(Pattern.quote(div));
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
	
	public void skrivArkiv(CDarkiv2<CD> arkiv) throws IOException {
		skrivArkiv(arkiv, '|'); // TODO: Skriv logikk for å velge divisor
	}
	
	public void skrivArkiv(CDarkiv2<CD> arkiv, char div) throws IOException {
		if (leser == null) throw new FileNotFoundException("Ingen fil er åpnet");
		PrintWriter skriver = new PrintWriter(new FileWriter(filnavn));
		skriver.print(div);
		skriver.println(størrelse);
		CD[] cder = arkiv.finnCDer("");
		for (CD cd : cder) {
			skriver.print(cd.getCdnummer());
			skriver.print(div);
			skriver.print(cd.getArtist());
			skriver.print(div);
			skriver.print(cd.getNavn());
			skriver.print(div);
			skriver.print(cd.getÅr());
			skriver.print(div);
			skriver.print(cd.getSjanger());
			skriver.print(div);
			skriver.println(cd.getPlateselskap());
		}
		skriver.close();
	}
	
}