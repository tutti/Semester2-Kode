package no.hib.dat102;

public class KjedetStabel<T> implements StabelADT<T> {
	
	private class Node<E> {
		
		private E element;
		private Node<E> neste;
		
		private Node(E e) {
			element = e;
		}
		
		private E getElement() {
			return element;
		}
		
		private void setNeste(Node<E> neste) {
			this.neste = neste;
		}
		
		private Node<E> getNeste() {
			return neste;
		}
	}
	
	private Node<T> f�rste = null;
	private int antall = 0;

	@Override
	public void push(T element) {
		Node<T> ny = new Node<T>(element);
		ny.setNeste(f�rste);
		f�rste = ny;
	}

	@Override
	public T pop() throws EmptyCollectionException {
		if (f�rste == null) throw new EmptyCollectionException("Ingen elementer");
		Node<T> pop = f�rste;
		f�rste = pop.getNeste();
		--antall;
		return pop.getElement();
	}

	@Override
	public T peek() throws EmptyCollectionException {
		if (f�rste == null) throw new EmptyCollectionException("Ingen elementer");
		return f�rste.getElement();
	}

	@Override
	public boolean erTom() {
		return f�rste == null;
	}

	@Override
	public int antall() {
		return antall;
	}

}
