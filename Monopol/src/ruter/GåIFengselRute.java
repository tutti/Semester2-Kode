package ruter;

import main.Spiller;
import adt.RuteADT;

public class GÂIFengselRute implements RuteADT {
	public void spillerLander(Spiller spiller, int kast) {
		spiller.plasser(this);
	}
}
