public class Game {

	private Player player1;
	private Player player2;
	private Player winner;
	private boolean tie;

	public Game(Player p1, Player p2) {
		player1 = p1;
		player2 = p2;
		winner = null;
		tie = false;
	}

	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}
	
	public Player getOtherPlayer(Player player) {
		if (player1.equals(player))
			return player2;
		if (player2.equals(player))
			return player1;
		return null;
	}

	public boolean hasPlayer(Player p) {
		return p.equals(player1) || p.equals(player2);
	}

	public boolean isBetweenPlayers(Player p1, Player p2) {
		return (p1 == player1 && p2 == player2)
				|| (p1 == player2 && p2 == player1);
	}
	
	public void dropPlayer(Player player) {
		if (winner == null && !tie) {
			setWinner(getOtherPlayer(player));
		}
	}

	public void setWinner(Player winner) {
		tie = false;
		if (winner != player1 && winner != player2) {
			throw new RuntimeException("Winner not in game");
		}
		this.winner = winner;
	}

	public Player getWinner() {
		return winner;
	}

	public void setTie() {
		winner = null;
		tie = true;
	}

	public boolean isTie() {
		return tie;
	}

	public void finish() {
		// If the game hasn't had an outcome marked for it yet,
		// mark it as a tie (unless either player is the bye player).
		if (winner == null) { // If a winner hasn't been set yet
			// If either player is the bye player, the other player wins.
			if (player1 == Player.getByePlayer()) {
				winner = player2;
			} else if (player2 == Player.getByePlayer()) {
				winner = player1;
			// Otherwise, if either player has dropped, the other player wins.
			} else if (player1.hasDropped()) {
				// But if both players dropped, it's a tie.
				if (player2.hasDropped()) {
					setTie();
				} else {
					winner = player2;
				}
			} else if (player2.hasDropped()) {
				winner = player1;
			
			// Otherwise, the game is an unfinished game, and thus a tie.
			} else {
				setTie();
			}
		}
		
		// Mark the players as having played each other
		player1.addGame(this, player2);
		player2.addGame(this, player1);
		
		// Add to the players' scores
		if (tie) {
			++player1.ties;
			++player2.ties;
		} else {
			if (player1 == winner) {
				++player1.wins;
				++player2.losses;
			} else {
				++player1.losses;
				++player2.wins;
			}
		}
	}

	@Override
	public String toString() {
		if (player1 == Player.getByePlayer()) {
			return player2 + " - Bye";
		}
		if (player2 == Player.getByePlayer()) {
			return player1 + " - Bye";
		}
		String s = player1 + " vs " + player2;
		if (tie)
			s += " - Tied";
		else if (winner == null)
			s += " - Still playing...";
		else
			s += " - Winner: " + winner;
		return s;
	}
	
	public String toJSON() {
		StringBuilder sb = new StringBuilder();
		sb.append("{\"p1\": ");
		sb.append(player1.getID());
		sb.append(", \"p2\": ");
		sb.append(player2.getID());
		if (winner != null) {
			sb.append(", \"winner\": ");
			sb.append(winner.getID());
		} else {
			sb.append(", \"tie\": ");
			sb.append(tie);
		}
		sb.append("}");
		
		return sb.toString();
	}

}
