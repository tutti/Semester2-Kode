package adt;

import main.Spiller;

public interface RuteADT {
	public boolean erEiendom();
	public boolean harEier();
	public Spiller hentEier();
	public void settEier(Spiller spiller);
	
	public void spillerForlater(Spiller spiller, int kast);
	public void spillerPasserer(Spiller spiller, int kast);
	public void spillerLander(Spiller spiller, int kast);
	
	public void spillerKjøper(Spiller spiller);
}
