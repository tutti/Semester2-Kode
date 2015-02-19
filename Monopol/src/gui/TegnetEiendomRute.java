package gui;

import java.awt.Color;
import java.awt.Graphics;

import ruter.EiendomRute;
import adt.RuteADT;

public abstract class TegnetEiendomRute extends TegnetRute {
	private static final long serialVersionUID = 274089860200865411L;

	public static final Color LYSEGUL = new Color(255, 255, 200);
	
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
		g.setColor(Color.WHITE);
		g.fillRect(4, 4, bredde-8, høyde-8);
		
		g.setColor(Color.BLACK);
		g.drawString(rute.navn(), 5, 32);
		
		if (rute.harEier()) {
			g.drawString("Eier: "+rute.hentEier().navn(), 5, 42);
		}
		
		tegnSpillere(g);
	}

}
