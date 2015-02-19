package gui;

import java.awt.Graphics;
import ruter.G�IFengselRute;
import adt.RuteADT;

public class TegnetG�IFengselRute extends TegnetRute {
	private static final long serialVersionUID = -4548353952235529954L;
	
	protected G�IFengselRute rute;

	public TegnetG�IFengselRute(RuteADT rute, int x, int y) {
		super(rute, x, y);
		if (!(rute instanceof G�IFengselRute)) {
			throw new RuntimeException("M� ha en G�IFengselRute.");
		}
		this.rute = (G�IFengselRute)rute;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		skrivRuteNavn(g, "G� i fengsel");
	}

}
