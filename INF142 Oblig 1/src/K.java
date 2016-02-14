import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.Scanner;

public class K {
	
	private static final int VERSION = 1;
	
	private static int V;
	private static String uuid;
	
	private static String comm(String message) throws Exception {
		Socket client = new Socket("127.0.0.1", 4343);
		DataOutputStream send = new DataOutputStream(client.getOutputStream());
		BufferedReader receive = new BufferedReader(new InputStreamReader(client.getInputStream()));
		
		send.writeBytes(message + "\n");
		StringBuilder ret = new StringBuilder();
		String back = receive.readLine();
		while (back != null) {
			ret.append("\n");
			ret.append(back);
			back = receive.readLine();
		}
		client.close();
		if (ret.length() > 0) {
			ret.deleteCharAt(0);
		}
		return ret.toString();
	}
	
	private static void parseVal(String message) {
		V = Integer.parseInt(message.split(":")[2]);
		uuid = message.split(":")[1];
	}
	
	private static String parseHistory(String message) {
		String[] lines = message.split("\n");
		StringBuilder ret = new StringBuilder();
		for (int i = 1; i < lines.length; ++i) { // Skip the first line
			String[] split = lines[i].split(":");
			ret.append("\n");
			ret.append(split[0]);
			if (split[2].charAt(0) == '+') {
				ret.append(" added ");
			} else {
				ret.append(" subtracted ");
			}
			ret.append(split[2].substring(1));
			ret.append(" on ");
			ret.append(new Date( Integer.parseInt(split[1]) * 1000 ).toString());
		}
		
		ret.deleteCharAt(0);
		return ret.toString();
	}
	
	private static void add(int add) throws Exception {
		String ret = comm("MOD:" + VERSION + ":" + uuid + ":+" + add);
		parseVal(ret);
	}
	
	private static void subtract(int subtract) throws Exception {
		String ret = comm("MOD:" + VERSION + ":" + uuid + ":-" + subtract);
		parseVal(ret);
	}
	
	private static void getVal() throws Exception {
		String ret = comm("GET:" + VERSION);
		parseVal(ret);
	}
	
	private static void getHistory() throws Exception {
		String ret = comm("HIS:" + VERSION);
		System.out.println();
		System.out.println("History:");
		System.out.println(parseHistory(ret));
		System.out.println();
	}
	
	private static void printMenu() {
		System.out.println("Value: " + V + " (" + uuid + ")");
		System.out.println("1. Add");
		System.out.println("2. Subtract");
		System.out.println("3. Refresh");
		System.out.println("4. History");
		System.out.println("5. Quit");
	}
	
	public static void main(String[] args) throws Exception {
//		String back = comm("GET:1");
//		System.out.println(back);
//		String uuid = back.split(":")[1];
//		for (int i = 1; i <= 10; ++i) {
//			String write = "MOD:1:" + uuid + ":+" + i;
//			back = comm(write);
//			uuid = back.split(":")[1];
//			System.out.println(back);
//		}
//		back = comm("HIS:1");
//		System.out.println(back);
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("Connecting...");
		getVal();
		System.out.println("Connected.");
		System.out.println();
		
		int choice = 0;
		int input;
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
