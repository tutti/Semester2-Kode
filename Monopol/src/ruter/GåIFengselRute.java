package ruter;

import main.Brett;
import main.Spiller;
import adt.RuteADT;

public class G�IFengselRute implements RuteADT {
	public void spillerLander(Spiller spiller, int kast) {
		spiller.plasser(Brett.hentRute(10));
	}
}
