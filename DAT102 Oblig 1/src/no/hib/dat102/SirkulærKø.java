package no.hib.dat102;

public class SirkulærKø<T> implements KøADT<T> {
	
	private T[] kø;
	private int antall;
	private int posisjon;
	private int sluttposisjon;
	
	public SirkulærKø(int plass) {
		kø = (T[]) new Object[plass];
		antall = 0;
		posisjon = 0;
		sluttposisjon = 0;
	}

	@Override
	public void innKø(T element) {
		kø[sluttposisjon] = element;
		sluttposisjon = (sluttposisjon + 1) % kø.length;
		++antall;
	}

	@Override
	public T utKø() throws EmptyCollectionException {
		if (antall == 0) throw new EmptyCollectionException("Ingen elementer");
		T element = kø[posisjon];
		posisjon = (posisjon + 1) % kø.length;
		--antall;
		return element;
	}

	@Override
	public T front() throws EmptyCollectionException {
		if (antall == 0) throw new EmptyCollectionException("Ingen elementer");
		return kø[posisjon];
	}

	@Override
	public boolean erTom() {
		return antall == 0;
	}
	
	@Override
	public int getAntall() {
		return antall;
	}
}
