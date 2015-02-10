package gui;

import java.awt.Graphics;
import ruter.SkatteRute;
import adt.RuteADT;

public class TegnetSkatteRute extends TegnetRute {
	
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
	}

}
