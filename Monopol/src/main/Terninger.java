package main;

import java.util.ArrayList;
import java.util.Random;

public class Terninger {
	
	private class Terning {
		
		private int sider = 6;
		private int sisteKast = 0;
		
		private Terning() {}
		
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
	
}
