package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import code.Board;
import code.Chunk;

public class DisplayChunk extends JButton {
	private static final long serialVersionUID = 815555903536680994L;
	
	private MouseAdapter adapter;
	private Board board;
	private int x;
	private int y;

	public DisplayChunk(Board board, int xval, int yval) {
		setBorderPainted(false);
		this.board = board;
		x = xval;
		y = yval;
		
		adapter = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int click_x = e.getX();
				int click_y = e.getY();
				if (click_x%6==0) return;
				if (click_y%6==0) return;
				int cell_x = click_x/6;
				int cell_y = click_y/6;
				Chunk chunk = board.getChunk(x, y);
				chunk.toggleCellAlive(cell_x, cell_y);
			}
		};

		addMouseListener(adapter);
	}
	
	public void moveView(int dx, int dy) {
		x += dx;
		y += dy;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Chunk chunk = board.getChunk(x, y);
		if (!chunk.isActive()) {
			g.setColor(Color.GREEN);
		}
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 151, 151);
		
		// Paint the lines between the cells
		g.setColor(Color.BLACK);
		for (int i=0; i<=25; ++i) {
			g.drawLine(6*i, 0, 6*i, 150);
			g.drawLine(0, 6*i, 150, 6*i);
		}
		
		// Fill the cells that are alive
		for (int x=0; x<25; ++x) {
			for (int y=0; y<25; ++y) {
				if (chunk.isCellAlive(x, y)) {
					g.fillRect(6*x, 6*y, 6, 6);
				}
			}
		}
	}
	
}
