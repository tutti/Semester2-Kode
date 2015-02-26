package balansering;

// Jeg har valgt � droppe get- og set-metodene, siden alle disse ga full tilgang
// til objektvariablene. Jeg synes det er un�dvendig � skjule informasjon for s�
// � gi full tilgang til den via en omvei.

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
