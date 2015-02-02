package ruter;

import main.Bank;
import main.Spiller;
import adt.RuteADT;

public class SkatteRute implements RuteADT {
	
	private String navn;
	private int verdi;
	
	public SkatteRute(String navn, int verdi) {
		this.navn = navn;
		this.verdi = verdi;
	}
	
	public String navn() {
		return navn;
	}
	
	public void spillerLander(Spiller spiller, int kast) {
		Bank.betale(spiller, verdi);
	}
	
}
