package code;

public class Cell {
	protected boolean alive;
	protected int livingNeighbours;
	
	public Cell() {
		alive = false;
		livingNeighbours = 0;
	}
	
	/**
	 * Checks if the cell is currently alive
	 * @return
	 */
	public boolean isAlive() {
		return alive;
	}
	
	/**
	 * Sets the cell to be alive or dead
	 * @param alive
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
//	/**
//	 * Schedules the cell to become alive or dead in the next generation,
//	 * based on the number of neighbours it has
//	 * @param neighbours
//	 */
//	public void setAliveFromNeighbours(int livingNeighbours) {
//		if (aliveNow) {
//			if (livingNeighbours < 2 || livingNeighbours > 3) aliveNext = false;
//			else aliveNext = true;
//		} else {
//			if (livingNeighbours == 3) aliveNext = true;
//			else aliveNext = false;
//		}
//	}
	
	public void addLivingNeighbour() {
		++livingNeighbours;
	}
	
	/**
	 * Advances the cell to the next generation
	 */
	public void advance() {
		if (alive) {
			if (livingNeighbours < 2 || livingNeighbours > 3)
				alive = false;
			else
				alive = true;
		} else {
			if (livingNeighbours == 3)
				alive = true;
			else
				alive = false;
		}
		livingNeighbours = 0;
	}
	
	/**
	 * Checks if a cell is active.
	 * An active cell is a cell that is either currently alive or will be
	 * alive in the next generation.
	 * @return
	 */
	public boolean isActive() {
		return alive || (livingNeighbours > 0);
	}
}
