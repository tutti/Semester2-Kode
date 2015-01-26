package no.hib.dat102;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class K�Test {
	
	public K�ADT<Integer> k�;
	
	@Before
	public final void setup() {
		k� = new Sirkul�rK�<Integer>(5);
	}
	
	@Test
	public final void testNyK�ErTom() {
		assertTrue(k�.erTom());
	}
	
	@Test
	public final void testLeggTilOgFjernOgK�ErFortsattTom() {
		k�.innK�(1);
		k�.innK�(3);
		try {
			k�.utK�();
			k�.utK�();
		} catch (EmptyCollectionException e) {
			fail("Uventet feil: " + e.getMessage());
		}
		assertTrue(k�.erTom());
	}
	
	@Test(expected = EmptyCollectionException.class)
	public final void testShiftFraTomK�KasterFeil() throws EmptyCollectionException {
		k�.innK�(5);
		k�.utK�();
		k�.utK�();
	}
	
	@Test(expected = EmptyCollectionException.class)
	public final void testPeekP�TomK�KasterFeil() throws EmptyCollectionException {
		k�.front();
	}
	
	@Test
	public final void testTrePushOgPopIRiktigRekkef�lge() {
		k�.innK�(3);
		k�.innK�(5);
		k�.innK�(8);
		try {
			assertEquals(k�.utK�(), new Integer(3));
			assertEquals(k�.utK�(), new Integer(5));
			assertEquals(k�.utK�(), new Integer(8));
			assertTrue(k�.erTom());
		} catch (EmptyCollectionException e) {
			fail("Uventet feil: " + e.getMessage());
		}
	}
	
	@Test
	public final void testPeekT�mmerIkkeK�() {
		k�.innK�(3);
		try {
			assertEquals(k�.front(), new Integer(3));
			assertEquals(k�.front(), new Integer(3));
			assertEquals(k�.front(), new Integer(3));
			assertFalse(k�.erTom());
			k�.utK�();
			assertTrue(k�.erTom());
		} catch (EmptyCollectionException e) {
			fail("Uventet feil: " + e.getMessage());
		}
	}
	
	@Test
	public final void testGetAntall() {
		assertEquals(k�.getAntall(), 0);
		k�.innK�(3);
		k�.innK�(9);
		k�.innK�(14);
		assertEquals(k�.getAntall(), 3);
		k�.innK�(22);
		assertEquals(k�.getAntall(), 4);
	}
	
	@Test
	public final void testK�enG�rRundt() {
		try {
			for (int i=0; i<5; ++i) {
				for (int j=0; j<3; ++j) {
					k�.innK�(3*i+j);
				}
				for (int j=0; j<3; ++j) {
					assertEquals(k�.utK�(), new Integer(3*i+j));
				}
			}
		} catch (EmptyCollectionException e) {
			fail("Uventet feil: " + e.getMessage());
		}
	}
}
