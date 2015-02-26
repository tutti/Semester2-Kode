package balansering;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Fil {

	public static String[] les(String filnavn) {
		ArrayList<String> innhold = new ArrayList<String>();
		FileReader tekstFilLeser = null;
		try {
			tekstFilLeser = new FileReader(filnavn);
		} catch (FileNotFoundException unntak) {
			System.out.println("Finner ikke filen!");
			System.exit(-1);
		}

		BufferedReader tekstLeser = new BufferedReader(tekstFilLeser);

		String linje;
		try {
			linje = tekstLeser.readLine();
			while (linje != null) {
				innhold.add(linje);
				linje = tekstLeser.readLine();
			}
			tekstFilLeser.close();
		} catch (IOException unntak) {
			System.out.println("Feil ved innlesing!");
			System.exit(-1);
		}

		Object[] objectList = innhold.toArray();
		return Arrays.copyOf(objectList, objectList.length, String[].class);

	}

}
