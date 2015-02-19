package gui;

import java.awt.Graphics;

import ruter.FengselRute;
import adt.RuteADT;

public class TegnetFengselRute extends TegnetRute {
	private static final long serialVersionUID = 6107030632314981013L;
	protected FengselRute rute;

	public TegnetFengselRute(RuteADT rute, int x, int y) {
		super(rute, x, y);
		if (!(rute instanceof FengselRute)) {
			throw new RuntimeException("Må ha en FengselRute.");
		}
		this.rute = (FengselRute)rute;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		skrivRuteNavn(g, "Fengsel");
	}

}
