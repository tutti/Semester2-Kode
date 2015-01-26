package no.hib.dat102;

//************************************************************
///  Representerer en node.
//************************************************************

class LinearNode<T> {
	private LinearNode<T> neste;
	private T element;

	/**********************************************************
	 * Oppretter en tom node
	 **********************************************************/
	public LinearNode() {
		neste = null;
		element = null;
	}

	/**********************************************************
	 * Oppretter en node med et element.
	 **********************************************************/
	public LinearNode(T elem) {
		neste = null;
		element = elem;
	}

	/**********************************************************
	 * Returnerer etterfølger.
	 **********************************************************/
	public LinearNode<T> hentNeste() {
		return neste;
	}

	/**********************************************************
	 * Setter neste til node
	 **********************************************************/
	public void settNeste(LinearNode<T> node) {
		neste = node;
	}

	/**********************************************************
	 * Returnerer elementet til denne noden
	 **********************************************************/
	public T hentElement() {
		return element;
	}

	/**********************************************************
	 * Setter nytt element i denne noden.
	 **********************************************************/
	public void settElement(T elem) {
		element = elem;
	}
}
