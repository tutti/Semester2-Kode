package no.hib.dat102;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

public class KjedetStabelTest {

	private StabelADT<Integer> stabel;
	
	private Integer e1 = 3;
	private Integer e2 = 5;
	private Integer e3 = 2;
	
	@Before
    public final void setup()  {
        stabel = new KjedetStabel<Integer>();
    }
	
	@Test
	public final void testPushOgPop() {
		stabel.push(e1);
		stabel.push(e2);
		stabel.push(e3);
		try {
			assertEquals(stabel.pop(), e3);
			assertEquals(stabel.pop(), e2);
			assertEquals(stabel.pop(), e1);
		} catch (EmptyCollectionException e) {
			fail("Ukjent feil: " + e.getMessage());
		}
	}
	
	@Test
	public final void testNyStabelErTom() {
		assertTrue(stabel.erTom());
	}
	
	@Test
	public final void testPushOgPopMedDuplikater() {
		stabel.push(e1);
		stabel.push(e2);
		stabel.push(e1);
		stabel.push(e3);
		try {
			assertEquals(stabel.pop(), e3);
			assertEquals(stabel.pop(), e1);
			assertEquals(stabel.pop(), e2);
			assertEquals(stabel.pop(), e1);
		} catch (EmptyCollectionException e) {
			fail("Ukjent feil: " + e.getMessage());
		}
	}
	
	@Test
	public final void testPushPopPushPop() {
		try {
			stabel.push(e2);
			assertEquals(stabel.pop(), e2);
			stabel.push(e3);
			assertEquals(stabel.pop(), e3);
			assertTrue(stabel.erTom());
		} catch (EmptyCollectionException e) {
			fail("Ukjent feil: " + e.getMessage());
		}
	}
	
	@Test
	public final void testStabelMedElementerErIkkeTom() {
		stabel.push(e1);
		assertFalse(stabel.erTom());
	}
	
	@Test
	public final void testPeekFjernerIkkeElement() {
		try {
			stabel.push(e3);
			assertEquals(stabel.peek(), e3);
			assertEquals(stabel.peek(), e3);
			assertFalse(stabel.erTom());
			assertEquals(stabel.pop(), e3);
			assertTrue(stabel.erTom());
		} catch (EmptyCollectionException e) {
			fail("Ukjent feil: " + e.getMessage());
		}
	}
	
	@Test(expected = EmptyCollectionException.class)
	public final void testPopTomStabelGirUnntak() throws EmptyCollectionException {
		stabel.pop();
	}
	
	@Test(expected = EmptyCollectionException.class)
	public final void testPeekTomStabelGirUnntak() throws EmptyCollectionException {
		stabel.peek();
	}
	
	@Test
	public final void testAntall() {
		try {
			assertEquals(stabel.antall(), 0);
			stabel.push(e1);
			assertEquals(stabel.antall(), 1);
			stabel.push(e2);
			assertEquals(stabel.antall(), 2);
			stabel.peek();
			assertEquals(stabel.antall(), 2);
			stabel.pop();
			assertEquals(stabel.antall(), 1);
		} catch (EmptyCollectionException e) {
			fail("Ukjent feil: " + e.getMessage());
		}
	}
}
