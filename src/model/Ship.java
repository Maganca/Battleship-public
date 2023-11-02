package model;

public class Ship {
	private int health;

	public Ship(int size) {
		health = size;
	}

	public int getHealth() {
		return health;
	}

	public void damage() {
		health -= 1;
	}
}
