package code;

import java.nio.ByteBuffer;

public class Chunk {
	private Cell[][] cells;
	private Board board;
	private int pos_x;
	private int pos_y;

	public Chunk(Board board, int pos_x, int pos_y) {
		cells = new Cell[25][25];
		for (int x = 0; x < 25; ++x) {
			for (int y = 0; y < 25; ++y) {
				cells[x][y] = new Cell();
			}
		}
		this.board = board;
		this.pos_x = pos_x;
		this.pos_y = pos_y;
	}

	public int getX() {
		return pos_x;
	}

	public int getY() {
		return pos_y;
	}

	/**
	 * An active chunk is one that has living cells. Inactive chunks are not
	 * meant to be kept in memory.
	 * 
	 * @return
	 */
	public boolean isActive() {
		for (int x = 0; x < 25; ++x) {
			for (int y = 0; y < 25; ++y) {
				if (cells[x][y].isActive())
					return true;
			}
		}
		return false;
	}

	/**
	 * Checks if a given cell is alive.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isCellAlive(int x, int y) {
		return cells[x][y].isAlive();
	}

	/**
	 * Sets a cell to become alive or dead in the next generation.
	 * 
	 * @param x
	 * @param y
	 * @param alive
	 */
	public void setCellAlive(int x, int y, boolean alive) {
		cells[x][y].setAlive(alive);
	}

	public void toggleCellAlive(int x, int y) {
		cells[x][y].toggleAlive();
	}

	public void addLivingNeighbour(int x, int y) {
		cells[x][y].addLivingNeighbour();
	}

	/**
	 * Calculates which cells should be alive or dead in the next generation.
	 * Does not advance any of the cells.
	 */
	public void calculateNextGeneration() {
		for (int x = 0; x < 25; ++x) {
			for (int y = 0; y < 25; ++y) {
				if (!cells[x][y].isAlive())
					continue;
				for (int a = x - 1; a <= x + 1; ++a) {
					for (int b = y - 1; b <= y + 1; ++b) {
						if (a == x && b == y)
							continue;
						if (a < 0 || a >= 25 || b < 0 || b >= 25) {
							int[] boardPos = Board.calculateBoardPosition(
									pos_x, pos_y, a, b);
							board.addLivingNeighbour(boardPos[0], boardPos[1]);
						} else {
							cells[a][b].addLivingNeighbour();
						}
					}
				}
			}
		}
	}

	/**
	 * Advances all cells in the chunk to the next generation.
	 */
	public void advance() {
		for (int x = 0; x < 25; ++x) {
			for (int y = 0; y < 25; ++y) {
				cells[x][y].advance();
			}
		}
	}

//	public String serialise() {
//
//		StringBuilder output = new StringBuilder();
//		
//		// Encode the chunk's position
//		int c0 = pos_x >> 16;
//		int c1 = pos_x - (c0 << 16);
//		int c2 = pos_y >> 16;
//		int c3 = pos_y - (c2 << 16);
//		output.append((char)c0);
//		output.append((char)c1);
//		output.append((char)c2);
//		output.append((char)c3);
//		
//		// Encode all the cells, 16 at a time
//		int current_num = 0;
//		for (int i=0; i<625; ++i) {
//			int x = i%25;
//			int y = i/25;
//			int pos = i%16;
//			if (pos == 0 && i>0) {
//				output.append((char)current_num);
//				current_num = 0;
//			}
//			if (cells[x][y].isAlive())
//				current_num = current_num | (int)Math.pow(2, pos);
//		}
//		
//		output.append((char)current_num);
//
//		return output.toString();
//	}
//
//	public static Chunk deserialise(String data, Board board) {
//		
//		// Decode the chunk's position
//		int pos_x = (data.charAt(0) << 16) + data.charAt(1);
//		int pos_y = (data.charAt(2) << 16) + data.charAt(3);
//		
//		Chunk chunk = new Chunk(board, pos_x, pos_y);
//		
//		// Decode all the cells
//		int pos = 0;
//		for (int i=4; i<44; ++i) {
//			char next = data.charAt(i);
//			for (int j=0; j<16; ++j) {
//				int x = pos%25;
//				int y = pos/25;
//				if ((next & (int)Math.pow(2, j)) == Math.pow(2, j)) {
//					chunk.setCellAlive(x, y, true);
//				}
//				++pos;
//			}
//		}
//		
//		return chunk;
//	}
	
	public byte[] toBytes() {
		ByteBuffer buffer = ByteBuffer.allocate(92);
		
		buffer.putInt(pos_x);
		buffer.putInt(pos_y);
		
		int current_num = 0;
		for (int i=0; i<625; ++i) {
			int x = i%25;
			int y = i/25;
			int pos = i%30;
			if (pos == 0 && i>0) {
				buffer.putInt(current_num);
				current_num = 0;
			}
			if (cells[x][y].isAlive())
				current_num = current_num | (int)Math.pow(2, pos);
		}
		
		buffer.putInt(current_num);
		
		return buffer.array();
	}
	
	public static Chunk fromBytes(byte[] bytes, Board board) {
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		
		int pos_x = buffer.getInt();
		int pos_y = buffer.getInt();
		
		Chunk chunk = new Chunk(board, pos_x, pos_y);
		
		int pos = 0;
		for (int i=0; i<21; ++i) {
			int next = buffer.getInt();
			for (int j = 0; j < 30; ++j) {
				int x = pos % 25;
				int y = pos / 25;
				if ((next & (int) Math.pow(2, j)) == (int)Math.pow(2, j)) {
					chunk.setCellAlive(x, y, true);
				}
				++pos;
			}
		}
		
		return chunk;
	}
	
	public String toLife105Block() {
		StringBuilder output = new StringBuilder();
		
		String nl = System.getProperty("line.separator");
		
		int[] position = Board.calculateBoardPosition(pos_x, pos_y, 0, 0);
		
		output.append("#P "+position[0]+" "+position[1]);
		output.append(nl);
		for (int x=0; x<25; ++x) {
			for (int y=0; y<25; ++y) {
				if (cells[y][x].isAlive())
					output.append("*");
				else
					output.append(".");
			}
			output.append(nl);
		}
		
		return output.toString();
	}

	public void print() {
		for (int x = 0; x < 25; ++x) {
			for (int y = 0; y < 25; ++y) {
				if (isCellAlive(x, y)) {
					System.out.print("X");
				} else {
					System.out.print("-");
				}
			}
			System.out.println();
		}
	}
}
