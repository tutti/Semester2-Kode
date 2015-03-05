package code;

public class Board {
	private TwoDMap<Chunk> chunks = new TwoDMap<Chunk>();
	
	/**
	 * Gets a chunk from the board. Note that you need the CHUNK'S position,
	 * NOT a cell position.
	 * @param chunk_x
	 * @param chunk_y
	 * @return The chunk
	 */
	public Chunk getChunk(int chunk_x, int chunk_y) {
		Chunk chunk = chunks.get(chunk_x, chunk_y);
		if (chunk == null) {
			chunk = new Chunk(this, chunk_x, chunk_y);
			chunks.put(chunk_x, chunk_y, chunk);
		}
		return chunk;
	}

	/**
	 * Calculates a chunk position from a board position
	 * 
	 * @param cell_x
	 *            x position on board
	 * @param cell_y
	 *            y position on board
	 * @return An array containing four values: (x,y) of chunk on board, and
	 *         (x,y) of cell within chunk
	 */
	public static int[] calculateChunkPosition(int cell_x, int cell_y) {
		int chunk_x = (cell_x + 12) / 25;
		int chunk_y = (cell_y + 12) / 25;
		int internal_x = (cell_x + 12) % 25;
		int internal_y = (cell_y + 12) % 25;
		int[] r = { chunk_x, chunk_y, internal_x, internal_y };
		return r;
	}

	/**
	 * Checks if a cell at any position is currently alive
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isCellAlive(int x, int y) {
		int[] cPos = calculateChunkPosition(x, y);
		Chunk chunk = chunks.get(cPos[0], cPos[1]);
		if (chunk == null) {
			return false;
		}
		return chunk.isCellAlive(cPos[2], cPos[3]);
	}

	/**
	 * Schedules a cell to become alive or dead in the next generation.
	 * 
	 * @param x
	 * @param y
	 * @param alive
	 */
	public void setCellAlive(int x, int y, boolean alive) {
		int[] cPos = calculateChunkPosition(x, y);
		Chunk chunk = chunks.get(cPos[0], cPos[1]);
		if (chunk == null) {
			chunk = new Chunk(this, cPos[0], cPos[1]);
			chunks.put(cPos[0], cPos[1], chunk);
		}
		chunk.setCellAlive(x, y, alive);
	}

	/**
	 * Advances the board one generation. Handles both calculating what the next
	 * generation is, and advancing to it.
	 */
	public void advance() {
		for (Chunk chunk : chunks) {
			chunk.calculateNextGeneration();
			int cx = chunk.getX();
			int cy = chunk.getY();
			if (!chunk.isActive()) {
				removeChunk(cx, cy);
			}
		}
		for (Chunk chunk : chunks) {
			chunk.advance();
		}
	}

	/**
	 * Removes a chunk from the board. Use only for chunks with no active cells.
	 */
	private void removeChunk(int x, int y) {
		chunks.remove(x, y);
	}
}
