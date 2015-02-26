package person;

public class Person implements Comparable<Person> {
	
	private static final String FORMAT_STRING = "%1$4d   %2$s, %3$s";
	
	private String fornavn;
	private String etternavn;
	private int fødselsår;
	
	public Person(String fornavn, String etternavn, int fødselsår) {
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.fødselsår = fødselsår;
	}
	
	public void settFornavn(String fornavn) {
		this.fornavn = fornavn;
	}
	
	public String hentFornavn() {
		return fornavn;
	}
	
	public void settEtternavn(String etternavn) {
		this.etternavn = etternavn;
	}
	
	public String hentEtternavn() {
		return etternavn;
	}
	
	// Ingen setter for fødselsår - en persons fødselsår kan ikke bli forandret
	
	public int hentFødselsår() {
		return fødselsår;
	}

	@Override
	public int compareTo(Person o) {
		// TODO Auto-generated method stub
		if (o.fødselsår == fødselsår) {
			if (o.etternavn.equals(etternavn)) {
				return fornavn.compareTo(o.fornavn);
			}
			return etternavn.compareTo(o.etternavn);
		}
		return fødselsår - o.fødselsår;
	}
	
	@Override
	public String toString() {
		
		return String.format(FORMAT_STRING, fødselsår, etternavn, fornavn);
	}

}
