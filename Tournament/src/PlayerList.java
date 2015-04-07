import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class PlayerList {
	private static Scanner fileReader = null;
	private static HashMap<Integer, Player> players = new HashMap<Integer, Player>();
	
	public static void load() {
		try {
			fileReader = new Scanner(new File("players.txt"));
			while (fileReader.hasNext()) {
				String line = fileReader.nextLine();
				String[] line_split = line.split(" ", 2);
				int id = Integer.parseInt(line_split[0]);
				Player p = new Player(id, line_split[1]);
				players.put(id, p);
			}
			fileReader.close();
		} catch (IOException e) {
			System.out.println("Could not open players file.");
		}
	}
	
	public static void save() {
		try {
			PrintWriter writer = new PrintWriter("players.txt");
			for (int key : players.keySet()) {
				if (key < 0) continue;
				Player p = players.get(key);
				writer.println(p.savedata());
			}
			writer.close();
		} catch (Exception e) {
			System.out.println("Could not save players file.");
		}
	}
	
	public static boolean hasPlayer(int id) {
		return players.containsKey(id);
	}
	
	public static Player getPlayer(int id) {
		return players.get(id);
	}
	
	public static Player createPlayer(int id, String name) {
		Player p = new Player(id, name);
		players.put(id, p);
		return p;
	}
}
