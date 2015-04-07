import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Tournament {
	private ArrayList<Player> players;
	private ArrayList<Player> droppedPlayers;
	private ArrayList<Game>[] rounds;
	private int numRounds;
	private int currentRound;
	private boolean currentlyInRound = false;
	private String tournamentName;

	public Tournament(String name) {
		players = new ArrayList<Player>();
		droppedPlayers = new ArrayList<Player>();
		currentRound = 0;

		tournamentName = name;
	}

	/**
	 * Adds a player to the tournament.
	 * 
	 * @param player
	 *            The player to add
	 */
	public void addPlayer(Player player) {
		if (players.contains(player))
			return;
		players.add(player);
		if (currentRound > 0) {
			player.markTardy();
			player.losses = currentRound;
		}

	}

	/**
	 * Removes a player from the tournament.
	 * 
	 * @param player
	 *            The player to remove.
	 */
	public void dropPlayer(Player player) {
		if (!players.contains(player))
			return;
		players.remove(player);
		if (currentRound > 0) {
			player.markDropped();
			droppedPlayers.add(player);
		}
		if (currentlyInRound) {
			for (Game game : rounds[currentRound - 1]) {
				if (game.hasPlayer(player)) {
					game.dropPlayer(player);
				}
			}
		}
	}

	/**
	 * Pairs the tournament's players for the next round.
	 */
	public ArrayList<Player> pair() {
		// If a full pair is possible, do that.
		@SuppressWarnings("unchecked")
		ArrayList<Player> playersClone = (ArrayList<Player>) players.clone();
		if (players.size() % 2 != 0) {
			playersClone.add(Player.getByePlayer());
		}
		ArrayList<Player> p = fullPair(playersClone);
		if (p != null)
			return p;

		// If not, do a quickpair.
		return quickPair(players);
	}

	private void sortPlayers() {
		Collections.sort(players, new Comparator<Player>() {
			public int compare(Player p1, Player p2) {
				return p2.compareTo(p1);
			}
		});
	}

	private static int log2(int n) {
		if (n <= 0)
			throw new IllegalArgumentException();
		return 31 - Integer.numberOfLeadingZeros(n);
	}

	/**
	 * Starts the tournament. Calculates the number of rounds from the number of
	 * players. Players added after this point will not be able to increase the
	 * number of rounds in the tournament.
	 */
	@SuppressWarnings("unchecked")
	public void startTournament() {
		int num_players = players.size();
		if (num_players < 2)
			throw new RuntimeException("Too few players.");
		numRounds = log2(num_players - 1) + 1;

		rounds = new ArrayList[numRounds];
	}

	/**
	 * Starts the next round. This will automatically pair the round.
	 */
	public void startRound() {
		if (currentlyInRound)
			return;
		if (hasFinished()) {
			return;
		}

		sortPlayers();

		// Pair the next round
		rounds[currentRound] = new ArrayList<Game>();
		ArrayList<Player> p = pair();
		for (int i = 0; i < p.size() - 1; i += 2) {
			Game game = new Game(p.get(i), p.get(i + 1));
			rounds[currentRound].add(game);
		}
		++currentRound;

		currentlyInRound = true;
	}

	/**
	 * Finishes the round. Any games that were unfinished will be recorded as
	 * ties. By finishing the round, the tournament is automatically saved.
	 */
	public void finishRound() {
		if (!currentlyInRound)
			return;
		for (Game game : rounds[currentRound - 1]) {
			game.finish();
		}

		currentlyInRound = false;
		save();
	}

	/**
	 * Marks a player as having won their game.
	 * 
	 * @param winner
	 *            The player who won their game.
	 */
	public void winner(Player winner) {
		if (!currentlyInRound)
			return;
		for (Game game : rounds[currentRound - 1]) {
			if (game.hasPlayer(winner)) {
				game.setWinner(winner);
				return;
			}
		}
	}

	/**
	 * Marks a player as having received a tie.
	 * 
	 * @param player
	 *            The player who got a tie.
	 */
	public void tie(Player player) {
		if (!currentlyInRound)
			return;
		for (Game game : rounds[currentRound - 1]) {
			if (game.hasPlayer(player)) {
				game.setTie();
				return;
			}
		}
	}

	/**
	 * Attempts to pair the entire player list. Will avoid pairing players with
	 * other players if they have already played against them. The player list
	 * must already be sorted from best to worst player.
	 * 
	 * @param players
	 *            The list of players to pair.
	 * @return The list of players, in pairing order, or null if not pairable.
	 */
	private ArrayList<Player> fullPair(ArrayList<Player> players) {
		if (players.size() == 1)
			return players;
		if (players.size() == 2) {
			if (!players.get(0).hasPlayed(players.get(1))) {
				return players;
			}
			return null;
		}
		@SuppressWarnings("unchecked")
		ArrayList<Player> playersCopy = (ArrayList<Player>) players.clone();

		Player p1 = playersCopy.remove(0);
		Player p2;
		int limit = playersCopy.size();
		for (int i = 0; i < limit; ++i) {
			if (p1.hasPlayed(playersCopy.get(i)))
				continue;
			p2 = playersCopy.remove(i);
			ArrayList<Player> results = fullPair(playersCopy);
			if (results != null) {
				results.add(0, p2);
				results.add(0, p1);
				return results;
			}
			playersCopy.add(i, p2);
		}

		return null;
	}

	/**
	 * Backup method for pairing the player list. Will pair players without
	 * regard for whether they have already played each other.
	 * 
	 * @param players
	 *            The list of players to pair
	 * @return The list of players, in pairing order.
	 */
	private ArrayList<Player> quickPair(ArrayList<Player> players) {
		System.out.println("Could not pair all players with new opponents.");
		if (players.size() <= 2)
			return players;
		@SuppressWarnings("unchecked")
		ArrayList<Player> playersCopy = (ArrayList<Player>) players.clone();

		Player p1 = playersCopy.remove(0);
		Player p2;
		int limit = playersCopy.size();
		for (int i = 0; i < limit; ++i) {
			if (p1.hasPlayed(playersCopy.get(i)))
				continue;
			p2 = playersCopy.remove(i);
			ArrayList<Player> results = fullPair(playersCopy);
			if (results != null) {
				results.add(0, p2);
				results.add(0, p1);
				return results;
			}
			playersCopy.add(i, p2);
		}

		return players;
	}

	/**
	 * Gets an array containing all the players in the tournament
	 * 
	 * @return
	 */
	public Player[] getPlayers() {
		sortPlayers();
		// Go to size-1 to avoid adding the bye player (which will always be at
		// the bottom).
		Player[] r = new Player[players.size()];
		for (int i = 0; i < players.size(); ++i) {
			r[i] = players.get(i);
		}

		return r;
	}

	/**
	 * Gets an array of the games in the current round.
	 * 
	 * @return The games in the current round.
	 */
	public Game[] getCurrentRound() {
		if (currentRound == 0)
			return null;
		Game[] r = new Game[rounds[currentRound - 1].size()];
		for (int i = 0; i < rounds[currentRound - 1].size(); ++i) {
			r[i] = rounds[currentRound - 1].get(i);
		}
		return r;
	}

	/**
	 * Gets an array of the games in any given round.
	 * 
	 * @param round
	 *            The round number you want the games for.
	 * @return The games in that round.
	 */
	public Game[] getRound(int round) {
		if (round > currentRound)
			return null;
		Game[] r = new Game[rounds[round - 1].size()];
		for (int i = 0; i < rounds[round - 1].size(); ++i) {
			r[i] = rounds[round - 1].get(i);
		}
		return r;
	}

	/**
	 * Gets the round number of the current round.
	 * 
	 * @return The round number of the current round.
	 */
	public int getRoundNumber() {
		return currentRound;
	}

	public boolean hasFinished() {
		return currentRound >= numRounds;
	}

	public int numRounds() {
		return numRounds;
	}

	/**
	 * Converts the tournament into a JSON string and returns it.
	 * 
	 * @return
	 */
	public String toJSON() {
		StringBuilder json = new StringBuilder();
		json.append("{\n");
		json.append("\t\"name\": \"" + tournamentName + "\",\n");
		json.append("\t\"players\": {");
		boolean comma, innercomma;
		comma = false;
		for (Player player : players) {
			if (player == Player.getByePlayer())
				continue;
			if (comma)
				json.append(",");
			json.append("\n\t\t" + player.infoToJSON());
			comma = true;
		}
		json.append("\n\t},");
		json.append("\n\t\"droppedPlayers\": {");
		comma = false;
		for (Player player : droppedPlayers) {
			if (comma)
				json.append(",");
			json.append("\n\t\t");
			json.append(player.infoToJSON());
			comma = true;
		}
		json.append("\n\t},");
		json.append("\n\t\"rounds\": [");
		comma = false;
		try {
			for (ArrayList<Game> round : rounds) {
				if (round == null)
					continue;
				if (comma)
					json.append(",");
				json.append("\n\t\t[");
				innercomma = false;
				for (Game game : round) {
					if (innercomma)
						json.append(",");
					json.append("\n\t\t\t");
					json.append(game.toJSON());
					innercomma = true;
				}
				json.append("\n\t\t]");
				comma = true;
			}
		} catch (Exception e) {
			// Correct way to handle is to simply do nothing
		}
		json.append("\n\t],");
		json.append("\n\t\"results\": [");
		comma = false;
		for (Player player : players) {
			if (player == Player.getByePlayer())
				continue;
			if (comma)
				json.append(",");
			json.append("\n\t\t" + player.resultsToJSON());
			comma = true;
		}
		json.append("\n\t]");
		json.append("\n}");

		return json.toString();
	}
	
	private void makeDirectory(String filename) {
		File directory = new File(filename);
		if (!directory.exists()) {
			if (!directory.mkdir()) {
				System.out.println("Could not create directory.");
				return;
			}
		}
	}

	/**
	 * Saves the tournament to a given filename.
	 * 
	 * @param filename
	 *            The filename to save to.
	 */
	private void saveJSON(String filename) {
		try {
			makeDirectory(filename);
			
			PrintWriter out = new PrintWriter(filename + "/data.json");
			out.print(toJSON());
			out.close();
		} catch (FileNotFoundException e) {
			System.out.println("Could not save tournament file.");
		}
	}

	/**
	 * Saves the tournament to a file based on the tournament's name.
	 */
//	public void saveJSON() {
//		String filename = tournamentName.replaceAll("\\s+", "_")
//				.replaceAll("\\W", "").toLowerCase();
//		saveJSON(filename);
//	}

	private void saveHTML(String filename) {
		try {
			makeDirectory(filename);
			
			String template = new String(Files.readAllBytes(Paths
					.get("template.html")), StandardCharsets.UTF_8);
			
			template = template.replaceAll("\\{\\{json\\}\\}", toJSON());
			template = template.replaceAll("\\{\\{title\\}\\}", tournamentName);

			PrintWriter out = new PrintWriter(filename + "/view.html");
			out.print(template);
			out.close();
		} catch (FileNotFoundException e) {
			System.out.println("Could not save tournament file.");
		} catch (IOException e) {
			System.out.println("Could not read HTML template file.");
		}
	}

	public void save(String filename) {
		sortPlayers();
		saveJSON(filename);
		saveHTML(filename);
	}

	public void save() {
		String filename = tournamentName.replaceAll("\\s+", "_")
				.replaceAll("\\W", "").toLowerCase();
		save(filename);
	}

}
