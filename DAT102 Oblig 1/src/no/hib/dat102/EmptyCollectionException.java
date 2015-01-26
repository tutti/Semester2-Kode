package no.hib.dat102;
/**
 * Represents the situation in which a collection is empty.
 */
public class EmptyCollectionException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Sets up this exception with an appropriate message.
     * 
     * @param collection the collection
     */
    public EmptyCollectionException(String collection) {
        super("The " + collection + " is empty.");
    }
}
