package ruter;

import main.Bank;
import main.Brett;
import main.Spiller;
import adt.RuteADT;

/**
 * TODO Alt her.
 * @author tutti
 *
 */
public class LykkeRute implements RuteADT {
	
	private abstract class LykkeKort {
		public String trekk;
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
				// TODO NYI
				return "G� til n�rmeste tjeneste. NYI";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				// TODO NYI
				return "G� til n�rmeste togbane. Betal dobbel leie. NYI";
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
				// TODO NYI
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
				// TODO NYI
				return "Reparer dine hus og hoteller. Betal "
					+Bank.skrivUtPengebel�p(25)
					+" for hvert hus, og "
					+Bank.skrivUtPengebel�p(100)
					+" for hvert hotell. NYI";
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
				// TODO NYI
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
				// TODO NYI
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
				// TODO NYI
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
				// TODO NYI
				return "Reparer dine hus og hoteller. Betal "
						+Bank.skrivUtPengebel�p(40)
						+" for hvert hus, og "
						+Bank.skrivUtPengebel�p(115)
						+" for hvert hotell. NYI";
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
	
}
