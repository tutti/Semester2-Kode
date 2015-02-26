package balansering;

import java.util.Scanner;

public class Klient {

	public static void main(String[] args) {
		
		Scanner tastatur = new Scanner(System.in);
		
		System.out.print("Skriv inn et filnavn: ");
		String filnavn = tastatur.nextLine();
		
		String[] innhold = Fil.les("src/balansering/"+filnavn);
		
		Balanse.balanser(innhold);
		
		tastatur.close();
		
	}

}
