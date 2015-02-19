package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import adt.RuteADT;
import adt.UIADT;
import main.Brett;
import main.Spill;
import main.Spiller;

public class Spillvindu implements UIADT {
	
	private TegnetRute[] ruter = new TegnetRute[40];
	private JFrame hovedvindu;
	private JLabel[] spillernavn = new JLabel[Spill.MAKS_SPILLERE];
	private Bilde[] spillerlistebrikke = new Bilde[Spill.MAKS_SPILLERE];
	private Bilde[] spillerbrettbrikke = new Bilde[Spill.MAKS_SPILLERE];
	
//	private boolean harStartet = false;
	
	public Spillvindu() {
		hovedvindu = new JFrame("Spillvindu");
		hovedvindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hovedvindu.setLayout(null);
        
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
        
//        JButton testknapp = new JButton();
//        testknapp.setBounds(200, 200, 100, 100);
//        testknapp.setText("Start");
//        testknapp.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				harStartet = true;
//			}
//        });
//        hovedvindu.getContentPane().add(testknapp);
        
        for (int i=0; i<Spill.MAKS_SPILLERE; ++i) {
        	spillernavn[i] = new JLabel("Spiller "+(i+1));
        	spillernavn[i].setBounds(135, 80+18*i, 120, 20);
        	spillerlistebrikke[i] = new Bilde("bilder/brikker/brikke"+(i+1)+".png");
        	spillerlistebrikke[i].setBounds(120, 84+18*i, 12, 12);
        	spillerbrettbrikke[i] = new Bilde("bilder/brikker/brikke"+(i+1)+".png");
        	hovedvindu.getContentPane().add(spillernavn[i]);
        	hovedvindu.getContentPane().add(spillerlistebrikke[i]);
        	hovedvindu.getContentPane().add(spillerbrettbrikke[i]);
        }
        
        hovedvindu.getContentPane().setPreferredSize(new Dimension(1100, 11*60));
        hovedvindu.pack();
        hovedvindu.setVisible(true);
	}
	
	@Override
	public void fortell(int hendelse_id, Object ... data) {
//		for (int i=0; i<Spill.MAKS_SPILLERE; ++i) {
//			spillernavn[i].repaint();
//			spillerlistebrikke[i].repaint();
//		}
		switch (hendelse_id) {
			case UIADT.EIENDOM_KJØPE_HUS:
			case UIADT.EIENDOM_SELGE_HUS:
			case UIADT.SPILLER_MOTTA_EIENDOM:
			case UIADT.SPILLER_FJERN_EIENDOM:
				for (int i=0; i<40; ++i) {
					ruter[i].repaint();
				}
				break;
			case UIADT.SPILLER_PLASSERE:
				// 0: Spiller
				// 1: Integer (ruten spilleren forlater)
				// 2: Integer (ruten spilleren plasseres på)
				Spiller[] spillere = Spill.hentSpillere();
				for (int i=0; i<spillere.length; ++i) {
					Spiller spiller = spillere[i];
					if (spiller == data[0]) {
						TegnetRute gammelRute = ruter[(Integer)data[1]];
						TegnetRute nyRute = ruter[(Integer)data[2]];
						gammelRute.fjernBrikke(spillerbrettbrikke[i]);
						gammelRute.repaint();
						nyRute.plasserBrikke(spillerbrettbrikke[i]);
						nyRute.repaint();
					}
				}
				
				
//				Spiller[] spillere = Spill.hentSpillere();
//				for (int i=0; i<spillere.length; ++i) {
//					Spiller spiller = spillere[i];
//					//if (spiller == null) continue; // Dette kan visst skje i begynnelsen...
//		        	//spillerbrettbrikke[i].setBounds(400+spiller.posisjon(), 84+18*i, 12, 12);
//					if (spiller == data[0]) {
//						//spillerbrettbrikke[i].setBounds(400+spiller.posisjon(), 84+18*i, 12, 12);
//						TegnetRute rute = ruter[spiller.posisjon()];
//						int[] punkt = rute.hentPunkt(TegnetRute.PUNKT_BUNN_HØYRE);
//						spillerbrettbrikke[i].setBounds(punkt[0]-12, punkt[1]-12, 12, 12);
//					}
//				}
				
				break;
		}
	}
	
	@Override
	public Object spør(int spørsmål_id, Object ... data) {
		return null;
	}
	
}
