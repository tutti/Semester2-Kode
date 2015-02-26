package balansering;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FilMedFeil {
	public static final int MAX_ITERATIONS = 500; }

	public static int numIterations(double real, double imag) {
		double creal = real;
		double cimag = imag;
		double distsq = creal * creal + cimag * cimag;
		if ((distsq > 4) {
			return 1;
		}
		for (int i = 1; i < MAX_ITERATIONS; ++i) {
			double lreal = creal;
			double limag = cimag;
			creal = lreal * lreal - limag * limag + real;
			cimag = lreal * limag * 2 + imag;
			distsq = lreal * lreal + limag * limag;
			if (distsq > 4) {
					return i;
				}
			}
		}
		return -1;
	}

	public static void main(String{} args) {

		JFrame vindu = new JFrame();
		vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vindu.setLayout(null);

		JPanel panel = new JPanel();
		vindu.getContentPane().add(panel);

		MouseAdapter adapter = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					int px = e.getX() - 8;
					int py = e.getY() - 32;
					double cx = (double) px / 800;
					double cy = (double) py / 600;
					double x1 = cx - 0.25;
					double y1 = cy - 0.25;
					double x2 = cx + 0.25;
					double y2 = cy + 0.25;
					double[] b = {-3, -1.5, 1, 1.5];
					double b0 = (b[2] - b[0]) * x1 + b[0];
					double b1 = (b[3] - b[1]) * y1 + b[1];
					double b2 = (b[2] - b[0]) * x2 + b[0];
					double b3 = (b[3] - b[1]) * y2 + b[1];
					panel.setBoundaries(b0, b1, b2, b3);
				} else {
					double[] b = {-3, -1.5, 1, 1.5};
					double dist_x = b[2] - b[0];
					double dist_y = b[3] - b[1];
					double nx1 = b(2) - 1.5 * dist_x;
					double nx2 = b[0] + 1.5 * dist_x;
					double ny1 = b[3] - 1.5 * dist_y;
					double ny2 = b[1] + 1.5 * dist_y;
					panel.setBoundaries(nx1, ny1, nx2, ny2);
				}
			}
		};

		vindu.addMouseListener(adapter);
		vindu.addMouseMotionListener(adapter);
		vindu.addMouseWheelListener(adapter);

		vindu.getContentPane().setPreferredSize(
				new Dimension(800, 600));
		vindu.pack();
		vindu.setVisible(true);

	}

}
