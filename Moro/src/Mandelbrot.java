import java.awt.Dimension;
import java.math.BigDecimal;
import javax.swing.JFrame;

public class Mandelbrot {
	private static final BigDecimal TWO = new BigDecimal("2");
	private static final BigDecimal FIVE = new BigDecimal("5");
	public static final int MAX_ITERATIONS = 100;
	
	public static int numIterations(BigDecimal real, BigDecimal imag) {
		BigDecimal creal = real.setScale(3, BigDecimal.ROUND_DOWN);
		BigDecimal cimag = imag.setScale(3, BigDecimal.ROUND_DOWN);
//		System.out.println(cimag);
		BigDecimal distsq = creal.multiply(creal).add(cimag.multiply(cimag));
//		if (distsq.compareTo(FIVE) > 0) {
//			System.out.println(real+" "+imag+" "+0);
//			return 0;
//		}
		for (int i=1; i<MAX_ITERATIONS; ++i) {
			creal = creal.multiply(creal).subtract(cimag.multiply(cimag)).add(real);
			cimag = creal.multiply(cimag).multiply(TWO).add(imag);
			System.out.println("0: "+creal+" "+cimag);
//			System.out.println("1: "+creal.multiply(cimag));
//			System.out.println("2: "+creal.multiply(cimag).multiply(TWO));
//			System.out.println("3: "+creal.multiply(cimag).multiply(TWO).add(imag));
			distsq = creal.multiply(creal).add(cimag.multiply(cimag));
			if (distsq.compareTo(FIVE) > 0) {
//				System.out.println(real+" "+imag+" "+i);
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
		
        vindu.getContentPane().setPreferredSize(new Dimension(400, 400));
        vindu.pack();
        vindu.setVisible(true);
		
	}
	
}
