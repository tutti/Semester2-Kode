package gui;

import java.awt.Graphics;
import ruter.StartRute;
import adt.RuteADT;

public class TegnetStartRute extends TegnetRute {
	private static final long serialVersionUID = -1418801196243725710L;
	
	protected StartRute rute;

	public TegnetStartRute(RuteADT rute, int x, int y) {
		super(rute, x, y);
		if (!(rute instanceof StartRute)) {
			throw new RuntimeException("Må ha en StartRute.");
		}
		this.rute = (StartRute)rute;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		skrivRuteNavn(g, "Start");
	}

}
