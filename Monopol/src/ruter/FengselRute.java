package ruter;

import main.Spiller;
import adt.RuteADT;

public class FengselRute implements RuteADT {

	@Override
	public boolean erEiendom() {
		return false;
	}
	
	@Override
	public void spillerForlater(Spiller spiller, int kast) {
		// Hvis spilleren st�r i fengsel, f�r den ikke forlate ruten.
		// (dette gj�res ved � sette spilleren tilbake til fengselsruten
		// etter at den har flyttet).
		if (spiller.tidIFengsel() > 0) {
			spiller.plasser(this);
		}
	}

}
