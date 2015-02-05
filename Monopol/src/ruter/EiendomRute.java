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
	
	public int pris() {
		return pris;
	}

	@Override
	public final boolean erEiendom() {
		return true;
	}

	@Override
	public final boolean harEier() throws IkkeEiendomException {
		return (eier != null);
	}

	@Override
	public final Spiller hentEier() throws IkkeEiendomException, IngenEierException {
		if (eier == null) throw new IngenEierException();
		return eier;
	}

	@Override
	public final void settEier(Spiller spiller) throws IkkeEiendomException {
		eier = spiller;
	}
	
	@Override
	public final void pantsett() throws IngenEierException, KanIkkeBetaleException {
		if (eier == null) throw new IngenEierException();
		if (pantsatt)
			throw new RuntimeException("Eiendom er allerede pantsatt.");
		Bank.motta(eier, pris/2);
	}
	
	@Override
	public final void utløs() {
		if (eier == null) throw new IngenEierException();
		if (Bank.hentPengebeløp(eier) < (pris / 2))
			throw new KanIkkeBetaleException();
		if (!pantsatt)
			throw new RuntimeException("Eiendom er ikke pantsatt.");
		Bank.betale(eier, (int)((pris/2)*1.1));
	}
	
	@Override
	public final boolean erPantsatt() {
		return pantsatt;
	}
	
	@Override
	public void spillerLander(Spiller spiller, int kast) {
		if (this.harEier()) {
			// Gaten er eid - spiller må betale leie
			int leie = this.beregnLeie(kast);
			Bank.betale(spiller, eier, leie);
		} else {
			// Gaten er ikke eid - spiller må velge om han/hun vil kjøpe
			boolean vilKjøpe = spiller.vilKjøpe(this);
			if (vilKjøpe) {
				Bank.betale(spiller, this.pris);
				this.eier = spiller;
				spiller.leggTilEiendom(this);
			} else {
				// TODO Auksjon.
			}
		}
	}
	
	@Override
	public void settFarge(RuteGruppe farge) {
		this.farge = farge;
		farge.leggTilRute(this);
	}
	
	public abstract int beregnLeie(int kast);

}
