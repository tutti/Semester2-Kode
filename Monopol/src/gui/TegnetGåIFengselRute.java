package gui;

import java.awt.Graphics;
import ruter.GÂIFengselRute;
import adt.RuteADT;

public class TegnetGÂIFengselRute extends TegnetRute {
	private static final long serialVersionUID = -4548353952235529954L;
	
	protected GÂIFengselRute rute;

	public TegnetGÂIFengselRute(RuteADT rute, int x, int y) {
		super(rute, x, y);
		if (!(rute instanceof GÂIFengselRute)) {
			throw new RuntimeException("MÂ ha en GÂIFengselRute.");
		}
		this.rute = (GÂIFengselRute)rute;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		skrivRuteNavn(g, "GÂ i fengsel");
	}

}
