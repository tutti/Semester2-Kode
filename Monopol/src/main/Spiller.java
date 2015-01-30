package main;

public abstract class Spiller {
	private String navn;
	private int fengsel;
	
	public Spiller(String navn) {
		this.navn = navn;
	}
	
	public final String hentNavn() {
		return navn;
	}
	
	public final void settIFengsel() {
		fengsel = 3;
	}
	
	public final int tidIFengsel() {
		return fengsel;
	}
}
