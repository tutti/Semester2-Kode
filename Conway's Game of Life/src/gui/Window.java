package gui;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;

import code.Board;

public class Window extends JFrame {
	
	private static final long serialVersionUID = -6739816451153974534L;
	
	private Board board;
	
	public Window(Board board) {
		super("Conway's Game of Life");
		this.board = board;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        getContentPane().setPreferredSize(new Dimension(1100, 11*60));
        pack();
        setVisible(true);
        
        for (int x=-1; x<=1; ++x) {
        	for (int y=-1; y<=1; ++y) {
                DisplayChunk dc = new DisplayChunk(board.getChunk(x, y));
                dc.setBounds(150*x+200, 150*y+200, 150, 150);
                getContentPane().add(dc);
        	}
        }
	}
	
}
