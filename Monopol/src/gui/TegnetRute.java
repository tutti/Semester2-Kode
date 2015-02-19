package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;

import main.Spiller;
import adt.RuteADT;

public abstract class TegnetRute extends JButton {
	private static final long serialVersionUID = 7110471747160876341L;
	
	public static final int PUNKT_TOPP_VENSTRE = 0;
	public static final int PUNKT_TOPP_H�YRE = 1;
	public static final int PUNKT_BUNN_VENSTRE = 2;
	public static final int PUNKT_BUNN_H�YRE = 3;
	
	public final static int bredde = 100;
	public final static int h�yde = 60;

	public static TegnetRute lagTegnetRute(RuteADT rute, int x, int y) {
		switch (rute.getClass().getName()) {
			case "ruter.FengselRute":
				return new TegnetFengselRute(rute, x, y);
			case "ruter.GateRute":
				return new TegnetGateRute(rute, x, y);
			case "ruter.GratisParkeringRute":
				return new TegnetGratisParkeringRute(rute, x, y);
			case "ruter.G�IFengselRute":
				return new TegnetG�IFengselRute(rute, x, y);
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
				return null;
		}
	}
	
	protected Font font = new Font("MonopolbrettFont", Font.PLAIN, 9);
	protected Font storFont = new Font("MonopolbrettStorFont", Font.BOLD, 14);
	
	protected RuteADT rute;
	protected int posisjon_x = 0;
	protected int posisjon_y = 0;
	
	private Map<Bilde, Boolean> spillere = new HashMap<Bilde, Boolean>();
	
	public TegnetRute(RuteADT rute) {
		this.rute = rute;
		this.setBounds(0, 0, bredde, h�yde);
	}
	
	public TegnetRute(RuteADT rute, int x, int y) {
		this.rute = rute;
		posisjon_x = x;
		posisjon_y = y;
		this.setBounds(x, y, bredde, h�yde);
	}
	
	public void settPosisjon(int x, int y) {
		posisjon_x = x;
		posisjon_y = y;
		this.setBounds(posisjon_x, posisjon_y, bredde, h�yde);
	}
	
	public int[] hentPunkt(int punkt) {
		int[] r = new int[2];
		r[0] = posisjon_x + (punkt & PUNKT_TOPP_H�YRE) * bredde;
		r[1] = posisjon_y + ((punkt & PUNKT_BUNN_VENSTRE) / PUNKT_BUNN_VENSTRE) * h�yde;
		return r;
	}
	
	public final void plasserBrikke(Bilde bilde) {
		spillere.put(bilde, true);
	}
	
	public final void fjernBrikke(Bilde bilde) {
		spillere.put(bilde, false);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, bredde, h�yde);
		tegnSpillere(g);
	}
	
	protected void skrivRuteNavn(Graphics g, String navn) {
		g.setFont(storFont);
		FontMetrics fm = g.getFontMetrics();
		int bredde = fm.stringWidth(navn);
		g.setColor(Color.BLACK);
		g.drawString(navn, (TegnetRute.bredde - bredde)/2, h�yde/2+3);
		g.setFont(font);
	}
	
	/**
	 * Tegner spillere nederst til venstre p� ruten
	 * Kalles automatisk, men kan kalles igjen dersom denne delen overskrives.
	 */
	protected void tegnSpillere(Graphics g) {
		int plass = 0;
		for (Map.Entry<Bilde, Boolean> entry : spillere.entrySet()) {
			if (entry.getValue()) {
				g.drawImage(entry.getKey().hentBilde(), bredde-16-12*plass++, h�yde-16, null);
			}
		}
	}
	
}
