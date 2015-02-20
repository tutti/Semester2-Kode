import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class MandelbrotPanel extends JPanel {

	public static final int WIDTH = 400;
	public static final int HEIGHT = 300;
	private static final Color[] colors = new Color[Mandelbrot.MAX_ITERATIONS];

	private static final long serialVersionUID = 4496197751041193170L;
	private int[][] pixels = new int[WIDTH][HEIGHT];
	
	private double x1;
	private double y1;
	private double x2;
	private double y2;

	public MandelbrotPanel() {
		super();
		setBoundaries(-3, -1.5, 1, 1.5);
		setBounds(0, 0, WIDTH, HEIGHT);
		double color = 255;
		for (int i = 0; i < colors.length; ++i) {
			colors[i] = new Color(255 - (int) color, 255 - (int) (color), 255);
			color *= 0.95;
		}
	}

	public void setBoundaries(double x1, double y1, double x2, double y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		double xStep = (x2 - x1) / WIDTH;
		double yStep = (y2 - y1) / HEIGHT;
		double xVal = x1;
		double yVal = y1;
		for (int i = 0; i < WIDTH; ++i) {
			for (int j = 0; j < HEIGHT; ++j) {
				xVal = x1 + i * xStep;
				yVal = y1 + j * yStep;
				pixels[i][j] = Mandelbrot.numIterations(xVal, yVal);
			}
		}
		repaint();
	}
	
	public double[] getBoundaries() {
		double[] r = {x1, y1, x2, y2};
		return r;
	}

	public void paintComponent(Graphics g) {
		for (int x = 0; x < WIDTH; ++x) {
			for (int y = 0; y < HEIGHT; ++y) {
				int iterations = pixels[x][y];
				if (iterations == -1)
					g.setColor(Color.BLACK);
				else
					g.setColor(colors[iterations]);
				g.fillRect(x, y, 1, 1);
			}
		}
	}

}
