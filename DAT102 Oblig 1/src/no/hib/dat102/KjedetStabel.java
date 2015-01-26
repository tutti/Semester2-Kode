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
	
	private Node<T> første = null;
	private int antall = 0;

	@Override
	public void push(T element) {
		Node<T> ny = new Node<T>(element);
		ny.setNeste(første);
		første = ny;
	}

	@Override
	public T pop() throws EmptyCollectionException {
		if (første == null) throw new EmptyCollectionException("Ingen elementer");
		Node<T> pop = første;
		første = pop.getNeste();
		--antall;
		return pop.getElement();
	}

	@Override
	public T peek() throws EmptyCollectionException {
		if (første == null) throw new EmptyCollectionException("Ingen elementer");
		return første.getElement();
	}

	@Override
	public boolean erTom() {
		return første == null;
	}

	@Override
	public int antall() {
		return antall;
	}

}
