package code;

import java.nio.ByteBuffer;
import java.util.ArrayList;

public class Board {
	private TwoDMap<Chunk> chunks = new TwoDMap<Chunk>();
	private int numChunks;
	
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
	
	public void addLivingNeighbour(int x, int y) {
		int[] cPos = calculateChunkPosition(x, y);
		Chunk chunk = chunks.get(cPos[0], cPos[1]);
		if (chunk == null) {
			chunk = new Chunk(this, cPos[0], cPos[1]);
			chunks.put(cPos[0], cPos[1], chunk);
		}
		chunk.addLivingNeighbour(cPos[2], cPos[3]);
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
		if (internal_x < 0) {
			--chunk_x;
			internal_x += 25;
		}
		if (internal_y < 0) {
			--chunk_y;
			internal_y += 25;
		}
		int[] r = { chunk_x, chunk_y, internal_x, internal_y };
		return r;
	}
	
	/**
	 * Calculates a board position from a chunk position
	 * @param chunk_x
	 * @param chunk_y
	 * @param internal_x
	 * @param internal_y
	 * @return
	 */
	public static int[] calculateBoardPosition(int chunk_x, int chunk_y, int internal_x, int internal_y) {
		int cell_x = (25*chunk_x - 12) + internal_x;
		int cell_y = (25*chunk_y - 12) + internal_y;
		int[] r = { cell_x, cell_y };
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
	 * Schedules a cell to become alive or dead.
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
		chunk.setCellAlive(cPos[2], cPos[3], alive);
	}
	
	public void toggleCellAlive(int x, int y) {
		int[] cPos = calculateChunkPosition(x, y);
		Chunk chunk = chunks.get(cPos[0], cPos[1]);
		if (chunk == null) {
			chunk = new Chunk(this, cPos[0], cPos[1]);
			chunks.put(cPos[0], cPos[1], chunk);
		}
		chunk.toggleCellAlive(cPos[2], cPos[3]);
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
				chunks.remove(cx, cy);
			}
		}
		numChunks = 0;
		for (Chunk chunk : chunks) {
			++numChunks;
			chunk.advance();
		}
	}
	
	public void reset() {
		chunks = new TwoDMap<Chunk>();
	}
	
	public int countChunks() {
		return numChunks;
	}
	
	public void setRules(String rulestring) {
		System.out.println("Rulesets are not yet supported.");
	}
	
	public byte[] toInfiniteLifeFileData() {
		int numChunks = 0;
		for (Chunk chunk : chunks) {
			if (!chunk.isActive()) continue;
			++numChunks;
		}
		ByteBuffer buffer = ByteBuffer.allocate(92*numChunks);
		for (Chunk chunk : chunks) {
			if (!chunk.isActive()) continue;
			buffer.put(chunk.toBytes());
		}
		return buffer.array();
	}
	
	public ArrayList<String> toLife105FileData() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("#Life 1.05");
		for (Chunk chunk : chunks) {
			if (!chunk.isActive()) continue;
			String s = chunk.toLife105Block();
			list.add(s);
		}
		return list;
	}
	
	public void loadFromFile(String[] lines, String extension) {
		reset();
		switch (extension.toLowerCase()) {
		case "lif":
		case "life":
			// LIFE 1.05/1.06
			if (lines[0].equals("#Life 1.05"))
				loadLife105File(lines);
			else if (lines[0].equals("#Life 1.06"))
				loadLife106File(lines);
			else
				throw new InvalidLifeFileException();
			break;
		case "ilf":
			// Infinite Life
//			loadInfiniteLifeFile(lines);
			break;
		default:
			throw new InvalidLifeFileException();
		}
	}
	
	private void loadLife105File(String[] lines) {
		String rules = "23/3";
		
		// Position of current block
		int topleft_x = 0;
		int topleft_y = 0;
		
		// Lines for the current block
		ArrayList<String> blocklines = null;
		
		for (String line : lines) {
			if (line.equals("")) continue;
			if (line.startsWith("#D")) continue;
			if (line.startsWith("#R")) {
				rules = line.substring(3);
				continue;
			}
			else if (line.startsWith("#P")) {
				// Finish the previous block
				if (blocklines != null) {
					int y = 0;
					for (String blockline : blocklines) {
						for (int i=0; i<blockline.length(); ++i) {
							setCellAlive(i+topleft_x, y+topleft_y, blockline.charAt(i) == '*');
						}
						++y;
					}
				}
				
				// Start the next block
				String[] splitline = line.split(" ");
				topleft_x = Integer.valueOf(splitline[1]);
				topleft_y = Integer.valueOf(splitline[2]);
				blocklines = new ArrayList<String>();
			}
			else if (line.startsWith("#")) continue;
			else {
				blocklines.add(line);
			}
		}
		
		// Finish the final block
		if (blocklines != null) {
			int y = 0;
			for (String blockline : blocklines) {
				for (int i=0; i<blockline.length(); ++i) {
					setCellAlive(i+topleft_x, y+topleft_y, blockline.charAt(i) == '*');
				}
				++y;
			}
		}
		
		setRules(rules);
	}
	
	private void loadLife106File(String[] lines) {
		for (String line : lines) {
			if (line.startsWith("#")) continue;
			if (line.equals("")) continue;
			String[] split = line.split(" ");
			setCellAlive(Integer.valueOf(split[0]), Integer.valueOf(split[1]), true);
		}
	}
	
	public void loadInfiniteLifeFile(byte[] data) {
		ByteBuffer buffer = ByteBuffer.wrap(data);
		while (buffer.remaining() > 0) {
			byte[] chunkdata = new byte[92];
			buffer.get(chunkdata, 0, 92);
			Chunk chunk = Chunk.fromBytes(chunkdata, this);
			chunks.put(chunk.getX(), chunk.getY(), chunk);
		}
	}
}
