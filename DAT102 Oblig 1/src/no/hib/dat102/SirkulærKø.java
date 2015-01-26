package no.hib.dat102;

public class Sirkul�rK�<T> implements K�ADT<T> {
	
	private T[] k�;
	private int antall;
	private int posisjon;
	private int sluttposisjon;
	
	public Sirkul�rK�(int plass) {
		k� = (T[]) new Object[plass];
		antall = 0;
		posisjon = 0;
		sluttposisjon = 0;
	}

	@Override
	public void innK�(T element) {
		k�[sluttposisjon] = element;
		sluttposisjon = (sluttposisjon + 1) % k�.length;
		++antall;
	}

	@Override
	public T utK�() throws EmptyCollectionException {
		if (antall == 0) throw new EmptyCollectionException("Ingen elementer");
		T element = k�[posisjon];
		posisjon = (posisjon + 1) % k�.length;
		--antall;
		return element;
	}

	@Override
	public T front() throws EmptyCollectionException {
		if (antall == 0) throw new EmptyCollectionException("Ingen elementer");
		return k�[posisjon];
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
