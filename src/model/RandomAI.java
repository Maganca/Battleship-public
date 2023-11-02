package model;

import java.util.Random;

public class RandomAI {

	private static Random generator;

	public RandomAI() {
		generator = new Random();
	}

	@Override
	public int[] shot(Board game) {
		int size = game.getSize();
		while (!game.isFull()) {
			int row = generator.nextInt(size);
			int col = generator.nextInt(size);
			if (game.available(row, col)) {
				return new int[] { row, col };
			}
		}
	}
}