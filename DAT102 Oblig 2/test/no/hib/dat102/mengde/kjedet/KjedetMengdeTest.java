package no.hib.dat102.mengde.kjedet;

import static org.junit.Assert.assertTrue;
import no.hib.dat102.mengde.adt.MengdeADT;

import org.junit.Before;
import org.junit.Test;

public class KjedetMengdeTest {
	
	private MengdeADT<String> m1;
	private MengdeADT<String> m2;
	private MengdeADT<String> m3;
	
	private final String S1 = "en";
	private final String S2 = "to";
	private final String S3 = "tre";
	
	@Before
	public final void setup() {
		m1 = new KjedetMengde<String>();
		m2 = new KjedetMengde<String>();
		m3 = new KjedetMengde<String>();
		
		m1.leggTil(S1);
		m1.leggTil(S2);
		m2.leggTil(S2);
		m2.leggTil(S3);
	}
	
	@Test
	public final void testUnion() {
		m3.leggTil(S1);
		m3.leggTil(S2);
		m3.leggTil(S3);
		
		assertTrue(m3.erLik(m1.union(m2)));
	}
	
	@Test
	public final void testSnitt() {
		m3.leggTil(S2);
		assertTrue(m3.erLik(m1.snitt(m2)));
	}
	
	@Test
	public final void testDifferans() {
		m3.leggTil(S1);
		assertTrue(m3.erLik(m1.differans(m2)));
	}
}
