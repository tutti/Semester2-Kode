import java.awt.Color;
import java.awt.Graphics;
import java.math.BigDecimal;

import javax.swing.JPanel;

public class MandelbrotPanel extends JPanel {

	public static final int WIDTH = 400;
	public static final int HEIGHT = 400;
	private static final BigDecimal N_TWO = new BigDecimal("-2");
	private static final BigDecimal N_ONE = new BigDecimal("-1");
	private static final BigDecimal ONE = new BigDecimal("1");
	private static final Color[] colors = new Color[Mandelbrot.MAX_ITERATIONS];

	private static final long serialVersionUID = 4496197751041193170L;
	private int[][] pixels = new int[WIDTH][HEIGHT];

	public MandelbrotPanel() {
		super();
		setBoundaries(N_TWO, N_ONE, ONE, ONE);
		setBounds(0, 0, WIDTH, HEIGHT);
		for (int i=0; i<colors.length; ++i) {
			colors[i] = new Color((int)(i*2.5), (int)(i*2.5), 255); // TODO: Lose dependency on max_it <= 127.
		}
	}

	public void setBoundaries(BigDecimal x1, BigDecimal y1, BigDecimal x2, BigDecimal y2) {
		BigDecimal xStep = x1.abs().add(x2.abs()).divide(new BigDecimal(Integer.toString(WIDTH)));
		BigDecimal yStep = y1.abs().add(y2.abs()).divide(new BigDecimal(Integer.toString(HEIGHT)));
		BigDecimal xVal = x1;
		BigDecimal yVal = y1;
//		System.out.println(xVal+" "+yVal);
		for (int i=0; i<WIDTH; ++i) {
			xVal = xVal.add(xStep);
			yVal = y1;
			for (int j=0; j<HEIGHT; ++j) {
				System.out.println(i+" "+j+" "+xVal+" "+yVal);
				yVal = yVal.add(yStep);
				pixels[i][j] = Mandelbrot.numIterations(xVal, yVal);
			}
		}
//		System.out.println(xVal+" "+yVal);
	}

	public void paintComponent(Graphics g) {
		for (int x=0; x<WIDTH; ++x) {
			for (int y=0; y<HEIGHT; ++y) {
				int iterations = pixels[x][y];
				if (iterations == -1) g.setColor(Color.BLACK);
				else g.setColor(colors[iterations]);
				g.fillRect(x, y, 1, 1);
			}
		}
	}

}
