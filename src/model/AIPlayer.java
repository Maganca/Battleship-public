package model;

import model.Board.ShotResult;

public class AIPlayer {

	// AIStrategy for the current selected strategy
	private AIStrategy currentStrategy;

	public AIPlayer() {
		// This default TicTacToeStrategy can be changed with setStrategy
		currentStrategy = new RandomAI();
	}

	/**
	 * Change the AI for this player.
	 * 
	 * @param strategy Any type that implements AIStrategy
	 */
	public void setStrategy(AIStrategy strategy) {
		currentStrategy = strategy;
	}

	public boolean placeShips(Board theGame) {
		return currentStrategy.placeShips(theGame);
	}

	public ShotResult shoot(Board theGame) {
		return currentStrategy.shoot(theGame);
	}
}
