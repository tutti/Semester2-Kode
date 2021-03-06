package gui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import ruter.GateRute;
import adt.RuteADT;

public class TegnetGateRute extends TegnetEiendomRute {
	private static final long serialVersionUID = -7398121836996070815L;
	
	protected static Image husBilde;
	protected static Image hotellBilde;
	static {
		try {
			husBilde = ImageIO.read(new File("bilder/hus.png"));
			hotellBilde = ImageIO.read(new File("bilder/hotell.png"));
		} catch (Exception e) {}
	}
	
	protected GateRute rute;

	public TegnetGateRute(RuteADT rute, int x, int y) {
		super(rute, x, y);
		if (!(rute instanceof GateRute)) {
			throw new RuntimeException("M� ha en GateRute.");
		}
		this.rute = (GateRute)rute;
		farge = this.rute.farge();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (rute.harEier()) {
			g.drawString("Leie: "+rute.beregnLeie(0), 5, 52);
			g.drawLine(4, 20, bredde-6, 20);
		} else {
			g.drawString("Pris: "+rute.pris(), 5, 17);
		}
		
		if (rute.antallHus() == 5) {
			g.drawImage(hotellBilde, 4, 4, null);
		} else {
			for (int i=0; i<rute.antallHus(); ++i) {
				g.drawImage(husBilde, 4+(bredde/4-2)*i, 4, null);
			}
		}
	}

}
