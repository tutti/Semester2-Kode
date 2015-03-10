package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

import code.Board;

public class Window extends JFrame {

	private static final long serialVersionUID = -6739816451153974534L;
	
	private JFileChooser fc = new JFileChooser();
	
	private DisplayChunk[][] displayChunks = new DisplayChunk[3][3];
	
	private boolean autoplay = false;

	public Window(Board board) {
		super("Conway's Game of Life");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		getContentPane().setPreferredSize(new Dimension(700, 550));
		pack();
		setVisible(true);

		JButton autoplayButton = new JButton();
		autoplayButton.setBounds(550, 50, 100, 20);
		autoplayButton.setText("Play");
		autoplayButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (autoplay) {
					autoplay = false;
					autoplayButton.setText("Play");
				} else {
					autoplay = true;
					autoplayButton.setText("Pause");
				}
			}
		});
		this.getContentPane().add(autoplayButton);
		
		JButton advanceButton = new JButton();
		advanceButton.setBounds(550, 80, 100, 20);
		advanceButton.setText("Advance");
		advanceButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				board.advance();
				repaint();
			}
		});
		this.getContentPane().add(advanceButton);
		
		JButton openFileButton = new JButton();
		openFileButton.setBounds(550, 140, 100, 20);
		openFileButton.setText("Load");
		openFileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int returnVal = fc.showOpenDialog(null);

		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File file = fc.getSelectedFile();
		            
		            Path path = file.toPath();
		            String extension = "";

		            int i = path.toString().lastIndexOf('.');
		            if (i >= 0) {
		                extension = path.toString().substring(i+1);
		            }
		            
		            if (extension.equals("ilf")) {
			            byte[] contents;
			            try {
							contents = Files.readAllBytes(path);
						} catch (IOException e) {
							e.printStackTrace();
							return;
						}
		            	board.loadInfiniteLifeFile(contents);
		            } else {
			            List<String> contents;
			            try {
							contents = Files.readAllLines(path);
						} catch (IOException e) {
							e.printStackTrace();
							return;
						}
						 String[] stringcontents = new String[contents.size()];
						
						 board.loadFromFile(contents.toArray(stringcontents),
						 extension);
		            }
		            

		            

		            
		        }
			}
		});
		this.getContentPane().add(openFileButton);
		
		JButton saveFileButton = new JButton();
		saveFileButton.setBounds(550, 170, 100, 20);
		saveFileButton.setText("Save");
		saveFileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int returnVal = fc.showSaveDialog(null);

		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File file = fc.getSelectedFile();
//		            ArrayList<String> contents = board.toLife105FileData();
		            byte[] contents = board.toInfiniteLifeFileData();
		            Path path = file.toPath();
		            try {
//						OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file));
						Files.write(path, contents);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
//		            try {
//						Files.write(path, contents, StandardCharsets.UTF_8);
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
		        }
			}
		});
		this.getContentPane().add(saveFileButton);
		
		JLabel chunkCount = new JLabel();
		chunkCount.setBounds(550, 230, 100, 20);
		this.getContentPane().add(chunkCount);
		
		new DirectionButton(0, 1, this);
		new DirectionButton(-1, 0, this);
		new DirectionButton(0, -1, this);
		new DirectionButton(1, 0, this);
		
		for (int x = -1; x <= 1; ++x) {
			for (int y = -1; y <= 1; ++y) {
				DisplayChunk dc = new DisplayChunk(board, x, y);
				displayChunks[x+1][y+1] = dc;
				dc.setBounds(150 * x + 200, 150 * y + 200, 151, 151);
				getContentPane().add(dc);
			}
		}
		repaint();

		while (true) {
			try {
				Thread.sleep(100);
			} catch (Exception e) {
			}
			if (autoplay) board.advance();
			chunkCount.setText("Chunks: "+board.countChunks());
			repaint();
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
