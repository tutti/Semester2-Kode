package gui;

import java.awt.Color;
import java.awt.Graphics;

import ruter.EiendomRute;
import adt.RuteADT;

public abstract class TegnetEiendomRute extends TegnetRute {
	
	protected EiendomRute rute;
	protected Color farge = Color.GRAY;

	public TegnetEiendomRute(RuteADT rute, int x, int y) {
		super(rute, x, y);
		if (!(rute instanceof EiendomRute)) {
			throw new RuntimeException("Må ha en EiendomRute.");
		}
		this.rute = (EiendomRute)rute;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(farge);
		g.fillRect(0, 0, bredde-1, høyde-1);
		if (rute.harEier())
			g.setColor(Color.YELLOW);
		else
			g.setColor(Color.WHITE);
		g.fillRect(4, 4, bredde-8, høyde-8);
		
		g.setColor(Color.BLACK);
		g.drawString(rute.navn(), 5, 32);
	}

}
