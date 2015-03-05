package code;

public class Cell {
	protected boolean aliveNow;
	protected boolean aliveNext;
	
	public Cell() {
		aliveNow = false;
		aliveNext = false;
	}
	
	/**
	 * Checks if the cell is currently alive
	 * @return
	 */
	public boolean isAlive() {
		return aliveNow;
	}
	
	/**
	 * Schedules the cell to become alive or dead in the next generation
	 * @param alive
	 */
	public void setAlive(boolean alive) {
		aliveNext = alive;
	}
	
	/**
	 * Schedules the cell to become alive or dead in the next generation,
	 * based on the number of neighbours it has
	 * @param neighbours
	 */
	public void setAliveFromNeighbours(int livingNeighbours) {
		if (aliveNow) {
			if (livingNeighbours < 2 || livingNeighbours > 3) aliveNext = false;
			else aliveNext = true;
		} else {
			if (livingNeighbours == 3) aliveNext = true;
			else aliveNext = false;
		}
	}
	
	/**
	 * Advances the cell to the next generation
	 */
	public void advance() {
		aliveNow = aliveNext;
	}
	
	/**
	 * Checks if a cell is active.
	 * An active cell is a cell that is either currently alive or will be
	 * alive in the next generation.
	 * @return
	 */
	public boolean isActive() {
		return aliveNow || aliveNext;
	}
}
