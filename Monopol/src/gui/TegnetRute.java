package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JButton;

import adt.RuteADT;

public abstract class TegnetRute extends JButton {
	
	public static TegnetRute lagTegnetRute(RuteADT rute, int x, int y) {
		switch (rute.getClass().getName()) {
			case "ruter.FengselRute":
				return new TegnetFengselRute(rute, x, y);
			case "ruter.GateRute":
				return new TegnetGateRute(rute, x, y);
			case "ruter.GratisParkeringRute":
				return new TegnetGratisParkeringRute(rute, x, y);
			case "ruter.GåIFengselRute":
				return new TegnetGåIFengselRute(rute, x, y);
			case "ruter.LykkeRute":
				return new TegnetLykkeRute(rute, x, y);
			case "ruter.SkatteRute":
				return new TegnetSkatteRute(rute, x, y);
			case "ruter.StartRute":
				return new TegnetStartRute(rute, x, y);
			case "ruter.TjenesteRute":
				return new TegnetTjenesteRute(rute, x, y);
			case "ruter.TogRute":
				return new TegnetTogRute(rute, x, y);
			default:
//				System.out.println("Kan ikke lage "+rute.getClass().getName());
				return null;
		}
	}
	
	private Font font = new Font("MonopolbrettFont", Font.PLAIN, 9);
	private Font storFont = new Font("MonopolbrettStorFont", Font.BOLD, 14);
	
	protected RuteADT rute;
	public final static int bredde = 100;
	public final static int høyde = 60;
	protected int posisjon_x = 0;
	protected int posisjon_y = 0;
	
	public TegnetRute(RuteADT rute) {
		this.rute = rute;
		this.setBounds(0, 0, bredde, høyde);
	}
	
	public TegnetRute(RuteADT rute, int x, int y) {
		this.rute = rute;
		posisjon_x = x;
		posisjon_y = y;
		this.setBounds(x, y, bredde, høyde);
	}
	
	public void settPosisjon(int x, int y) {
		posisjon_x = x;
		posisjon_y = y;
		this.setBounds(posisjon_x, posisjon_y, bredde, høyde);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, bredde, høyde);
	}
	
	protected void skrivRuteNavn(Graphics g, String navn) {
		g.setFont(storFont);
		FontMetrics fm = g.getFontMetrics();
		int bredde = fm.stringWidth(navn);
		g.setColor(Color.BLACK);
		g.drawString(navn, (TegnetRute.bredde - bredde)/2, høyde/2+3);
		g.setFont(font);
	}
	
}
