import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class Mandelbrot {
	public static final int MAX_ITERATIONS = 1000;

	public static int numIterations(double real, double imag) {
		double creal = real;
		double cimag = imag;
		double distsq = creal * creal + cimag * cimag;
		if (distsq > 5) {
			return 1;
		}
		for (int i = 1; i < MAX_ITERATIONS; ++i) {
			double lreal = creal;
			double limag = cimag;
			creal = lreal * lreal - limag * limag + real;
			cimag = lreal * limag * 2 + imag;
			distsq = lreal * lreal + limag * limag;
			if (distsq > 5) {
				return i;
			}
		}
		return -1;
	}

	public static void main(String[] args) {

		JFrame vindu = new JFrame();
		vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vindu.setLayout(null);

		MandelbrotPanel panel = new MandelbrotPanel();
		vindu.getContentPane().add(panel);

		vindu.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					double cx = (double)(e.getX()-8) / MandelbrotPanel.WIDTH;
					double cy = (double)(e.getY()-32) / MandelbrotPanel.HEIGHT;
					double x1 = Math.max(cx - 0.25, 0);
					double y1 = Math.max(cy - 0.25, 0);
					double x2 = Math.min(x1 + 0.5, MandelbrotPanel.WIDTH);
					double y2 = Math.min(y1 + 0.5, MandelbrotPanel.HEIGHT);
					x1 = x2 - 0.5;
					y1 = y2 - 0.5;
					double[] b = panel.getBoundaries();
					double b0 = (b[2]-b[0])*x1+b[0];
					double b1 = (b[3]-b[1])*y1+b[1];
					double b2 = (b[2]-b[0])*x2+b[0];
					double b3 = (b[3]-b[1])*y2+b[1];
					panel.setBoundaries(b0, b1, b2, b3);
				}
				else {
					double[] b = panel.getBoundaries();
					panel.setBoundaries(b[0]*2, b[1]*2, b[2]*2, b[3]*2);
				}
				
			}
		});

		vindu.getContentPane().setPreferredSize(
				new Dimension(MandelbrotPanel.WIDTH, MandelbrotPanel.HEIGHT));
		vindu.pack();
		vindu.setVisible(true);

	}

}
