import java.io.*;
import java.lang.reflect.Method;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Pattern;

public class T {
	private static final int VERSION = 1;
	private static final Class<?>[] STRINGCLASS = {String.class};
	
	private static final Pattern uuidPattern = Pattern.compile("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}");
	private static final Pattern simpleSyntaxPattern = Pattern.compile("^[A-Z]{3}:[0-9]*$");
	
	private static int V = 0;
	private static String uuid = UUID.randomUUID().toString();
	private static ArrayList<Change> changes = new ArrayList<Change>();
	
	private static String lastIP;
	
	/**
	 * Saves the entire change log to a file
	 * @throws Exception If there was a problem with the file
	 */
	private static void saveToFile() throws Exception {
		/*
		 * This overwrites the file instead of appending.
		 * In a real situation this would create a bottleneck.
		 * I decided not to spend inordinate amounts of time on this issue for this
		 * assignment.
		 */
		PrintWriter writer = new PrintWriter("database.txt", "UTF-8");
		for (int i = 0; i < changes.size(); ++i) {
			writer.println(changes.get(i).toString());
		}
		writer.close();
	}
	
	/**
	 * Reads the database file into the Change list
	 * @throws Exception If there was a problem with the file
	 */
	private static void loadFile() throws Exception {
		try {
		    Scanner file = new Scanner(new File("database.txt"));
		    while(file.hasNextLine()){
		        String line = file.nextLine();
		        Change c = new Change(line);
		        V = c.apply(V);
		        changes.add(c);
		    }
		    file.close();
		} catch (FileNotFoundException e) {} // Do nothing if the file does not exist
	}
	
	/**
	 * Tests if a given string is a valid integer
	 * @param num The string to test
	 * @return true if the string is an integer, false if not
	 */
	private static boolean isInt(String num) {
		try {
			Integer.parseInt(num);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Tests if a given string is a valid UUID
	 * @param uuid The string to test
	 * @return true if the string is a valid UUID, false if not
	 */
	private static boolean isUUID(String uuid) {
		return uuidPattern.matcher(uuid).matches();
	}
	
	/**
	 * Tests whether the version number in a message is one the server can
	 * communicate with. In this version, that just means it has to equal 1.
	 * @param message The message that was received
	 * @return true if the server can communicate with the client, false if not
	 */
	private static boolean validateVersion(String message) {
		String[] split = message.split(":");
		try {
			int version = Integer.parseInt(split[1]);
			return version == 1;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Tests whether a VER message has valid syntax
	 * @param message The message to check
	 * @return true if the syntax is valid, false if not
	 */
	@SuppressWarnings("unused")
	private static boolean syntaxcheck_VER(String message) {
		return simpleSyntaxPattern.matcher(message).matches();
	}

	/**
	 * Tests whether a GET message has valid syntax
	 * @param message The message to check
	 * @return true if the syntax is valid, false if not
	 */
	@SuppressWarnings("unused")
	private static boolean syntaxcheck_GET(String message) {
		return simpleSyntaxPattern.matcher(message).matches();
	}

	/**
	 * Tests whether a MOD message has valid syntax
	 * @param message The message to check
	 * @return true if the syntax is valid, false if not
	 */
	@SuppressWarnings("unused")
	private static boolean syntaxcheck_MOD(String message) {
		String[] split = message.split(":");
		// Check that the version number is an integer.
		if (!isInt(split[1])) {
			return false;
		}
		// Check that the UUID is a valid UUID.
		if (!isUUID(split[2])) {
			return false;
		}
		// Check that the change starts with a + or -, and is an integer.
		if (split[3].charAt(0) != '+' && split[3].charAt(0) != '-') {
			return false;
		}
		if (!isInt(split[3].substring(1))) {
			return false;
		}
		return true;
	}

	/**
	 * Tests whether a HIS message has valid syntax
	 * @param message The message to check
	 * @return true if the syntax is valid, false if not
	 */
	@SuppressWarnings("unused")
	private static boolean syntaxcheck_HIS(String message) {
		return simpleSyntaxPattern.matcher(message).matches();
	}
	
	/**
	 * Reacts to a VER message, returning the response
	 * @param message The message that was received
	 * @return The response
	 */
	@SuppressWarnings("unused")
	private static String do_VER(String message) {
		return response_VER();
	}

	/**
	 * Reacts to a GET message, returning the response
	 * @param message The message that was received
	 * @return The response
	 */
	@SuppressWarnings("unused")
	private static String do_GET(String message) {
		return response_VAL();
	}

	/**
	 * Reacts to a MOD message, returning the response
	 * @param message The message that was received
	 * @return The response
	 */
	@SuppressWarnings("unused")
	private static String do_MOD(String message) throws Exception {
		String[] split = message.split(":");
		if (!split[2].equals(uuid)) {
			return response_REJ("outdated");
		}
		int num = Integer.parseInt(split[3].substring(1));
		char op = split[3].charAt(0);
		if (num == 0) {
			return response_REJ("nochange");
		}
		try {
			if (op == '+') {
				V = Math.addExact(V, num);
			} else {
				V = Math.subtractExact(V, num);
			}
			uuid = UUID.randomUUID().toString();
			
			int timestamp = (int)(System.currentTimeMillis() / 1000);
			Change change = new Change(lastIP, timestamp, op, num);
			changes.add(change);
			saveToFile();
			
			return response_VAL();
		} catch (ArithmeticException e) {
			return response_REJ("overflow");
		}
	}

	/**
	 * Reacts to a HIS message, returning the response
	 * @param message The message that was received
	 * @return The response
	 */
	@SuppressWarnings("unused")
	private static String do_HIS(String message) {
		if (changes.size() == 0) {
			return response_NOH();
		}
		return response_HIS();
	}
	
	/**
	 * Generates a VER response
	 * @return A VER response
	 */
	private static String response_VER() {
		return "VER:" + VERSION;
	}

	/**
	 * Generates a NOP response
	 * @return A NOP response
	 */
	private static String response_NOP() {
		return "NOP";
	}

	/**
	 * Generates a MAL response
	 * @return A MAL response
	 */
	private static String response_MAL() {
		return "MAL";
	}

	/**
	 * Generates an IVN response
	 * @return An IVN response
	 */
	private static String response_IVN() {
		return "IVN";
	}

	/**
	 * Generates a VAL response
	 * @return A VAL response
	 */
	private static String response_VAL() {
		return "VAL:" + uuid + ":" + V;
	}
	
	/**
	 * Generates a REJ response with a given reason
	 * @param reason The reason the message was rejected
	 * @return A REJ response
	 */
	private static String response_REJ(String reason) {
		return "REJ:" + reason;
	}

	/**
	 * Generates a NOH response
	 * @return A NOH response
	 */
	private static String response_NOH() {
		return "NOH";
	}

	/**
	 * Generates a HIS response
	 * @return A HIS response
	 */
	private static String response_HIS() {
		StringBuilder ret = new StringBuilder();
		ret.append("HIS:");
		for (int i = 0; i < changes.size(); ++i) {
			ret.append("\n");
			ret.append(changes.get(i).toString());
		}
		
		return ret.toString();
	}
	
	/**
	 * Parses a received message, routing it to the appropriate validation
	 * and execution methods.
	 * @param message The message that was received
	 * @return The string that was returned
	 */
	private static synchronized String parseMessage(String message) {
		// First, do a version check
		if (!validateVersion(message)) {
			return response_IVN();
		}
		try {
			// Check if there is a proper syntax check method for the supplied
			// message type. For example, if the message starts with "MOD",
			// check if there's a syntaxcheck_MOD(String) method on this class.
			Method methodToFind = null;
			methodToFind = T.class.getDeclaredMethod("syntaxcheck_" + message.substring(0, 3), STRINGCLASS);
			boolean valid = (boolean)methodToFind.invoke(null, message);
			// If the syntax wasn't valid, return a MAL (malformed) response.
			if (!valid) {
				return response_MAL();
			}
			// If the message syntax was valid, look for a corresponding do_
			// method (e.g. do_MOD(String)). Call it and get the return value.
			methodToFind = T.class.getDeclaredMethod("do_" + message.substring(0, 3), STRINGCLASS);
			String ret = (String)methodToFind.invoke(null, message);
			
			// Return the response
			return ret;
			
		} catch (Exception e) {
			// This will happen if there was no method with those names,
			// i.e. if either the syntaxcheck_* or do_* method was missing for
			// that message type. Return a NOP response.
			return response_NOP();
		}
	}
	
	public static void main(String[] args) throws Exception {
		// Start by reading the database file.
		loadFile();
		// This socket is never closed, as the code is not meant to ever stop.
		@SuppressWarnings("resource")
		ServerSocket server = new ServerSocket(4343);
		System.out.println("Server running.");
		
		while (true) {
			// Open a connection socket
			Socket connection = server.accept();
			
			System.out.println("Received message.");
			
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			
			lastIP = connection.getInetAddress().getHostName();
			
			System.out.println("Opened streams.");
			
			String message = in.readLine();
			
			System.out.println("Message: " + message);
			
			String response = parseMessage(message);
			
			System.out.println("Response: " + response);
			
			out.writeBytes(response + "\n");
			
			System.out.println("Response sent.");
			
			connection.close();
			
			System.out.println("Connection closed.");
		}
	}
}
