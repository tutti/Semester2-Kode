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
	private static Farge R�D = new Farge(Color.RED);
	private static Farge GUL = new Farge(Color.YELLOW);
	private static Farge GR�NN = new Farge(Color.GREEN);
	private static Farge BL� = new Farge(Color.BLUE);
	private static Farge TOG = new Farge(Color.GRAY);
	private static Farge TJENESTER = new Farge(Color.GRAY);
	// For � forenkle koden anses settene av tog og tjenester som "fargegrupper".
	
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
		ruter[9] = new GateRute("�vre Slottsgate", 120);
		
		ruter[1].settFarge(BRUN);
		ruter[3].settFarge(BRUN);
		ruter[6].settFarge(CYAN);
		ruter[8].settFarge(CYAN);
		ruter[9].settFarge(CYAN);
		
		ruter[1].settLeie(2, 10, 30, 90, 160, 250);
		ruter[3].settLeie(4, 20, 60, 180, 320, 450);
		ruter[6].settLeie(6, 30, 90, 270, 400, 550);
		ruter[8].settLeie(6, 30, 90, 270, 400, 550);
		ruter[9].settLeie(8, 40, 100, 300, 450, 600);
		
		ruter[10] = new FengselRute();
		ruter[11] = new GateRute("Nedre Slottsgate", 140);
		ruter[12] = new TjenesteRute("Oslo Lysverker", 150);
		ruter[13] = new GateRute("Trondheimsveien", 140);
		ruter[14] = new GateRute("Nobels Gate", 160);
		ruter[15] = new TogRute("Sk�yen Stasjon", 200);
		ruter[16] = new GateRute("Grensen", 180);
		ruter[17] = new LykkeRute();
		ruter[18] = new GateRute("Gabels Gate", 180);
		ruter[19] = new GateRute("Ringgata", 200);
		
		ruter[11].settFarge(ROSA);
		ruter[13].settFarge(ROSA);
		ruter[14].settFarge(ROSA);
		ruter[16].settFarge(ORANSJE);
		ruter[18].settFarge(ORANSJE);
		ruter[19].settFarge(ORANSJE);

		ruter[11].settLeie(10, 50, 150, 450, 625, 750);
		ruter[13].settLeie(10, 50, 150, 450, 625, 750);
		ruter[14].settLeie(12, 60, 180, 500, 700, 900);
		ruter[16].settLeie(14, 70, 200, 550, 750, 950);
		ruter[18].settLeie(14, 70, 200, 550, 750, 950);
		ruter[19].settLeie(16, 80, 220, 600, 800, 1000);
		
		ruter[20] = new GratisParkeringRute();
		ruter[21] = new GateRute("Bygd�y All�", 220);
		ruter[22] = new LykkeRute();
		ruter[23] = new GateRute("Skarpsno", 220);
		ruter[24] = new GateRute("Slemdal", 240);
		ruter[25] = new TogRute("Grorud Stasjon", 200);
		ruter[26] = new GateRute("Karl Johans Gate", 260);
		ruter[27] = new GateRute("Stortorget", 260);
		ruter[28] = new TjenesteRute("Vannverket", 150);
		ruter[29] = new GateRute("Torggata", 280);

		ruter[21].settFarge(R�D);
		ruter[23].settFarge(R�D);
		ruter[24].settFarge(R�D);
		ruter[26].settFarge(GUL);
		ruter[27].settFarge(GUL);
		ruter[29].settFarge(GUL);
		
		ruter[21].settLeie(18, 90, 250, 700, 875, 1050);
		ruter[23].settLeie(18, 90, 250, 700, 875, 1050);
		ruter[24].settLeie(20, 100, 300, 750, 925, 1100);
		ruter[26].settLeie(22, 110, 330, 800, 975, 1150);
		ruter[27].settLeie(22, 110, 330, 800, 975, 1150);
		ruter[29].settLeie(24, 120, 360, 850, 1025, 1200);
		
		ruter[30] = new G�IFengselRute();
		ruter[31] = new GateRute("Trosterudveien", 300);
		ruter[32] = new GateRute("Pilestredet", 300);
		ruter[33] = new LykkeRute();
		ruter[34] = new GateRute("Sinsen", 320);
		ruter[35] = new TogRute("Bryn Stasjon", 200);
		ruter[36] = new LykkeRute();
		ruter[37] = new GateRute("Ullev�l Hageby", 350);
		ruter[38] = new SkatteRute("Luksusskatt", 100);
		ruter[39] = new GateRute("R�dhusplassen", 400);

		ruter[31].settFarge(GR�NN);
		ruter[32].settFarge(GR�NN);
		ruter[34].settFarge(GR�NN);
		ruter[37].settFarge(BL�);
		ruter[39].settFarge(BL�);
		
		ruter[31].settLeie(26, 130, 390, 900, 1100, 1275);
		ruter[32].settLeie(26, 130, 390, 900, 1100, 1275);
		ruter[34].settLeie(28, 150, 450, 1000, 1200, 1400);
		ruter[37].settLeie(35, 175, 500, 1100, 1300, 1500);
		ruter[39].settLeie(50, 200, 600, 1400, 1700, 2000);
		
		TOG.leggTilRute((EiendomRute)ruter[5]);
		TOG.leggTilRute((EiendomRute)ruter[15]);
		TOG.leggTilRute((EiendomRute)ruter[25]);
		TOG.leggTilRute((EiendomRute)ruter[35]);

		TJENESTER.leggTilRute((EiendomRute)ruter[12]);
		TJENESTER.leggTilRute((EiendomRute)ruter[28]);
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
