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

		Window window = new Window(board);
		window.repaint();

		while (true) {
			try {
				Thread.sleep(100);
			} catch (Exception e) {
			}
			board.advance();
			window.repaint();
		}
	}
}
