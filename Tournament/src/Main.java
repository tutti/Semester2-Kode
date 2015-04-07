import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	private static Scanner keyboard = new Scanner(System.in);
	private static Tournament tournament;

	private static Player getPlayer(int id) {
		if (PlayerList.hasPlayer(id)) {
			return PlayerList.getPlayer(id);
		}
		System.out.print("Enter player's name: ");
		return PlayerList.createPlayer(id, keyboard.nextLine());
	}

	private static void showPlayers() {
		Player[] players = tournament.getPlayers();
		for (Player player : players) {
			System.out.println(player.longString());
		}
	}

	private static void showRound(int roundnum) {
		Game[] round = tournament.getRound(roundnum);
		if (round == null) {
			System.out.println("Round hasn't started.");
			return;
		}
		System.out.println("Round " + roundnum + " of "
				+ tournament.numRounds());
		for (Game game : round) {
			System.out.println(game);
		}
	}

	public static void showCurrentRound() {
		int roundnum = tournament.getRoundNumber();
		Game[] round = tournament.getCurrentRound();
		if (round == null) {
			System.out.println("Round hasn't started.");
			return;
		}
		System.out.println("Round " + roundnum + " of "
				+ tournament.numRounds());
		for (Game game : round) {
			System.out.println(game);
		}
	}

	public static void main(String[] args) {
		PlayerList.load();

		System.out.print("Tournament name: ");
		String tname = keyboard.nextLine();
		tournament = new Tournament(tname);

		boolean started = false;

		String cmd = "";
		String[] cmd_split;
		while (!cmd.equals("exit")) {
			System.out.print("> ");
			cmd = keyboard.nextLine();
			cmd_split = cmd.split(" ");
			Player p;
			switch (cmd_split[0]) {
			case "add":
				// Add player
				try {
					p = getPlayer(Integer.parseInt(cmd_split[1]));
					tournament.addPlayer(p);
				} catch (Exception e) {
					System.out.println("Please enter a valid player ID.");
				}
				break;
			case "drop":
				// Drop player
				try {
					p = getPlayer(Integer.parseInt(cmd_split[1]));
					tournament.dropPlayer(p);
				} catch (Exception e) {
					System.out.println("Please enter a valid player ID.");
				}
				break;
			case "show":
				// Show players or round
				if (cmd_split.length < 2)
					break;
				switch (cmd_split[1]) {
				case "players":
					showPlayers();
					break;
				case "round":
					try {
						showRound(Integer.parseInt(cmd_split[2]));
					} catch (Exception e) {
						showCurrentRound();
					}
					break;
				default:
					System.out.println("Nothing to show: " + cmd_split[1]);
					break;
				}
				break;
			case "start":
				// Start the round
				if (!started) {
					try {
						tournament.startTournament();
					} catch (Exception e) {
						System.out.println("Can't start tournament: "
								+ e.getMessage());
						break;
					}
					started = true;
				}
				if (tournament.hasFinished()) {
					System.out.println("Tournament has finished.");
					break;
				}
				tournament.startRound();
				showCurrentRound();
				break;
			case "finish":
				// Finish the round
				tournament.finishRound();
				if (tournament.hasFinished()) {
					System.out.println("Final standings:");
				} else {
					System.out.println("Standings after round "
							+ tournament.getRoundNumber() + " of "
							+ tournament.numRounds() + ":");
				}
				showPlayers();
				break;
			case "winner":
				// Mark a player as winner
				try {
					p = getPlayer(Integer.parseInt(cmd_split[1]));
					tournament.winner(p);
				} catch (Exception e) {
					System.out.println("Please enter a valid player ID.");
				}
				break;
			case "tie":
				// Mark a player as having a tie
				try {
					p = getPlayer(Integer.parseInt(cmd_split[1]));
					tournament.tie(p);
				} catch (Exception e) {
					System.out.println("Please enter a valid player ID.");
				}
				break;
			case "save":
				if (cmd_split.length > 1) {
					tournament.save(cmd_split[1]);
				} else {
					tournament.save();
				}
				break;
			case "exit":
				PlayerList.save();
				break;
			case "help":
				try {
					Scanner fileReader = new Scanner(new File("help.txt"));
					while (fileReader.hasNext()) {
						System.out.println(fileReader.nextLine());
					}
					fileReader.close();
				} catch (FileNotFoundException e) {

				}
				break;
			default:
				System.out.println("Unknown command: " + cmd_split[0]);
				break;
			}
		}
	}

}
