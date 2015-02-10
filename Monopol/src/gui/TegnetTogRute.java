package gui;

import java.awt.Graphics;
import ruter.TogRute;
import adt.RuteADT;

public class TegnetTogRute extends TegnetEiendomRute {
	
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
	}

}
