package gui;

import java.awt.Graphics;
import ruter.TogRute;
import adt.RuteADT;

public class TegnetTogRute extends TegnetEiendomRute {
	private static final long serialVersionUID = -5805840983527993433L;
	
	protected TogRute rute;

	public TegnetTogRute(RuteADT rute, int x, int y) {
		super(rute, x, y);
		if (!(rute instanceof TogRute)) {
			throw new RuntimeException("Må ha en TogRute.");
		}
		this.rute = (TogRute)rute;
		farge = this.rute.farge();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (rute.harEier()) {
			g.drawString("Leie: "+rute.beregnLeie(0), 5, 52);
		} else {
			g.drawString("Pris: "+rute.pris(), 5, 17);
		}
	}

}
