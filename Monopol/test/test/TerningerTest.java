package test;

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import main.Terninger;

public class TerningerTest {
	
	Terninger terninger;
	
	@Before
	public final void setup() {
		terninger = new Terninger();
	}
	
	@Test
	public final void hundreKastMedEn6SidetTerning() {
		terninger.leggTilTerning();
		for (int i=0; i<100; ++i) {
			int kast = terninger.kast();
			assertTrue(kast >= 1 && kast <= 6);
		}
	}
	
	@Test
	public final void hundreKastMedEn20SidetTerning() {
		terninger.leggTilTerning(20);
		for (int i=0; i<100; ++i) {
			int kast = terninger.kast();
			assertTrue(kast >= 1 && kast <= 20);
		}
	}
	
	@Test
	public final void hundreKastMedTre6SidedeTerninger() {
		terninger.leggTilTerninger(3);
		for (int i=0; i<100; ++i) {
			int kast = terninger.kast();
			assertTrue(kast >= 3 && kast <= 18);
		}
	}
	
	@Test
	public final void hundreKastMedTre20SidedeTerninger() {
		terninger.leggTilTerninger(3, 20);
		for (int i=0; i<100; ++i) {
			int kast = terninger.kast();
			assertTrue(kast >= 3 && kast <= 60);
		}
	}
	
}
