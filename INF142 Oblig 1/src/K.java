import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.Scanner;

public class K {
	
	private static final int VERSION = 1;
	
	private static int V;
	private static String uuid;
	
	/**
	 * Sends a message to the server and returns the response
	 * @param message The message to send
	 * @return The response that was received
	 * @throws Exception If there's a problem with creating or using the Socket.
	 */
	private static String comm(String message) throws Exception {
		// Open the client side socket and get the streams
		Socket client = new Socket("127.0.0.1", 4343);
		DataOutputStream send = new DataOutputStream(client.getOutputStream());
		BufferedReader receive = new BufferedReader(new InputStreamReader(client.getInputStream()));
		
		// Write the message to the socket
		send.writeBytes(message + "\n");
		
		// Read each line that gets returned
		StringBuilder ret = new StringBuilder();
		String back = receive.readLine();
		while (back != null) {
			ret.append("\n");
			ret.append(back);
			back = receive.readLine();
		}
		client.close();
		
		// If there were any lines, the first character is a redundant \n
		if (ret.length() > 0) {
			ret.deleteCharAt(0);
		}
		return ret.toString();
	}
	
	/**
	 * Parses a VAL response and updates the values
	 * @param message The message that was received
	 */
	private static void parseVal(String message) {
		V = Integer.parseInt(message.split(":")[2]);
		uuid = message.split(":")[1];
	}
	
	/**
	 * Parses a HIS response into a string
	 * @param message The message that was received
	 * @return A printable history string
	 */
	private static String parseHistory(String message) {
		// Split the string into lines
		String[] lines = message.split("\n");
		StringBuilder ret = new StringBuilder();
		for (int i = 1; i < lines.length; ++i) { // Skip the first line
			// Split the line at : characters
			String[] split = lines[i].split(":");
			
			// Write a human-readable version of the change line
			ret.append("\n");
			ret.append(split[0]);
			if (split[2].charAt(0) == '+') {
				ret.append(" added ");
			} else {
				ret.append(" subtracted ");
			}
			ret.append(split[2].substring(1));
			ret.append(" on ");
			ret.append(new Date( Long.parseLong(split[1]) * 1000 ).toString());
		}
		
		// Delete the first line change and return the string
		ret.deleteCharAt(0);
		return ret.toString();
	}
	
	/**
	 * Sends a MOD message to add a value
	 * @param add The value to add
	 * @throws Exception If there was a communication problem
	 */
	private static void add(int add) throws Exception {
		String ret = comm("MOD:" + VERSION + ":" + uuid + ":+" + add);
		parseVal(ret);
	}

	/**
	 * Sends a MOD message to subtract a value
	 * @param add The value to subtract
	 * @throws Exception If there was a communication problem
	 */
	private static void subtract(int subtract) throws Exception {
		String ret = comm("MOD:" + VERSION + ":" + uuid + ":-" + subtract);
		parseVal(ret);
	}
	
	/**
	 * Sends a GET message to update the value to the newest version
	 * @throws Exception If there was a communication problem
	 */
	private static void getVal() throws Exception {
		String ret = comm("GET:" + VERSION);
		parseVal(ret);
	}
	
	/**
	 * Sends a HIS message to get the update history
	 * @throws Exception If there was a communication problem
	 */
	private static void getHistory() throws Exception {
		String ret = comm("HIS:" + VERSION);
		System.out.println();
		System.out.println("History:");
		System.out.println(parseHistory(ret));
		System.out.println();
	}
	
	/**
	 * Prints the client menu
	 */
	private static void printMenu() {
		System.out.println("Value: " + V + " (" + uuid + ")");
		System.out.println("1. Add");
		System.out.println("2. Subtract");
		System.out.println("3. Refresh");
		System.out.println("4. History");
		System.out.println("5. Quit");
	}
	
	public static void main(String[] args) throws Exception {
		// Scanner for reading the user's input
		Scanner in = new Scanner(System.in);
		
		// "Connect" (get the initial value)
		System.out.println("Connecting...");
		getVal();
		System.out.println("Connected.");
		System.out.println();
		
		int choice = 0;
		int input;
		
		// Print the menu and follow the user's choice
		while (choice != 5) {
			switch (choice) {
				case 1:
					System.out.println("Add how much?");
					input = in.nextInt();
					add(input);
					break;
				case 2:
					System.out.println("Subtract how much?");
					input = in.nextInt();
					subtract(input);
					break;
				case 3:
					System.out.println("Refreshing...");
					getVal();
					break;
				case 4:
					getHistory();
					break;
			}
			
			printMenu();
			choice = in.nextInt();
		}
		
		in.close();
	}
}
