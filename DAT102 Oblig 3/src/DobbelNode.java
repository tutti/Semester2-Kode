//********************************************************************
//  DobbelNode.java       
//
//  Representer en node i en dobbeltkjedet liste..
//********************************************************************

public class DobbelNode<E>{
    private DobbelNode<E> neste;
    private DobbelNode<E> forrige;
    private E element;

    /******************************************************************
      Oppretter en tom node node.
    ******************************************************************/
    public DobbelNode(){    
       neste   = null;
       element = null;
       forrige = null;
    }

    /******************************************************************
      Oppretter en node som lagrer det spesifiserte elementet.
    ******************************************************************/
    public DobbelNode(E elem) {
       neste = null;
       forrige = null;
       element = elem;
    }

    /******************************************************************
      Returnerer en refranse til etterfølgeren av denne noden, 
      eller null hvis denne er siste.
    ******************************************************************/
    public DobbelNode<E> getNeste() {
       return neste;
    }

    /*************************************************************************
      Returnerer forgjengeren til denne noden, eller null hvis denne er første 
    **************************************************************************/
    public DobbelNode<E>  getForrige() {
       return forrige;
    }

    /******************************************************************
      Setter neste til å peke på dnode.
    ******************************************************************/
    public void setNeste (DobbelNode<E> dnode){
       neste = dnode;
    }

    /******************************************************************
      Settter ny forrige til å peke på dnode.
    ******************************************************************/
    public void setForrige(DobbelNode<E> dnode) {
       forrige = dnode;
    }


    /******************************************************************
      Returnerer elementet til denne noden..
    ******************************************************************/
    public E getElement() {
       return element;
    }

    /******************************************************************
      Lagrer nytt element i denne noden..
    ******************************************************************/
    public void setElement (E elem) {
       element = elem;
    }

}



