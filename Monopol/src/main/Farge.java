package main;

import java.awt.Color;

import adt.RuteADT;

public class Farge {
	
	private Color farge;
	private RuteADT[] ruter = new RuteADT[2];
	private int antall = 0;
	
	public Farge(Color farge) {
		this.farge = farge;
	}
	
	public void leggTilRute(RuteADT rute) {
		++antall;
		if (antall > 2) {
			RuteADT[] nyRuter = new RuteADT[antall];
			for (int i=0; i<antall-1; ++i) nyRuter[i] = ruter[i];
			ruter = nyRuter;
		}
		ruter[antall] = rute;
	}
	
	private int hentAntall() {
		return antall;
	}
	
	private RuteADT[] hentRuter() {
		return ruter.clone();
	}
	
	private Color hentFarge() {
		return farge;
	}
	
}
