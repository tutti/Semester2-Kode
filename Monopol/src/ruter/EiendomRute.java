package ruter;

import main.Bank;
import main.RuteGruppe;
import main.Spiller;
import unntak.IkkeEiendomException;
import unntak.IngenEierException;
import unntak.KanIkkeBetaleException;
import adt.RuteADT;

public abstract class EiendomRute implements RuteADT {
	
	protected Spiller eier = null;
	protected String navn = null;
	protected int pris = 0;
	protected RuteGruppe farge;
	protected boolean pantsatt = false;
	
	public EiendomRute(String navn, int pris) {
		this.navn = navn;
		this.pris = pris;
	}
	
	public String navn() {
		return navn;
	}

	@Override
	public boolean erEiendom() {
		return true;
	}

	@Override
	public boolean harEier() throws IkkeEiendomException {
		return (eier != null);
	}

	@Override
	public Spiller hentEier() throws IkkeEiendomException, IngenEierException {
		if (eier == null) throw new IngenEierException();
		return eier;
	}

	@Override
	public void settEier(Spiller spiller) throws IkkeEiendomException {
		eier = spiller;
	}
	
	@Override
	public void pantsett() throws IngenEierException, KanIkkeBetaleException {
		if (eier == null) throw new IngenEierException();
		if (pantsatt)
			throw new RuntimeException("Eiendom er allerede pantsatt.");
		Bank.motta(eier, pris/2);
	}
	
	@Override
	public void utløs() {
		if (eier == null) throw new IngenEierException();
		if (Bank.hentPengebeløp(eier) < (pris / 2))
			throw new KanIkkeBetaleException();
		if (!pantsatt)
			throw new RuntimeException("Eiendom er ikke pantsatt.");
		Bank.betale(eier, (int)((pris/2)*1.1));
	}
	
	@Override
	public void spillerLander(Spiller spiller, int kast) {
		// TODO: Spiller må enten velge å kjøpe/ikke kjøpe gaten, eller betale leie
	}
	
	public abstract int beregnLeie(int kast);
	
	@Override
	public void settFarge(RuteGruppe farge) {
		this.farge = farge;
		farge.leggTilRute(this);
	}

}
