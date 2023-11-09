package model;

import java.util.Random;

import model.Board.ShotResult;

public class HardAI {

	private static Random generator;

	public HardAI() {
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

	/**
	 * Given the opponents board, it takes a shot using a smarter strategy
	 * 
	 * @param game
	 * @return
	 */
	public boolean shoot(Board game) {
		int size = game.getSize();
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				if (game.getHiddenCoord(x, y).equals(ShotResult.HIT)) {
					if (game.getHiddenCoord(x + 1, y) == null) {
						game.shotAttempt(x + 1, y);
						return true;
					}
					if (game.getHiddenCoord(x - 1, y) == null) {
						game.shotAttempt(x - 1, y);
						return true;
					}
					if (game.getHiddenCoord(x, y + 1) == null) {
						game.shotAttempt(x, y + 1);
						return true;
					}
					if (game.getHiddenCoord(x, y - 1) == null) {
						game.shotAttempt(x, y - 1);
						return true;
					}
				}
			}
		}

		// Makes a random shot attempt because it has no idea where to shoot
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