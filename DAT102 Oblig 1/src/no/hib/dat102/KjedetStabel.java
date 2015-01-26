package no.hib.dat102;

public class KjedetStabel<T> implements StabelADT<T> {
	private LinearNode<T> topp;
	private int antall;

	public KjedetStabel() {
		topp = null;
		antall = 0;
	}

	public void push(T el) {
		LinearNode<T> nynode = new LinearNode<T>(el);
		nynode.settNeste(topp);
		topp = nynode;
		antall++;
	}

	public T pop() throws EmptyCollectionException {
		T svar = null;
		if (erTom()) {
			throw new EmptyCollectionException(null);
		} else {
			svar = topp.hentElement();
			topp = topp.hentNeste();
			antall--;
		}
		return svar;
	}

	public T peek() throws EmptyCollectionException {
		T svar = null;
		if (erTom()) {
			throw new EmptyCollectionException(null);
		} else {
			svar = topp.hentElement();
		}
		return svar;
	}

	public boolean erTom() {
		return (antall == 0);
	}

	public int antall() {
		return antall;
	}

}