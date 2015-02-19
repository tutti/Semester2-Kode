package gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Brukes alle steder der bilder må tegnes, som ikke dekkes av mer spesialiserte
 * klasser.
 * 
 * @author tutti
 *
 */
public class Bilde extends JPanel {
	private static final long serialVersionUID = -2062743847201944182L;
	private BufferedImage bilde;

	public Bilde(String filnavn) {
		try {
			System.out.println("Leser bilde: "+filnavn);
			bilde = ImageIO.read(new File(filnavn));
		} catch (IOException ex) {
			System.out.println("Fant ikke bilde: "+filnavn);
		}
	}
	
	public BufferedImage hentBilde() {
		return bilde;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bilde, 0, 0, null);
	}

}
