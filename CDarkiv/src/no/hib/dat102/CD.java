package no.hib.dat102;

import java.util.Calendar;

public class CD {

	private int cdnummer;
	private String artist;
	private String navn;
	private int år;
	private Sjanger sjanger;
	private String plateselskap;
	
	public CD() {
		this.cdnummer = 0;
		this.artist = "Ingen artist";
		this.navn = "Uten navn";
		this.år = Calendar.getInstance().get(Calendar.YEAR);
		this.sjanger = Sjanger.POP;
		this.plateselskap = "Ingen selskap";
	}
	
	public CD(int cdnummer, String artist, String navn, int år,
			Sjanger sjanger, String plateselskap) {
		this.cdnummer = cdnummer;
		this.artist = artist;
		this.navn = navn;
		this.år = år;
		this.sjanger = sjanger;
		this.plateselskap = plateselskap;
	}
	
	public boolean equals(Object cd) {
		if (!(cd instanceof CD)) return false;
		return this.cdnummer == ((CD)cd).cdnummer;
	}
	
	public String toString() {
		return String.format("[%d] %s: \"%s\", %s (%d)", cdnummer, plateselskap, navn, artist, år);
	}

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