package no.hib.dat102;

import java.util.Calendar;

public class CD {

	private int cdnummer;
	private String artist;
	private String navn;
	private int �r;
	private Sjanger sjanger;
	private String plateselskap;
	
	public CD() {
		// Velger "tomme" verdier for alle variabler
		this.cdnummer = 0;
		this.artist = "Ingen artist";
		this.navn = "Uten navn";
		this.�r = Calendar.getInstance().get(Calendar.YEAR);
		this.sjanger = Sjanger.POP;
		this.plateselskap = "Ingen selskap";
	}
	
	public CD(int cdnummer, String artist, String navn, int �r,
			Sjanger sjanger, String plateselskap) {
		// Setter alle variabler etter konstrukt�rens argumenter
		this.cdnummer = cdnummer;
		this.artist = artist;
		this.navn = navn;
		this.�r = �r;
		this.sjanger = sjanger;
		this.plateselskap = plateselskap;
	}
	
	public boolean equals(Object cd) {
		// To CDer anses som like dersom de har samme CD-nummer
		if (!(cd instanceof CD)) return false;
		return this.cdnummer == ((CD)cd).cdnummer;
	}
	
	public String toString() {
		// Returnerer CDen i et menneskelig leselig format
		return String.format("[%d] %s: \"%s\", %s (%d)", cdnummer, plateselskap, navn, artist, �r);
	}

	// Get- og set-metoder
	public int getCdnummer() { return cdnummer; }
	public void setCdnummer(int cdnummer) { this.cdnummer = cdnummer; }
	public String getArtist() { return artist; }
	public void setArtist(String artist) { this.artist = artist; }
	public String getNavn() { return navn; }
	public void setNavn(String navn) { this.navn = navn; }
	public int get�r() { return �r; }
	public void set�r(int �r) { this.�r = �r; }
	public Sjanger getSjanger() { return sjanger; }
	public void setSjanger(Sjanger sjanger) { this.sjanger = sjanger; }
	public String getPlateselskap() { return plateselskap; }
	public void setPlateselskap(String plateselskap) { this.plateselskap = plateselskap; }
}