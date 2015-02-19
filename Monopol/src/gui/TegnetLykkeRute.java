package gui;

import java.awt.Graphics;
import ruter.LykkeRute;
import adt.RuteADT;

public class TegnetLykkeRute extends TegnetRute {
	private static final long serialVersionUID = -6119263784571164533L;
	
	protected LykkeRute rute;

	public TegnetLykkeRute(RuteADT rute, int x, int y) {
		super(rute, x, y);
		if (!(rute instanceof LykkeRute)) {
			throw new RuntimeException("Må ha en LykkeRute.");
		}
		this.rute = (LykkeRute)rute;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
//		switch (rute.type()) {
//			case "lykkerute":
//				skrivRuteNavn(g, "Prøv Lykken");
//				break;
//			case "sjanserute":
//				skrivRuteNavn(g, "Sjanse");
//				break;
//			default:
//				skrivRuteNavn(g, "Ukjent");
//				break;
//		}
		skrivRuteNavn(g, rute.type());
	}

}
