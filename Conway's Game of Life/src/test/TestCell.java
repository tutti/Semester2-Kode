package test;

import static org.junit.Assert.*;
import org.junit.*;

import code.Cell;

public class TestCell {
	private Cell c1;
	private Cell c2;
	
	@Before
	public final void setup() {
		c1 = new Cell();
		c2 = new Cell();
	}
	
	@Test
	public final void testStartsDead() {
		assertFalse(c1.isAlive());
	}
	
	@Test
	public final void testCellBecomesAlive() {
		c1.setAlive(true);
		assertFalse(c1.isAlive());
		c1.advance();
		assertTrue(c1.isAlive());
		c1.advance();
		assertTrue(c1.isAlive());
		c1.setAlive(false);
		assertTrue(c1.isAlive());
		c1.advance();
		assertFalse(c1.isAlive());
	}
	
//	@Test
//	public final void testOneNeighbour() {
//		c2.setAlive(true);
//		c1.advance();
//		c2.advance();
//		
//		c1.setAliveFromNeighbours(1);
//		c2.setAliveFromNeighbours(1);
//		c1.advance();
//		c2.advance();
//		
//		assertFalse(c1.isAlive());
//		assertFalse(c2.isAlive());
//	}
	
//	@Test
//	public final void testTwoNeighbours() {
//		c2.setAlive(true);
//		c1.advance();
//		c2.advance();
//		
//		c1.setAliveFromNeighbours(2);
//		c2.setAliveFromNeighbours(2);
//		c1.advance();
//		c2.advance();
//		
//		assertFalse(c1.isAlive());
//		assertTrue(c2.isAlive());
//	}
	
//	@Test
//	public final void testThreeNeighbours() {
//		c2.setAlive(true);
//		c1.advance();
//		c2.advance();
//		
//		c1.setAliveFromNeighbours(3);
//		c2.setAliveFromNeighbours(3);
//		c1.advance();
//		c2.advance();
//		
//		assertTrue(c1.isAlive());
//		assertTrue(c2.isAlive());
//	}
	
//	@Test
//	public final void testFourNeighbours() {
//		c2.setAlive(true);
//		c1.advance();
//		c2.advance();
//		
//		c1.setAliveFromNeighbours(4);
//		c2.setAliveFromNeighbours(4);
//		c1.advance();
//		c2.advance();
//		
//		assertFalse(c1.isAlive());
//		assertFalse(c2.isAlive());
//	}
	
	@Test
	public final void testCellActivity() {
		assertFalse(c1.isActive());
		c1.setAlive(true);
		assertTrue(c1.isActive());
		c1.advance();
		assertTrue(c1.isActive());
		c1.setAlive(false);
		assertTrue(c1.isActive());
		c1.advance();
		assertFalse(c1.isActive());
	}
}
