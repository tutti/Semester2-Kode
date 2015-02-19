package gui;

import java.awt.Graphics;
import ruter.SkatteRute;
import adt.RuteADT;

public class TegnetSkatteRute extends TegnetRute {
	private static final long serialVersionUID = -4001748907686360298L;
	
	protected SkatteRute rute;

	public TegnetSkatteRute(RuteADT rute, int x, int y) {
		super(rute, x, y);
		if (!(rute instanceof SkatteRute)) {
			throw new RuntimeException("Må ha en SkatteRute.");
		}
		this.rute = (SkatteRute)rute;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		skrivRuteNavn(g, rute.navn());
	}

}
