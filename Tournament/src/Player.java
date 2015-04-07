import java.util.ArrayList;
import java.util.Locale;

public class Player implements Comparable<Player> {

	private int id;
	private String name;
	private ArrayList<Player> opponents;
	private ArrayList<Game> games;
	private boolean dropped = false;
	private boolean tardy = false;
	public int wins;
	public int losses;
	public int ties;

	private static Player byePlayer = null;

	/**
	 * Returns the bye player. The bye player is not the player that gets a bye
	 * in any given round, but rather a hidden player object which represents
	 * the bye itself.
	 * 
	 * @return The bye player.
	 */
	public static Player getByePlayer() {
		if (byePlayer != null)
			return byePlayer;
		byePlayer = new Player(0, "Bye");
		return byePlayer;
	}

	public Player(int id, String name) {
		this.id = id;
		this.name = name;
		opponents = new ArrayList<Player>();
		games = new ArrayList<Game>();
		wins = 0;
		losses = 0;
		ties = 0;
	}

	public int getID() {
		return id;
	}

	public String savedata() {
		return id + " " + name;
	}

	@Override
	public String toString() {
		return savedata() + " (" + wins + "/" + losses + "/" + ties + ")";
	}

	public String longString() {
		String r = toString() + " - Op win %: "
				+ ((float) getOpponentsWinPercentage() / 100)
				+ " - Op Op Win %: "
				+ ((float) getOpponentsOpponentsWinPercentage() / 100);
		if (tardy)
			r += " (tardy)";
		return r;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Player))
			return false;
		return ((Player) o).id == id;
	}

	public int getPoints() {
		return 3 * wins + ties;
	}

	@Override
	public int compareTo(Player other) {
		// Bye player is always at the bottom
		if (this == byePlayer)
			return -1;
		if (other == byePlayer)
			return 1;

		// Main decider - points
		if (getPoints() != other.getPoints())
			return getPoints() - other.getPoints();

		// Tiebreaker 1: Tardiness
		if (tardy && !other.tardy)
			return -1;
		if (!tardy && other.tardy)
			return 1;

		// Tiebreaker 2: Opponents' win percentage
		if (getOpponentsWinPercentage() != other.getOpponentsWinPercentage()) {
			return getOpponentsWinPercentage()
					- other.getOpponentsWinPercentage();
		}

		// Tiebreaker 3: Opponents' opponents' win percentage
		if (getOpponentsOpponentsWinPercentage() != other
				.getOpponentsOpponentsWinPercentage()) {
			return getOpponentsOpponentsWinPercentage()
					- other.getOpponentsOpponentsWinPercentage();
		}

		// Tiebreaker 4: Head to head
		for (Game game : games) {
			if (game.isBetweenPlayers(this, other)) {
				if (this.equals(game.getWinner()))
					return 1;
				return -1;
			}
		}

		// Tiebreaker 5: Standing of last opponent
		// Not implemented. Maybe in the future?
		return 0; // TODO
	}

	public void addGame(Game game, Player opponent) {
		games.add(game);
		opponents.add(opponent);
	}

	public boolean hasPlayed(Player other) {
		return opponents.contains(other);
	}

	public void markDropped() {
		dropped = true;
	}

	public boolean hasDropped() {
		return dropped;
	}

	public void markTardy() {
		tardy = true;
	}

	/**
	 * Returns the player's win percentage. This is represented as an integer
	 * where the last two digits represent the decimal digits of the percentage.
	 * This is to avoid floating point rounding errors.
	 * 
	 * @return
	 */
	public int getWinPercentage() {
		int totalRounds = wins + ties + losses;
		int percentage = (wins * 10000) / totalRounds;
		if (percentage < 2500)
			percentage = 2500;
		if (dropped && percentage > 7500)
			percentage = 7500;
		return percentage;
	}

	public int getOpponentsWinPercentage() {
		if (opponents.size() == 0)
			return 0;
		int sum = 0;
		for (Player opponent : opponents) {
			sum += opponent.getWinPercentage();
		}
		return sum / opponents.size();
	}

	public int getOpponentsOpponentsWinPercentage() {
		if (opponents.size() == 0)
			return 0;
		int sum = 0;
		for (Player opponent : opponents) {
			sum += opponent.getOpponentsWinPercentage();
		}
		return sum / opponents.size();
	}

	public String infoToJSON() {
		return "\"" + id + "\": \"" + name + "\"";
	}

	public String resultsToJSON() {
		return String
				.format(Locale.ENGLISH, "{\"id\": %d, \"wins\": %d, \"losses\": %d, \"ties\": %d, \"owp\": %.2f, \"oowp\": %.2f}",
						id, wins, losses, ties,
						(float) getOpponentsWinPercentage() / 100,
						(float) getOpponentsOpponentsWinPercentage() / 100);
	}

}
