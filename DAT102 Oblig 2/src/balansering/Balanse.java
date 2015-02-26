package balansering;

public class Balanse {

	private static final String FEIL_FORMAT = "Linje %d, tegn %d: %s%n";

	private static boolean passer(char åpent, char lukket) {
		switch (åpent) {
		case '(':
			return (lukket == ')');
		case '[':
			return (lukket == ']');
		case '{':
			return (lukket == '}');
		default:
			return false;
		}
	}

	public static void balanser(String[] innhold) {
		boolean feilfritt = true;
		StabelADT<Parentesinfo> stabel = new TabellStabel<Parentesinfo>();

		for (int linjenr = 1; linjenr <= innhold.length; ++linjenr) {
			String linje = innhold[linjenr - 1];
			for (int i = 1; i <= linje.length(); ++i) {
				char tegn = linje.charAt(i - 1);
				switch (tegn) {
				case '(':
				case '[':
				case '{':
					stabel.push(new Parentesinfo(linjenr, i, tegn));
					break;
				case ')':
				case ']':
				case '}':
					if (stabel.erTom()) {
						feilfritt = false;
						System.out.printf(FEIL_FORMAT, linjenr, i, "Tegnet "
								+ tegn + " har ingen tilsvarende åpnesymbol.");
					} else {
						Parentesinfo info = stabel.pop();
						char tegn2 = info.tegn;
						if (!passer(tegn2, tegn)) {
							feilfritt = false;
							System.out.printf(FEIL_FORMAT, linjenr, i,
									"Tegnet " + tegn2 + " passer ikke til "
											+ tegn + ".");
						}
					}
					break;
				}
			}
		}

		while (!stabel.erTom()) {
			feilfritt = false;
			Parentesinfo info = stabel.pop();
			System.out.printf(FEIL_FORMAT, info.linjenr, info.posisjon,
					"Tegnet " + info.tegn
							+ " har ingen tilsvarende lukkesymbol. ");
		}

		if (feilfritt) {
			System.out.println("Ingen parentesfeil!");
		}

	}
}
