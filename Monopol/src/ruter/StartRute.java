package ruter;

import main.Bank;
import main.Spiller;
import adt.RuteADT;

public class StartRute implements RuteADT {

	@Override
	public boolean erEiendom() {
		return false;
	}
	
	@Override
	public void spillerPasserer(Spiller spiller, int kast) {
//		System.out.println(spiller.navn()+" passerer start.");
//		System.out.println(Bank.hentPengebeløp(spiller));
		Bank.motta(spiller, 200);
//		System.out.println(Bank.hentPengebeløp(spiller));
	}

}
