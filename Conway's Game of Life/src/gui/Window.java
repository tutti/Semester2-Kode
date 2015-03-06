package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import code.Board;

public class Window extends JFrame {

	private static final long serialVersionUID = -6739816451153974534L;
	
	private DisplayChunk[][] displayChunks = new DisplayChunk[3][3];

	public Window(Board board) {
		super("Conway's Game of Life");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		getContentPane().setPreferredSize(new Dimension(1100, 11 * 60));
		pack();
		setVisible(true);

		JButton advanceButton = new JButton();
		advanceButton.setBounds(600, 200, 100, 20);
		advanceButton.setText("Advance");
		advanceButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				board.advance();
				repaint();
			}
		});
		this.getContentPane().add(advanceButton);
		
		JButton downButton = new DirectionButton(0, 1, this);
		JButton leftButton = new DirectionButton(-1, 0, this);
		JButton upButton = new DirectionButton(0, -1, this);
		JButton rightButton = new DirectionButton(1, 0, this);
		
//		JButton downButton = new JButton();
//		downButton.setBounds(265, 525, 20, 20);
//		downButton.setText("v");
//		downButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				for (DisplayChunk[] row : displayChunks) {
//					for (DisplayChunk dc : row) {
//						dc.moveView(0, 1);
//					}
//				}
//			}
//		});
//		this.getContentPane().add(downButton);
//		
//		JButton leftButton = new DirectionButton(-1, 0);
//		leftButton.setBounds(15, 265, 20, 20);
//		leftButton.setText("<");
//		leftButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				for (DisplayChunk[] row : displayChunks) {
//					for (DisplayChunk dc : row) {
//						dc.moveView(-1, 0);
//					}
//				}
//			}
//		});
//		this.getContentPane().add(leftButton);
		
		for (int x = -1; x <= 1; ++x) {
			for (int y = -1; y <= 1; ++y) {
				DisplayChunk dc = new DisplayChunk(board, x, y);
				displayChunks[x+1][y+1] = dc;
				dc.setBounds(150 * x + 200, 150 * y + 200, 150, 150);
				getContentPane().add(dc);
			}
		}
	}
	
	public void moveView(int dx, int dy) {
		for (DisplayChunk[] row : displayChunks) {
			for (DisplayChunk dc : row) {
				dc.moveView(dx, dy);
			}
		}
	}

}
