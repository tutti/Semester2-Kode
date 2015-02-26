package person;

public class Person implements Comparable<Person> {
	
	private static final String FORMAT_STRING = "%1$4d   %2$s, %3$s";
	
	private String fornavn;
	private String etternavn;
	private int f�dsels�r;
	
	public Person(String fornavn, String etternavn, int f�dsels�r) {
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.f�dsels�r = f�dsels�r;
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
	
	// Ingen setter for f�dsels�r - en persons f�dsels�r kan ikke bli forandret
	
	public int hentF�dsels�r() {
		return f�dsels�r;
	}

	@Override
	public int compareTo(Person o) {
		// TODO Auto-generated method stub
		if (o.f�dsels�r == f�dsels�r) {
			if (o.etternavn.equals(etternavn)) {
				return fornavn.compareTo(o.fornavn);
			}
			return etternavn.compareTo(o.etternavn);
		}
		return f�dsels�r - o.f�dsels�r;
	}
	
	@Override
	public String toString() {
		
		return String.format(FORMAT_STRING, f�dsels�r, etternavn, fornavn);
	}

}
