package test;

import static org.junit.Assert.*;
import org.junit.*;

import code.Chunk;
import code.Board;

public class TestChunk {
	
	private boolean[][][] glider = {
			{
				{false,false,false,true ,false},
				{false,false,true ,false,false},
				{false,false,true ,true ,true },
				{false,false,false,false,false},
				{false,false,false,false,false},
			},
			{
				{false,false,false,false,false},
				{false,false,true ,false,true },
				{false,false,true ,true ,false},
				{false,false,false,true ,false},
				{false,false,false,false,false},
			},
			{
				{false,false,false,false,false},
				{false,false,true ,false,false},
				{false,false,true ,false,true },
				{false,false,true ,true ,false},
				{false,false,false,false,false},
			},
			{
				{false,false,false,false,false},
				{false,false,false,true ,false},
				{false,true ,true ,false,false},
				{false,false,true ,true ,false},
				{false,false,false,false,false},
			},
			{
				{false,false,false,false,false},
				{false,false,true ,false,false},
				{false,true ,false,false,false},
				{false,true ,true ,true ,false},
				{false,false,false,false,false},
			}
	};
	
	private Board board = new Board();
	private Chunk ch1;
	
	@Before
	public final void setup() {
		ch1 = new Chunk(board, 0, 0);
	}
	
	@Test
	public final void testLivingNeighbours() {
		ch1.setCellAlive(1, 1, true);
		for (int i=3; i<=11; ++i) {
			ch1.setCellAlive(i, 1, true);
			if (i >= 6) ch1.setCellAlive(i, 2, true);
			if (i >= 9) ch1.setCellAlive(i, 0, true);
		}
		ch1.advance();
		
		assertEquals(ch1.livingNeighbours(1, 1), 0);
		for (int i=3; i<= 10; ++i) {
			assertEquals(ch1.livingNeighbours(i, 1), i-2);
		}
	}
	
	@Test
	public final void testGlider() {
		for (int x=0; x<5; ++x) {
			for (int y=0; y<5; ++y) {
				ch1.setCellAlive(x, y, glider[0][x][y]);
			}
		}
		ch1.advance();
		for (int i=0; i<5; ++i) {
			System.out.println();
			for (int x=0; x<5; ++x) {
				for (int y=0; y<5; ++y) {
					assertEquals(ch1.isCellAlive(x, y), glider[i][x][y]);
				}
			}
			ch1.calculateNextGeneration();
			ch1.advance();
		}
	}
	
}
