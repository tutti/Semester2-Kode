package ruter;

import java.util.Random;

import main.Bank;
import main.Brett;
import main.Spill;
import main.Spiller;
import adt.RuteADT;

/**
 * Implementasjon av feltene Pr�v Lykken og Sjanse.
 * @author tutti
 *
 */
public class LykkeRute implements RuteADT {
	
	private abstract class LykkeKort {
		public abstract String trekk(Spiller spiller);
	}
	
	private LykkeKort[] sjansekort = {
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				spiller.flyttTil(Brett.hentRute(0));
				return "G� til Start.";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				spiller.flyttTil(Brett.finnRute("Slemdal"));
				return "G� til Slemdal.";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				spiller.flyttTil(Brett.finnRute("Nedre Slottsgate"));
				return "G� til Nedre Slottsgate.";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				RuteADT rute = Brett.finnRute("Oslo Lysverker");
				RuteADT rute2 = Brett.finnRute("Vannverket");
				if (spiller.avstandTil(rute2) < spiller.avstandTil(rute))
					rute = rute2;
				spiller.flyttTil(rute);
				return "G� til n�rmeste tjeneste. NYI";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				// TODO
				RuteADT rute = Brett.hentRute(5);
				for (int plass = 15; plass <= 35; ++plass) {
					RuteADT rute2 = Brett.hentRute(plass);
					if (spiller.avstandTil(rute2) > spiller.avstandTil(rute))
						rute = rute2;
				}
				spiller.flyttTil(rute);
				return "G� til n�rmeste togbane. Betal dobbel leie.";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				Bank.motta(spiller, 50);
				return "Banken betaler deg "+Bank.skrivUtPengebel�p(50)+".";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				spiller.slippUtAvFengselKort = true;
				return "Slipp gratis ut av fengsel.";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				spiller.flytt(-3);
				return "G� 3 plasser bakover. NYI";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				spiller.plasser(Brett.hentRute(10));
				return "G� direkte i fengsel.";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				int antallHus = spiller.antallHus();
				int antallHoteller = spiller.antallHoteller();
				int sum = 25*antallHus + 100*antallHoteller;
				Bank.betale(spiller, sum);
				return "Reparer dine hus og hoteller. Betal "
					+Bank.skrivUtPengebel�p(25)
					+" for hvert hus, og "
					+Bank.skrivUtPengebel�p(100)
					+" for hvert hotell (totalt "
					+Bank.skrivUtPengebel�p(sum)
					+").";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				Bank.betale(spiller, 15);
				return "Betal "+Bank.skrivUtPengebel�p(15)+" til de fattige.";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				spiller.flyttTil(Brett.finnRute("Oslo S"));
				return "G� til Oslo S.";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				spiller.flyttTil(Brett.finnRute("R�dhusplassen"));
				return "G� til R�dhusplassen.";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				Spiller[] spillere = Spill.hentSpillere();
				for (Spiller hverspiller : spillere) {
					Bank.betale(spiller, hverspiller, 50);
				}
				return "Betal hver spiller "+Bank.skrivUtPengebel�p(50)+". NYI";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				Bank.motta(spiller, 150);
				return "Motta renter p� l�n "+Bank.skrivUtPengebel�p(150)+".";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				Bank.motta(spiller, 100);
				return "Du har vunnet "
					+Bank.skrivUtPengebel�p(100)
					+" i en kryssordkonkurranse.";
			}
		}
	};
	
	private LykkeKort[] lykkekort = {
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				spiller.flyttTil(Brett.hentRute(0));
				return "G� til start.";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				Bank.motta(spiller, 200);
				return "Bankfeil i din fav�r - motta "
					+Bank.skrivUtPengebel�p(200)
					+".";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				Bank.betale(spiller, 50);
				return "Egenandel p� legebes�k. Betal "
					+Bank.skrivUtPengebel�p(50)
					+".";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				Bank.motta(spiller, 50);
				return "Motta "
					+Bank.skrivUtPengebel�p(50)
					+" fra salg av aksjer.";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				spiller.slippUtAvFengselKort = true;
				return "Slipp gratis ut av fengsel.";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				spiller.plasser(Brett.hentRute(10));
				return "G� direkte i fengsel.";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				Spiller[] spillere = Spill.hentSpillere();
				for (Spiller hverspiller : spillere) {
					Bank.betale(hverspiller, spiller, 50);
				}
				return "Operakveld - motta "
					+Bank.skrivUtPengebel�p(50)
					+" fra hver spiller. NYI";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				Bank.motta(spiller, 100);
				return "Julefondet ditt gir avkastning. Motta "
					+Bank.skrivUtPengebel�p(100)
					+".";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				Bank.motta(spiller, 20);
				return "Tilbakebetaling p� skatt. Motta "
					+Bank.skrivUtPengebel�p(20)
					+".";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				Spiller[] spillere = Spill.hentSpillere();
				for (Spiller hverspiller : spillere) {
					Bank.betale(hverspiller, spiller, 10);
				}
				return "Det er din f�dselsdag. Motta "
					+Bank.skrivUtPengebel�p(10)
					+" fra hver spiller. NYI";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				Bank.motta(spiller, 100);
				return "Avkastning p� livsforsikring. Motta "
					+Bank.skrivUtPengebel�p(100)
					+".";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				Bank.betale(spiller, 100);
				return "Betal for et sykehusopphold "
					+Bank.skrivUtPengebel�p(100)
					+".";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				Bank.betale(spiller, 150);
				return "Betal semesteravgift "
					+Bank.skrivUtPengebel�p(150)
					+".";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				Bank.motta(spiller, 25);
				return "Motta "
					+Bank.skrivUtPengebel�p(25)
					+" for tjenester utf�rt.";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				int antallHus = spiller.antallHus();
				int antallHoteller = spiller.antallHoteller();
				int sum = 40*antallHus + 115*antallHoteller;
				Bank.betale(spiller, sum);
				return "Reparer dine hus og hoteller. Betal "
						+Bank.skrivUtPengebel�p(40)
						+" for hvert hus, og "
						+Bank.skrivUtPengebel�p(115)
						+" for hvert hotell (totalt "
						+Bank.skrivUtPengebel�p(sum)
						+").";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				Bank.motta(spiller, 10);
				return "Du har vunnet andrepremie i en skj�nnhets"
					+"konkurranse. Motta "
					+Bank.skrivUtPengebel�p(10)
					+".";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				Bank.motta(spiller, 100);
				return "Du arver "
					+Bank.skrivUtPengebel�p(100)
					+".";
			}
		}
	};
	
	private String type;
	private Random rnd = new Random();
	
	public LykkeRute(String type) {
		if (type.equals("Pr�v Lykken") || type.equals("Sjanse")) {
			this.type = type;
		} else {
			throw new RuntimeException("Ugyldig lykkerute-type");
		}
	}
	
	@Override
	public void spillerLander(Spiller spiller, int kast) {
		LykkeKort[] kort = type.equals("Pr�v Lykken") ? lykkekort : sjansekort;
		int velgKort = rnd.nextInt(kort.length);
		kort[velgKort].trekk(spiller);
	}
	
}
