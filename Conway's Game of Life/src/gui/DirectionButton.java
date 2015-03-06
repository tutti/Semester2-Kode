package gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

public class DirectionButton extends JButton {
	
	protected Image image;
	
	public DirectionButton(int dx, int dy, Window window) {
		try {
			image = ImageIO.read(new File("images/"+dx+"_"+dy+".png"));
		} catch (IOException e) {}
		
		setBounds(265+240*dx, 265+240*dy, 20, 20);
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.moveView(dx, dy);
			}
		});
		window.getContentPane().add(this);
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, null);
	}
	
}
