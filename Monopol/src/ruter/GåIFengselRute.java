package ruter;

import main.Brett;
import main.Spiller;
import adt.RuteADT;

public class GÂIFengselRute implements RuteADT {
	public void spillerLander(Spiller spiller, int kast) {
		spiller.plasser(Brett.hentRute(10));
	}
}
