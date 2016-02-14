
public class Change {
	private String ip;
	private int timestamp;
	private char mode;
	private int value;
	
	public Change(String ip, int timestamp, char mode, int value) {
		this.ip = ip;
		this.timestamp = timestamp;
		this.mode = mode;
		this.value = value;
	}
	
	public Change(String saved) {
		String[] split = saved.split(":");
		ip = split[0];
		timestamp = Integer.parseInt(split[1]);
		mode = split[2].charAt(0);
		value = Integer.parseInt(split[2].substring(1));
	}
	
	public int apply(int prev) {
		if (mode == '+') {
			return prev + value;
		} else {
			return prev - value;
		}
	}
	
	public String toString() {
		return (ip + ":" + timestamp + ":" + mode) + value;
	}
}
