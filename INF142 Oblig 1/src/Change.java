
/**
 * Represents a single value change (addition or subtraction).
 * This object is immutable; once created, its values can not be changed.
 * 
 * @author Pål Vårdal Gjerde
 *
 */
public class Change {
	private String ip;
	private int timestamp;
	private char mode;
	private int value;
	
	/**
	 * Creates a Change object from the information pieces that make it up
	 * @param ip The IP that made the connection
	 * @param timestamp The timestamp (UNIX) of the change
	 * @param mode '+' or '-'
	 * @param value The value of the change
	 */
	public Change(String ip, int timestamp, char mode, int value) {
		this.ip = ip;
		this.timestamp = timestamp;
		this.mode = mode;
		this.value = value;
	}
	
	/**
	 * Parses a saved line (from a file) as a Change
	 * @param saved The line that was read from a file. Same format as toString() outputs.
	 */
	public Change(String saved) {
		String[] split = saved.split(":");
		ip = split[0];
		timestamp = Integer.parseInt(split[1]);
		mode = split[2].charAt(0);
		value = Integer.parseInt(split[2].substring(1));
	}
	
	/**
	 * Applies this Change to a value and returns the result
	 * @param prev The existing value
	 * @return The value after this Change
	 */
	public int apply(int prev) {
		if (mode == '+') {
			return prev + value;
		} else {
			return prev - value;
		}
	}
	
	// Converts the Change to a string that can be saved
	public String toString() {
		return (ip + ":" + timestamp + ":" + mode) + value;
	}
}
