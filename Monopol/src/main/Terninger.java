package main;

import java.util.ArrayList;
import java.util.Random;

public class Terninger {
	
	private class Terning {
		
		private int sider = 0;
		private int sisteKast = 0;
		
		private Terning() {
			sider = 6;
		}
		
		private Terning(int sider) {
			this.sider = sider;
		}
		
		private int kast() {
			sisteKast = rnd.nextInt(sider)+1;
			return sisteKast;
		}
		
		private int sisteKast() {
			return sisteKast;
		}
		
	}
	
	private static Random rnd = new Random();
	private ArrayList<Terning> terninger = new ArrayList<Terning>();
	
	public void leggTilTerning() {
		terninger.add(new Terning());
	}
	
	public void leggTilTerning(int sider) {
		terninger.add(new Terning(sider));
	}
	
	public void leggTilTerninger(int antallTerninger) {
		for (;antallTerninger-->0;terninger.add(new Terning()));
	}
	
	public void leggTilTerninger(int antallTerninger, int sider) {
		for (;antallTerninger-->0;terninger.add(new Terning(sider)));
	}
	
	public int kast() {
		int sum = 0;
		for (Terning t : terninger) {
			sum += t.kast();
		}
		return sum;
	}
	
	public int sisteKast() {
		int sum = 0;
		for (Terning t : terninger) {
			sum += t.sisteKast();
		}
		return sum;
	}
	
	public boolean alleLike() {
		int kast = terninger.get(0).sisteKast();
		for (Terning t : terninger) {
			if (t.sisteKast() != kast) return false;
		}
		return true;
	}
	
}
