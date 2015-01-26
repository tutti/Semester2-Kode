package no.hib.dat102;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class KøTest {
	
	public KøADT<Integer> kø;
	
	@Before
	public final void setup() {
		kø = new SirkulærKø<Integer>(5);
	}
	
	@Test
	public final void testNyKøErTom() {
		assertTrue(kø.erTom());
	}
	
	@Test
	public final void testLeggTilOgFjernOgKøErFortsattTom() {
		kø.innKø(1);
		kø.innKø(3);
		try {
			kø.utKø();
			kø.utKø();
		} catch (EmptyCollectionException e) {
			fail("Uventet feil: " + e.getMessage());
		}
		assertTrue(kø.erTom());
	}
	
	@Test(expected = EmptyCollectionException.class)
	public final void testShiftFraTomKøKasterFeil() throws EmptyCollectionException {
		kø.innKø(5);
		kø.utKø();
		kø.utKø();
	}
	
	@Test(expected = EmptyCollectionException.class)
	public final void testPeekPåTomKøKasterFeil() throws EmptyCollectionException {
		kø.front();
	}
	
	@Test
	public final void testTrePushOgPopIRiktigRekkefølge() {
		kø.innKø(3);
		kø.innKø(5);
		kø.innKø(8);
		try {
			assertEquals(kø.utKø(), new Integer(3));
			assertEquals(kø.utKø(), new Integer(5));
			assertEquals(kø.utKø(), new Integer(8));
			assertTrue(kø.erTom());
		} catch (EmptyCollectionException e) {
			fail("Uventet feil: " + e.getMessage());
		}
	}
	
	@Test
	public final void testPeekTømmerIkkeKø() {
		kø.innKø(3);
		try {
			assertEquals(kø.front(), new Integer(3));
			assertEquals(kø.front(), new Integer(3));
			assertEquals(kø.front(), new Integer(3));
			assertFalse(kø.erTom());
			kø.utKø();
			assertTrue(kø.erTom());
		} catch (EmptyCollectionException e) {
			fail("Uventet feil: " + e.getMessage());
		}
	}
	
	@Test
	public final void testGetAntall() {
		assertEquals(kø.getAntall(), 0);
		kø.innKø(3);
		kø.innKø(9);
		kø.innKø(14);
		assertEquals(kø.getAntall(), 3);
		kø.innKø(22);
		assertEquals(kø.getAntall(), 4);
	}
	
	@Test
	public final void testKøenGårRundt() {
		try {
			for (int i=0; i<5; ++i) {
				for (int j=0; j<3; ++j) {
					kø.innKø(3*i+j);
				}
				for (int j=0; j<3; ++j) {
					assertEquals(kø.utKø(), new Integer(3*i+j));
				}
			}
		} catch (EmptyCollectionException e) {
			fail("Uventet feil: " + e.getMessage());
		}
	}
}
