package balansering;

public class FilUtenFeil1 {
	private double realPart;
	private double imaginaryPart;
	
	public FilUtenFeil1(double realPart) {
		this.realPart = realPart;
		this.imaginaryPart = 0;
	}
	
	public FilUtenFeil1(double realPart, double imaginaryPart) {
		this.realPart = realPart;
		this.imaginaryPart = imaginaryPart;
	}
	
	public FilUtenFeil1 add(Number n) {
		return new FilUtenFeil1(realPart + n.doubleValue(), imaginaryPart);
	}
	
	public FilUtenFeil1 add(FilUtenFeil1 n) {
		return new FilUtenFeil1(realPart + n.realPart, imaginaryPart + n.imaginaryPart);
	}
	
	public FilUtenFeil1 subtrat(Number n) {
		return new FilUtenFeil1(realPart - n.doubleValue(), imaginaryPart);
	}
	
	public FilUtenFeil1 subtract(FilUtenFeil1 n) {
		return new FilUtenFeil1(realPart - n.realPart, imaginaryPart - n.imaginaryPart);
	}
	
	public FilUtenFeil1 multiply(Number n) {
		return new FilUtenFeil1(realPart * n.doubleValue(), imaginaryPart * n.doubleValue());
	}
	
	public FilUtenFeil1 multiply(FilUtenFeil1 n) {
		// (a+bi)(c+di) = ac+adi+bci-bd (bdi^2 = -bd)
		double real1 = realPart * n.realPart;
		double imag1 = realPart * n.imaginaryPart;
		double imag2 = n.realPart * imaginaryPart;
		double real2 = -(imaginaryPart * n.imaginaryPart);
		return new FilUtenFeil1(real1+real2, imag1+imag2);
	}
	
	public FilUtenFeil1 divide(Number n) {
		return new FilUtenFeil1(realPart / n.doubleValue(), imaginaryPart / n.doubleValue());
	}
	
	public FilUtenFeil1 divide(FilUtenFeil1 n) {
		// (a+bi) / (c+di) = (a+bi)(c-di) / (c+di)(c-di) = (a+bi)(c-di) / (c^2 * d^2)
		// = (ac + adi + cbi - dbi^2) / (c^2 * d^2) = (ac + db + adi - cbi) / (c^2 * d^2)
		double numeratorRealPart = realPart*n.realPart + imaginaryPart*n.imaginaryPart;
		double numeratorImaginaryPart = n.realPart*imaginaryPart - realPart*n.imaginaryPart;
		double denominator = n.realPart*n.realPart + n.imaginaryPart*n.imaginaryPart;
		return new FilUtenFeil1(numeratorRealPart / denominator, numeratorImaginaryPart / denominator);
	}
	
	public double abs() {
		return Math.sqrt(realPart*realPart + imaginaryPart*imaginaryPart);
	}
	
	public double getRealPart() {
		return realPart;
	}
	
	public double getImaginaryPart() {
		return imaginaryPart;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (realPart == (int)realPart) sb.append((int)realPart); else sb.append(realPart);
		if (imaginaryPart > 0) sb.append("+");
		if (imaginaryPart != 0) {
			if (imaginaryPart == (int)imaginaryPart) sb.append((int)imaginaryPart); else sb.append(imaginaryPart);
			sb.append("i");
		}
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof FilUtenFeil1) {
			FilUtenFeil1 n = (FilUtenFeil1)o;
			return (realPart == n.realPart && imaginaryPart == n.imaginaryPart);
		} else if (imaginaryPart == 0 && o instanceof Number) {
			Number n = (Number)o;
			return (realPart == n.doubleValue());
		} else {
			return false;
		}
	}
}
