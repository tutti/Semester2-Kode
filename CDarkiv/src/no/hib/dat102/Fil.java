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
			',', ':', '|', '#', '�', '&'
	};
	
	private String filnavn;
	public int st�rrelse = 0;
	private BufferedReader leser = null;
	
	public void �pneFil(String filnavn) throws IOException {
		try { lukkFil(); } catch (Exception e) {}
		this.filnavn = filnavn;
		leser = new BufferedReader(new FileReader(filnavn));
		leser.mark(0);
	}
	
	public void nyFil(String filnavn) throws IOException {
		try { lukkFil(); } catch (Exception e) {}
		PrintWriter skriver = new PrintWriter(new FileWriter(filnavn));
		skriver.close();
		�pneFil(filnavn);
	}
	
	public void lukkFil() throws IOException {
		if (leser == null) return;
		leser.close();
		leser = null;
	}
	
	public CDarkiv2<CD> lesArkiv() throws IOException {
		if (leser == null) throw new FileNotFoundException("Ingen fil er �pnet");
		leser.reset();
		String div = String.valueOf((char)leser.read());
		st�rrelse = Integer.valueOf(leser.readLine());
		CDarkiv2<CD> arkiv = new CDarkiv2<CD>(st�rrelse);
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
		skrivArkiv(arkiv, '|'); // TODO: Skriv logikk for � velge divisor
	}
	
	public void skrivArkiv(CDarkiv2<CD> arkiv, char div) throws IOException {
		if (leser == null) throw new FileNotFoundException("Ingen fil er �pnet");
		PrintWriter skriver = new PrintWriter(new FileWriter(filnavn));
		skriver.print(div);
		skriver.println(st�rrelse);
		CD[] cder = arkiv.finnCDer("");
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