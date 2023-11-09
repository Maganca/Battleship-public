package model;

import java.util.Random;

import model.Board.ShotResult;

public class RandomAI {

	private static Random generator;

	public RandomAI() {
		generator = new Random();
	}

	public boolean placeShips(Board game) {
		int[] shipSizes = new int[] { 2, 3, 3, 4, 5 };
		for (int size : shipSizes) {
			boolean placed = false;
			while (!placed) {
				int row = generator.nextInt(game.getSize());
				int col = generator.nextInt(game.getSize());
				int direction = generator.nextInt(1);
				Ship temp = new Ship(new int[] { row, col }, direction, size);
				placed = game.addShipToBoard(temp);
			}
		}
		return false;
	}

	public boolean shoot(Board game) {
		int size = game.getSize();
		boolean run = true;
		while (run) {
			int row = generator.nextInt(size);
			int col = generator.nextInt(size);
			if (game.shotAttempt(row, col) != ShotResult.REPEAT_ATTEMPT)
				return true;
		}
		return false;
	}
}