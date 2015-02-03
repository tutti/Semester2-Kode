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
				return "Gå til Start.";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				spiller.flyttTil(Brett.finnRute("Slemdal"));
				return "Gå til Slemdal.";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				spiller.flyttTil(Brett.finnRute("Nedre Slottsgate"));
				return "Gå til Nedre Slottsgate.";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				// TODO NYI
				return "Gå til nærmeste tjeneste. NYI";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				// TODO NYI
				return "Gå til nærmeste togbane. Betal dobbel leie. NYI";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				Bank.motta(spiller, 50);
				return "Banken betaler deg "+Bank.skrivUtPengebeløp(50)+".";
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
				return "Gå 3 plasser bakover. NYI";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				spiller.plasser(Brett.hentRute(10));
				return "Gå direkte i fengsel.";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				// TODO NYI
				return "Reparer dine hus og hoteller. Betal "
					+Bank.skrivUtPengebeløp(25)
					+" for hvert hus, og "
					+Bank.skrivUtPengebeløp(100)
					+" for hvert hotell. NYI";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				Bank.betale(spiller, 15);
				return "Betal "+Bank.skrivUtPengebeløp(15)+" til de fattige.";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				spiller.flyttTil(Brett.finnRute("Oslo S"));
				return "Gå til Oslo S.";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				spiller.flyttTil(Brett.finnRute("Rådhusplassen"));
				return "Gå til Rådhusplassen.";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				// TODO NYI
				return "Betal hver spiller "+Bank.skrivUtPengebeløp(50)+". NYI";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				Bank.motta(spiller, 150);
				return "Motta renter på lån "+Bank.skrivUtPengebeløp(150)+".";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				Bank.motta(spiller, 100);
				return "Du har vunnet "
					+Bank.skrivUtPengebeløp(100)
					+" i en kryssordkonkurranse.";
			}
		}
	};
	
	private LykkeKort[] lykkekort = {
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				spiller.flyttTil(Brett.hentRute(0));
				return "Gå til start.";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				Bank.motta(spiller, 200);
				return "Bankfeil i din favør - motta "
					+Bank.skrivUtPengebeløp(200)
					+".";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				Bank.betale(spiller, 50);
				return "Egenandel på legebesøk. Betal "
					+Bank.skrivUtPengebeløp(50)
					+".";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				Bank.motta(spiller, 50);
				return "Motta "
					+Bank.skrivUtPengebeløp(50)
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
				return "Gå direkte i fengsel.";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				// TODO NYI
				return "Operakveld - motta "
					+Bank.skrivUtPengebeløp(50)
					+" fra hver spiller. NYI";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				Bank.motta(spiller, 100);
				return "Julefondet ditt gir avkastning. Motta "
					+Bank.skrivUtPengebeløp(100)
					+".";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				Bank.motta(spiller, 20);
				return "Tilbakebetaling på skatt. Motta "
					+Bank.skrivUtPengebeløp(20)
					+".";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				// TODO NYI
				return "Det er din fødselsdag. Motta "
					+Bank.skrivUtPengebeløp(10)
					+" fra hver spiller. NYI";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				Bank.motta(spiller, 100);
				return "Avkastning på livsforsikring. Motta "
					+Bank.skrivUtPengebeløp(100)
					+".";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				Bank.betale(spiller, 100);
				return "Betal for et sykehusopphold "
					+Bank.skrivUtPengebeløp(100)
					+".";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				Bank.betale(spiller, 150);
				return "Betal semesteravgift "
					+Bank.skrivUtPengebeløp(150)
					+".";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				Bank.motta(spiller, 25);
				return "Motta "
					+Bank.skrivUtPengebeløp(25)
					+" for tjenester utført.";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				// TODO NYI
				return "Reparer dine hus og hoteller. Betal "
						+Bank.skrivUtPengebeløp(40)
						+" for hvert hus, og "
						+Bank.skrivUtPengebeløp(115)
						+" for hvert hotell. NYI";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				Bank.motta(spiller, 10);
				return "Du har vunnet andrepremie i en skjønnhets"
					+"konkurranse. Motta "
					+Bank.skrivUtPengebeløp(10)
					+".";
			}
		},
		new LykkeKort() {
			public String trekk(Spiller spiller) {
				Bank.motta(spiller, 100);
				return "Du arver "
					+Bank.skrivUtPengebeløp(100)
					+".";
			}
		}
	};
	
}
