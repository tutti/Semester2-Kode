package ruter;

import main.Bank;
import main.Spiller;
import unntak.IkkeEiendomException;
import unntak.IngenEierException;
import adt.RuteADT;

public class StartRute implements RuteADT {

	@Override
	public boolean erEiendom() {
		return false;
	}
	
	@Override
	public void spillerPasserer(Spiller spiller, int kast) {
		Bank.motta(spiller, 200);
	}

}
