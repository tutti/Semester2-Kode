package main;

import java.awt.Color;
import ruter.EiendomRute;
import adt.RuteADT;

public class RuteGruppe {
	
	private Color farge;
	private RuteADT[] ruter = new RuteADT[2];
	private int antall = 0;
	
	public RuteGruppe(Color farge) {
		this.farge = farge;
	}
	
	public void leggTilRute(EiendomRute rute) {
		++antall;
		if (antall > 2) {
			RuteADT[] nyRuter = new RuteADT[antall];
			for (int i=0; i<antall-1; ++i) nyRuter[i] = ruter[i];
			ruter = nyRuter;
		}
		ruter[antall-1] = rute;
	}
	
	public int hentAntall() {
		return antall;
	}
	
	public RuteADT[] hentRuter() {
		return ruter.clone();
	}
	
	public Color hentFarge() {
		return farge;
	}
	
	public int antallEid(Spiller spiller) {
		int antall = 0;
		for (RuteADT rute : ruter) {
			if (!rute.harEier()) continue;
			if (rute.hentEier() == spiller) ++antall;
		}
		return antall;
	}
	
}
