import java.util.ArrayList;
import java.util.HashMap;

public class Oppg1 {
	private static HashMap<Integer, Integer> oppgBResultater = new HashMap<Integer, Integer>();
	
	static {
		oppgBResultater.put(0, 0);
		oppgBResultater.put(1, 5);
	}
	
	// a)
	public static int førsteNNaturligeTall(int n) {
		if (n == 1) return 1;
		return n+førsteNNaturligeTall(n-1);
	}
	
	// b)
	public static int oppgB(int n) {
		if (oppgBResultater.containsKey(n)) {
			return oppgBResultater.get(n);
		}
		int resultat = 5*oppgB(n-1)-6*oppgB(n-2)+2;
		oppgBResultater.put(n, resultat);
		return resultat;
	}
	
	public static void main(String[] args) {
		for (int i=0; i<10; ++i) {
			System.out.println(oppgB(i));
		}
	}
}
