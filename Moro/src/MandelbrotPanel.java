import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MandelbrotPanel extends JPanel {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	private static final Color[] colors = new Color[Mandelbrot.MAX_ITERATIONS];

	private static final long serialVersionUID = 4496197751041193170L;
	private int[][] pixels = new int[WIDTH][HEIGHT];

	public MandelbrotPanel() {
		super();
		setBoundaries(-3, -1.5, 1, 1.5);
		setBounds(0, 0, WIDTH, HEIGHT);
		double color = 255;
		for (int i = 0; i < colors.length; ++i) {
			colors[i] = new Color(255 - (int) color, 255 - (int) (color), 255);
			color *= 0.9;
		}
	}

	public void setBoundaries(double x1, double y1, double x2, double y2) {
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
