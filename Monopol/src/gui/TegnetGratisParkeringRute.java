package gui;

import java.awt.Graphics;
import ruter.GratisParkeringRute;
import adt.RuteADT;

public class TegnetGratisParkeringRute extends TegnetRute {
	private static final long serialVersionUID = -1209553190772528254L;
	
	protected GratisParkeringRute rute;

	public TegnetGratisParkeringRute(RuteADT rute, int x, int y) {
		super(rute, x, y);
		if (!(rute instanceof GratisParkeringRute)) {
			throw new RuntimeException("Må ha en GratisParkeringRute.");
		}
		this.rute = (GratisParkeringRute)rute;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		skrivRuteNavn(g, "Gratis parkering");
	}

}
