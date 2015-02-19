package gui;

import java.awt.Graphics;
import ruter.TjenesteRute;
import adt.RuteADT;

public class TegnetTjenesteRute extends TegnetEiendomRute {
	private static final long serialVersionUID = -7481862064092017390L;
	
	protected TjenesteRute rute;

	public TegnetTjenesteRute(RuteADT rute, int x, int y) {
		super(rute, x, y);
		if (!(rute instanceof TjenesteRute)) {
			throw new RuntimeException("Må ha en TjenesteRute.");
		}
		this.rute = (TjenesteRute)rute;
		farge = this.rute.farge();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

}
