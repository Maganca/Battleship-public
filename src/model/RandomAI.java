package model;

import java.util.Random;

import model.Board.ShotResult;

public class RandomAI {

	private static Random generator;

	public RandomAI() {
		generator = new Random();
	}

	public boolean shoot(Board game) {
		int size = game.getSize();
		boolean run = true;
		while (run) {
			int row = generator.nextInt(size);
			int col = generator.nextInt(size);
			if (game.shotAttempt(row, col)!=ShotResult.REPEAT_ATTEMPT)
				return true;
		}
		return false;
	}
}