package gui;

import code.Board;

public class Main {
	public static void main(String[] args) {

		Board board = new Board();

		board.setCellAlive(0, 0, true);
		board.setCellAlive(-1, 1, true);
		board.setCellAlive(-1, 2, true);
		board.setCellAlive(0, 2, true);
		board.setCellAlive(1, 2, true);

		// board.setCellAlive(-25, 25, true);
		// board.setCellAlive(-26, 26, true);
		// board.setCellAlive(-26, 27, true);
		// board.setCellAlive(-25, 27, true);
		// board.setCellAlive(-24, 27, true);
		// board.setCellAlive(-37, 13, true);
		// board.setCellAlive(-37, 14, true);
		// board.setCellAlive(-36, 13, true);
		// board.setCellAlive(-36, 14, true);
		// board.setCellAlive(11, 13, true);
		// board.setCellAlive(11, 14, true);
		// board.setCellAlive(12, 13, true);
		// board.setCellAlive(12, 14, true);

		board.setCellAlive(-37, -37, true);
		board.setCellAlive(-37, -36, true);
		board.setCellAlive(-36, -37, true);
		board.setCellAlive(-36, -36, true);

		Window window = new Window(board);
		window.repaint();
		
		int i=0;

		while (true) {
			try {
				Thread.sleep(100);
			} catch (Exception e) {
			}
			board.setCellAlive(i-37, -37, false);
			board.setCellAlive(i-37, -36, false);
			board.setCellAlive(i-35, -37, true);
			board.setCellAlive(i-35, -36, true);
			board.advance();
			window.repaint();
			++i;
		}
	}
}
