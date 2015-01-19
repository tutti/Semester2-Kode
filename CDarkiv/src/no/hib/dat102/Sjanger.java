package no.hib.dat102;

public enum Sjanger {
	POP, ROCK, OPERA, KLASSISK;
	
	// Sjekker om en verdi er en gyldig Sjanger
	public static boolean contains(String string) {
	    for (Sjanger s : Sjanger.values()) {
	        if (s.name().equals(string)) {
	            return true;
	        }
	    }
	    return false;
	}
}