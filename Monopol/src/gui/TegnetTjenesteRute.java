package gui;

import java.awt.Graphics;
import ruter.TjenesteRute;
import adt.RuteADT;

public class TegnetTjenesteRute extends TegnetEiendomRute {
	
	protected TjenesteRute rute;

	public TegnetTjenesteRute(RuteADT rute, int x, int y) {
		super(rute, x, y);
		if (!(rute instanceof TjenesteRute)) {
			throw new RuntimeException("M� ha en TjenesteRute.");
		}
		this.rute = (TjenesteRute)rute;
		farge = this.rute.farge();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

}
