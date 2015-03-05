package code;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class TwoDMap<T> implements Iterable<T> {
	private HashMap<Integer, HashMap<Integer, T>> objects = new HashMap<Integer, HashMap<Integer, T>>();
	
	/**
	 * Gets the object at (x, y).
	 * @param x
	 * @param y
	 * @return The object
	 */
	public T get(int x, int y) {
		HashMap<Integer, T> row = objects.get(y);
		if (row == null) return null;
		return row.get(x);
	}
	
	/**
	 * Puts an object at (x, y).
	 * @param x
	 * @param y
	 * @param object The object to put
	 */
	public void put(int x, int y, T object) {
		HashMap<Integer, T> row = objects.get(y);
		if (row == null) {
			row = new HashMap<Integer, T>();
			objects.put(y, row);
		}
		row.put(x, object);
	}
	
	/**
	 * Removes an object from (x, y).
	 * @param x
	 * @param y
	 */
	public void remove(int x, int y) {
		HashMap<Integer, T> row = objects.get(y);
		if (row == null) return;
		row.remove(x);
	}
	
	public ArrayList<T> values() {
		ArrayList<T> all = new ArrayList<T>();
		for (HashMap<Integer, T> row : objects.values()) {
			for (T object : row.values()) {
				all.add(object);
			}
		}
		return all;
	}

	@Override
	public Iterator<T> iterator() {
		return values().iterator();
	}
}
