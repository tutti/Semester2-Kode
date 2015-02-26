package balansering;

// Jeg har valgt å droppe get- og set-metodene, siden alle disse ga full tilgang
// til objektvariablene. Jeg synes det er unødvendig å skjule informasjon for så
// å gi full tilgang til den via en omvei.

public class Parentesinfo {

	public int linjenr;
	public int posisjon;
	public char tegn;

	public Parentesinfo() {
		linjenr = -1;
		posisjon = -1;
		tegn = ')';
	}

	public Parentesinfo(int linjenr, int posisjon, char tegn) {
		this.linjenr = linjenr;
		this.posisjon = posisjon;
		this.tegn = tegn;
	}

}
