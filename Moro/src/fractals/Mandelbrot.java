package fractals;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.JFrame;

import mandelbrot.MandelbrotPanel;
import no.hib.mat104.ComplexNumber;

public class Mandelbrot implements FractalGenerator {
	
	public static final int MAX_ITERATIONS = 250;

	@Override
	public int generate(ComplexNumber c) {
		ComplexNumber current = c;
		
		if (current.abs() > 2) {
			return 1;
		}
		
		for (int i = 1; i < MAX_ITERATIONS; ++i) {
			current = current.multiply(current);
			if (current.abs() > 2) {
				return i;
			}
		}
		
		return -1;
	}

}
