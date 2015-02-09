package ruter;

import adt.RuteADT;

public class TjenesteRute extends EiendomRute implements RuteADT {
	
	public TjenesteRute(String navn, int pris) {
		super(navn, pris);
	}
	
	public int beregnLeie(int kast) {
		if (eier == null) throw new RuntimeException("Ingen eier");
		if (farge == null) throw new RuntimeException("Ingen farge");
		if (farge.antallEid(eier) == 2) return kast * 10;
		return kast * 4;
	}

}
