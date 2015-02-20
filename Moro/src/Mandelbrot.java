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
		if (distsq > 4) {
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
		return -1;
	}

	public static void main(String[] args) {

		JFrame vindu = new JFrame();
		vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vindu.setLayout(null);

		MandelbrotPanel panel = new MandelbrotPanel();
		vindu.getContentPane().add(panel);
		// panel.setBoundaries(-2, -2, 2, 2);

		vindu.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
//				System.out.print("Mouse click: ");
//				if (e.getButton() == MouseEvent.BUTTON1)
//					System.out.print("Left button ");
//				else
//					System.out.print("Other button ");
//				System.out.println(e.getX()+" "+e.getY());
			}
			
			public void mouseMoved(MouseEvent e) {
				
			}
		});

		vindu.getContentPane().setPreferredSize(
				new Dimension(MandelbrotPanel.WIDTH, MandelbrotPanel.HEIGHT));
		vindu.pack();
		vindu.setVisible(true);

	}

}
