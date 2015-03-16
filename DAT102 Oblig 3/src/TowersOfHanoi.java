public class TowersOfHanoi {
	private int totalDisks;
	private long totalMoves;

	public TowersOfHanoi(int disks) {
		totalDisks = disks;
		totalMoves = 0;
	}

	public void solve() {
		moveTower(totalDisks, 1, 3, 2);
		System.out.println("Total moves: "+totalMoves);
	}

	private void moveTower(int numDisks, int start, int end, int temp) {
		if (numDisks == 1)
			moveOneDisk(start, end);
		else {
			moveTower(numDisks-1, start, temp, end);
			moveOneDisk(start, end);
			moveTower(numDisks-1, temp, end, start);
		}
	}
	
	private void moveOneDisk(int start, int end) {
//		System.out.println("Move one disk from "+start+" to "+end);
		++totalMoves;
	}
	
	public static void main(String[] args) {
		TowersOfHanoi towers = new TowersOfHanoi(32);
		long time1 = System.nanoTime();
		towers.solve();
		long time2 = System.nanoTime();
		long time = (time2-time1) / 1000000;
		System.out.println("Time: "+time+"ms");
	}
}
