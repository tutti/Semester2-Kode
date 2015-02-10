package gui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

import ruter.EiendomRute;
import ruter.GateRute;
import adt.RuteADT;

public class TegnetGateRute extends TegnetEiendomRute {
	
	protected static Image husBilde;
	protected static Image hotellBilde;
	static {
		try {
			husBilde = ImageIO.read(new File("hus.png"));
			hotellBilde = ImageIO.read(new File("hotell.png"));
		} catch (Exception e) {}
	}
	
	protected GateRute rute;

	public TegnetGateRute(RuteADT rute, int x, int y) {
		super(rute, x, y);
		if (!(rute instanceof GateRute)) {
			throw new RuntimeException("Må ha en GateRute.");
		}
		this.rute = (GateRute)rute;
		farge = this.rute.farge();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (rute.antallHus() == 5) {
			g.drawImage(hotellBilde, 4, 4, null);
		} else {
			for (int i=0; i<rute.antallHus(); ++i) {
				g.drawImage(husBilde, 4+18*i, 4, null);
			}
		}
	}

}
