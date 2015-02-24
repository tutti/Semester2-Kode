package no.hib.dat102.kontakt;

import java.util.Iterator;

import no.hib.dat102.mengde.kjedet.KjedetMengde;

public class Medlem {
	private String navn;
	private KjedetMengde<Hobby> hobbyer;
	private int statusIndeks = -1;
	
	public Medlem(String navn) {
		settNavn(navn);
		hobbyer = new KjedetMengde<Hobby>();
	}
	
	public void settNavn(String navn) {
		this.navn = navn;
	}
	
	public String hentNavn() {
		return navn;
	}
	
	public void settStatusIndeks(int indeks) {
		statusIndeks = indeks;
	}
	
	public int hentStatusIndeks() {
		return statusIndeks;
	}
	
	public void leggTilHobby(Hobby hobby) {
		hobbyer.leggTil(hobby);
	}
	
	public boolean harHobby(Hobby hobby) {
		return hobbyer.inneholder(hobby);
	}
	
	public void fjernHobby(Hobby hobby) {
		hobbyer.fjern(hobby);
	}
	
	public Iterator<Hobby> hentHobbyer() {
		return hobbyer.oppramser();
	}
	
	public boolean passerTil(Medlem m2) {
		if (this.equals(m2)) return false;
		return hobbyer.erLik(m2.hobbyer);
	}
}
