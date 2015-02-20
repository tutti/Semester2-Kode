

public class ComplexNumber {
	private double realPart;
	private double imaginaryPart;
	
	public ComplexNumber(double realPart, double imaginaryPart) {
		this.realPart = realPart;
		this.imaginaryPart = imaginaryPart;
	}
	
	public ComplexNumber add(Number n) {
		return new ComplexNumber(realPart + n.doubleValue(), imaginaryPart);
	}
	
	public ComplexNumber add(ComplexNumber n) {
		return new ComplexNumber(realPart + n.realPart, imaginaryPart + n.imaginaryPart);
	}
	
	public ComplexNumber sub(Number n) {
		return new ComplexNumber(realPart - n.doubleValue(), imaginaryPart);
	}
	
	public ComplexNumber sub(ComplexNumber n) {
		return new ComplexNumber(realPart - n.realPart, imaginaryPart - n.imaginaryPart);
	}
	
	public ComplexNumber mul(Number n) {
		return new ComplexNumber(realPart * n.doubleValue(), imaginaryPart * n.doubleValue());
	}
	
	public ComplexNumber mul(ComplexNumber n) {
		// (a+bi)(c+di) = ac+adi+bci-bd (bdi^2 = -bd)
		double real1 = realPart * n.realPart;
		double imag1 = realPart * n.imaginaryPart;
		double imag2 = n.realPart * imaginaryPart;
		double real2 = -(imaginaryPart * n.imaginaryPart);
		return new ComplexNumber(real1+real2, imag1+imag2);
	}
	
	public ComplexNumber div(Number n) {
		return new ComplexNumber(realPart / n.doubleValue(), imaginaryPart / n.doubleValue());
	}
	
	public ComplexNumber div(ComplexNumber n) {
		// (a+bi) / (c+di) = (a+bi)(c-di) / (c+di)(c-di) = (a+bi)(c-di) / (c^2 * d^2)
		// = (ac + adi + cbi - dbi^2) / (c^2 * d^2) = (ac + db + adi - cbi) / (c^2 * d^2)
		double numeratorRealPart = realPart*n.realPart + imaginaryPart*n.imaginaryPart;
		double numeratorImaginaryPart = n.realPart*imaginaryPart - realPart*n.imaginaryPart;
		double denominator = n.realPart*n.realPart + n.imaginaryPart*n.imaginaryPart;
		return new ComplexNumber(numeratorRealPart / denominator, numeratorImaginaryPart / denominator);
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
		if (!(o instanceof ComplexNumber)) {
			return false;
		}
		ComplexNumber n = (ComplexNumber)o;
		return (realPart == n.realPart && imaginaryPart == n.imaginaryPart);
	}
}
