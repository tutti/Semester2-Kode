package ruter;

public class TogRute extends EiendomRute {
	
	public TogRute(String navn, int pris) {
		super(navn, pris);
	}
	
	public int beregnLeie(int kast) {
		return (int)Math.pow(2, farge.antallEid(eier)-1)*25;
	}
}
