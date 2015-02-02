package main;

import java.awt.Color;
import adt.RuteADT;
import ruter.*;

public class Brett {
	
	private static RuteADT[] ruter;
	private static Farge BRUN = new Farge(new Color(139,69, 19));
	private static Farge CYAN = new Farge(new Color(0, 255, 255));
	private static Farge ROSA = new Farge(Color.PINK);
	private static Farge ORANSJE = new Farge(Color.ORANGE);
	private static Farge RØD = new Farge(Color.RED);
	private static Farge GUL = new Farge(Color.YELLOW);
	private static Farge GRØNN = new Farge(Color.GREEN);
	private static Farge BLÅ = new Farge(Color.BLUE);
	
	public static final int ANTALL_RUTER = 40;
	
	static {
		ruter = new RuteADT[ANTALL_RUTER];
		
		ruter[0] = new StartRute();
		ruter[1] = new GateRute("Parkveien", 60);
		ruter[2] = new LykkeRute();
		ruter[3] = new GateRute("Kirkeveien", 60);
		ruter[4] = new SkatteRute("Inntektsskatt", 200);
		ruter[5] = new TogRute("Oslo S", 200);
		ruter[6] = new GateRute("Kongens Gate", 100);
		ruter[7] = new LykkeRute();
		ruter[8] = new GateRute("Prinsens Gate", 100);
		ruter[9] = new GateRute("Øvre Slottsgate", 120);
		
		ruter[10] = new FengselRute();
		ruter[11] = new GateRute("Nedre Slottsgate", 140);
		ruter[12] = new TjenesteRute("Oslo Lysverker", 150);
		ruter[13] = new GateRute("Trondheimsveien", 140);
		ruter[14] = new GateRute("Nobels Gate", 160);
		ruter[15] = new TogRute("Skøyen Stasjon", 200);
		ruter[16] = new GateRute("Grensen", 180);
		ruter[17] = new LykkeRute();
		ruter[18] = new GateRute("Gabels Gate", 180);
		ruter[19] = new GateRute("Ringgata", 200);
		
		ruter[20] = new GratisParkeringRute();
		ruter[21] = new GateRute("Bygdøy Allé", 220);
		ruter[22] = new LykkeRute();
		ruter[23] = new GateRute("Skarpsno", 220);
		ruter[24] = new GateRute("Slemdal", 240);
		ruter[25] = new TogRute("Grorud Stasjon", 200);
		ruter[26] = new GateRute("Karl Johans Gate", 260);
		ruter[27] = new GateRute("Stortorget", 260);
		ruter[28] = new TjenesteRute("Vannverket", 150);
		ruter[29] = new GateRute("Torggata", 280);
		
		ruter[30] = new GåIFengselRute();
		ruter[31] = new GateRute("Trosterudveien", 300);
		ruter[32] = new GateRute("Pilestredet", 300);
		ruter[33] = new LykkeRute();
		ruter[34] = new GateRute("Sinsen", 320);
		ruter[35] = new TogRute("Bryn Stasjon", 200);
		ruter[36] = new LykkeRute();
		ruter[37] = new GateRute("Ullevål Hageby", 350);
		ruter[38] = new SkatteRute("Luksusskatt", 100);
		ruter[39] = new GateRute("Rådhusplassen", 400);
	}
	
	public static RuteADT hentRute(int plass) {
		return null;
	}
	
	public static int hentRutenummer(RuteADT rute) {
		for (int i=0; i<ANTALL_RUTER; ++i) {
			if (ruter[i] == rute) return i;
		}
		throw new RuntimeException("Rute ikke funnet");
	}
}
