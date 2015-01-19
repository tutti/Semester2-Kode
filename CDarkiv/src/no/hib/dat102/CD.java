package no.hib.dat102;

import java.util.Calendar;

public class CD {

	private int cdnummer; // Nummer på CD; skal være unikt
	private String artist; // Artisten som laget CDen
	private String navn; // Navnet på CDen
	private int år; // Året CDen ble utgitt
	private Sjanger sjanger; // Sjangeren CDen hører til
	private String plateselskap; // Plateselskapet som ga ut CDen
	
	/**
	 * Lager en ny CD med default-verdier. Ikke bruk en slik CD uten å sette CD-nummer.
	 */
	public CD() {
		// Velger "tomme" verdier for alle variabler
		this.cdnummer = 0;
		this.artist = "Ingen artist";
		this.navn = "Uten navn";
		this.år = Calendar.getInstance().get(Calendar.YEAR);
		this.sjanger = Sjanger.POP;
		this.plateselskap = "Ingen selskap";
	}
	
	/**
	 * Lager en ny CD med gitte verdier
	 * @param cdnummer int CDens nummer. Skal være unikt.
	 * @param artist String Artisten som laget CDen
	 * @param navn String Navnet til CDen
	 * @param år int Året CDen ble utgitt
	 * @param sjanger Sjanger Sjangeren til CDen
	 * @param plateselskap Plateselskapet som ga ut CDen
	 */
	public CD(int cdnummer, String artist, String navn, int år,
			Sjanger sjanger, String plateselskap) {
		// Setter alle variabler etter konstruktørens argumenter
		this.cdnummer = cdnummer;
		this.artist = artist;
		this.navn = navn;
		this.år = år;
		this.sjanger = sjanger;
		this.plateselskap = plateselskap;
	}
	
	/**
	 * Sjekker om to CDer er samme CD. Det skal de være hvis de har samme CD-nummer.
	 */
	public boolean equals(Object cd) {
		// To CDer anses som like dersom de har samme CD-nummer
		if (!(cd instanceof CD)) return false;
		return this.cdnummer == ((CD)cd).cdnummer;
	}
	
	/**
	 * Returnerer CDen i et lesbart format
	 */
	public String toString() {
		// Returnerer CDen i et menneskelig leselig format
		return String.format("[%d] %s: \"%s\", %s (%d)", cdnummer, plateselskap, navn, artist, år);
	}

	// Get- og set-metoder
	public int getCdnummer() { return cdnummer; }
	public void setCdnummer(int cdnummer) { this.cdnummer = cdnummer; }
	public String getArtist() { return artist; }
	public void setArtist(String artist) { this.artist = artist; }
	public String getNavn() { return navn; }
	public void setNavn(String navn) { this.navn = navn; }
	public int getÅr() { return år; }
	public void setÅr(int år) { this.år = år; }
	public Sjanger getSjanger() { return sjanger; }
	public void setSjanger(Sjanger sjanger) { this.sjanger = sjanger; }
	public String getPlateselskap() { return plateselskap; }
	public void setPlateselskap(String plateselskap) { this.plateselskap = plateselskap; }
}