package gui;

import java.awt.Dimension;

import javax.swing.*;

import adt.UIADT;
import main.Brett;

public class Spillvindu implements UIADT {
	
	private TegnetRute[] ruter = new TegnetRute[40];
	
	private JFrame hovedvindu;
	
	public Spillvindu() {
		hovedvindu = new JFrame("Spillvindu");
		hovedvindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hovedvindu.setLayout(null);
        
//        TegnetRute testknapp = TegnetRute.lagTegnetRute(Brett.hentRute(10), 20, 20);
//        hovedvindu.getContentPane().add(testknapp);
        
        for (int i=0; i<40; ++i) {
        	int x, y;
        	if (i<10) {
        		x = TegnetRute.bredde*(10-i);
        		y = 10*TegnetRute.høyde;
        	} else if (i<20) {
        		x = 0;
        		y = TegnetRute.høyde*(20-i);
        	} else if (i<30) {
        		x = TegnetRute.bredde*(i-20);
        		y = 0;
        	}
        	else {
        		x = 10*TegnetRute.bredde;
        		y = TegnetRute.høyde*(i-30);
        	}
        	ruter[i] = TegnetRute.lagTegnetRute(Brett.hentRute(i), x, y);
        	hovedvindu.getContentPane().add(ruter[i]);
        }
        
//        hovedvindu.setSize(200, 200);
        hovedvindu.getContentPane().setPreferredSize(new Dimension(1100, 11*60));
        hovedvindu.pack();
        hovedvindu.setVisible(true);
	}
	
	@Override
	public void hendelse(int hendelse_id, Object ... data) {
		switch (hendelse_id) {
			case UIADT.EIENDOM_KJØPE_HUS:
			case UIADT.EIENDOM_SELGE_HUS:
			case UIADT.SPILLER_MOTTA_EIENDOM:
			case UIADT.SPILLER_FJERN_EIENDOM:
				for (int i=0; i<40; ++i) {
					ruter[i].repaint();
				}
		}
	}
	
}
