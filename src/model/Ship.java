//Danilo Malo-Molina

package model;

import java.util.ArrayList;

public class Ship {
	private int health;
	public ArrayList<int[]> locations;
	private int startingSize;

	public Ship(int size) {
		health = size;
		startingSize = size;
		locations = new ArrayList<int[]>();
	}

	public Ship(int[] start, int direction, int size) {
		locations = new ArrayList<int[]>();
		if (direction == 0) {
			for (int i = 0; i < size; i++) {
				locations.add(new int[] { start[0] + i, start[1] });
			}
		} else {
			for (int i = 0; i < size; i++) {
				locations.add(new int[] { start[0], start[1] + i });
			}
		}
		health = locations.size();
	}

	public int getHealth() {
		return health;
	}

	public void damage() {
		health -= 1;
	}
	
	public int getSize() {
		return startingSize;
	}
}