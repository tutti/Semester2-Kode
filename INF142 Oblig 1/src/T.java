import java.io.*;
import java.lang.reflect.Method;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Pattern;

public class T {
	private static final int VERSION = 1;
	private static final Class[] STRINGCLASS = {String.class};
	
	private static final Pattern uuidPattern = Pattern.compile("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}");
	
	private static int V = 0;
	private static String uuid = UUID.randomUUID().toString();
	private static ArrayList<Change> changes = new ArrayList<Change>();
	
	private static String lastIP;
	
	private static void saveToFile() throws Exception {
		PrintWriter writer = new PrintWriter("database.txt", "UTF-8");
		for (int i = 0; i < changes.size(); ++i) {
			writer.println(changes.get(i).toString());
		}
		writer.close();
	}
	
	private static void loadFile() throws Exception {
	    Scanner file = new Scanner(new File("database.txt"));
	    while(file.hasNextLine()){
	        String line = file.nextLine();
	        Change c = new Change(line);
	        V = c.apply(V);
	        changes.add(c);
	    }
	    file.close();
	}
	
	private static boolean isInt(String num) {
		try {
			Integer.parseInt(num);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	private static boolean isUUID(String uuid) {
		return uuidPattern.matcher(uuid).matches();
	}
	
	private static boolean validateVersion(String message) {
		String[] split = message.split(":");
		try {
			int version = Integer.parseInt(split[1]);
			return version == 1;
		} catch (Exception e) {
			return false;
		}
	}
	
	private static boolean syntaxcheck_VER(String message) {
		// TODO: Don't version check, just validate
		return message.equals("VER:" + VERSION);
	}
	
	private static String do_VER(String message) {
		return response_VER();
	}
	
	private static boolean syntaxcheck_GET(String message) {
		// TODO: Don't version check, just validate
		return message.equals("GET:" + VERSION);
	}
	
	private static String do_GET(String message) {
		return response_VAL();
	}
	
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
	
	private static boolean syntaxcheck_HIS(String message) {
		// TODO Validate.
		return true;
	}
	
	private static String do_HIS(String message) {
		if (changes.size() == 0) {
			return response_NOH();
		}
		return response_HIS();
	}
	
	private static String response_VER() {
		return "VER:" + VERSION;
	}
	
	private static String response_NOP() {
		return "NOP";
	}
	
	private static String response_MAL() {
		return "MAL";
	}
	
	private static String response_IVN() {
		return "IVN";
	}
	
	private static String response_VAL() {
		return "VAL:" + uuid + ":" + V;
	}
	
	private static String response_REJ(String reason) {
		return "REJ:" + reason;
	}
	
	private static String response_NOH() {
		return "NOH";
	}
	
	private static String response_HIS() {
		// TODO
		StringBuilder ret = new StringBuilder();
		ret.append("HIS:");
		for (int i = 0; i < changes.size(); ++i) {
			ret.append("\n");
			ret.append(changes.get(i).toString());
		}
		
		return ret.toString();
	}
	
	private static synchronized String parseMessage(String message) {
		if (!validateVersion(message)) {
			return response_IVN();
		}
		try {
			// Look for validation and execution methods for the message type,
			// and call them.
			Method methodToFind = null;
			methodToFind = T.class.getDeclaredMethod("syntaxcheck_" + message.substring(0, 3), STRINGCLASS);
			boolean valid = (boolean)methodToFind.invoke(null, message);
			if (!valid) {
				return response_MAL();
			}
			methodToFind = T.class.getDeclaredMethod("do_" + message.substring(0, 3), STRINGCLASS);
			String ret = (String)methodToFind.invoke(null, message);
			
			return ret;
			
		} catch (Exception e) {
			return response_NOP();
		}
	}
	
	public static void main(String[] args) throws Exception {
		// TODO: Read value from file
		loadFile();
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
