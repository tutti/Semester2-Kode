package code;

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
