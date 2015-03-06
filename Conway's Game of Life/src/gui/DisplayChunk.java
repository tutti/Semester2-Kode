package gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

import code.Chunk;

public class DisplayChunk extends JButton {
	private static final long serialVersionUID = 815555903536680994L;
	
	private Chunk chunk;

	public DisplayChunk(Chunk chunk) {
		setBorderPainted(false);
		this.chunk = chunk;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
		if (!chunk.isActive()) {
			g.setColor(Color.GREEN);
		}
		g.fillRect(0, 0, 150, 150);
		
		
		// Paint the lines between the cells
		g.setColor(Color.BLACK);
		for (int i=0; i<25; ++i) {
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
