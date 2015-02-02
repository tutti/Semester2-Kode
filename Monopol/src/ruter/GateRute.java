package ruter;

import main.Farge;
import main.Spiller;

public class GateRute extends EiendomRute {
	
	private String navn;
	private Farge farge;
	
	public GateRute(String navn, int pris) {
		super(navn, pris);
	}
	
	public void settFarge(Farge farge) {
		this.farge = farge;
	}
	
}
